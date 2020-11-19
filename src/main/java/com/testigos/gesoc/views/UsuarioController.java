package com.testigos.gesoc.views;

import java.util.List;

import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.model.services.UsuarioService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService = new UsuarioService();

    @GetMapping(path = "/all")
    public List<Usuario> usuarios() {
        return usuarioService.findAll();
    }

    @GetMapping(path = "/{username}")
    public Usuario usuarios(@PathVariable String username) {
        Usuario usuario = usuarioService.find(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("No existe el usuario.");
        }

        return usuario;
    }

    @PostMapping(path = "/add")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        usuarioService.persist(usuario);
        return usuario;
    }
}