package com.testigos.gesoc.views.controllers;

import java.time.LocalDate;
import java.util.List;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.egresos.Item;
import com.testigos.gesoc.model.domain.usuarios.Mensaje;
import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.model.services.EgresoService;
import com.testigos.gesoc.model.services.ItemService;
import com.testigos.gesoc.model.services.MensajeService;
import com.testigos.gesoc.model.services.ProveedorService;
import com.testigos.gesoc.model.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private ItemService itemService;

    private Egreso egresoActual;

    @GetMapping
    public String egresos(Model model, Authentication auth) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        List<Egreso> egresos = egresoService.findAllConProveedor();
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
        model.addAttribute("proveedores", proveedorService.findAll());
        return "egresos_add";
    }

    @PostMapping(path = "/add/{egreso_id}")
    public String addItems(Model model, Authentication auth, @PathVariable("egreso_id") String id,
            @ModelAttribute Egreso egreso, @RequestParam("proveedor_elegido") String prov) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        egreso.setVendedor(proveedorService.find(Integer.parseInt(prov)));
        egreso.setFechaOperacion(LocalDate.now());
        egresoService.persist(egreso);
        egresoActual = egreso;
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("egreso", egreso);
        model.addAttribute("new_item", new Item());
        return "item_add";
    }

    @PostMapping(path = "/item/add")
    public String addSingleItem(Model model, Authentication auth, @ModelAttribute("new_item") Item item,
            @RequestParam(value = "action") String action) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        itemService.persist(item, egresoActual);
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        if (action.equals("continue")) {
            model.addAttribute("new_item", new Item());
            return "item_add";
        } else
            return "egresos_add_result";
    }
}
