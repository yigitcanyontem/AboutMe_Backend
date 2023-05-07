package com.yigitcanyontem.aboutme.users.socialmedia;

import com.yigitcanyontem.aboutme.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialMediaRepository extends JpaRepository<SocialMedia, Integer> {
    @Query("SELECT max(a.id) FROM SocialMedia a")
    Integer maxId();
    @Query(value = "SELECT * FROM SocialMedia b WHERE b.id =?1",nativeQuery = true)
    SocialMedia findSocialMediaByUsersid(Integer usersid);

    SocialMedia getReferenceByUsersid(Users usersid);
    void deleteSocialMediaByUsersid(Users usersid);
}
