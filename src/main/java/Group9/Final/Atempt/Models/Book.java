package Group9.Final.Atempt.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String author;

    private String genre;

    private String description;

    private int soldCopies;
    
    private long isbn;

    private long yearPublished;

    private int rating;
    
    private String publisher;

    private double discountPercent;

    private double price;

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscountPercent() {
        return this.discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getSoldCopies() {
        return this.soldCopies;
    }

    public void setSoldCopies(int soldCopies) {
        this.soldCopies = soldCopies;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIsbn() {
        return this.isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public long getYearPublished() {
        return this.yearPublished;
    }

    public void setYearPublished(long yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String toString() {
        return "[ISBN: " + this.isbn + ", name: " + this.name + ", description: " + this.description + ", price: " + this.price + 
                ", author: " + this.author + ", genre: " + this.genre + ", publisher: " + this.publisher + ", year published: " + this.yearPublished + ", copies sold: " + this.soldCopies + "]";
    }
}
