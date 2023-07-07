package com.yigitcanyontem.aboutme.users.socialmedia;

import com.yigitcanyontem.aboutme.users.Users;
import jakarta.transaction.Transactional;
import org.springframework.aop.AopInvocationException;
import org.springframework.stereotype.Service;

@Service
public class SocialMediaService {
    private final SocialMediaRepository socialMediaRepository;

    public SocialMediaService(SocialMediaRepository socialMediaRepository) {
        this.socialMediaRepository = socialMediaRepository;
    }

    public Integer max(){
        try {
            return socialMediaRepository.maxId();
        }catch (AopInvocationException e){
            return 0;
        }
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
