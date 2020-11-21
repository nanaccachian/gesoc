package com.testigos.gesoc.views;

import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.model.services.UsuarioService;
import com.testigos.gesoc.model.services.passwordValidator.ValidadorContrasenia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping(path = "/all")
    public String usuariosAll(Model model) {
        model.addAttribute("users", service.findAll());
        return "api_usuarios_all";
    }

    @GetMapping()
    public String usuarios(Model model) {
        model.addAttribute("new_username", new Usuario());
        return "api_usuarios";
    }

    @PostMapping()
    public String usuarios(@ModelAttribute("user") Usuario usuario, Model model) {
        model.addAttribute("user", service.find(usuario.getUsername()));
        return "api_usuarios_result";
    }

    @GetMapping(path = "/{username}")
    public Usuario usuarios(@PathVariable String username) {
        Usuario usuario = service.find(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("No existe el usuario.");
        }

        return usuario; //arreglar
    }

    @GetMapping(path = "/add")
    public String usuariosForm(Model model) {
        model.addAttribute("new_user", new Usuario());

        return "api_usuarios_add";
    }

    @PostMapping(path = "/add")
    public String formResult(@ModelAttribute("user") Usuario usuario, Model model) {
        model.addAttribute("user", usuario.toString());

        if (!ValidadorContrasenia.validarContrasenia(usuario.getPassword()))
            model.addAttribute("error", "CONTRASEÑA INVÁLIDA");
        else if (service.find(usuario.getUsername()) != null)
            model.addAttribute("error", "NOMBRE DE USUARIO NO DISPONIBLE");
        else
            service.persist(usuario);

        return "api_usuarios_add_result";
    }
}