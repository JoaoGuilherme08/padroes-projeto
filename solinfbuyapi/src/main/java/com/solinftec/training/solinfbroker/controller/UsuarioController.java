package com.solinftec.training.solinfbroker.controller;

import org.springframework.web.bind.annotation.RestController;

import com.solinftec.training.solinfbroker.repository.UsersRepository;
import com.solinftec.training.solinfbroker.services.UserService.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.util.Date;
import com.solinftec.training.solinfbroker.model.Users;

@RestController
@CrossOrigin
@RequestMapping("/usuarios")
public class UsuarioController {

    IUserService userService;

    public UsuarioController(UsersRepository usersRepository, IUserService userService) {
        super();
        this.usersRepository = usersRepository;
        this.userService = userService;
    }

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping()
    public Users listar(@RequestParam(required = false, defaultValue = "0") long id,
            @RequestParam(required = false, defaultValue = "0") String email) {

        if (email != "") {
            return userService.ListarEmail(email);
        } else {
            return userService.Listar(id);
        }

    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Users user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public Users replaceUsers(@RequestBody Users newUsers, @PathVariable Long id) {

        return usersRepository.findById(id)
                .map(usuario -> {
                    usuario.setUsername(newUsers.getUsername());
                    usuario.setEnabled(newUsers.isEnabled());
                    usuario.setUpdated_on(Date.from(Instant.now()));
                    return usersRepository.save(usuario);

                }).orElseGet(() -> {
                    newUsers.setId(id);
                    return usersRepository.save(newUsers);
                });

    }
}