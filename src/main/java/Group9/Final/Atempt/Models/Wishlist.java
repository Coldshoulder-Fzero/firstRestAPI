package Group9.Final.Atempt.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long iD;

    private String books;

    public long getiD() {
        return iD;
    }

    public void setiD(long iD) {
        this.iD = iD;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public Wishlist(long iD, String books) {
        this.iD = iD;
        this.books = books;
    }
}
