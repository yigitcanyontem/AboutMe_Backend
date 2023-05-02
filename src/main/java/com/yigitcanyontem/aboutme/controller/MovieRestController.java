package com.yigitcanyontem.aboutme.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.yigitcanyontem.aboutme.entities.*;
import com.yigitcanyontem.aboutme.model.*;
import com.yigitcanyontem.aboutme.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class MovieRestController {
    @Autowired
    UsersService usersService;
    @Autowired
    CountryService countryService;
    @Autowired
    FavMovieService favMovieService;
    @Autowired
    FavAlbumsService favAlbumsService;
    @Autowired
    FavShowsService favShowsService;
    @Autowired
    FavBooksService favBooksService;
    @Autowired
    DescriptionService descriptionService;
    @Autowired
    MovieService movieService;
    @Autowired
    ShowService showService;
    @Autowired
    BookService bookService;
    @Autowired
    AlbumService albumService;
    @Autowired
    PasswordsService passwordsService;
    /////////////////////SHOWS////////////////////////
    @GetMapping("/tv/{showid}")
    public Show getSingleShowById(@PathVariable Integer showid) throws JsonProcessingException {
        return showService.getSingleShowById(showid);
    }
    @GetMapping("/search/tv/{title}")
    public List<Show> getShowSearchResults(@PathVariable String title) throws JsonProcessingException {
        return showService.getShowSearchResults(title);
    }
    /////////////////////MOVIES////////////////////////
    @GetMapping("/movie/{movieid}")
    public Movie getSingleMovieById(@PathVariable Integer movieid) throws JsonProcessingException {
        return movieService.getSingleMovieById(movieid);
    }
    @GetMapping("/search/movie/{title}")
    public List<Movie> getMovieSearchResults(@PathVariable String title) throws JsonProcessingException {
        return movieService.getMovieSearchResults(title);
    }
    /////////////////////BOOKS////////////////////////
    @GetMapping("/search/book/{title}")
    public List<Book> getBookSearchResults(@PathVariable String title) throws JsonProcessingException {
        return bookService.getBookSearchResults(title);
    }
    @GetMapping("/book/{bookid}")
    public Book getSingleBookById(@PathVariable String bookid) throws JsonProcessingException {
       return bookService.getSingleBookById(bookid);
    }
    /////////////////////ALBUMS////////////////////////
    @GetMapping("/search/album/{title}")
    public List<Album> getAlbumSearchResults(@PathVariable String title) throws JsonProcessingException {
        return albumService.getAlbumSearchResults(title);
    }
    @GetMapping("/album/{mbid}")
    public Album getSingleAlbumById(@PathVariable String mbid) throws JsonProcessingException {
        return albumService.getSingleAlbumById(mbid);
    }



    /////////////////////USERS////////////////////////
    @PostMapping("/user/create")
    public String newCustomer(@RequestBody UserModel user){
         usersService.newCustomer(user);
         return "Success";
    }
    @GetMapping("/user/{id}")
    public Users getCustomer(@PathVariable Integer id){
        return usersService.getUser(id);
    }
    @GetMapping("/user/socialmedia/{id}")
    public SocialMedia getCustomerSocialMedia(@PathVariable Integer id){
        return usersService.getCustomerSocialMedia(id);
    }
    @GetMapping("/user/description/{id}")
    public Description getCustomerDescription(@PathVariable Integer id){
        return descriptionService.description(id);
    }
    @PutMapping("/user/login")
    public String getPassword(@RequestBody PasswordModel passwordModel) {
        return passwordsService.logIn(passwordModel);
    }
    @PutMapping("/user/update")
    public String updateCustomer(@RequestBody AssignModel assignModel) {
        return usersService.updateCustomer(assignModel);
    }
    @DeleteMapping("/user/delete/{usersid}")
    public String deleteCustomer(@PathVariable Integer usersid) {
        return usersService.deleteCustomer(usersid);
    }
    /////////////////////FavMovies////////////////////////
    @GetMapping("/user/favmovie/{id}")
    public List<Movie> getFavMovies(@PathVariable Integer id) throws JsonProcessingException {
        List<FavMovie> movieids = favMovieService.findByUserId(getCustomer(id));
        List<Movie> movies = new ArrayList<>();
        for (FavMovie x:movieids){
            movies.add(getSingleMovieById(x.getMovieid()));
        }
        return movies;
    }
    /////////////////////FavShows////////////////////////
    @GetMapping("/user/favshows/{id}")
    public List<Show> getFavShows(@PathVariable Integer id) throws JsonProcessingException {
        List<FavShows> showids = favShowsService.findByUserId(getCustomer(id));
        List<Show> shows = new ArrayList<>();
        for (FavShows x:showids){
            shows.add(getSingleShowById(x.getShowid()));
        }
        return shows;
    }
    /////////////////////FavAlbums////////////////////////
    @GetMapping("/user/favalbums/{id}")
    public List<Album> getFavAlbums(@PathVariable Integer id) throws JsonProcessingException {
        List<FavAlbums> albumids = favAlbumsService.findByUserId(getCustomer(id));
        List<Album> albums = new ArrayList<>();
        for (FavAlbums x:albumids){
            albums.add(getSingleAlbumById(x.getAlbumid()));
        }
        return albums;
    }
    /////////////////////FavBooks////////////////////////
    @GetMapping("/search/user/{username}")
    public List<Users> getUserSearchResults(@PathVariable String username) {
        return usersService.usersList(username);
    }
    @GetMapping("/user/favbooks/{id}")
    public List<Book> getFavBooks(@PathVariable Integer id) throws JsonProcessingException {
        List<FavBooks> bookids = favBooksService.findByUserId(getCustomer(id));
        List<Book> books = new ArrayList<>();
        for (FavBooks x:bookids){
            books.add(getSingleBookById(x.getBookid()));
        }
        return books;
    }
    @PutMapping("/user/favmovie/{usersid}/{movieid}")
    public String saveFavMovies(@PathVariable Integer usersid,@PathVariable Integer movieid)  {
        favMovieService.saveFavMovie(getCustomer(usersid),movieid);
        return "Success";
    }
    @PutMapping("/user/favshows/{usersid}/{showid}")
    public String saveFavShows(@PathVariable Integer usersid,@PathVariable Integer showid)  {
        favShowsService.saveFavShows(getCustomer(usersid),showid);
        return "Success";
    }
    @PutMapping("/user/favalbums/{usersid}/{albumid}")
    public String saveFavAlbums(@PathVariable Integer usersid,@PathVariable String albumid)  {
        favAlbumsService.saveFavAlbums(getCustomer(usersid),albumid);
        return "Success";
    }
    @PutMapping("/user/favbooks/{usersid}/{bookid}")
    public String saveFavBooks(@PathVariable Integer usersid,@PathVariable String bookid)  {
        favBooksService.saveFavBooks(getCustomer(usersid),bookid);
        return "Success";
    }
    @DeleteMapping("/user/favmovie/delete/{usersid}/{movieid}")
    public String deleteFavMovies(@PathVariable Integer usersid,@PathVariable Integer movieid)  {
        favMovieService.deleteUserFavMovieById(getCustomer(usersid),movieid);
        return "Success";
    }
    @DeleteMapping("/user/favshows/delete/{usersid}/{showid}")
    public String deleteFavShows(@PathVariable Integer usersid,@PathVariable Integer showid)  {
        favShowsService.deleteUserFavShowsById(getCustomer(usersid),showid);
        return "Success";
    }
    @DeleteMapping("/user/favalbums/delete/{usersid}/{albumid}")
    public String deleteFavAlbums(@PathVariable Integer usersid,@PathVariable String albumid)  {
        favAlbumsService.deleteUserFavAlbumsById(getCustomer(usersid),albumid);
        return "Success";
    }
    @DeleteMapping("/user/favbooks/delete/{usersid}/{bookid}")
    public String deleteFavBooks(@PathVariable Integer usersid,@PathVariable String bookid)  {
        favBooksService.deleteUserFavBooksById(getCustomer(usersid),bookid);
        return "Success";
    }
    /////////////////////Languages////////////////////////
    @GetMapping("/countries")
    public List<Country> getCountries(){
        return countryService.allCountries();
    }

}
