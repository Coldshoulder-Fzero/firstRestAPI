package Group9.Final.Atempt.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String wishlistName;

    private List<Long> books;

    public Wishlist(Long userId, String wishlistName) {
        this.userId = userId;
        this.wishlistName = wishlistName;
        this.books = new ArrayList<Long>();
    }

    public Long getUserId() {
        return userId;
    }

    public String getWishlistName() {
        return wishlistName;
    }

    public List<Long> getBooks() {
        return books;
    }

    public void addBook(Long book) {
        books.add(book);
        System.out.println(book + " added to " + wishlistName);
    }

    public void removeBook(Long book) {
        if (books.remove(book)) {
            System.out.println(book + " removed from " + wishlistName);
        } else {
            System.out.println(book + " not found in " + wishlistName);
        }


    }
}


