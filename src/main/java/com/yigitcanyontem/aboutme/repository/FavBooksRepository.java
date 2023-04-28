package com.yigitcanyontem.aboutme.repository;

import com.yigitcanyontem.aboutme.entities.FavBooks;
import com.yigitcanyontem.aboutme.entities.FavMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavBooksRepository extends JpaRepository<FavBooks,String> {
    @Query("SELECT fm.bookid FROM FavBooks fm WHERE fm.usersid = :usersid")
    List<String> findByUserId(@Param("usersid") Integer usersid);
}
