package com.yigitcanyontem.aboutme.users.passwords;

import com.yigitcanyontem.aboutme.users.Users;
import com.yigitcanyontem.aboutme.users.UsersRepository;
import org.springframework.aop.AopInvocationException;
import org.springframework.stereotype.Service;

@Service
public class PasswordsService {
    private final PasswordsRepository passwordsRepository;
    private final UsersRepository usersRepository;

    public PasswordsService(PasswordsRepository passwordsRepository, UsersRepository usersRepository) {
        this.passwordsRepository = passwordsRepository;
        this.usersRepository = usersRepository;
    }

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
        try {
            return passwordsRepository.maxId();
        }catch (AopInvocationException e){
            return 0;
        }
    }

    public Passwords savePassword(Passwords passwords){
        return passwordsRepository.save(passwords);
    }

    public void deletePasswordsByUsersid(Users usersid){
        passwordsRepository.deletePasswordsByUsersid(usersid);
    }

}
