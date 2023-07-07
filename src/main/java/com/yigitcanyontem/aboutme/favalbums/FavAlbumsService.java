package com.yigitcanyontem.aboutme.favalbums;

import com.yigitcanyontem.aboutme.users.Users;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavAlbumsService {
    private final FavAlbumsRepository favAlbumsRepository;

    public FavAlbumsService(FavAlbumsRepository favAlbumsRepository) {
        this.favAlbumsRepository = favAlbumsRepository;
    }

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
