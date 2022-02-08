package com.solinftec.training.solinfbroker.controller;

import org.springframework.web.bind.annotation.RestController;
import com.solinftec.training.solinfbroker.services.UserService.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.solinftec.training.solinfbroker.model.Users;

@RestController
@CrossOrigin
@RequestMapping("/usuarios")
public class UsuarioController {

    IUserService userService;

    public UsuarioController(IUserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping()
    public Users listar(@RequestParam(required = false, defaultValue = "0") long id,
            @RequestParam(required = false, defaultValue = "0") String email) {

        if (email != "") {
            return userService.listarEmail(email);
        } else {
            return userService.listar(id);
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Users user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceUsers(@RequestBody Users user, @PathVariable Long id) {
        return userService.alterarUsers(user, id);
    }
}