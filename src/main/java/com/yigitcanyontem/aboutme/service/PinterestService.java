package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.entities.Pinterest;
import com.yigitcanyontem.aboutme.repository.PinterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PinterestService {

    @Autowired
    public PinterestRepository pinterestRepository;

    public Pinterest getPinterest(Integer id){
        return pinterestRepository.findById(id).orElse(new Pinterest());
    }
}
