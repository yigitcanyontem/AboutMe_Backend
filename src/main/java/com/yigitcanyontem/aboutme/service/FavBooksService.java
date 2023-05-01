package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.entities.FavBooks;
import com.yigitcanyontem.aboutme.entities.Users;
import com.yigitcanyontem.aboutme.repository.FavBooksRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavBooksService {
    @Autowired
    FavBooksRepository favBooksRepository;

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
        favBooksRepository.save(new FavBooks(favBooksRepository.maxId()+1,usersid,favbooksid));
    }
}
