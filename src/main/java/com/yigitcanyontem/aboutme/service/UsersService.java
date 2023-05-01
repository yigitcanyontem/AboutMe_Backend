package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.entities.Users;
import com.yigitcanyontem.aboutme.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;
    public Users getUser(Integer id){
        return usersRepository.findById(id).orElseThrow();
    }
    public Optional<Users> singleUser(Integer id){
        return usersRepository.findById(id);
    }
    public Users addUser(Users user){
        return usersRepository.save(user);
    }
    public Integer max(){
        return usersRepository.maxUsersId();
    }
    public Integer getUserByUsername(String username){
        return usersRepository.getUsersByUsername(username).getId();
    }
    public List<Users> usersList(String username){
        return usersRepository.findByUsernameContaining(username);
    }

    @Transactional
    public void deleteUser(Integer usersid){
        usersRepository.deleteById(usersid);
    }
}
