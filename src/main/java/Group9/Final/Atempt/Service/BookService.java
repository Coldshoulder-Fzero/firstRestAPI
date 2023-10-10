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

}
