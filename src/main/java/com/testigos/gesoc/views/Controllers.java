package com.testigos.gesoc.views;

import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.model.services.MensajeService;
import com.testigos.gesoc.model.services.UsuarioService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controllers {

    private UsuarioService usuarioService = new UsuarioService();
    private MensajeService mensajeService = new MensajeService();

    @GetMapping(path = { "/index", "/index*" })
    public String index(Model model, Authentication auth) {
        Usuario user = usuarioService.find(auth.getName());
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajeService.getMensajes(user));
        return "index";
    }

    @GetMapping(path = { "/login", "/login*" })
    public String loginView() {
        return "login";
    }
}