/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.porfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Nickler
 */
@Controller
@RequestMapping("")
public class PortfolioController {
  @GetMapping("")
    public String login(@RequestParam(name = "error", required = false)String error, Model modelo) {
	if(error != null && !error.isEmpty()) {
	    modelo.addAttribute("error", "Usuario o contraseña incorrectos");
	}
	return "portfolioInicio";
    }
}
