package com.testigos.gesoc.views.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.testigos.gesoc.model.domain.egresos.*;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import com.testigos.gesoc.model.domain.usuarios.Mensaje;
import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.model.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/egresos*")
public class EgresosController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private EgresoService egresoService;

    @Autowired
    private EgresoCPService egresoCPService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private DocumentoComercialService documentoComercialService;

    @Autowired
    private CriteriosService criteriosService;

    @Autowired
    private PresupuestoService presupuestoService;

    private Egreso egresoActual;

    @GetMapping
    public String egresos(Model model, Authentication auth) {
        Usuario user = usuarioService.findConEntidad(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        List<Egreso> egresos = egresoService.findAllConProveedor(user.getEntidad());
        egresos.addAll(egresoCPService.findAllConProveedor(user.getEntidad()));
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("egresos", egresos);
        return "egresos";
    }

    @GetMapping(path = "/add")
    public String egresosAdd(Model model, Authentication auth) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("new_egreso", new Egreso());
        model.addAttribute("proveedores", proveedorService.findAll());
        return "egresos_add";
    }

    @PostMapping(path = "/add/{egreso_id}")
    public String addItems(Model model, Authentication auth, @PathVariable("egreso_id") String id,
            @ModelAttribute Egreso egreso, @RequestParam("proveedor_elegido") String prov) {
        Usuario user = usuarioService.findConEntidad(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        egreso.setComprador(user.getEntidad());
        egreso.setVendedor(proveedorService.find(Integer.parseInt(prov)));
        egreso.setFechaOperacion(LocalDate.now());
        egresoService.persist(egreso);
        egresoActual = egreso;
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("egreso", egreso);
        model.addAttribute("new_item", new Item());
        return "item_add";
    }

    @PostMapping(path = "/item/add")
    public String addSingleItem(Model model, Authentication auth, @ModelAttribute("new_item") Item item,
            @RequestParam(value = "action") String action) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        itemService.persist(item, egresoActual);
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        if (action.equals("continue")) {
            model.addAttribute("new_item", new Item());
            return "item_add";
        } else
            return "egresos_add_result";
    }

    @GetMapping(path = "/doc_comercial/add")
    public String docComercialAdd(Model model, Authentication auth) {
        Usuario user = usuarioService.findConEntidad(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        List<Egreso> egresos = egresoService.findAllSinDocumentoComercial(user.getEntidad());
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("egresos", egresos);
        model.addAttribute("new_doc", new DocumentoComercial());
        return "doc_comercial_add";
    }

    @PostMapping(path = "/doc_comercial/add")
    public String persistDocComercial(Model model, Authentication auth,
                                      @ModelAttribute("new_doc") DocumentoComercial documentoComercial,
                                      @RequestParam("egreso_id") String egr) {

        Usuario user = usuarioService.findConEntidad(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        Egreso egreso = egresoService.find(Integer.parseInt(egr));
        documentoComercialService.persist(documentoComercial,egreso);
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        return "doc_comercial_add_result";
    }

    @GetMapping(path = "/cp/add")
    public String egresosConPresupuestoAdd(Model model, Authentication auth) {
        Usuario user = usuarioService.findConEntidad(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        List<Usuario> usuarios = usuarioService.findAll(user.getEntidad());
        List<CriterioSeleccion> criterios = criteriosService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("proveedores", proveedorService.findAll());
        model.addAttribute("revisores", usuarios);
        model.addAttribute("criterios", criterios);
        model.addAttribute("new_egreso", new EgresoConPresupuestos());
        return "egresos_add_cp";
    }

    @PostMapping(path = "/cp/add/{egreso_id}")
    public String persistEgresoCP(Model model, Authentication auth, @ModelAttribute EgresoConPresupuestos egresoConPresupuestos,
                             @RequestParam("proveedor_elegido") String prov,
                             @PathVariable("egreso_id") String id,
                             @RequestParam("revisor_elegido") String rev,
                             @RequestParam("criterio_elegido") String crit) {
        Usuario user = usuarioService.findConEntidad(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        egresoConPresupuestos.setRevisor(usuarioService.findConEntidad(rev));
        egresoConPresupuestos.setCriterio(criteriosService.find(Integer.parseInt(crit)));
        egresoConPresupuestos.setComprador(user.getEntidad());
        egresoConPresupuestos.setVendedor(proveedorService.find(Integer.parseInt(prov)));
        egresoConPresupuestos.setFechaOperacion(LocalDate.now());
        egresoCPService.persist(egresoConPresupuestos);
        egresoActual = egresoConPresupuestos;
        model.addAttribute("egreso", egresoConPresupuestos);
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("new_item", new Item());
        return "item_cp_add";
    }

    @PostMapping(path = "/cp/add/item")
    public String addItemCP(Model model, Authentication auth,
                            @ModelAttribute("new_item") Item item,
                            @RequestParam(value = "action") String action) {
        Usuario user = usuarioService.findConEntidad(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        itemService.persist(item, egresoActual);
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        if (action.equals("continue")) {
            model.addAttribute("new_item", new Item());
            return "item_cp_add";
        } else {
            model.addAttribute("new_presupuesto", new Presupuesto());
            return "presupuesto_add";
        }
    }

    @PostMapping(path = "/cp/add/presupuesto")
    public String addPresupuesto(Model model, Authentication auth,
                            @ModelAttribute("new_presupuesto") Presupuesto presupuesto) {
        Usuario user = usuarioService.findConEntidad(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        presupuesto.setEgresoConPresupuestos((EgresoConPresupuestos) egresoActual);
        presupuestoService.persist(presupuesto);
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("new_item", new Item());
        model.addAttribute("presupuesto", presupuesto);
        return "item_presupuesto_add";
    }

    @PostMapping(path = "/cp/add/item/presupuesto/{presupuesto_id}")
    public String addItemPresupuesto(Model model, Authentication auth,
                                        @ModelAttribute("new_item") Item item,
                                        @RequestParam(value = "action") String action,
                                        @PathVariable("presupuesto_id") String presu) {
        Usuario user = usuarioService.findConEntidad(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        Presupuesto presupuesto = presupuestoService.find(Integer.parseInt(presu));
        item.setPresupuesto(presupuesto);
        itemService.persist(item);
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("egreso", egresoActual);
        if (action.equals("add_item")) {
            model.addAttribute("new_item", new Item());
            model.addAttribute("presupuesto", presupuesto);
            return "item_presupuesto_add";
        } else if (action.equals("add_presupuesto")) {
            model.addAttribute("new_presupuesto", new Presupuesto());
            return "presupuesto_add";
        } else {
            List<Presupuesto> presupuestos = egresoCPService.findConPresupuestos(egresoActual.getId()).getTodosLosPresupuestos();
            model.addAttribute("presupuestos",presupuestos);
            return "presupuesto_eleccion";
        }
    }

    @PostMapping(path = "/cp/add/result")
    public String addFinalPresupuesto(Model model, Authentication auth,
                                      @RequestParam("presupuesto_elegido") String presupuesto) {
        Usuario user = usuarioService.findConEntidad(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        EgresoConPresupuestos egreso = egresoCPService.findConPresupuestos(egresoActual.getId());
        egreso.setPresupuestoElegido(egreso.getTodosLosPresupuestos().stream().filter(p -> p.getId() == Integer.parseInt(presupuesto)).findFirst().get());
        egresoCPService.updatePresupuesto(egreso);
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        return "egresos_add_result";
    }

    @GetMapping(path = "/presupuestos/{egreso_id}")
    public String getPresupuestos(Model model, Authentication auth, @PathVariable("egreso_id") int egreso) {
        Usuario user = usuarioService.find(auth.getName());
        List<Mensaje> mensajes = mensajeService.getMensajes(user);
        EgresoConPresupuestos eg = egresoCPService.findConPresupuestos(egreso);
        List<Presupuesto> presupuestos = new ArrayList<>();
        List<Presupuesto> presupuestos2 = new ArrayList<>();
        Presupuesto presupuesto = null;
        if (eg != null) {
            presupuestos = eg.getTodosLosPresupuestos();
            presupuesto = eg.getPresupuestoElegido();
        }
        model.addAttribute("user", user);
        model.addAttribute("mensajes", mensajes);
        model.addAttribute("presupuestos", presupuestos);
        model.addAttribute("presupuesto", presupuesto);
        return "egreso_presupuestos";
    }
}
