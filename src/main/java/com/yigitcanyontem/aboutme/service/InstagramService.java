package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.entities.Country;
import com.yigitcanyontem.aboutme.entities.Instagram;
import com.yigitcanyontem.aboutme.entities.Linkedin;
import com.yigitcanyontem.aboutme.repository.CountryRepository;
import com.yigitcanyontem.aboutme.repository.InstagramRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstagramService {

    @Autowired
    public InstagramRepository instagramRepository;
    @Transactional
    public void saveInstagram(Integer id, String instagramId){
        if (instagramRepository.existsById(id)){
            instagramRepository.deleteInstagramByUsersid(id);
        }
        instagramRepository.save(new Instagram(id, instagramId));
    }
    public Instagram getInstagram(Integer id){
        return instagramRepository.findById(id).orElse(new Instagram());
    }

    @Transactional
    public void deleteUserInstagram(Integer usersid){
        instagramRepository.deleteInstagramByUsersid(usersid);
    }
}
