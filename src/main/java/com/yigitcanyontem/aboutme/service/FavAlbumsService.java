package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.entities.FavAlbums;
import com.yigitcanyontem.aboutme.entities.Users;
import com.yigitcanyontem.aboutme.repository.FavAlbumsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavAlbumsService {
    @Autowired
    FavAlbumsRepository favAlbumsRepository;

    public List<FavAlbums> findByUserId(Users usersid){
        return favAlbumsRepository.findByUsersid(usersid);
    }

    @Transactional
    public void deleteUserFavAlbums(Users usersid){
        favAlbumsRepository.deleteFavAlbumsByUsersid(usersid);
    }
    @Transactional
    public void deleteUserFavAlbumsById(Users usersid, String albumid){
        favAlbumsRepository.deleteFavAlbumsByUsersidAndAlbumid(usersid,albumid);
    }
    public void saveFavAlbums(Users usersid, String favalbumsid){
        favAlbumsRepository.save(new FavAlbums(favAlbumsRepository.maxId()+1,usersid,favalbumsid));
    }
}
