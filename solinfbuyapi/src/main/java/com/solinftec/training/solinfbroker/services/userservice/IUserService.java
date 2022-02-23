package com.solinftec.training.solinfbroker.services.userservice;

import com.solinftec.training.solinfbroker.model.Users;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<?> save(Users users);

    Users listar(long id);

    Users listarEmail(String email);

    ResponseEntity<?> alterarUsers(Users users, long idUser);
}
