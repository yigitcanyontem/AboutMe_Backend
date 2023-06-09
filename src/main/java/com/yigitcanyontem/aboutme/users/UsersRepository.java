package com.yigitcanyontem.aboutme.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    @Query("SELECT max(a.id) FROM Users a")
    int maxUsersId();
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Users getUsersByUsername(String username);
    List<Users> findByUsernameContaining(String username);
}
