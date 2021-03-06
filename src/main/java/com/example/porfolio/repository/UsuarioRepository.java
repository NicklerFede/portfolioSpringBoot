/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.porfolio.repository;

import com.example.porfolio.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nickler
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    
    @Query("SELECT u from Usuario u where u.username = :pepe")
    public Usuario buscarUsuarioPorUsername(@Param("pepe") String username);
    
    public Usuario findByUsername(String username);
}