package com.yigitcanyontem.aboutme.repository;

import com.yigitcanyontem.aboutme.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    @Query("SELECT max(a.id) FROM Users a")
    int maxUsersId();
}
