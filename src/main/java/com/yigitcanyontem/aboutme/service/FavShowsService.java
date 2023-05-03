package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.entities.FavShows;
import com.yigitcanyontem.aboutme.entities.Users;
import com.yigitcanyontem.aboutme.repository.FavShowsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavShowsService {
    @Autowired
    FavShowsRepository favShowsRepository;

    public List<FavShows> findByUserId(Users usersid){
        return favShowsRepository.findByUsersid(usersid);
    }

    @Transactional
    public void deleteUserFavShows(Users usersid){
        favShowsRepository.deleteFavShowsByUsersid(usersid);
    }
    @Transactional
    public void deleteUserFavShowsById(Users usersid, Integer showid){
        favShowsRepository.deleteFavShowsByUsersidAndShowid(usersid,showid);
    }
    public void saveFavShows(Users usersid, Integer favshowsid){
        favShowsRepository.save(new FavShows(favShowsRepository.maxId()+1,usersid,favshowsid));
    }
}
