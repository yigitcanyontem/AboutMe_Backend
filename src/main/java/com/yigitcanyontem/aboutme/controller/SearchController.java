package com.yigitcanyontem.aboutme.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yigitcanyontem.aboutme.model.Album;
import com.yigitcanyontem.aboutme.model.Book;
import com.yigitcanyontem.aboutme.model.Movie;
import com.yigitcanyontem.aboutme.model.Show;
import com.yigitcanyontem.aboutme.service.AlbumService;
import com.yigitcanyontem.aboutme.service.BookService;
import com.yigitcanyontem.aboutme.service.MovieService;
import com.yigitcanyontem.aboutme.service.ShowService;
import com.yigitcanyontem.aboutme.users.Users;
import com.yigitcanyontem.aboutme.users.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("search")
public class SearchController {
    private final UsersService usersService;
    private final ShowService showService;
    private final BookService bookService;
    private final AlbumService albumService;
    private final MovieService movieService;

    public SearchController(UsersService usersService, ShowService showService, BookService bookService, AlbumService albumService, MovieService movieService) {
        this.usersService = usersService;
        this.showService = showService;
        this.bookService = bookService;
        this.albumService = albumService;
        this.movieService = movieService;
    }

    @GetMapping("tv/{title}")
    public List<Show> getShowSearchResults(@PathVariable String title) throws JsonProcessingException {
        return showService.getShowSearchResults(title);
    }
    @GetMapping("/album/{title}")
    public List<Album> getAlbumSearchResults(@PathVariable String title) throws JsonProcessingException {
        return albumService.getAlbumSearchResults(title);
    }
    @GetMapping("/book/{title}")
    public List<Book> getBookSearchResults(@PathVariable String title) throws JsonProcessingException {
        return bookService.getBookSearchResults(title);
    }
    @GetMapping("/movie/{title}")
    public List<Movie> getMovieSearchResults(@PathVariable String title) throws JsonProcessingException {
        return movieService.getMovieSearchResults(title);
    }
    @GetMapping("/user/{username}")
    public List<Users> getUserSearchResults(@PathVariable String username) {
        return usersService.usersList(username);
    }
}
