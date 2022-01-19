package com.solinftec.training.solinfbroker.services.UserService;

import com.solinftec.training.solinfbroker.model.Users;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<?> save(Users users);
    Users Listar(long id);
}
