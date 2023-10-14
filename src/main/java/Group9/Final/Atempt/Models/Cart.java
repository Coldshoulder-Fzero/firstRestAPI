package Group9.Final.Atempt.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;

    private String listofbooks;

    public Cart() {
        
    }

    public Cart(long userid, String listofbooks) {
        this.userid = userid;
        this.listofbooks = listofbooks;
    }

    public long getUserId() {
        return this.userid;
    }

    public void setUserId(long userid) {
        this.userid = userid;
    }

    public String getListOfBooks(){
        return this.listofbooks;
    }

    public void setListOfBooks(String listofbooks){
        this.listofbooks = listofbooks;
    }

}
