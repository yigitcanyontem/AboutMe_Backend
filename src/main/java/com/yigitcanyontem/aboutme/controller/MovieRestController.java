package com.yigitcanyontem.aboutme.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.yigitcanyontem.aboutme.entities.Country;
import com.yigitcanyontem.aboutme.entities.UserModel;
import com.yigitcanyontem.aboutme.entities.Users;
import com.yigitcanyontem.aboutme.model.Language;
import com.yigitcanyontem.aboutme.model.Movie;
import com.yigitcanyontem.aboutme.model.Show;
import com.yigitcanyontem.aboutme.service.CountryService;
import com.yigitcanyontem.aboutme.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class MovieRestController {
    @Value("${api_key}")
    String api_key;
    @Autowired
    UsersService usersService;
    @Autowired
    CountryService countryService;


    @GetMapping("/tv/{showid}")
    public Show getSingleShowById(@PathVariable Integer showid) throws JsonProcessingException {
        String url = "https://api.themoviedb.org/3/tv/"+showid+"?api_key="+api_key;
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode list = objectMapper.readTree(json);

        assert json != null;
        String temp = Arrays.toString(json.split("\""));
        String s1 = temp.substring(temp.indexOf("homepage"),temp.indexOf("languages"));
        String s = s1.substring(s1.indexOf("id"),s1.indexOf("in_production"));
        Show show = new Show();
        show.setId(s.replaceAll("[^0-9]", ""));
        show.setAdult(list.findValue("adult").asBoolean());
        show.setBackdrop_path("https://image.tmdb.org/t/p/w500"+list.findValue("backdrop_path").asText());
        show.setOriginal_title(list.findValue("original_name").asText());
        show.setOverview(list.findValue("overview").asText());
        show.setPoster_path("https://image.tmdb.org/t/p/w500"+list.findValue("poster_path").asText());
        show.setFirst_air_date(list.findValue("first_air_date").asText());
        show.setOriginal_language(list.findValue("original_language").asText());
        show.setVote_count(list.findValue("vote_count").asInt());
        return show;
    }
    //Return Movies Based on Parameters
    @GetMapping("/search/tv/{title}/{year}")
    public List<Show> getShowSearchResults(@PathVariable String title, @PathVariable String year) throws JsonProcessingException {
        String url = "https://api.themoviedb.org/3/search/tv?api_key="+api_key+"&query="+title+"&first_air_date_year="+year;
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode list = objectMapper.readTree(json).findValue("results");
        List<Show> shows = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            Show show = new Show();

            show.setId(list.get(i).get("id").asText());
            show.setAdult(list.get(i).get("adult").asBoolean());
            show.setBackdrop_path("https://image.tmdb.org/t/p/w500"+list.get(i).get("backdrop_path").asText());
            show.setOriginal_title(list.get(i).get("original_name").asText());
            show.setOverview(list.get(i).get("overview").asText());
            show.setPoster_path("https://image.tmdb.org/t/p/w500"+list.get(i).get("poster_path").asText());
            show.setFirst_air_date(list.get(i).get("first_air_date").asText());
            show.setOriginal_language(list.get(i).get("original_language").asText());
            show.setVote_count(list.get(i).get("vote_count").asInt());
            shows.add(show);
        }
        shows.sort(Comparator.comparing(Show::getVote_count).reversed());
        return shows;
    }
    @GetMapping("/search/tv/{title}")
    public List<Show> getShowSearchResults(@PathVariable String title) throws JsonProcessingException {
        String url = "https://api.themoviedb.org/3/search/tv?api_key="+api_key+"&query="+title;
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode list = objectMapper.readTree(json).findValue("results");
        List<Show> shows = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            Show show = new Show();
            show.setId(list.get(i).get("id").asText());
            show.setAdult(list.get(i).get("adult").asBoolean());
            show.setBackdrop_path("https://image.tmdb.org/t/p/w500"+list.get(i).get("backdrop_path").asText());
            show.setOriginal_title(list.get(i).get("original_name").asText());
            show.setOverview(list.get(i).get("overview").asText());
            show.setPoster_path("https://image.tmdb.org/t/p/w500"+list.get(i).get("poster_path").asText());
            show.setFirst_air_date(list.get(i).get("first_air_date").asText());
            show.setOriginal_language(list.get(i).get("original_language").asText());
            show.setVote_count(list.get(i).get("vote_count").asInt());
            shows.add(show);
        }
        shows.sort(Comparator.comparing(Show::getVote_count).reversed());
        return shows;
    }
    @GetMapping("/movie/{movieid}")
    public Movie getSingleMovieById(@PathVariable Integer movieid) throws JsonProcessingException {
        String url = "https://api.themoviedb.org/3/movie/"+movieid+"?api_key="+api_key;
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode list = objectMapper.readTree(json);

        assert json != null;
        String temp = Arrays.toString(json.split("\""));
        String s = temp.substring(temp.indexOf("homepage"),temp.indexOf("imdb_id"));

        Movie movie = new Movie();
        movie.setId(s.replaceAll("[^0-9]", ""));
        movie.setAdult(list.findValue("adult").asBoolean());
        movie.setBackdrop_path("https://image.tmdb.org/t/p/w500"+list.findValue("backdrop_path").asText());
        movie.setOriginal_title(list.findValue("original_title").asText());
        movie.setOverview(list.findValue("overview").asText());
        movie.setPoster_path("https://image.tmdb.org/t/p/w500"+list.findValue("poster_path").asText());
        movie.setRelease_date(list.findValue("release_date").asText());
        movie.setLanguage(list.findValue("original_language").asText());
        movie.setVote_count(list.findValue("vote_count").asInt());
        return movie;
    }
    //Return Movies Based on Parameters
    @GetMapping("/search/movie/{title}/{year}")
    public List<Movie> getMovieSearchResults(@PathVariable String title, @PathVariable String year) throws JsonProcessingException {
        String url = "https://api.themoviedb.org/3/search/movie?api_key="+api_key+"&query="+title+"&primary_release_year="+year;
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode list = objectMapper.readTree(json).findValue("results");
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            Movie movie = new Movie();
            movie.setId(list.get(i).get("id").asText());
            movie.setAdult(list.get(i).get("adult").asBoolean());
            movie.setBackdrop_path("https://image.tmdb.org/t/p/w500"+list.get(i).get("backdrop_path").asText());
            movie.setOriginal_title(list.get(i).get("original_title").asText());
            movie.setOverview(list.get(i).get("overview").asText());
            movie.setPoster_path("https://image.tmdb.org/t/p/w500"+list.get(i).get("poster_path").asText());
            movie.setRelease_date(list.get(i).get("release_date").asText());
            movie.setLanguage(list.get(i).get("original_language").asText());
            movie.setVote_count(list.get(i).get("vote_count").asInt());
            movies.add(movie);
        }
        movies.sort(Comparator.comparing(Movie::getVote_count).reversed());
        return movies;
    }
    @GetMapping("/search/movie/{title}")
    public List<Movie> getMovieSearchResults(@PathVariable String title) throws JsonProcessingException {
        String url = "https://api.themoviedb.org/3/search/movie?api_key="+api_key+"&query="+title;
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode list = objectMapper.readTree(json).findValue("results");
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            Movie movie = new Movie();
            movie.setId(list.get(i).get("id").asText());
            movie.setAdult(list.get(i).get("adult").asBoolean());
            movie.setBackdrop_path("https://image.tmdb.org/t/p/w500"+list.get(i).get("backdrop_path").asText());
            movie.setOriginal_title(list.get(i).get("original_title").asText());
            movie.setOverview(list.get(i).get("overview").asText());
            movie.setPoster_path("https://image.tmdb.org/t/p/w500"+list.get(i).get("poster_path").asText());
            movie.setRelease_date(list.get(i).get("release_date").asText());
            movie.setLanguage(list.get(i).get("original_language").asText());
            movie.setVote_count(list.get(i).get("vote_count").asInt());
            movies.add(movie);
        }
        movies.sort(Comparator.comparing(Movie::getVote_count).reversed());
        return movies;
    }
    //Return All Countries
    @GetMapping("/countries")
    public List<Country> getCountries(){
        return countryService.allCountries();
    }
    //Create New User
    @PostMapping("/user")
    public Users newCustomer(@RequestBody UserModel user){
        Users users = new Users();
        users.setId(usersService.max()+1);
        users.setFirstName(user.getFirstName());
        users.setLastName(user.getLastName());
        users.setDate_of_birth(user.getDate_of_birth());
        users.setCountry(countryService.singleCountry(user.getCountry()));
        users.setEmail(user.getEmail());
        users.setUsername(user.getUsername());

        return usersService.addUser(users);
    }

/*
    @GetMapping("/languages")
    public List<Language> getSearchResults() throws JsonProcessingException {
        String url = "https://api.themoviedb.org/3/configuration/languages?api_key="+api_key;
        RestTemplate restTemplate = new RestTemplate();
        JsonNode list = restTemplate.getForObject(url, JsonNode.class);
        List<Language> languages = new ArrayList<>();
        for (int i = 0; i < Objects.requireNonNull(list).size(); i++){
            Language language = new Language();
            language.setIso_639_1(list.get(i).get("iso_639_1").asText());
            language.setEnglish_name(list.get(i).get("english_name").asText());
            language.setName(list.get(i).get("name").asText());
            languages.add(language);
        }
        languages.sort(Comparator.comparing(Language::getEnglish_name));
        return languages;
    }
 */
}