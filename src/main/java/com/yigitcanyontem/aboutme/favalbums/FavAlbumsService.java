package com.yigitcanyontem.aboutme.favalbums;

import com.yigitcanyontem.aboutme.exceptions.SearchNotFoundException;
import com.yigitcanyontem.aboutme.users.Users;
import com.yigitcanyontem.aboutme.users.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavAlbumsService {
    private final FavAlbumsRepository favAlbumsRepository;
    private final UsersRepository usersRepository;

    public FavAlbumsService(FavAlbumsRepository favAlbumsRepository, UsersRepository usersRepository) {
        this.favAlbumsRepository = favAlbumsRepository;
        this.usersRepository = usersRepository;
    }

    public List<FavAlbums> findByUserId(Users usersid){
        if (!favAlbumsRepository.existsFavAlbumsByUsersid(usersid)){
            throw new SearchNotFoundException("User Not Found");
        }
        return favAlbumsRepository.findByUsersid(usersid);
    }

    @Transactional
    public void deleteUserFavAlbums(Users usersid){
        if (!favAlbumsRepository.existsFavAlbumsByUsersid(usersid)){
            throw new SearchNotFoundException("User Not Found");
        }
        favAlbumsRepository.deleteFavAlbumsByUsersid(usersid);
    }
    @Transactional
    public void deleteUserFavAlbumsById(Users usersid, String albumid){
        if (!favAlbumsRepository.existsFavAlbumsByUsersid(usersid)){
            throw new SearchNotFoundException("User Not Found");
        }
        if (!favAlbumsRepository.existsFavAlbumsByUsersidAndAlbumid(usersid,albumid)){
            throw new SearchNotFoundException("Album Not Favorited");
        }
        favAlbumsRepository.deleteFavAlbumsByUsersidAndAlbumid(usersid,albumid);
    }
    public void saveFavAlbums(Users usersid, String favalbumsid){
        if (!usersRepository.existsById(usersid.getId())){
            throw new SearchNotFoundException("User Not Found");
        }
        if (favAlbumsRepository.countFavAlbumsByUsersid(usersid) == 6){
            throw new SearchNotFoundException("You can favorite maximum 6 albums please remove one");
        }
        if (favAlbumsRepository.existsFavAlbumsByUsersidAndAlbumid(usersid,favalbumsid)){
            throw new SearchNotFoundException("Already Favorited");
        }
        favAlbumsRepository.save(new FavAlbums(usersid,favalbumsid));
    }
}
