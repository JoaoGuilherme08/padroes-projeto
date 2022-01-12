package com.solinftec.training.solinfbroker.controller;
import org.springframework.web.bind.annotation.RestController;

import com.solinftec.training.solinfbroker.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.solinftec.training.solinfbroker.model.Users;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public List<Users> listar() {
        return usersRepository.findAll();
    }

    @PostMapping
    public Users adicionar(@RequestBody Users user){
        return usersRepository.save(user);
    }

    @PutMapping("/{id}")
    public Users replaceUsers(@RequestBody Users newUsers, @PathVariable Long id){
       
        return usersRepository.findById(id)
         .map(usuario -> {
            usuario.setUsername(newUsers.getUsername());
            usuario.setUpdated_on(Date.from(Instant.now()));
            return usersRepository.save(usuario);

        }).orElseGet(() -> {
            newUsers.setId(id);
            return usersRepository.save(newUsers);
        });

    }
}   