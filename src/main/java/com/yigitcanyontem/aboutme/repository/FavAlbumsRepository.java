package com.yigitcanyontem.aboutme.repository;

import com.yigitcanyontem.aboutme.entities.FavAlbums;
import com.yigitcanyontem.aboutme.entities.FavMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavAlbumsRepository extends JpaRepository<FavAlbums,String> {
    @Query("SELECT fm.albumid FROM FavAlbums fm WHERE fm.usersid = :usersid")
    List<String> findByUserId(@Param("usersid") Integer usersid);
}
