package Group9.Final.Atempt.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import Group9.Final.Atempt.Repo.BookRepo;

@RestController
public class MainController {

    @Autowired
    private BookRepo bookRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome";
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
            String genre = book.getGenres();
            genreList.add(genre);
        });
    
            return genreList;
     }
}