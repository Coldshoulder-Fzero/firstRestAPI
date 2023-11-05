package Group9.Final.Atempt.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Profile{
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)

    private String username;
    private String full_name;
    private String email_address;
    private String home_address;
    private String password;

    public Profile() {
        // Default constructor required by JPA
    }

    public Profile(String username, String full_name, String email_address, String home_address, String password){
        this.username = username;
        this.full_name = full_name;
        this.email_address = email_address;
        this.home_address = home_address;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String usr){
        this.username = usr;
    }

    public String getFullName(){
        return full_name;
    }

    public void setFullName(String name){
        this.full_name = name;
    }

    public String getEmail(){
        return email_address;
    }

    public void setEmail(String email){
        this.email_address = email;
    }

    public String getAddress(){
        return home_address;
    }

    public void setAddress(String addy){
        this.home_address = addy;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String pswd){
        this.password = pswd;
    }

    public String toString(){
        String str = 
        "\nUsername : " + this.username +
        "\nPassword : " + this.password +
        "\nFull Name : " + this.full_name + 
        "\nEmail Address : " + this.email_address +
        "\nHome Address : " + this.home_address + "\n";
        return str;
    }
}