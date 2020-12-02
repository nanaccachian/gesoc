package com.testigos.gesoc.views.controllers;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.ingresos.Empatadora.Empatadora;
import com.testigos.gesoc.model.domain.ingresos.Empatadora.PrimeroEgreso;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import com.testigos.gesoc.model.domain.usuarios.Mensaje;
import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.model.services.EgresoService;
import com.testigos.gesoc.model.services.IngresoService;
import com.testigos.gesoc.model.services.MensajeService;
import com.testigos.gesoc.model.services.UsuarioService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/justificacion*")
public class JustificacionController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private EgresoService egresoService;

    @GetMapping
    public String proyectos(Model model, Authentication auth) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        List<Ingreso> ingresos = ingresoService.getIngresosDisponibles();
        List<Egreso> egresos = egresoService.getEgresosNoJustificados();
        new Empatadora(new PrimeroEgreso()).empatar(ingresos, egresos);
        egresoService.update(egresos);
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("ingresos", ingresos);
        model.addAttribute("egresos", egresos);
        return "justificacion";
    }
}
