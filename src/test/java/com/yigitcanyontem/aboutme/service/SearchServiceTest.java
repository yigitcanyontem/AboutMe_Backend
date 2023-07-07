package com.yigitcanyontem.aboutme.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yigitcanyontem.aboutme.model.Album;
import com.yigitcanyontem.aboutme.model.Book;
import com.yigitcanyontem.aboutme.model.Movie;
import com.yigitcanyontem.aboutme.model.Show;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)    // @RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
class SearchServiceTest {
    @Value("${tmdb_api_key}")
    String tmdb_api_key;
    @Value("${last_fm_api_key}")
    String last_fm_api_key;
    @Value("${last_fm_url}")
    String last_fm_url;

    private AlbumService albumService;
    private MovieService movieService;
    private BookService bookService;
    private ShowService showService;
    private AutoCloseable autoCloseable;
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        albumService = new AlbumService();
        albumService.last_fm_url = last_fm_url;
        albumService.last_fm_api_key = last_fm_api_key;

        movieService = new MovieService();
        movieService.tmdb_api_key = tmdb_api_key;

        bookService = new BookService();

        showService = new ShowService();
        showService.tmdb_api_key = tmdb_api_key;
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }
    @Test
    void getSingleShowById() throws JsonProcessingException {
        assertThat(showService.getSingleShowById(50).getOriginal_title()).isEqualTo("Pacific Blue");
    }

    @Test
    void getSingleAlbumById() throws JsonProcessingException {
        assertThat(albumService.getSingleAlbumById("c559efc2-f734-41ae-93bd-2d78414e0356").getName()).isEqualTo("Believe");
    }

    @Test
    void getSingleMovieById() throws JsonProcessingException {
        assertThat(movieService.getSingleMovieById(150).getOriginal_title()).isEqualTo("48 Hrs.");
    }

    @Test
    void getSingleBookById() throws JsonProcessingException {
        assertThat(bookService.getSingleBookById("vrpPEAAAQBAJ").getTitle()).isEqualTo("Normal People");
    }
}