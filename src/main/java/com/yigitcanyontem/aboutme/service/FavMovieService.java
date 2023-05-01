package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.entities.FavMovie;
import com.yigitcanyontem.aboutme.entities.Users;
import com.yigitcanyontem.aboutme.repository.FavMovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FavMovieService {
    @Autowired
    FavMovieRepository favMovieRepository;

    public List<FavMovie> findByUserId(Users usersid){
        return favMovieRepository.findByUsersid(usersid);
    }

    @Transactional
    public void deleteUserFavMovie(Users usersid){
        favMovieRepository.deleteFavMovieByUsersid(usersid);
    }

    @Transactional
    public void deleteUserFavMovieById(Users usersid, Integer movieid){
        favMovieRepository.deleteFavMovieByUsersidAndMovieid(usersid,movieid);
    }

    public void saveFavMovie(Users usersid, Integer favmovieid){
        favMovieRepository.save(new FavMovie(favMovieRepository.maxId()+1,usersid,favmovieid));
    }
}
