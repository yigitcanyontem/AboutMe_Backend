package com.yigitcanyontem.aboutme.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yigitcanyontem.aboutme.model.Show;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class ShowService {
    @Value("${tmdb_api_key}")
    String tmdb_api_key;
    public Show getSingleShowById(Integer showid) throws JsonProcessingException {
        String url = "https://api.themoviedb.org/3/tv/"+showid+"?api_key="+ tmdb_api_key;
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
        try {
            show.setImdb_url(list.findValue("homepage").asText());
        }catch (Exception e){
            show.setImdb_url("");
        }
        show.setVote_count(list.findValue("vote_count").asInt());
        return show;
    }
    public List<Show> getShowSearchResults(String title) throws JsonProcessingException {
        String url = "https://api.themoviedb.org/3/search/tv?api_key="+ tmdb_api_key +"&query="+title;
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode list = objectMapper.readTree(json).findValue("results");
        List<Show> shows = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).get("poster_path").asText().equals("null")){
                continue;
            }
            Show show = new Show();
            show.setId(list.get(i).get("id").asText());
            show.setAdult(list.get(i).get("adult").asBoolean());
            show.setBackdrop_path("https://image.tmdb.org/t/p/w500"+list.get(i).get("backdrop_path").asText());
            show.setOriginal_title(list.get(i).get("original_name").asText());
            show.setOverview(list.get(i).get("overview").asText());
            show.setPoster_path("https://image.tmdb.org/t/p/w500"+list.get(i).get("poster_path").asText());
            show.setFirst_air_date(list.get(i).get("first_air_date").asText());
            show.setOriginal_language(list.get(i).get("original_language").asText());
            show.setImdb_url("");
            show.setVote_count(list.get(i).get("vote_count").asInt());
            shows.add(show);
        }
        shows.sort(Comparator.comparing(Show::getVote_count).reversed());
        return shows;
    }
}
