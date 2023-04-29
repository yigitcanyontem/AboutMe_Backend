package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.entities.Country;
import com.yigitcanyontem.aboutme.entities.Twitter;
import com.yigitcanyontem.aboutme.entities.Users;
import com.yigitcanyontem.aboutme.repository.CountryRepository;
import com.yigitcanyontem.aboutme.repository.TwitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterService {

    @Autowired
    public TwitterRepository twitterRepository;

    public Twitter getTwitter(Integer id){
        return twitterRepository.findById(id).orElse(new Twitter());
    }
}
