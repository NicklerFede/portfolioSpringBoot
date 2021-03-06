/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.porfolio.servicio;

import com.example.porfolio.entidades.Usuario;
import com.example.porfolio.enums.Rol;
import com.example.porfolio.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Nickler
 */
@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(String username, String password, String password2) throws Exception {
        if (username.isEmpty()) {
            throw new Exception("El username no puede estar vacío");
        }

        if (password.isEmpty()) {
            throw new Exception("La contraseña no puede estar vacía");
        }

        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario != null) {
            throw new Exception("El usuario ya existe, pruebe con otro nombre");
        }
        if (!password.equals(password2)) {
            throw new Exception("Las contraseñas ingresadas deben ser iguales");
        }
        usuario = new Usuario();
        usuario.setUsername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(password));
        usuario.setRol(Rol.USUARIO);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
    
    public void agregarUsuarioALaSesion(Usuario usuario) {
        ServletRequestAttributes attributes  = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(true);
        session.setAttribute("usuario", usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Usuario usuario = usuarioRepository.findByUsername(username);
            List<GrantedAuthority> pepe = new ArrayList<>();
            agregarUsuarioALaSesion(usuario);
            pepe.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
            return new User(username, usuario.getPassword(), pepe);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario no existe");
        }
    }

}