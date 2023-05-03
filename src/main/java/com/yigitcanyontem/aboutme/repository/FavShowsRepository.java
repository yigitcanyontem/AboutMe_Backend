package com.yigitcanyontem.aboutme.repository;

import com.yigitcanyontem.aboutme.entities.FavShows;
import com.yigitcanyontem.aboutme.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavShowsRepository extends JpaRepository<FavShows,Integer> {
    List<FavShows> findByUsersid(Users usersid);
    @Query("SELECT max(a.id) FROM FavShows a")
    int maxId();
    void deleteFavShowsByUsersid(Users usersid);
    void deleteFavShowsByUsersidAndShowid(Users usersid, Integer showid);
}
