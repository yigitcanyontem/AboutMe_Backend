package com.yigitcanyontem.aboutme.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yigitcanyontem.aboutme.country.Country;
import com.yigitcanyontem.aboutme.country.CountryService;
import com.yigitcanyontem.aboutme.model.*;
import com.yigitcanyontem.aboutme.service.*;
import com.yigitcanyontem.aboutme.users.UsersService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class MovieRestController {
    private final UsersService usersService;
    private final ShowService showService;
    private final BookService bookService;
    private final AlbumService albumService;
    private final MovieService movieService;
    private final CountryService countryService;

    public MovieRestController(UsersService usersService, ShowService showService, BookService bookService, AlbumService albumService, MovieService movieService, CountryService countryService) {
        this.usersService = usersService;
        this.showService = showService;
        this.bookService = bookService;
        this.albumService = albumService;
        this.movieService = movieService;
        this.countryService = countryService;
    }
    @GetMapping("/tv/{showid}")
    public Show getSingleShowById(@PathVariable Integer showid) throws JsonProcessingException {
        return showService.getSingleShowById(showid);
    }
    @GetMapping("/movie/{movieid}")
    public Movie getSingleMovieById(@PathVariable Integer movieid) throws JsonProcessingException {
        return movieService.getSingleMovieById(movieid);
    }
    @GetMapping("/book/{bookid}")
    public Book getSingleBookById(@PathVariable String bookid) throws JsonProcessingException {
       return bookService.getSingleBookById(bookid);
    }
    @GetMapping("/album/{mbid}")
    public Album getSingleAlbumById(@PathVariable String mbid) throws JsonProcessingException {
        return albumService.getSingleAlbumById(mbid);
    }
    @GetMapping("/countries")
    public List<Country> getCountries(){
        return countryService.allCountries();
    }

}
