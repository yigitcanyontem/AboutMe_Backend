package com.yigitcanyontem.aboutme.repository;

import com.yigitcanyontem.aboutme.entities.Linkedin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkedinRepository extends JpaRepository<Linkedin,Integer> {
    void deleteLinkedinByUsersid(Integer usersid);
}
