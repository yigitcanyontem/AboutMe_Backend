package com.yigitcanyontem.aboutme.users.passwords;

import com.yigitcanyontem.aboutme.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordsRepository extends JpaRepository<Passwords,Integer> {
    Passwords getPasswordsByUsersid(Users usersid);
    @Query("SELECT max(a.id) FROM Passwords a")
    int maxId();

    void deletePasswordsByUsersid(Users usersid);
}
