package Group9.Final.Atempt.Repo;
import Group9.Final.Atempt.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import Group9.Final.Atempt.Models.Wishlist;

import java.util.List;
import java.util.Map;

public interface WishlistRepo extends JpaRepository <Wishlist, Long> {}


