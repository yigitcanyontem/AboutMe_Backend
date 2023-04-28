package com.yigitcanyontem.aboutme.repository;

import com.yigitcanyontem.aboutme.entities.FavMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavMovieRepository extends JpaRepository<FavMovie,Integer> {
    @Query("SELECT fm.movieid FROM FavMovie fm WHERE fm.usersid = :usersid")
    List<Integer> findByUserId(@Param("usersid") Integer usersid);
}
