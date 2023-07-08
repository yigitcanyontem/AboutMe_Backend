package com.yigitcanyontem.aboutme.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yigitcanyontem.aboutme.exceptions.SearchNotFoundException;
import com.yigitcanyontem.aboutme.favmovies.FavMovieRepository;
import com.yigitcanyontem.aboutme.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class MovieService {
    @Value("${tmdb_api_key}")
    String tmdb_api_key;

    @Autowired
    private FavMovieRepository favMovieRepository;


    public Movie getSingleMovieById(Integer movieid) throws JsonProcessingException {
        String url = "https://api.themoviedb.org/3/movie/"+movieid+"?api_key="+ tmdb_api_key;
        RestTemplate restTemplate = new RestTemplate();
        try {
            String json = restTemplate.getForObject(url,String.class);
        }catch (Exception ignored){
            throw new SearchNotFoundException("Movie with id " + movieid + " doesn't exist");
        }
        String json = restTemplate.getForObject(url,String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode list = objectMapper.readTree(json);
        assert json != null;
        String temp = Arrays.toString(json.split("\""));
        String s = temp.substring(temp.indexOf("homepage"),temp.indexOf("imdb_id"));

        String s1 = temp.substring(temp.indexOf("popularity"),temp.indexOf("production_companies"));
        s1 = s1.substring(s1.indexOf("poster_path")+14).replaceAll(",","");
        Movie movie = new Movie();
        movie.setId(s.replaceAll("[^0-9]", ""));
        movie.setAdult(list.findValue("adult").asBoolean());
        movie.setBackdrop_path("https://image.tmdb.org/t/p/w500"+list.findValue("backdrop_path").asText());
        movie.setOriginal_title(list.findValue("original_title").asText());
        movie.setOverview(list.findValue("overview").asText());
        movie.setPoster_path("https://image.tmdb.org/t/p/w500" + s1.trim());
        movie.setRelease_date(list.findValue("release_date").asText());
        movie.setLanguage(list.findValue("original_language").asText());
        movie.setImdb_url("https://www.imdb.com/title/"+list.findValue("imdb_id").asText());
        movie.setVote_count(list.findValue("vote_count").asInt());
        movie.setFavorite_count(favMovieRepository.countFavMoviesByMovieid(movieid));
        return movie;
    }
    public List<Movie> getMovieSearchResults(String title) throws JsonProcessingException {
        String url = "https://api.themoviedb.org/3/search/movie?api_key="+ tmdb_api_key +"&query="+title;
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode list = objectMapper.readTree(json).findValue("results");
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            Movie movie = new Movie();
            if(list.get(i).get("poster_path").asText().equals("null")){
                continue;
            }
            movie.setId(list.get(i).get("id").asText());
            movie.setAdult(list.get(i).get("adult").asBoolean());
            movie.setBackdrop_path("https://image.tmdb.org/t/p/w500"+list.get(i).get("backdrop_path").asText());
            movie.setOriginal_title(list.get(i).get("original_title").asText());
            movie.setOverview(list.get(i).get("overview").asText());
            movie.setPoster_path("https://image.tmdb.org/t/p/w500"+list.get(i).get("poster_path").asText());
            movie.setRelease_date(list.get(i).get("release_date").asText());
            movie.setLanguage(list.get(i).get("original_language").asText());
            movie.setImdb_url("");
            movie.setVote_count(list.get(i).get("vote_count").asInt());
            movie.setFavorite_count(favMovieRepository.countFavMoviesByMovieid(list.get(i).get("id").asInt()));
            movies.add(movie);
        }
        movies.sort(Comparator.comparing(Movie::getVote_count).reversed());
        if (movies.size() == 0){
            throw new SearchNotFoundException("No Movie Found");
        }
        return movies;
    }
}
