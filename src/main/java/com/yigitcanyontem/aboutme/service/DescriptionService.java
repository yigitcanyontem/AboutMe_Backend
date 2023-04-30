package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.entities.Description;
import com.yigitcanyontem.aboutme.repository.DescriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DescriptionService {
    @Autowired
    DescriptionRepository descriptionRepository;
    @Transactional
    public void saveDescription(Integer id, String  description){
        if (descriptionRepository.existsById(id)){
            descriptionRepository.deleteDescriptionByUsersid(id);
        }
        descriptionRepository.save(new Description(id, description));
    }
    public Description description(Integer id){
        return descriptionRepository.findById(id).orElse(new Description());
    }

}
