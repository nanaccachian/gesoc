package com.testigos.gesoc.views.controllers;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.ingresos.Empatadora.Empatadora;
import com.testigos.gesoc.model.domain.ingresos.Empatadora.EstrategiaEmpatadora;
import com.testigos.gesoc.model.domain.ingresos.Empatadora.PrimeroEgreso;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

//TODO NO FUNCIONA
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

    @GetMapping(path = "/justificacion")
    public String justificar(Model model, Authentication auth) {
        Usuario user = usuarioService.findConEntidad(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        Empatadora empatadora = Empatadora.getInstance();
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("estrategias", empatadora.getEstrategias().stream().map(e -> e.getClass().getSimpleName()).collect(Collectors.toList()));
        return "elegir_criterio";
    }

//  TODO SACAR LA CONFIGURACION DE LA EMPATADORA DE ACA
    @PostMapping(path = "/justificacion")
    public String justificar(Model model, Authentication auth, @RequestParam("criterio") String criterio ) {
        Usuario user = usuarioService.findConEntidad(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        List<Ingreso> ingresos = ingresoService.getIngresosDisponibles(user.getEntidad());
        List<Egreso> egresos = egresoService.getEgresosNoJustificados(user.getEntidad());

        Empatadora empatadora = Empatadora.getInstance();
        empatadora.setEstrategiaElegida(empatadora.getEstrategia(criterio));
        List<Egreso> egresosActualizados = empatadora.empatar(ingresos, egresos);

        egresoService.update(egresosActualizados);
        ingresoService.update(ingresos);

        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("ingresos", ingresos);
        model.addAttribute("egresos", egresosActualizados);
        return "justificacion";
    }
}
