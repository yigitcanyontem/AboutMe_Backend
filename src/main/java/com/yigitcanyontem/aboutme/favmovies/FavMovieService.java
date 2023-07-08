package com.yigitcanyontem.aboutme.favmovies;

import com.yigitcanyontem.aboutme.exceptions.SearchNotFoundException;
import com.yigitcanyontem.aboutme.users.Users;
import com.yigitcanyontem.aboutme.users.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FavMovieService {
    private final FavMovieRepository favMovieRepository;
    private final UsersRepository usersRepository;
    public FavMovieService(FavMovieRepository favMovieRepository, UsersRepository usersRepository) {
        this.favMovieRepository = favMovieRepository;
        this.usersRepository = usersRepository;
    }

    public List<FavMovie> findByUserId(Users usersid){
        if (!favMovieRepository.existsByUsersid(usersid)){
            throw new SearchNotFoundException("User Not Found");
        }
        return favMovieRepository.findByUsersid(usersid);
    }

    @Transactional
    public void deleteUserFavMovie(Users usersid){
        if (!favMovieRepository.existsByUsersid(usersid)){
            throw new SearchNotFoundException("User Not Found");
        }
        favMovieRepository.deleteFavMovieByUsersid(usersid);
    }

    @Transactional
    public void deleteUserFavMovieById(Users usersid, Integer movieid){
        if (!favMovieRepository.existsByUsersid(usersid)){
            throw new SearchNotFoundException("User Not Found");
        }
        if (!favMovieRepository.existsFavMovieByUsersidAndMovieid(usersid,movieid)){
            throw new SearchNotFoundException("Movie Not Favorited");
        }
        favMovieRepository.deleteFavMovieByUsersidAndMovieid(usersid,movieid);
    }

    public void saveFavMovie(Users usersid, Integer favmovieid){
        if (!usersRepository.existsById(usersid.getId())){
            throw new SearchNotFoundException("User Not Found");
        }
        if (favMovieRepository.existsFavMovieByUsersidAndMovieid(usersid,favmovieid)){
            throw new SearchNotFoundException("Already Favorited");
        }
        if (favMovieRepository.countFavMoviesByUsersid(usersid) == 6){
            throw new SearchNotFoundException("You can favorite maximum 6 movies please remove one");
        }
        favMovieRepository.save(new FavMovie(usersid,favmovieid));
    }


}
