/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.porfolio.repository;

import com.example.porfolio.entidades.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nickler
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{
   
     @Query("select a from Admin a where a.acercaDeMi = :acercaDeMi")
    public Admin buscarAcercaDeMi(@Param("acercaDeMi") String acercaDeMi);

}
