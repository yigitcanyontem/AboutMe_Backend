package com.yigitcanyontem.aboutme.favbooks;

import com.yigitcanyontem.aboutme.exceptions.SearchNotFoundException;
import com.yigitcanyontem.aboutme.users.Users;
import com.yigitcanyontem.aboutme.users.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavBooksService {
    private final FavBooksRepository favBooksRepository;
    private final UsersRepository usersRepository;

    public FavBooksService(FavBooksRepository favBooksRepository, UsersRepository usersRepository) {
        this.favBooksRepository = favBooksRepository;
        this.usersRepository = usersRepository;
    }

    public List<FavBooks> findByUserId(Users usersid){
        if (!favBooksRepository.existsFavBooksByUsersid(usersid)){
            throw new SearchNotFoundException("User Not Found");
        }
        return favBooksRepository.findByUsersid(usersid);
    }

    @Transactional
    public void deleteUserFavBooks(Users usersid){
        if (!favBooksRepository.existsFavBooksByUsersid(usersid)){
            throw new SearchNotFoundException("User Not Found");
        }
        favBooksRepository.deleteFavBooksByUsersid(usersid);
    }
    @Transactional
    public void deleteUserFavBooksById(Users usersid, String bookid){
        if (!favBooksRepository.existsFavBooksByUsersid(usersid)){
            throw new SearchNotFoundException("User Not Found");
        }
        if (!favBooksRepository.existsFavBooksByUsersidAndBookid(usersid,bookid)){
            throw new SearchNotFoundException("Book Not Favorited");
        }
        favBooksRepository.deleteFavBooksByUsersidAndBookid(usersid,bookid);
    }
    public void saveFavBooks(Users usersid, String favbooksid){
        if (!usersRepository.existsById(usersid.getId())){
            throw new SearchNotFoundException("User Not Found");
        }
        if (favBooksRepository.countFavBooksByUsersid(usersid) == 6){
            throw new SearchNotFoundException("You can favorite maximum 6 books please remove one");
        }
        if (favBooksRepository.existsFavBooksByUsersidAndBookid(usersid,favbooksid)){
            throw new SearchNotFoundException("Already Favorited");
        }
        favBooksRepository.save(new FavBooks(usersid,favbooksid));
    }
}
