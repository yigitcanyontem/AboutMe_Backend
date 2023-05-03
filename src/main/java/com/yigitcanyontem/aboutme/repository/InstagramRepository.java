package com.yigitcanyontem.aboutme.repository;

import com.yigitcanyontem.aboutme.entities.Instagram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstagramRepository extends JpaRepository<Instagram,Integer> {
    void deleteInstagramByUsersid(Integer usersid);
}
