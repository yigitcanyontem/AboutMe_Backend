package com.yigitcanyontem.aboutme.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yigitcanyontem.aboutme.model.Album;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AlbumService {
    @Value("${last_fm_api_key}")
    String last_fm_api_key;
    public List<Album> getAlbumSearchResults(String title) throws JsonProcessingException {
        String url = "http://ws.audioscrobbler.com/2.0/?method=album.search&album="+title+"&api_key="+last_fm_api_key+"&format=json";
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode list = objectMapper.readTree(json).findValue("album");

        List<Album> albums = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).get("mbid").asText().equals("")){
                continue;
            }
            Album album = new Album();
            JsonNode images = list.get(i).findValue("image");
            album.setMbid(list.get(i).findValue("mbid").asText());
            album.setName(list.get(i).findValue("name").asText());
            album.setArtist(list.get(i).findValue("artist").asText());
            album.setUrl(list.get(i).findValue("url").asText());
            album.setImage(images.get(3).findValue("#text").asText());
            albums.add(album);
        }
        return albums;
    }
    public Album getSingleAlbumById(String mbid) throws JsonProcessingException {
        String url = "https://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key="+last_fm_api_key+"&mbid="+mbid+"&format=json";
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode list = objectMapper.readTree(json).findValue("album");

        assert json != null;
        String temp = Arrays.toString(json.split("\""));
        String s;
        try {
            s = temp.substring(temp.indexOf("tag"),temp.indexOf("image"));
        }catch (Exception e){
            s = temp.substring(temp.indexOf("tag"),temp.indexOf("playcount"));
        }
        s = s.substring(s.lastIndexOf("name"));
        s = s.replaceAll("[^a-zA-Z\\s]", "");
        Album album = new Album();
        JsonNode images = list.findValue("image");
        album.setMbid(mbid);
        album.setName(s.substring(s.indexOf("name")+4).trim());
        album.setArtist(list.findValue("artist").asText());
        album.setUrl("https://www.last.fm/music/"+album.getArtist()+"/"+album.getName());
        album.setImage(images.get(3).findValue("#text").asText());
        return album;
    }
}
