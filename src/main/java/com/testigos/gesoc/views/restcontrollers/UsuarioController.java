package com.testigos.gesoc.views.restcontrollers;

import java.util.List;

import com.testigos.gesoc.model.domain.entidades.JuridicaIGJ;
import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.model.services.UsuarioService;
import com.testigos.gesoc.model.services.passwordValidator.ValidadorContrasenia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping(path = "/rest/usuarios/all")
    public List<Usuario> usuariosAll() {
        return service.findAll();
    }

    @GetMapping(path = "/rest/usuarios/{username}")
    public Usuario usuarios(@PathVariable String username) {
        Usuario usuario = service.find(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("No existe el usuario.");
        }

        return usuario;
    }

    @PostMapping(path = "/rest/usuarios/add")
    public void usuariosAdd(@RequestParam String username, @RequestParam String password, @RequestParam String permisos,
            @RequestParam String name, @RequestParam String surname) {

        if (!ValidadorContrasenia.validarContrasenia(password))
            throw new RuntimeException("Contraseña invalida");

        if (service.find(username) != null)
            throw new RuntimeException("Nombre de usuario no disponible");

        Usuario usuario = new Usuario(username, password, permisos, new JuridicaIGJ(), surname, name);
        service.persist(usuario);
    }
}