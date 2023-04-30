package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.entities.Country;
import com.yigitcanyontem.aboutme.entities.Description;
import com.yigitcanyontem.aboutme.entities.Twitter;
import com.yigitcanyontem.aboutme.entities.Users;
import com.yigitcanyontem.aboutme.repository.CountryRepository;
import com.yigitcanyontem.aboutme.repository.TwitterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterService {

    @Autowired
    public TwitterRepository twitterRepository;
    @Transactional
    public void saveTwitter(Integer id, String twitterId){
        if (twitterRepository.existsById(id)){
            twitterRepository.deleteTwitterByUsersid(id);
        }
        twitterRepository.save(new Twitter(id, twitterId));
    }
    public Twitter getTwitter(Integer id){
        return twitterRepository.findById(id).orElse(new Twitter());
    }
}
