package com.yigitcanyontem.aboutme.favmovies;

import com.yigitcanyontem.aboutme.users.Users;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FavMovieService {
    private final FavMovieRepository favMovieRepository;

    public FavMovieService(FavMovieRepository favMovieRepository) {
        this.favMovieRepository = favMovieRepository;
    }

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
