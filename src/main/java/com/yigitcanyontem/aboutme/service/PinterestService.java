package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.entities.Pinterest;
import com.yigitcanyontem.aboutme.entities.Twitter;
import com.yigitcanyontem.aboutme.repository.PinterestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PinterestService {

    @Autowired
    public PinterestRepository pinterestRepository;
    @Transactional
    public void savePinterest(Integer id, String pinterestId){
        if (pinterestRepository.existsById(id)){
            pinterestRepository.deletePinterestByUsersid(id);
        }
        pinterestRepository.save(new Pinterest(id, pinterestId));
    }
    public Pinterest getPinterest(Integer id){
        return pinterestRepository.findById(id).orElse(new Pinterest());
    }

    @Transactional
    public void deleteUserPinterest(Integer usersid){
        pinterestRepository.deletePinterestByUsersid(usersid);
    }
}
