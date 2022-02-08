package com.solinftec.training.solinfbroker.services.UserService;

import java.time.Instant;
import java.util.Date;

import com.solinftec.training.solinfbroker.model.Users;
import com.solinftec.training.solinfbroker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    public UserService(UsersRepository usersRepository) {
        super();
        this.usersRepository = usersRepository;
    }

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public ResponseEntity<?> save(Users users) {

        Users usersExistente = usersRepository.findUserExistente(users.getUsername());

        if (usersExistente != null && usersExistente.getUsername() != "") {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        } else {
            usersRepository.save(users);

            return ResponseEntity.ok().body(users);
        }
    }

    @Override
    public Users Listar(long id) {

        return usersRepository.findId(id);
    }

    @Override
    public Users ListarEmail(String email) {
        if (usersRepository.findUserExistente(email) == null) {
            Users user = new Users();
            user.setUsername(email);
            user.setEnabled(true);
            user.setdollarBalance(0.00);
            return usersRepository.save(user);
        } else {
            return usersRepository.findUserExistente(email);
        }
    }

    @Override
    public ResponseEntity<?> alterarUsers(Users newUsers, long idUser) {
        return usersRepository.findById(idUser)
                .map(usuario -> {
                    usuario.setUsername(newUsers.getUsername());
                    usuario.setEnabled(newUsers.isEnabled());
                    usuario.setupdatedOn(Date.from(Instant.now()));
                    return ResponseEntity.ok().body(usersRepository.save(usuario));

                }).orElseGet(() -> {
                    newUsers.setId(idUser);
                    return ResponseEntity.ok().body(usersRepository.save(newUsers));
                });
    }
}
