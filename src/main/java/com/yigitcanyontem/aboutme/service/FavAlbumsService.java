package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.repository.FavAlbumsRepository;
import com.yigitcanyontem.aboutme.repository.FavMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavAlbumsService {
    @Autowired
    FavAlbumsRepository favAlbumsRepository;

    public List<String> findByUserId(Integer usersid){
        return favAlbumsRepository.findByUserId(usersid);
    }
}
