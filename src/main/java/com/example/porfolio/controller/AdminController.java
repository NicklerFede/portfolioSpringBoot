package com.example.porfolio.controller;

import com.example.porfolio.entidades.Admin;
import com.example.porfolio.servicio.AdminService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


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
        Admin admin = new Admin();
        modelo.addAttribute("admin", admin);
        return "portfolioAdmin";
    }
    
    @GetMapping("/modificar")
    public String formulario(@RequestParam(name = "id", required = true) String id, Model modelo, @RequestParam("archivo") MultipartFile archivo) {
       Admin admin = adminService.buscarPorId(id);
        modelo.addAttribute("admin", admin);
        return "portfolioAdmin";
    }
}
