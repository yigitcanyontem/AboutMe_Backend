package com.yigitcanyontem.aboutme.repository;

import com.yigitcanyontem.aboutme.entities.Instagram;
import com.yigitcanyontem.aboutme.entities.Pinterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinterestRepository extends JpaRepository<Pinterest,Integer> {
    void deletePinterestByUsersid(Integer usersid);
}
