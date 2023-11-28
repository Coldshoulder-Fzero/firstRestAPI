package Group9.Final.Atempt.Service;

import Group9.Final.Atempt.Models.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WishlistService {



    public Map<String, Wishlist> wishlists;

    public WishlistService() {
        this.wishlists = new HashMap<>();
    }

    public void createWishlist(Long userId, String wishlistName) {
        Wishlist wishlist = new Wishlist(userId, wishlistName);
        wishlists.put(generateKey(userId, wishlistName), wishlist);
        System.out.println("Wishlist created successfully: " + wishlistName);
    }

    public void addBookToWishlist(Long userId, String wishlistName, Long bookID) {
        Wishlist wishlist = wishlists.get(generateKey(userId, wishlistName));
        if (wishlist != null) {
            wishlist.addBook(bookID);
        } else {
            System.out.println("Wishlist not found: " + wishlistName);
        }
    }

    public void removeBookFromWishlist(Long userId, String wishlistName, Long bookID) {
        Wishlist wishlist = wishlists.get(generateKey(userId, wishlistName));
        if (wishlist != null) {
            wishlist.removeBook(bookID);
        } else {
            System.out.println("Wishlist not found: " + wishlistName);
        }
    }

    public List<Long> getAllBooksInWishlist(Long userId, String wishlistName) {
        Wishlist wishlist = wishlists.get(generateKey(userId, wishlistName));
        if (wishlist != null) {
            return wishlist.getBooks();
        } else {
            System.out.println("Wishlist not found: " + wishlistName);
            return new ArrayList<>();
        }
    }
    private String generateKey(Long userId, String wishlistName) {
        return userId + "_" + wishlistName;
    }
}




