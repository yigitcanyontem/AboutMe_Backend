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
    @Value("${tmdb_api_key}")
    String tmdb_api_key;
    @Value("${last_fm_api_key}")
    String last_fm_api_key;
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
    TwitterService twitterService;
    @Autowired
    InstagramService instagramService;
    @Autowired
    LinkedinService linkedinService;
    @Autowired
    PinterestService pinterestService;
    @Autowired
    DescriptionService descriptionService;


    /////////////////////SHOWS////////////////////////
    @GetMapping("/tv/{showid}")
    public Show getSingleShowById(@PathVariable Integer showid) throws JsonProcessingException {
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
    @GetMapping("/search/tv/{title}")
    public List<Show> getShowSearchResults(@PathVariable String title) throws JsonProcessingException {
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


    /////////////////////MOVIES////////////////////////
    @GetMapping("/movie/{movieid}")
    public Movie getSingleMovieById(@PathVariable Integer movieid) throws JsonProcessingException {
        String url = "https://api.themoviedb.org/3/movie/"+movieid+"?api_key="+ tmdb_api_key;
        RestTemplate restTemplate = new RestTemplate();
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
        return movie;
    }
    @GetMapping("/search/movie/{title}")
    public List<Movie> getMovieSearchResults(@PathVariable String title) throws JsonProcessingException {
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
            movies.add(movie);
        }
        movies.sort(Comparator.comparing(Movie::getVote_count).reversed());
        return movies;
    }


    /////////////////////BOOKS////////////////////////
    @GetMapping("/search/book/{title}")
    public List<Book> getBookSearchResults(@PathVariable String title) throws JsonProcessingException {
        String url = "https://www.googleapis.com/books/v1/volumes?q="+title;
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode list = objectMapper.readTree(json).findValue("items");
        List<Book> books = new ArrayList<>();
        if (list != null){
            for (int i = 0; i < list.size(); i++){
                Book book = new Book();

                try {
                    book.setId(list.get(i).findValue("id").asText());
                    book.setTitle(list.get(i).findValue("title").asText());
                    book.setAuthors(list.get(i).findValue("authors").get(0).asText());
                    book.setDescription(list.get(i).findValue("description").asText());
                }catch (Exception e){
                    continue;
                }
                try {
                    book.setPageCount(list.get(i).findValue("pageCount").asInt());
                }catch (Exception e){
                    book.setPageCount(0);
                }

                book.setCover_url("https://books.google.com/books/publisher/content/images/frontcover/"+book.getId()+"?fife=w400-h600");
                books.add(book);
            }
        }
        return books;
    }
    @GetMapping("/book/{bookid}")
    public Book getSingleBookById(@PathVariable String bookid) throws JsonProcessingException {
        String url = "https://www.googleapis.com/books/v1/volumes/"+bookid;
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(url,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode list = objectMapper.readTree(json);


        Book book = new Book();
        book.setId(list.findValue("id").asText());
        book.setTitle(list.findValue("title").asText());
        book.setAuthors(list.findValue("authors").get(0).asText());
        book.setDescription(list.findValue("description").asText());
        book.setPageCount(list.findValue("pageCount").asInt());
        book.setCover_url("https://books.google.com/books/publisher/content/images/frontcover/"+book.getId()+"?fife=w400-h600");
        book.setWebReaderLink(list.findValue("webReaderLink").asText());
        return book;
    }


    /////////////////////ALBUMS////////////////////////
    @GetMapping("/search/album/{title}")
    public List<Album> getAlbumSearchResults(@PathVariable String title) throws JsonProcessingException {
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
    @GetMapping("/album/{mbid}")
    public Album getSingleAlbumById(@PathVariable String mbid) throws JsonProcessingException {
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


    /////////////////////COUNTRIES////////////////////////
    @GetMapping("/countries")
    public List<Country> getCountries(){
        return countryService.allCountries();
    }


    /////////////////////USERS////////////////////////
    @PostMapping("/user/create")
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
    @GetMapping("/user/{id}")
    public Users getCustomer(@PathVariable Integer id){
        return usersService.getUser(id);
    }
    @GetMapping("/user/socialmedia/{id}")
    public SocialMedia getCustomerSocialMedia(@PathVariable Integer id){
        SocialMedia socialMedia = new SocialMedia();

        socialMedia.setTwitteruser(twitterService.getTwitter(id).getTwitteruser());
        socialMedia.setInstagramuser(instagramService.getInstagram(id).getInstagramuser());
        socialMedia.setLinkedinuser(linkedinService.getLinkedin(id).getLinkedinuser());
        socialMedia.setPinterestuser(pinterestService.getPinterest(id).getPinterestuser());

        return socialMedia;
    }
    @GetMapping("/user/description/{id}")
    public Description getCustomerDescription(@PathVariable Integer id){
        return descriptionService.description(id);
    }
    @GetMapping("/search/user/{username}")
    public List<Users> getUserSearchResults(@PathVariable String username) {
        return usersService.usersList(username);
    }
    @GetMapping("/user/all/{id}")
    public UserOneModel getUserAll(@PathVariable Integer id) throws JsonProcessingException {
        UserOneModel userOneModel = new UserOneModel();
        userOneModel.setUsers(usersService.getUser(id));
        userOneModel.setSocialMedia(getCustomerSocialMedia(id));
        userOneModel.setDescription(descriptionService.description(id));
        userOneModel.setFavmovies(getFavMovies(id));
        userOneModel.setFavshows(getFavShows(id));
        userOneModel.setFavalbums(getFavAlbums(id));
        userOneModel.setFavbooks(getFavBooks(id));
        return userOneModel;
    }
    @PutMapping("/user/update")
    public String updateCustomer(@RequestBody AssignModel assignModel) {
        System.out.println(assignModel);
        int id = usersService.getUserByUsername(assignModel.getUsername());
        String description = assignModel.getDescription();
        String instagramuser = assignModel.getInstagramuser();
        String pinterestuser = assignModel.getPinterestuser();
        String linkedinuser = assignModel.getPinterestuser();
        String twitteruser = assignModel.getTwitteruser();
        if (!Objects.equals(description, "")){
            descriptionService.saveDescription(id,assignModel.getDescription());
        }
        if (!Objects.equals(instagramuser, "")){
            instagramService.saveInstagram(id,assignModel.getInstagramuser());
        }
        if (!Objects.equals(pinterestuser, "")){
            pinterestService.savePinterest(id,assignModel.getPinterestuser());
        }
        if (!Objects.equals(linkedinuser, "")){
            linkedinService.saveLinkedin(id,assignModel.getLinkedinuser());
        }
        if (!Objects.equals(twitteruser, "")){
            twitterService.saveTwitter(id,assignModel.getLinkedinuser());
        }
        return "Success";
    }
    @DeleteMapping("/user/delete/{usersid}")
    public String updateCustomer(@PathVariable Integer usersid) {
        System.out.println(usersid);

        instagramService.deleteUserInstagram(usersid);
        twitterService.deleteUserTwitter(usersid);
        pinterestService.deleteUserPinterest(usersid);
        linkedinService.deleteUserLinkedin(usersid);
        favShowsService.deleteUserFavShows(getCustomer(usersid));
        favMovieService.deleteUserFavMovie(getCustomer(usersid));
        favAlbumsService.deleteUserFavAlbums(getCustomer(usersid));
        favBooksService.deleteUserFavBooks(getCustomer(usersid));
        descriptionService.deleteUserDescription(usersid);
        usersService.deleteUser(usersid);
        return "Success";
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
    @GetMapping("/languages")
    public List<Language> getMovieLanguages(){
        String url = "https://api.themoviedb.org/3/configuration/languages?api_key="+ tmdb_api_key;
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

}
