package com.yigitcanyontem.aboutme.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yigitcanyontem.aboutme.exceptions.SearchNotFoundException;
import com.yigitcanyontem.aboutme.favbooks.FavBooksRepository;
import com.yigitcanyontem.aboutme.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private FavBooksRepository favBooksRepository;

    public List<Book> getBookSearchResults(String title) throws JsonProcessingException {
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
                book.setFavorite_count(favBooksRepository.countFavBooksByBookid(book.getId()));
                books.add(book);
            }
        }
        if (books.size() == 0){
            throw new SearchNotFoundException("No Book Found");
        }
        return books;
    }
    public Book getSingleBookById(String bookid) throws JsonProcessingException {
        String url = "https://www.googleapis.com/books/v1/volumes/"+bookid;
        RestTemplate restTemplate = new RestTemplate();
        try {
            String json = restTemplate.getForObject(url,String.class);
        }catch (Exception ignored){
            throw new SearchNotFoundException("Book with id " + bookid + " doesn't exist");
        }
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
        book.setFavorite_count(favBooksRepository.countFavBooksByBookid(bookid));
        return book;
    }

}
