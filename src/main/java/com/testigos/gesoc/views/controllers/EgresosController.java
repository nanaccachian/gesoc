package com.testigos.gesoc.views.controllers;

import java.util.ArrayList;
import java.util.List;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.egresos.Item;
import com.testigos.gesoc.model.domain.usuarios.Mensaje;
import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.model.services.EgresoService;
import com.testigos.gesoc.model.services.MensajeService;
import com.testigos.gesoc.model.services.ProveedorService;
import com.testigos.gesoc.model.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/egresos*")
public class EgresosController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private EgresoService egresoService;

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public String egresos(Model model, Authentication auth) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        List<Egreso> egresos = egresoService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("egresos", egresos);
        return "egresos";
    }

    @GetMapping(path = "/add")
    public String egresosAdd(Model model, Authentication auth) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("new_egreso", new Egreso());
        model.addAttribute("cant_items", null);
        model.addAttribute("items", new ArrayList<Item>());
        model.addAttribute("proveedores", proveedorService.findAll());
        return "egresos_add";
    }

    @PostMapping(path = "/add")
    public String egresosAddResult(Model model, Authentication auth, @ModelAttribute Egreso egreso) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        egresoService.persist(egreso);
        List<Egreso> egresos = egresoService.findAll();
        // user.getEntidad().getIngresos();
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("egresos", egresos);
        ArrayList<Item> items = (ArrayList<Item>) model.getAttribute("items");
        return "egresos_add_result";
    }
}
