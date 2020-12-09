package com.testigos.gesoc.views.controllers;

import java.util.List;

import com.testigos.gesoc.model.domain.egresos.Item;
import com.testigos.gesoc.model.domain.usuarios.Mensaje;
import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.model.services.EgresoService;
import com.testigos.gesoc.model.services.ItemService;
import com.testigos.gesoc.model.services.MensajeService;
import com.testigos.gesoc.model.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items*")
public class ItemsController {

    @Autowired
    public ItemService itemService;

    @Autowired
    public UsuarioService usuarioService;

    @Autowired
    public MensajeService mensajeService;

    @Autowired
    public EgresoService egresoService;

    @GetMapping(path = "/{egreso_id}")
    public String getItems(Model model, Authentication auth, @PathVariable("egreso_id") int egreso) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        List<Item> items = itemService.findItems(egresoService.find(egreso));
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("items", items);
        model.addAttribute("egreso",egreso);
        return "items";
    }
}
