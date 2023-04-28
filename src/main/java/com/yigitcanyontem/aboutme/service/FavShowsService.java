package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.repository.FavMovieRepository;
import com.yigitcanyontem.aboutme.repository.FavShowsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavShowsService {
    @Autowired
    FavShowsRepository favShowsRepository;

    public List<Integer> findByUserId(Integer usersid){
        return favShowsRepository.findByUserId(usersid);
    }
}
