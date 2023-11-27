package Group9.Final.Atempt.Controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import Group9.Final.Atempt.Models.Wishlist;
import Group9.Final.Atempt.Repo.WishlistRepo;
import Group9.Final.Atempt.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
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
import Group9.Final.Atempt.Repo.BookRepo;
import Group9.Final.Atempt.Service.BookService;

import Group9.Final.Atempt.Models.Cart;
import Group9.Final.Atempt.Repo.CartRepo;

@RestController
public class MainController {


    private final BookRepo bookRepo;
    private final BookService bookService;
    private CartRepo cartRepo;

    private WishlistService wishlistService;
    private WishlistRepo wishlistRepo;

    @Autowired
    public MainController(BookRepo bookRepo, BookService bookService, CartRepo cartRepo) {
        this.bookRepo = bookRepo;
        this.bookService = bookService;
        this.cartRepo = cartRepo;
    }

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
    public String saveBook(@RequestBody Book book) {
        bookRepo.save(book);
        return "Saved...";
    }

    @PutMapping(value = "update/{id}")
    public String updateBook(@PathVariable long id, @RequestBody Book book) {
        Book updateBook = bookRepo.findById(id).get();
        updateBook.setAuthor(book.getAuthor());
        updateBook.setName(book.getName());
        updateBook.setGenre(book.getGenre());
        updateBook.setSoldCopies(book.getSoldCopies());
        updateBook.setPrice(book.getPrice());
        updateBook.setDiscountPercent(book.getDiscountPercent());
        bookRepo.save(updateBook);
        return "Updated...";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteBook(@PathVariable long id) {
        Book deleteBook = bookRepo.findById(id).get();
        bookRepo.delete(deleteBook);
        return "Delete user with the id: " + id;
    }

    @PostMapping(value = "/cart/{userid}/add/{id}")
    public String cartAdd(@PathVariable long userid, @PathVariable long id) {
        if (!cartRepo.existsById(userid)) { // if user cart doesnt exist
            cartRepo.save(new Cart(userid, ""));
            // System.out.println("i have saved a new cart for this user");
        }

        Cart userCart = cartRepo.findById(userid).get(); // gets userCart
        String listOfBooksString = userCart.getListOfBooks(); //get list of books

        ArrayList<Long> bookIds = new ArrayList<>();
        String[] bookIdStrings = listOfBooksString.split(","); // convert to string array

        try {
            for (String bookIdString : bookIdStrings) {
                if (!(bookIdString.isEmpty())) {
                    long bookId = Long.parseLong(bookIdString);
                    bookIds.add(bookId);  // add book to arraylist
                }
            }

            if (bookRepo.existsById(id)) {
                bookIds.add(id); // add latest book if it exists in our book repository
            } else {
                return "That book does not exist in our book repository, please try again.";
            }

            String updatedListOfBooksString = String.join(",", bookIds.stream().map(Object::toString).toArray(String[]::new));
            userCart.setListOfBooks(updatedListOfBooksString); // put string back into databae
            cartRepo.save(userCart); // Save the updated Cart object

            return "Book saved in cart...";
        } catch (Exception e) {
            return "couldnt do it sorry";
        }
    }

    @PostMapping(value = "/cart/{userid}/delete/{id}")
    public String cartDelete(@PathVariable long userid, @PathVariable long id) {
        if (!cartRepo.existsById(userid)) { // if user cart doesnt exist
            cartRepo.save(new Cart(userid, ""));
            return "This cart is empty";
        }

        Cart userCart = cartRepo.findById(userid).get(); // gets userCart
        String listOfBooksString = userCart.getListOfBooks(); //get list of books

        ArrayList<Long> bookIds = new ArrayList<>();
        String[] bookIdStrings = listOfBooksString.split(","); // convert to string array

        try {
            for (String bookIdString : bookIdStrings) {
                if (!(bookIdString.isEmpty())) {
                    long bookId = Long.parseLong(bookIdString);
                    bookIds.add(bookId);  // add book to arraylist
                }
            }
            bookIds.remove(id); // add latest book 

            String updatedListOfBooksString = String.join(",", bookIds.stream().map(Object::toString).toArray(String[]::new));
            userCart.setListOfBooks(updatedListOfBooksString); // put string back into databae
            cartRepo.save(userCart); // Save the updated Cart object

            return "Book deleted from cart...";
        } catch (Exception e) {
            return "Could not delete book from cart";
        }
    }


    @GetMapping(value = "/cart/{userid}")
    public ArrayList<String> getCart(@PathVariable long userid) {
        if (!cartRepo.existsById(userid)) { // if user cart doesnt exist
            cartRepo.save(new Cart(userid, ""));
        }

        try {
            Cart userCart = cartRepo.findById(userid).get(); // gets userCart
            String listOfBooksString = userCart.getListOfBooks(); //get list of books

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

    @GetMapping(value = "/cart/subtotal/{userid}")
    public double getCartSubtotal(@PathVariable long userid) {
        if (!cartRepo.existsById(userid)) { // if user cart doesnt exist
            cartRepo.save(new Cart(userid, ""));
            return 0;
        }

        try {
            Cart userCart = cartRepo.findById(userid).get(); // gets userCart
            String listOfBooksString = userCart.getListOfBooks(); //get list of books

            ArrayList<Long> bookIds = new ArrayList<>();
            String[] bookIdStrings = listOfBooksString.split(","); // convert to arraylist<long> with book ids
            for (String bookIdString : bookIdStrings) {
                long bookId = Long.parseLong(bookIdString);
                bookIds.add(bookId);  // add bookid to arraylist<long>
            }

            double sum = 0;
            for (Long id : bookIds) {
                Book book = bookRepo.findById(id).get();
                sum += book.getPrice();
            }
            return sum;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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

    @PostMapping(value = "/personID/{userID}/wishlistName/{wishlistName}")
    public ResponseEntity<String> getWishlist(@RequestParam long iD, @RequestParam String wishlistName) {
        wishlistService.createWishlist(iD, wishlistName);
        return ResponseEntity.ok("Wishlist Created");
    }


    @PostMapping(value = "/userID/{iD}/wishlistId/{wishlistID}/ADDbookID/{bookID}/")
    public ResponseEntity<String> addBookWishlist(@RequestParam long iD, String wishlistName, Long bookID) {
        wishlistService.addBookToWishlist(iD, wishlistName, bookID);
        return ResponseEntity.ok("Book added to Wishlist");}


    @DeleteMapping(value = "/userID/{iD}/wishlistId/{wishlistID}/DELETEbookID/{bookID}/")
    public ResponseEntity<String> deleteBookWishlist(@RequestParam long iD, @RequestParam String wishlistName, long bookID)  {
        wishlistService.removeBookFromWishlist(iD, wishlistName, bookID);
        return ResponseEntity.ok("Book deleted from Wishlist");
    }

    @GetMapping(value = "/userID/{iD}/SHOWwishlist/{wishlistName}/")
    public ResponseEntity<String> showBookWishlist(@RequestParam long iD, @RequestParam String wishlistName) {
        wishlistService.getAllBooksInWishlist(iD, wishlistName);
        return ResponseEntity.ok("Showing books in Wishlist");
    }
}

