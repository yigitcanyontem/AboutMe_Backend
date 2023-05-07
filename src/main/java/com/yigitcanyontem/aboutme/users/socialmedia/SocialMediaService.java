package com.yigitcanyontem.aboutme.users.socialmedia;

import com.yigitcanyontem.aboutme.users.Users;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialMediaService {
    @Autowired
    public SocialMediaRepository socialMediaRepository;

    public Integer max(){
        return socialMediaRepository.maxId();
    }
    public SocialMedia getSocialMedia(Integer id) {
        return socialMediaRepository.findSocialMediaByUsersid(id);
    }

    public SocialMedia getSocialMediaRef(Users usersid) {
        return socialMediaRepository.getReferenceByUsersid(usersid);
    }
    @Transactional
    public void deleteSocialMedia(Users usersid){
        socialMediaRepository.deleteSocialMediaByUsersid(usersid);
    }
    @Transactional
    public void saveSocialMedia(SocialMedia socialMedia){
        socialMediaRepository.save(socialMedia);
    }

}
