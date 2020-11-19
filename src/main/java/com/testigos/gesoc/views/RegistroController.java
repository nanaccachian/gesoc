package com.testigos.gesoc.views;

import com.testigos.gesoc.model.services.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class RegistroController {
    @Autowired
    private RegistroService service;

    @RequestMapping(method = GET, path = "/registers")
    public String registersView(Model model) {
        model.addAttribute("registros", service.findAll());
//        model.addAttribute("bySalary", Comparator.comparing(Employee::getSalary));
        return "registers";
    }
}
