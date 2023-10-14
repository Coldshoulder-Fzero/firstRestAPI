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

import Group9.Final.Atempt.Models.Cart;
import Group9.Final.Atempt.Repo.CartRepo;

@RestController
public class MainController {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private CartRepo cartRepo;

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

    // my feature

    /*@PostMapping(value = "/cartsave")
    public String saveCart(@RequestBody Cart cart) {
       cartRepo.save(cart);
       return "Saved Cart...";
    }*/

    @PostMapping(value = "/cart/{userid}/add/{id}")
    public String cartAdd(@PathVariable long userid, @PathVariable long id) {  
        // it works!!
        // however, it puts in book ids for books that might not exist
        if (!cartRepo.existsById(userid)) { // if user cart doesnt exist
            cartRepo.save(new Cart(userid, ""));
            // System.out.println("i have saved a new cart for this user");
        }

        Cart userCart = cartRepo.findById(userid).get(); // gets userCart
        String listOfBooksString =  userCart.getListOfBooks(); //get list of books

        ArrayList<Long> bookIds = new ArrayList<>();
        String[] bookIdStrings = listOfBooksString.split(","); // convert to string array

        try{
            for (String bookIdString : bookIdStrings) {
                if(!(bookIdString.isEmpty())){
                    long bookId = Long.parseLong(bookIdString);
                    // System.out.print("okayyyy");
                    bookIds.add(bookId);  // add book to arraylist
                }
            }
            // System.out.println("made progress");
            bookIds.add(id); // add latest book 

            // System.out.println("did that");
            String updatedListOfBooksString = String.join(",", bookIds.stream().map(Object::toString).toArray(String[]::new));
            userCart.setListOfBooks(updatedListOfBooksString); // put string back into databae
            cartRepo.save(userCart); // Save the updated Cart object

            return "Book saved in cart...";
        } catch(Exception e){
            return "couldnt do it sorry";
        }
    }

    @GetMapping(value = "/cart/{userid}")
    public ArrayList<String> getCart(@PathVariable long userid) {
        // this works as well !
        if (!cartRepo.existsById(userid)) { // if user cart doesnt exist
            cartRepo.save(new Cart(userid, ""));
        } 

        try{
            Cart userCart = cartRepo.findById(userid).get(); // gets userCart
            String listOfBooksString =  userCart.getListOfBooks(); //get list of books

            ArrayList<Long> bookIds = new ArrayList<>();
            String[] bookIdStrings = listOfBooksString.split(","); // convert to arraylist<long> with book ids
            for (String bookIdString : bookIdStrings) {
                long bookId = Long.parseLong(bookIdString);
                bookIds.add(bookId);  // add bookid to arraylist<long>
            }

            ArrayList<String> bookList = new ArrayList<>();
            for (Long id : bookIds) {
                Book book = bookRepo.findById(id).get();
                bookList.add(book.toString());
            }
            
            return bookList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Handle the exception or return an empty list as needed
        }
    }

    /* @GetMapping(value = "/cart/subtotal/{id}")
    public double getCartSubtotal(@PathVariable long userid) {
        if (!cartRepo.existsById(userid)) { // if user cart doesnt exist
            cartRepo.save(new Cart(userid, ""));
            return 0;
        } 

        Cart userCart = cartRepo.findById(userid).get(); // gets userCart
        String jsonList = userCart.getListOfBooks(); //get list of books in json
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Deserialize the JSON array into an ArrayList<Book>
            ArrayList<Book> bookList = objectMapper.readValue(jsonList, new TypeReference<ArrayList<Book>>() {});
            double sum = 0;
            for (Book book : bookList) {
                sum += book.getPrice();
            }
            return sum;

        } catch (Exception e) {
            e.printStackTrace();
            return 404; // Handle the exception or return an empty list as needed
        }
    }
    */


}