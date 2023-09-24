/*
 
package Group9.Final.Atempt.Controller.BookController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Group9.Final.Atempt.Models.Book;
import Group9.Final.Atempt.Repo.bookrepo;

@Controller
@RequestMapping(path="/demo") // This means URL's start with /demo
public class BookController {
    @Autowired //This means to get the bean calles userRepository
    private bookrepo BookRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewBook (@RequestParam String name, @RequestParam String author) {
        //@ResponseBody means the returned String is the response, not a view name

        //@RequestParam means it is a parameter from the Get or POST request

        Book b = new Book();
        b.setName(name);
        b.setAuthor(author);
        BookRepository.save(b);
        return "Saved";

    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Book> getAllBooks() {
        // This returns a JSON or XML with the Books
        return BookRepository.findAll();
    }
    
}
*/