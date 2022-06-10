package com.example.porfolio.servicio;

import com.example.porfolio.entidades.Admin;
import com.example.porfolio.repository.AdminRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin guardarAcercaDeMi(Admin admin) throws Exception {
	if (admin.getacercaDeMi().isEmpty()) {
	    throw new Exception("El nombre del autor no puede estar vacio");
	}
	return adminRepository.save(admin);
    }

    public Admin modificarAcercaDeMi(Admin admin, String acercaDeMi) throws Exception {
	if (acercaDeMi.isEmpty()) {
	    throw new Exception("El nuevo nombre del autor no puede estar vacio");
	}
	admin.setacercaDeMi(acercaDeMi);
	return adminRepository.save(admin);
    }

    public Admin darBaja(Admin admin) {
	admin.setAlta(false);
	return adminRepository.save(admin);
    }

    public List<Admin> listarAutoresl() {
	return adminRepository.findAll();
    }

//    public Admin buscarPorNombre(String nombre) {
//	return adminRepository.buscarPorNombre(nombre);
//    }

    public Admin buscarPorId(String id) {
	return adminRepository.getById(id);
    }

    public void borrarAcercaDeMi(String id) throws Error {
	Optional<Admin> respuesta = adminRepository.findById(id);
	if (respuesta.isPresent()) {
	    Admin admin = respuesta.get();
	    admin.setAlta(false);
	    adminRepository.save(admin);
	} else {
	    throw new Error("No se encontró un autor con ese nombre");
	}
    }

    public void altaAutor(String id) throws Error {
	Optional<Admin> respuesta = adminRepository.findById(id);
	if (respuesta.isPresent()) {
	    Admin admin = respuesta.get();
	    admin.setAlta(true);
	    adminRepository.save(admin);
	} else {
	    throw new Error("No se encontró un autor con ese nombre");
	}
    }

    public void eliminarAcercaDeMi(String id) throws Error {
	Admin admin = adminRepository.getById(id);
	if (admin == null) {
	    throw new Error("El autor no existe en la base de datos");
	} else {
	    adminRepository.delete(admin);
	}
    }
 }
