package com.testigos.gesoc.views.controllers;

import java.time.LocalDate;
import java.util.List;

import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import com.testigos.gesoc.model.domain.usuarios.Mensaje;
import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.model.services.IngresoService;
import com.testigos.gesoc.model.services.MensajeService;
import com.testigos.gesoc.model.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ingresos")
public class IngresosController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private IngresoService ingresoService;

    @GetMapping
    public String ingresos(Model model, Authentication auth) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        List<Ingreso> ingresos = ingresoService.getIngresos();
        // user.getEntidad().getIngresos();
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("ingresos", ingresos);
        return "ingresos";
    }

    @GetMapping(path = "/add")
    public String ingresosAdd(Model model, Authentication auth) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("new_ingreso", new Ingreso());
        return "ingresos_add";
    }

    @PostMapping(path = "/add")
    public String ingresosAddResult(Model model, Authentication auth, @ModelAttribute Ingreso ingreso) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        ingreso.setEntidad(user.getEntidad());
        ingreso.setFechaIngreso(LocalDate.now());
        ingresoService.persist(ingreso);
        List<Ingreso> ingresos = ingresoService.getIngresos();
        ingresos.add(ingreso);
        // user.getEntidad().getIngresos();
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("ingresos", ingresos);
        return "ingresos_add_result";
    }
}