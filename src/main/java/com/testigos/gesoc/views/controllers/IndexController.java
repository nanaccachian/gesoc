package com.testigos.gesoc.views.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import com.testigos.gesoc.model.domain.usuarios.Mensaje;
import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.model.services.EgresoService;
import com.testigos.gesoc.model.services.IngresoService;
import com.testigos.gesoc.model.services.MensajeService;
import com.testigos.gesoc.model.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index*")
public class IndexController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private EgresoService egresoService;

    @Autowired
    private IngresoService ingresoService;

    @GetMapping
    public String index(Model model, Authentication auth) {
        Usuario user = usuarioService.findConEntidad(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        Double balance = ingresoService.getTotalIngresos(user.getEntidad()) - egresoService.montoActual(user.getEntidad());

        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("hora", LocalTime.now().format(DateTimeFormatter.ofPattern("h:mm a")));
        model.addAttribute("dia", LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        model.addAttribute("balance", balance);
        model.addAttribute("egresos_mes", egresoService.montoMes(user.getEntidad()));
        model.addAttribute("ingresos_mes", ingresoService.getTotalIngresosEsteMes(user.getEntidad()));
        model.addAttribute("egresos_anio", egresoService.montoAnio(user.getEntidad()));
        model.addAttribute("ultimo_egreso", egresoService.findUltimoEgreso(user.getEntidad()));
        model.addAttribute("ultimo_ingreso", ingresoService.findUltimoIngreso(user.getEntidad()));
        return "index";
    }
}
