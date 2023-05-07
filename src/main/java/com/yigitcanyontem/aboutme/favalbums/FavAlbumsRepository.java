package com.yigitcanyontem.aboutme.favalbums;

import com.yigitcanyontem.aboutme.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavAlbumsRepository extends JpaRepository<FavAlbums,String> {
    List<FavAlbums> findByUsersid(Users usersid);
    @Query("SELECT max(a.id) FROM FavAlbums a")
    int maxId();
    void deleteFavAlbumsByUsersid(Users usersid);
    void deleteFavAlbumsByUsersidAndAlbumid(Users usersid, String albumid);
}
