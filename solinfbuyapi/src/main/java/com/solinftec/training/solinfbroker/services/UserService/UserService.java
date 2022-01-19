package com.solinftec.training.solinfbroker.services.UserService;

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
        usersRepository.save(users);
        
        return ResponseEntity.ok().body(users);
    }

    @Override
    public Users Listar(long id) {
        
        return usersRepository.findId(id);
    }
    
}
