package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.entities.Country;
import com.yigitcanyontem.aboutme.entities.Linkedin;
import com.yigitcanyontem.aboutme.entities.Pinterest;
import com.yigitcanyontem.aboutme.repository.CountryRepository;
import com.yigitcanyontem.aboutme.repository.LinkedinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkedinService {

    @Autowired
    public LinkedinRepository linkedinRepository;

    public Linkedin getLinkedin(Integer id){
        return linkedinRepository.findById(id).orElse(new Linkedin());
    }
}
