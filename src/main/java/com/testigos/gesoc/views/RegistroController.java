package com.testigos.gesoc.views;

import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.model.services.MensajeService;
import com.testigos.gesoc.model.services.RegistroService;
import com.testigos.gesoc.model.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class RegistroController {

    @Autowired
    private RegistroService service;

    private final UsuarioService usuarioService = new UsuarioService();
    private final MensajeService mensajeService = new MensajeService();

    @RequestMapping(method = GET, path = {"/registers", "/registers*"})
    public String registersView(Model model, Authentication auth) {
        model.addAttribute("registros", service.findAll());
        Usuario user = usuarioService.find(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajeService.getMensajes(user));
        return "registers";
    }
}
