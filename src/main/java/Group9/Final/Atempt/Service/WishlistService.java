package Group9.Final.Atempt.Service;

import Group9.Final.Atempt.Models.Book;
import Group9.Final.Atempt.Repo.BookRepo;
import Group9.Final.Atempt.Repo.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepo wishlistRepo;

    @Autowired
    public WishlistService(WishlistRepo wishlistRepo) {
        this.wishlistRepo = wishlistRepo;
    }

    public List<Book> retrievedPersonID() {
        HashMap<Integer, Integer> bookToPerson = new HashMap<>();

        // Store books with person IDs
        bookToPerson.put(1, 101);
        bookToPerson.put(2, 102);
        bookToPerson.put(3, 101);
        bookToPerson.put(4, 103);

        // Retrieve all books associated with a specific person ID
        int personIdToRetrieve = 101;
        List<Integer> booksForPerson = getBooksForPerson(personIdToRetrieve, bookToPerson);

        System.out.println("Books associated with Person ID " + personIdToRetrieve + ": " + booksForPerson);
        return wishlistRepo.retrievedPersonID();
    }

    // Method to retrieve all books associated with a person ID
    public static List<Integer> getBooksForPerson(int personId, HashMap<Integer, Integer> bookToPerson) {
        List<Integer> booksForPerson = new ArrayList<>();
        for (int bookId : bookToPerson.keySet()) {
            if (bookToPerson.get(bookId) == personId) {
                booksForPerson.add(bookId);
            }
        }
        return booksForPerson;
    }

    public static void deleteBookFromWishlist(int bookId, HashMap<Integer, Integer> bookToPerson) {
        if (bookToPerson.containsKey(bookId)) {
            bookToPerson.remove(bookId);
        }
    }
}



