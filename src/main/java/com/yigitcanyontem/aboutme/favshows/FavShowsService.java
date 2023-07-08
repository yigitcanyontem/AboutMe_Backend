package com.yigitcanyontem.aboutme.favshows;

import com.yigitcanyontem.aboutme.exceptions.SearchNotFoundException;
import com.yigitcanyontem.aboutme.users.Users;
import com.yigitcanyontem.aboutme.users.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavShowsService {
    private final FavShowsRepository favShowsRepository;
    private final UsersRepository usersRepository;


    public FavShowsService(FavShowsRepository favShowsRepository, UsersRepository usersRepository) {
        this.favShowsRepository = favShowsRepository;
        this.usersRepository = usersRepository;
    }

    public List<FavShows> findByUserId(Users usersid){
        if (!favShowsRepository.existsByUsersid(usersid)){
            throw new SearchNotFoundException("User Not Found");
        }
        return favShowsRepository.findByUsersid(usersid);
    }

    @Transactional
    public void deleteUserFavShows(Users usersid){
        if (!favShowsRepository.existsByUsersid(usersid)){
            throw new SearchNotFoundException("User Not Found");
        }
        favShowsRepository.deleteFavShowsByUsersid(usersid);
    }
    @Transactional
    public void deleteUserFavShowsById(Users usersid, Integer showid){
        if (!favShowsRepository.existsByUsersid(usersid)){
            throw new SearchNotFoundException("User Not Found");
        }
        if (!favShowsRepository.existsByUsersidAndShowid(usersid,showid)){
            throw new SearchNotFoundException("Show Not Favorited");
        }
        favShowsRepository.deleteFavShowsByUsersidAndShowid(usersid,showid);
    }
    public void saveFavShows(Users usersid, Integer favshowsid){
        if (!usersRepository.existsById(usersid.getId())){
            throw new SearchNotFoundException("User Not Found");
        }
        if (favShowsRepository.countFavShowsByUsersid(usersid) == 6){
            throw new SearchNotFoundException("You can favorite maximum 6 shows please remove one");
        }
        if (favShowsRepository.existsByUsersidAndShowid(usersid,favshowsid)){
            throw new SearchNotFoundException("Already Favorited");
        }
        favShowsRepository.save(new FavShows(usersid,favshowsid));
    }
}
