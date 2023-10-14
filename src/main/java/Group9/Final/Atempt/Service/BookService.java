package Group9.Final.Atempt.Service;
import java.util.List;
import Group9.Final.Atempt.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Group9.Final.Atempt.Repo.BookRepo;

@Service
public class BookService {
    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getTop10BestSoldBooks() {
        return bookRepo.findTop10ByOrderBySoldCopiesDesc();
    }
    
    public List<Book> getBooksByRating(int rating) {
    
            return bookRepo.findByRatingGreaterThanEqual(rating);
       
    }

    public void discountBooksByPublisher(String publisher, double discountPercent){
        List<Book> books = bookRepo.findByPublisher(publisher);
        for (Book book : books) {
            double discountPrice = book.getPrice() * (1 - discountPercent / 100);
            book.setPrice(discountPrice);
        }
        bookRepo.saveAll(books);
    }
}
