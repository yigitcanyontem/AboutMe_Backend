package com.yigitcanyontem.aboutme.repository;

import com.yigitcanyontem.aboutme.entities.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DescriptionRepository extends JpaRepository<Description,Integer> {

    void deleteDescriptionByUsersid(Integer usersid);
}
