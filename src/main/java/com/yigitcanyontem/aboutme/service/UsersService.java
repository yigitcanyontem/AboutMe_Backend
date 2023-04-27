package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.entities.Users;
import com.yigitcanyontem.aboutme.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    public Optional<Users> singleUser(Integer id){
        return usersRepository.findById(id);
    }

    public Users addUser(Users user){
        return usersRepository.save(user);
    }
    public Integer max(){
        return usersRepository.maxUsersId();
    }
}
