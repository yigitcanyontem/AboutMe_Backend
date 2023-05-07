package com.yigitcanyontem.aboutme.favbooks;

import com.yigitcanyontem.aboutme.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavBooksRepository extends JpaRepository<FavBooks,String> {
    List<FavBooks> findByUsersid(Users usersid);
    @Query("SELECT max(a.id) FROM FavBooks a")
    int maxId();
    void deleteFavBooksByUsersid(Users usersid);
    void deleteFavBooksByUsersidAndBookid(Users usersid, String bookid);

}
