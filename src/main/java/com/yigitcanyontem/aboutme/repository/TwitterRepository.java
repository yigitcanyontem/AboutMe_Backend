package com.yigitcanyontem.aboutme.repository;

import com.yigitcanyontem.aboutme.entities.Twitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterRepository extends JpaRepository<Twitter,Integer> {
    void deleteTwitterByUsersid(Integer usersid);
}
