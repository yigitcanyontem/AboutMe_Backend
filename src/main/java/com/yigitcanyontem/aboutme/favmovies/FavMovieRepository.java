package com.yigitcanyontem.aboutme.favmovies;

import com.yigitcanyontem.aboutme.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavMovieRepository extends JpaRepository<FavMovie,Users> {
    List<FavMovie> findByUsersid(Users usersid);
    @Query("SELECT max(a.id) FROM FavMovie a")
    int maxId();
    void deleteFavMovieByUsersid(Users usersid);
    void deleteFavMovieByUsersidAndMovieid(Users usersid, Integer movieid);
}