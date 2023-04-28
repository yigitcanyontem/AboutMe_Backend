package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.entities.FavMovie;
import com.yigitcanyontem.aboutme.repository.FavMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FavMovieService {
    @Autowired
    FavMovieRepository favMovieRepository;

    public List<Integer> findByUserId(Integer usersid){
        return favMovieRepository.findByUserId(usersid);
    }
}
