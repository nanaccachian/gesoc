package com.testigos.gesoc.views;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.model.services.UsuarioService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioService usuarioService = new UsuarioService();

    @RequestMapping(method = GET, path = "/usuarios/all")
    public List<Usuario> usuarios() {
        return usuarioService.findAll();
    }

    @RequestMapping(method = GET, path = "/usuarios/{username}")
    public Usuario usuarios(@PathVariable String username) {
        Usuario usuario = usuarioService.find(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("No existe el usuario.");
        }

        return usuario;
    }

    @RequestMapping(method = POST, path = "/usuarios/add")
    public Usuario crearUsuario(@RequestParam String username, @RequestParam String password, @RequestParam String rol) {
        Usuario usuario = new Usuario(username, password, rol);
        usuarioService.persist(usuario);
        return usuario;
    }
}