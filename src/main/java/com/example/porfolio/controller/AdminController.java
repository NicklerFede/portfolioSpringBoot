package com.example.porfolio.controller;

import com.example.porfolio.entidades.Admin;
import com.example.porfolio.entidades.Usuario;
import com.example.porfolio.servicio.AdminService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/administrador")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("")
    public String admin(Model modelo, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }
        Usuario admin = new Usuario();
        admin.getUsername();
        modelo.addAttribute("admin", admin);
        return "portfolioAdmin";
    }

    @PostMapping("/save/acercaDeMi")
    public String formularioData(@RequestParam("acercaDeMi") String acercaDeMi,
            @RequestParam("id") String id, @RequestParam(name = "modificar", required = false) String modificar,
            Model modelo) {
        Admin admin = new Admin();
        try {
            admin.setAcercaDeMi(acercaDeMi);
            admin.setId(id);
            adminService.guardarAcercaDeMi(admin);
            modelo.addAttribute("admin", admin);
            if (modificar != null) {
                return "redirect:/portfolioAdmin";
            }
            return "portfolioAdmin";
        } catch (Exception ex) {
            ex.printStackTrace();
            modelo.addAttribute("admin", admin);
            modelo.addAttribute("error", ex.getMessage());
            return "portfolioAdmin";
        }
    }

    @GetMapping("/list")
    public String listAll(Model modelo, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }
        List<Admin> acercaDe = adminService.listarAcercaDeMi();
        modelo.addAttribute("listaAcercaDe", acercaDe);
        return "portfolioAdmin";
    }

//      @GetMapping("/modificar")
//    public String formulario(@RequestParam(name = "id", required = true) String id, Model modelo) {
//        Admin admin= adminService.buscarPorId(id);
//        modelo.addAttribute("admin", admin);
//        return "autor";
//    }
}
