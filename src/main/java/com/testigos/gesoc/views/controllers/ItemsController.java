package com.testigos.gesoc.views.controllers;

import java.util.List;

import com.testigos.gesoc.model.domain.egresos.Categoria;
import com.testigos.gesoc.model.domain.egresos.CriterioDeCategorizacion;
import com.testigos.gesoc.model.domain.egresos.Item;
import com.testigos.gesoc.model.domain.usuarios.Mensaje;
import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.model.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public CriterioDeCategorizacionService criteriosDeCategorizacionService;

    @Autowired
    public CategoriaService categoriaService;

    @GetMapping(path = "/{egreso_id}")
    public String getItems(Model model, Authentication auth, @PathVariable("egreso_id") int egreso) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        List<Item> items = itemService.findItems(egresoService.findEgreso(egreso));
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("items", items);
        model.addAttribute("egreso",egreso);
        return "items";
    }

    @GetMapping(path = "/{egreso_id}/categorizar")
    public String elegir_categoria(Model model, Authentication auth, @PathVariable("egreso_id") int egreso) {
        Usuario user = usuarioService.findConEntidadYManejador(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);

        List<Item> items = itemService.findItems(egresoService.findEgreso(egreso));
        List<CriterioDeCategorizacion> criteriosDeCategorizaciones = user.getEntidad().getManejadorDeCategorias().getCategorizacionesAplicables();

        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("items", items);
        model.addAttribute("egreso", egreso);
        model.addAttribute("criterios", criteriosDeCategorizaciones);
        return "categorizar_eleccion";
    }

    @PostMapping(path = "/{egreso_id}/cat_item")
    public String categorizar(Model model, Authentication auth, @PathVariable("egreso_id") int egreso,
                              @RequestParam("criterio_elegido") int criterio_elegido,
                              @RequestParam("item_elegido") int item_elegido) {

        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);

        Item item = itemService.find(item_elegido);
        CriterioDeCategorizacion criterioDeCategorizacion = criteriosDeCategorizacionService.findConCategorias(criterio_elegido);

        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("item", item);
        model.addAttribute("egreso", egreso);
        model.addAttribute("categorias", criterioDeCategorizacion.getCategorias());
        return "categorizar_item";
    }

    @PostMapping(path = "/{egreso_id}/item/{item_id}/categorizar")
    public String categorizar_result(Model model, Authentication auth, @PathVariable("egreso_id") int egreso,
                                     @PathVariable("item_id") int item_id, @RequestParam("categoria_elegida") int categoria_elegida) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);

        Item item = itemService.findConCategorias(item_id);
        Categoria categoria = categoriaService.findConCriterio(categoria_elegida);

        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        if (item.categorizar(categoria)) {
            itemService.update(item);
            return "categorizacion_success";
        }
        else return "categorizacion_failure";

    }
}
