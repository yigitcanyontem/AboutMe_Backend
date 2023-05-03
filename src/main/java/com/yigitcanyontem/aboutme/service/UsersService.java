package com.yigitcanyontem.aboutme.service;

import com.yigitcanyontem.aboutme.entities.Passwords;
import com.yigitcanyontem.aboutme.entities.SocialMedia;
import com.yigitcanyontem.aboutme.entities.Users;
import com.yigitcanyontem.aboutme.model.AssignModel;
import com.yigitcanyontem.aboutme.model.UserModel;
import com.yigitcanyontem.aboutme.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    TwitterService twitterService;
    @Autowired
    InstagramService instagramService;
    @Autowired
    LinkedinService linkedinService;
    @Autowired
    PinterestService pinterestService;
    @Autowired
    DescriptionService descriptionService;
    @Autowired
    FavMovieService favMovieService;
    @Autowired
    FavAlbumsService favAlbumsService;
    @Autowired
    FavShowsService favShowsService;
    @Autowired
    FavBooksService favBooksService;
    @Autowired
    CountryService countryService;
    @Autowired
    PasswordsService passwordsService;
    public Users getUser(Integer id){
        return usersRepository.findById(id).orElseThrow();
    }
    public Integer max(){
        return usersRepository.maxUsersId();
    }
    public Integer getUserByUsername(String username){
        return usersRepository.getUsersByUsername(username).getId();
    }
    public List<Users> usersList(String username){
        return usersRepository.findByUsernameContaining(username);
    }


    public String updateCustomer(AssignModel assignModel) {
        int id = getUserByUsername(assignModel.getUsername());
        String description = assignModel.getDescription();
        String instagramuser = assignModel.getInstagramuser();
        String pinterestuser = assignModel.getPinterestuser();
        String linkedinuser = assignModel.getPinterestuser();
        String twitteruser = assignModel.getTwitteruser();
        if (!Objects.equals(description, "")){
            descriptionService.saveDescription(id,assignModel.getDescription());
        }
        if (!Objects.equals(instagramuser, "")){
            instagramService.saveInstagram(id,assignModel.getInstagramuser());
        }
        if (!Objects.equals(pinterestuser, "")){
            pinterestService.savePinterest(id,assignModel.getPinterestuser());
        }
        if (!Objects.equals(linkedinuser, "")){
            linkedinService.saveLinkedin(id,assignModel.getLinkedinuser());
        }
        if (!Objects.equals(twitteruser, "")){
            twitterService.saveTwitter(id,assignModel.getLinkedinuser());
        }
        return "Success";
    }
    @Transactional
    public String deleteCustomer(Integer usersid) {
        instagramService.deleteUserInstagram(usersid);
        twitterService.deleteUserTwitter(usersid);
        pinterestService.deleteUserPinterest(usersid);
        linkedinService.deleteUserLinkedin(usersid);
        favShowsService.deleteUserFavShows(getUser(usersid));
        favMovieService.deleteUserFavMovie(getUser(usersid));
        favAlbumsService.deleteUserFavAlbums(getUser(usersid));
        favBooksService.deleteUserFavBooks(getUser(usersid));
        descriptionService.deleteUserDescription(usersid);
        passwordsService.deletePasswordsByUsersid(getUser(usersid));
        usersRepository.deleteById(usersid);
        return "Success";
    }
    public SocialMedia getCustomerSocialMedia(Integer id){
        SocialMedia socialMedia = new SocialMedia();
        socialMedia.setTwitteruser(twitterService.getTwitter(id).getTwitteruser());
        socialMedia.setInstagramuser(instagramService.getInstagram(id).getInstagramuser());
        socialMedia.setLinkedinuser(linkedinService.getLinkedin(id).getLinkedinuser());
        socialMedia.setPinterestuser(pinterestService.getPinterest(id).getPinterestuser());

        return socialMedia;
    }

    public void newCustomer( UserModel user){
        Users users = new Users();
        Passwords passwords = new Passwords();
        users.setId(max()+1);
        users.setFirstName(user.getFirstName());
        users.setLastName(user.getLastName());
        users.setDate_of_birth(user.getDate_of_birth());
        users.setCountry(countryService.singleCountry(user.getCountry()));
        users.setEmail(user.getEmail());
        users.setUsername(user.getUsername());
        usersRepository.save(users);
        passwords.setId(passwordsService.max()+1);
        passwords.setUsersid(users);
        passwords.setPassword(user.getPassword());
        passwordsService.savePassword(passwords);
    }
}
