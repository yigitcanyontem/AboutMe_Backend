package com.yigitcanyontem.aboutme.users;

import com.yigitcanyontem.aboutme.country.CountryService;
import com.yigitcanyontem.aboutme.users.passwords.Passwords;
import com.yigitcanyontem.aboutme.users.socialmedia.SocialMedia;
import com.yigitcanyontem.aboutme.favalbums.FavAlbumsService;
import com.yigitcanyontem.aboutme.favbooks.FavBooksService;
import com.yigitcanyontem.aboutme.favmovies.FavMovieService;
import com.yigitcanyontem.aboutme.favshows.FavShowsService;
import com.yigitcanyontem.aboutme.model.AssignModel;
import com.yigitcanyontem.aboutme.users.descriptions.DescriptionService;
import com.yigitcanyontem.aboutme.users.passwords.PasswordsService;
import com.yigitcanyontem.aboutme.users.socialmedia.SocialMediaService;
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
    @Autowired
    SocialMediaService socialMediaService;
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
        SocialMedia socialMedia = socialMediaService.getSocialMediaRef(getUser(id));
        socialMediaService.deleteSocialMedia(getUser(id));
        socialMedia.setId(socialMediaService.max()+1);
        if (!Objects.equals(description, "")){
            descriptionService.saveDescription(id,description);
        }
        if (!Objects.equals(instagramuser, "")){
            socialMedia.setInstagram(instagramuser);
        }
        if (!Objects.equals(pinterestuser, "")){
            socialMedia.setPinterest(pinterestuser);
        }
        if (!Objects.equals(linkedinuser, "")){
            socialMedia.setLinkedin(linkedinuser);
        }
        if (!Objects.equals(twitteruser, "")){
            socialMedia.setTwitter(twitteruser);
        }
        socialMediaService.saveSocialMedia(socialMedia);
        return "Success";
    }
    @Transactional
    public String deleteCustomer(Integer usersid) {
        Users users = getUser(usersid);
        socialMediaService.deleteSocialMedia(users);
        favShowsService.deleteUserFavShows(users);
        favMovieService.deleteUserFavMovie(users);
        favAlbumsService.deleteUserFavAlbums(users);
        favBooksService.deleteUserFavBooks(users);
        descriptionService.deleteUserDescription(usersid);
        passwordsService.deletePasswordsByUsersid(users);
        usersRepository.deleteById(usersid);
        return "Success";
    }

    public SocialMedia getCustomerSocialMedia(Integer id){
        return socialMediaService.getSocialMedia(id);
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
        socialMediaService.saveSocialMedia(new SocialMedia(socialMediaService.max()+1, users,"","","",""));
    }
}
