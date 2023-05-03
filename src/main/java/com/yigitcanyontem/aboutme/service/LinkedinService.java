package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.entities.Linkedin;
import com.yigitcanyontem.aboutme.repository.LinkedinRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LinkedinService {

    @Autowired
    public LinkedinRepository linkedinRepository;
    @Transactional
    public void saveLinkedin(Integer id, String linkedinId){
        if (linkedinRepository.existsById(id)){
            linkedinRepository.deleteLinkedinByUsersid(id);
        }
        linkedinRepository.save(new Linkedin(id, linkedinId));
    }
    public Linkedin getLinkedin(Integer id){
        return linkedinRepository.findById(id).orElse(new Linkedin());
    }

    @Transactional
    public void deleteUserLinkedin(Integer usersid){
        linkedinRepository.deleteLinkedinByUsersid(usersid);
    }
}
