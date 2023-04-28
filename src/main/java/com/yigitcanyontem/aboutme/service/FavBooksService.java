package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.repository.FavBooksRepository;
import com.yigitcanyontem.aboutme.repository.FavMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavBooksService {
    @Autowired
    FavBooksRepository favBooksRepository;

    public List<String> findByUserId(Integer usersid){
        return favBooksRepository.findByUserId(usersid);
    }
}
