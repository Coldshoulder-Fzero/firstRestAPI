package Group9.Final.Atempt.Controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Group9.Final.Atempt.Models.Book;
import Group9.Final.Atempt.Models.Profile;
import Group9.Final.Atempt.Repo.BookRepo;
import Group9.Final.Atempt.Repo.ProfileRepo;
import Group9.Final.Atempt.Service.BookService;
import Group9.Final.Atempt.Service.ProfileService;

    
@ComponentScan("Group9.Final.Atempt.Repo")
    

@RestController
@RequestMapping(path="/geektext")
public class MainController {

    
    private final BookRepo bookRepo;
    private final BookService bookService;
    private final ProfileRepo profileRepo;
    private final ProfileService profileService; 


    @Autowired
    public MainController(BookRepo bookRepo, BookService bookService, ProfileRepo profileRepo, ProfileService profileService) {
        this.bookRepo = bookRepo;
        this.bookService = bookService;
        this.profileRepo = profileRepo;
        this.profileService = profileService;

    }

    
    
    @GetMapping(value = "/books")
    public List<Book> getBooks() {
        Iterable<Book> bookIterable = bookRepo.findAll();
        List<Book> bookList = new ArrayList<>();
        bookIterable.forEach(bookList::add); // Convert Iterable to List
        return bookList;
    }

    @PostMapping(value = "/save")
    public String saveBook (@RequestBody Book book) {
       bookRepo.save(book); 
       return "Saved...";
    }
    @PutMapping(value = "update/{id}")
    public String updateBook(@PathVariable long id, @RequestBody Book book){
        Book updateBook = bookRepo.findById(id).get();
        updateBook.setAuthor(book.getAuthor());
        updateBook.setName(book.getName());
        updateBook.setGenre(book.getGenre());
        updateBook.setSoldCopies(book.getSoldCopies());
        bookRepo.save(updateBook);
        return "Updated...";
    }

    @DeleteMapping(value ="/delete/{id}")
    public String deleteBook(@PathVariable long id){
        Book deleteBook = bookRepo.findById(id).get();
        bookRepo.delete(deleteBook);
        return "Delete user with the id: "+id;
    }
    
    /* 
    *  basic/old features ^
     * implementing new features
     * newer and more detailed featured v 
     */

     @GetMapping(value = "/genre")
     public List<String> getGenres() {
        Iterable<Book> bookIterable = bookRepo.findAll();
        List<String> genreList = new ArrayList<>();

        // Extract the genre from books and add them to the genreList
        bookIterable.forEach(book -> {
            String genre = book.getGenre();
            genreList.add(genre);
        });
    
            return genreList;
     }
     /*
      * creates a list called topSoldBooks that calls the springboot call
      bookRepo.find-top-10-ByOrder-BySoldCopiesDesc
      each section filters the search
      */
     @GetMapping(value = "/top-sold-books")
     public List<Book> getTopSoldBooks() {
        List<Book> topSoldBooks = bookRepo.findTop10ByOrderBySoldCopiesDesc();
        return topSoldBooks;
     }
     @GetMapping(value = "/books-by-genre")
     public List<Book> getBooksByGenre(@RequestParam String genre) {
         List<Book> booksByGenre = bookRepo.findByGenre(genre);
         return booksByGenre;
     }
     @GetMapping(value = "/books-by-rating")
     public ResponseEntity<List<Book>> getBooksByRating(@RequestParam int rating) {
         List<Book> booksByRating = bookService.getBooksByRating(rating);
         return ResponseEntity.ok(booksByRating);
     }

     @PutMapping(value = "/discount-books")
     public ResponseEntity<String> discountBooksByPublisher(@RequestParam String publisher, @RequestParam double discountPercent) {
       
            bookService.discountBooksByPublisher(publisher, discountPercent);
            return ResponseEntity.ok("Discount applied to books from publisher: " + publisher);
              
     }

    /**
     * PROFILE MAPPING
     */
    @PostMapping(value = "/create-profile")
public ResponseEntity<String> createProfile(@RequestBody Profile profile) {
            profileService.createProfile(profile.getUsername(), profile.getFullName(), profile.getEmail(), profile.getAddress(), profile.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body("Profile has been created");
        }

    @GetMapping(value = "/find-profile/{username}")
    public String findUser(@PathVariable String username){ //respone entity
        List<Profile> pl = profileService.findProfileByUsername(username);
        return pl.toString();
    }

    @PutMapping(value = "/update-profile-full-name/{username}-{full_name}")
    public String updateFullName(@PathVariable String username, @PathVariable String full_name){
        profileService.updateFullName(username, full_name);
        return "Successfully updated full name";
    }

    @PutMapping(value = "update-profile-username/{username}-{usrname}")
    public String updateUsername(@PathVariable String username, @PathVariable String usrname){
        profileService.updateUsername(username, usrname);
        return "Successfully updated username";
    }

    @PutMapping(value = "update-profile-home-address/{username}-{home_address}")
    public String updateHomeAddress(@PathVariable String username, @PathVariable String home_address){
        profileService.updateHomeAddress(username, home_address);
        return "Successfully updated home address";
    }

    @PutMapping(value = "update-profile-password/{username}-{password}")
    public String updatePassword(@PathVariable String username, @PathVariable String password){
        profileService.updatePassword(username, password);
        return "Successfully updated password";
    }

    @DeleteMapping(value = "delete-profile/{username}")
    public ResponseEntity<String> deleteProfile(@PathVariable String username) {
        boolean deleted = profileService.deleteProfile(username);
            if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found");
        }
    }
    
    
}