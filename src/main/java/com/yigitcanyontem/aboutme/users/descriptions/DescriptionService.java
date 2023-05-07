package com.yigitcanyontem.aboutme.users.descriptions;

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
    @Transactional
    public void deleteUserDescription(Integer usersid){
        descriptionRepository.deleteDescriptionByUsersid(usersid);
    }
}
