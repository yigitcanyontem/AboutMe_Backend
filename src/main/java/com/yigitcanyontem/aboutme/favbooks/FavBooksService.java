package com.yigitcanyontem.aboutme.favbooks;

import com.yigitcanyontem.aboutme.users.Users;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavBooksService {
    private final FavBooksRepository favBooksRepository;

    public FavBooksService(FavBooksRepository favBooksRepository) {
        this.favBooksRepository = favBooksRepository;
    }

    public List<FavBooks> findByUserId(Users usersid){
        return favBooksRepository.findByUsersid(usersid);
    }

    @Transactional
    public void deleteUserFavBooks(Users usersid){
        favBooksRepository.deleteFavBooksByUsersid(usersid);
    }
    @Transactional
    public void deleteUserFavBooksById(Users usersid, String bookid){
        favBooksRepository.deleteFavBooksByUsersidAndBookid(usersid,bookid);
    }
    public void saveFavBooks(Users usersid, String favbooksid){
        favBooksRepository.save(new FavBooks(usersid,favbooksid));
    }
}
