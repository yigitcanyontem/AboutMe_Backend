package com.yigitcanyontem.aboutme.users;

import com.yigitcanyontem.aboutme.country.CountryService;
import com.yigitcanyontem.aboutme.exceptions.SearchNotFoundException;
import com.yigitcanyontem.aboutme.users.socialmedia.SocialMedia;
import com.yigitcanyontem.aboutme.favalbums.FavAlbumsService;
import com.yigitcanyontem.aboutme.favbooks.FavBooksService;
import com.yigitcanyontem.aboutme.favmovies.FavMovieService;
import com.yigitcanyontem.aboutme.favshows.FavShowsService;
import com.yigitcanyontem.aboutme.model.AssignModel;
import com.yigitcanyontem.aboutme.users.descriptions.DescriptionService;
import com.yigitcanyontem.aboutme.users.socialmedia.SocialMediaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final DescriptionService descriptionService;
    private final FavMovieService favMovieService;
    private final FavAlbumsService favAlbumsService;
    private final FavShowsService favShowsService;
    private final FavBooksService favBooksService;
    private final CountryService countryService;
    private final SocialMediaService socialMediaService;

    public UsersService(UsersRepository usersRepository, DescriptionService descriptionService
            , FavMovieService favMovieService, FavAlbumsService favAlbumsService, FavShowsService favShowsService,
                        FavBooksService favBooksService, CountryService countryService,
                        SocialMediaService socialMediaService) {
        this.usersRepository = usersRepository;
        this.descriptionService = descriptionService;
        this.favMovieService = favMovieService;
        this.favAlbumsService = favAlbumsService;
        this.favShowsService = favShowsService;
        this.favBooksService = favBooksService;
        this.countryService = countryService;
        this.socialMediaService = socialMediaService;
    }

    public Users getUser(Integer id){
        return usersRepository.findById(id).orElseThrow(
                () -> new SearchNotFoundException(
                        "User with id " + id + " not found"
                )
        );
    }
    public UserDTO getUserModel(Integer id){
        Users users = usersRepository.findById(id).orElseThrow(
                () -> new SearchNotFoundException(
                        "User with id " + id + " not found"
                )
        );
        return new UserDTO(
                users.getId(),
                users.getFirstName(),
                users.getLastName(),
                users.getDate_of_birth(),
                users.getCountry(),
                users.getEmail(),
                users.getUsername()
        );
    }
    public Integer getUserByUsername(String username){
        return usersRepository.getUsersByUsername(username).getId();
    }

    public Users getUsersObjectByUsername(String username){
        return usersRepository.getUsersByUsername(username);
    }
    public List<Users> usersList(String username){
        List<Users> list = usersRepository.findByUsernameContaining(username);
        if (list.size() == 0){
            throw new SearchNotFoundException("No Users Found");
        }
        return usersRepository.findByUsernameContaining(username);
    }


    public String updateCustomer(AssignModel assignModel) {
        try {
            int id = getUserByUsername(assignModel.getUsername());
        }catch (NullPointerException e){
            throw new SearchNotFoundException("User with username " + assignModel.getUsername() + " not found");
        }

        int id = getUserByUsername(assignModel.getUsername());
        String description = assignModel.getDescription();
        String instagramuser = assignModel.getInstagramuser();
        String pinterestuser = assignModel.getPinterestuser();
        String linkedinuser = assignModel.getPinterestuser();
        String twitteruser = assignModel.getTwitteruser();
        SocialMedia socialMedia = socialMediaService.getSocialMediaRef(getUser(id));
        socialMediaService.deleteSocialMedia(getUser(id));
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
        if (!usersRepository.existsById(usersid)){
            throw new SearchNotFoundException("User with id "+ usersid + " not found");
        }
        Users users = getUser(usersid);
        socialMediaService.deleteSocialMedia(users);
        favShowsService.deleteUserFavShows(users);
        favMovieService.deleteUserFavMovie(users);
        favAlbumsService.deleteUserFavAlbums(users);
        favBooksService.deleteUserFavBooks(users);
        descriptionService.deleteUserDescription(usersid);
        usersRepository.deleteById(usersid);
        return "Success";
    }

    public void newCustomer(UserRegisterModel user){
        if (usersRepository.existsByUsername(user.getUsername())){
            throw new SearchNotFoundException("Username taken");
        }
        if (usersRepository.existsByEmail(user.getEmail())){
            throw new SearchNotFoundException("Email taken");
        }
        Users users = new Users();
        users.setFirstName(user.getFirstName());
        users.setLastName(user.getLastName());
        users.setDate_of_birth(user.getDate_of_birth());
        users.setCountry(countryService.singleCountry(user.getCountry()));
        users.setEmail(user.getEmail());
        users.setUsername(user.getUsername());
        users.setPassword(user.getPassword());
        usersRepository.save(users);
        Users users1 = usersRepository.getUsersByUsername(user.getUsername());
        socialMediaService.saveSocialMedia(new SocialMedia(users1,"","","",""));
        descriptionService.saveDescription(users1.getId(),"");
        favAlbumsService.saveFavAlbums(users1,"bc5a0db2-a123-4a29-bb75-de01c52da293");
        favBooksService.saveFavBooks(users1,"vrpPEAAAQBAJ");
        favMovieService.saveFavMovie(users1,550);
        favShowsService.saveFavShows(users1,100);
    }
}
