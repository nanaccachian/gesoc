package com.testigos.gesoc.views.controllers;

import java.util.List;

import com.testigos.gesoc.model.domain.financiamiento.ProyectoDeFinanciamiento;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import com.testigos.gesoc.model.domain.usuarios.Mensaje;
import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.model.services.IngresoService;
import com.testigos.gesoc.model.services.MensajeService;
import com.testigos.gesoc.model.services.ProyectoDeFinanciamientoService;
import com.testigos.gesoc.model.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/financiamiento*")
public class FinanciamientoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private ProyectoDeFinanciamientoService proyectosService;

    @Autowired
    private IngresoService ingresosService;

    @GetMapping
    public String financiamiento(Model model, Authentication auth) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        List<ProyectoDeFinanciamiento> proyectos = proyectosService.findAllConUsuario();
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("proyectos", proyectos);
        return "financiamiento";
    }

    @GetMapping(path = "/add")
    public String addProyectoAttributes(Model model, Authentication auth) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        List<Usuario> usuarios = usuarioService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("new_proyecto", new ProyectoDeFinanciamiento());
        model.addAttribute("usuario_proyecto", user.getUsername());
        return "proyecto_add";
    }

    @PostMapping(path = "/add")
    public String addProyecto(Model model, Authentication auth, @ModelAttribute ProyectoDeFinanciamiento proyecto, @RequestParam("usuario_proyecto") String usuario) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        Usuario u = usuarioService.findConEntidad(usuario);
        proyecto.setDirector(u);
        proyectosService.persist(proyecto);
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        return "proyecto_add_result";
    }

    @GetMapping(path = "/vinculacion")
    public String vinculacionAttributes(Model model, Authentication auth) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        List<ProyectoDeFinanciamiento> proyectos = proyectosService.findAll();
        List<Ingreso> ingresos = ingresosService.getIngresosSinProyecto();
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("proyectos", proyectos);
        model.addAttribute("ingresos", ingresos);
        return "vincular";
    }

    @PostMapping(path = "/vinculacion")
    public String vincular(Model model, Authentication auth, @RequestParam("ingreso_elegido") String ingreso_id, @RequestParam("proyecto_elegido") String proyecto_id) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        ProyectoDeFinanciamiento proyecto = proyectosService.find(Integer.parseInt(proyecto_id));
        Ingreso ingreso = ingresosService.find(Integer.parseInt(ingreso_id));
        if (proyecto.sePuedeAgregar(ingreso)) { //TODO PASAR AL SERVICE
            ingreso.setProyectoAsociado(proyecto);
            ingresosService.updateDoc(ingreso);
            model.addAttribute("user", user);
            model.addAttribute("mensajes", mensajes);
            return "vinculacion_result";
        } else {
            model.addAttribute("user", user);
            model.addAttribute("mensajes", mensajes);
            return "vinculacion_failure";
        }
    }

    @GetMapping(path = "/{proyecto_id}/ingresos")
    public String ingresosDeProyecto(Model model, Authentication auth, @PathVariable("proyecto_id") String proyecto_id) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        List<Ingreso> ingresos = proyectosService.find(Integer.parseInt(proyecto_id)).getIngresosAsociados();
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("ingresos", ingresos);
        return "ingresos_de_proyecto";
    }
}
