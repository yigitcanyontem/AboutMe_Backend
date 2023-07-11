package com.yigitcanyontem.aboutme.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yigitcanyontem.aboutme.controller.MovieRestController;
import com.yigitcanyontem.aboutme.favalbums.FavAlbums;
import com.yigitcanyontem.aboutme.favalbums.FavAlbumsService;
import com.yigitcanyontem.aboutme.favbooks.FavBooks;
import com.yigitcanyontem.aboutme.favbooks.FavBooksService;
import com.yigitcanyontem.aboutme.favmovies.FavMovie;
import com.yigitcanyontem.aboutme.favmovies.FavMovieService;
import com.yigitcanyontem.aboutme.favshows.FavShows;
import com.yigitcanyontem.aboutme.favshows.FavShowsService;
import com.yigitcanyontem.aboutme.model.*;
import com.yigitcanyontem.aboutme.users.descriptions.Description;
import com.yigitcanyontem.aboutme.users.descriptions.DescriptionService;
import com.yigitcanyontem.aboutme.users.socialmedia.SocialMediaDTO;
import com.yigitcanyontem.aboutme.users.socialmedia.SocialMediaService;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UsersController {
    private final UsersService usersService;
    private final DescriptionService descriptionService;
    private final SocialMediaService socialMediaService;
    private final FavBooksService favBooksService;
    private final FavShowsService favShowsService;
    private final FavAlbumsService favAlbumsService;
    private final FavMovieService favMovieService;
    private final MovieRestController movieRestController;

    public UsersController(UsersService usersService, DescriptionService descriptionService, SocialMediaService socialMediaService, FavBooksService favBooksService, FavShowsService favShowsService, FavAlbumsService favAlbumsService, FavMovieService favMovieService, MovieRestController movieRestController) {
        this.usersService = usersService;
        this.descriptionService = descriptionService;
        this.socialMediaService = socialMediaService;
        this.favBooksService = favBooksService;
        this.favShowsService = favShowsService;
        this.favAlbumsService = favAlbumsService;
        this.favMovieService = favMovieService;
        this.movieRestController = movieRestController;
    }

    @PostMapping("/create")
    public Integer newCustomer(@RequestBody UserRegisterModel user){
        usersService.newCustomer(user);
        return usersService.getUserByUsername(user.getUsername());
    }
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Integer id){
        return usersService.getUserModel(id);
    }

    public Users getCustomer(@PathVariable Integer id){
        return usersService.getUser(id);
    }
    @GetMapping("/socialmedia/{id}")
    public SocialMediaDTO getCustomerSocialMedia(@PathVariable Integer id){
        return socialMediaService.getSocialMedia(id);
    }
    @GetMapping("/description/{id}")
    public Description getCustomerDescription(@PathVariable Integer id){
        return descriptionService.description(id);
    }
    @PutMapping("/update")
    public String updateCustomer(@RequestBody AssignModel assignModel) {
        return usersService.updateCustomer(assignModel);
    }
    @DeleteMapping("/delete/{usersid}")
    public String deleteCustomer(@PathVariable Integer usersid) {
        return usersService.deleteCustomer(usersid);
    }
    @GetMapping("/favmovie/{id}")
    public List<Movie> getFavMovies(@PathVariable Integer id) throws JsonProcessingException {
        List<FavMovie> movieids = favMovieService.findByUserId(getCustomer(id));
        List<Movie> movies = new ArrayList<>();
        for (FavMovie x:movieids){
            movies.add(movieRestController.getSingleMovieById(x.getMovieid()));
        }
        return movies;
    }
    @GetMapping("/favshows/{id}")
    public List<Show> getFavShows(@PathVariable Integer id) throws JsonProcessingException {
        List<FavShows> showids = favShowsService.findByUserId(getCustomer(id));
        List<Show> shows = new ArrayList<>();
        for (FavShows x:showids){
            shows.add(movieRestController.getSingleShowById(x.getShowid()));
        }
        return shows;
    }
    @GetMapping("/favalbums/{id}")
    public List<Album> getFavAlbums(@PathVariable Integer id) throws JsonProcessingException {
        List<FavAlbums> albumids = favAlbumsService.findByUserId(getCustomer(id));
        List<Album> albums = new ArrayList<>();
        for (FavAlbums x:albumids){
            albums.add(movieRestController.getSingleAlbumById(x.getAlbumid()));
        }
        return albums;
    }
    @GetMapping("/favbooks/{id}")
    public List<Book> getFavBooks(@PathVariable Integer id) throws JsonProcessingException {
        List<FavBooks> bookids = favBooksService.findByUserId(getCustomer(id));
        List<Book> books = new ArrayList<>();
        for (FavBooks x:bookids){
            books.add(movieRestController.getSingleBookById(x.getBookid()));
        }
        return books;
    }
    @PutMapping("/favmovie/{usersid}/{movieid}")
    public String saveFavMovies(@PathVariable Integer usersid,@PathVariable Integer movieid)  {
        favMovieService.saveFavMovie(getCustomer(usersid),movieid);
        return "Success";
    }
    @PutMapping("/favshows/{usersid}/{showid}")
    public String saveFavShows(@PathVariable Integer usersid,@PathVariable Integer showid)  {
        favShowsService.saveFavShows(getCustomer(usersid),showid);
        return "Success";
    }
    @PutMapping("/favalbums/{usersid}/{albumid}")
    public String saveFavAlbums(@PathVariable Integer usersid,@PathVariable String albumid)  {
        favAlbumsService.saveFavAlbums(getCustomer(usersid),albumid);
        return "Success";
    }
    @PutMapping("/favbooks/{usersid}/{bookid}")
    public String saveFavBooks(@PathVariable Integer usersid,@PathVariable String bookid)  {
        favBooksService.saveFavBooks(getCustomer(usersid),bookid);
        return "Success";
    }
    @DeleteMapping("/favmovie/delete/{usersid}/{movieid}")
    public String deleteFavMovies(@PathVariable Integer usersid,@PathVariable Integer movieid)  {
        favMovieService.deleteUserFavMovieById(getCustomer(usersid),movieid);
        return "Success";
    }
    @DeleteMapping("/favshows/delete/{usersid}/{showid}")
    public String deleteFavShows(@PathVariable Integer usersid,@PathVariable Integer showid)  {
        favShowsService.deleteUserFavShowsById(getCustomer(usersid),showid);
        return "Success";
    }
    @DeleteMapping("/favalbums/delete/{usersid}/{albumid}")
    public String deleteFavAlbums(@PathVariable Integer usersid,@PathVariable String albumid)  {
        favAlbumsService.deleteUserFavAlbumsById(getCustomer(usersid),albumid);
        return "Success";
    }
    @DeleteMapping("/favbooks/delete/{usersid}/{bookid}")
    public String deleteFavBooks(@PathVariable Integer usersid,@PathVariable String bookid)  {
        favBooksService.deleteUserFavBooksById(getCustomer(usersid),bookid);
        return "Success";
    }
}
