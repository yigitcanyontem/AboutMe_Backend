package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.entities.Passwords;
import com.yigitcanyontem.aboutme.entities.Users;
import com.yigitcanyontem.aboutme.model.PasswordModel;
import com.yigitcanyontem.aboutme.repository.PasswordsRepository;
import com.yigitcanyontem.aboutme.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordsService {
    @Autowired
    PasswordsRepository passwordsRepository;
    @Autowired
    UsersRepository usersRepository;
    public String logIn(PasswordModel passwordModel){
        Users usersid = usersRepository.getUsersByUsername(passwordModel.getUsername());
        Passwords passwords = passwordsRepository.getPasswordsByUsersid(usersid);
        if (passwords.getPassword().equals(passwordModel.getPassword())){
            return  String.valueOf(usersid.getId());
        }else {
            return "Error";
        }
    }
    public Integer max(){
        return passwordsRepository.maxId();
    }

    public Passwords savePassword(Passwords passwords){
        return passwordsRepository.save(passwords);
    }

    public void deletePasswordsByUsersid(Users usersid){
        passwordsRepository.deletePasswordsByUsersid(usersid);
    }

}
