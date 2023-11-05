
package Group9.Final.Atempt.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Group9.Final.Atempt.Models.Profile;

import java.util.List;



@Repository
public interface ProfileRepo extends JpaRepository <Profile, String>{
    //find profile
    @Query(value = "SELECT u FROM profile u WHERE u.username IN :username", 
    nativeQuery = true)
    List<Profile> findProfileByUsername(@Param("username") String username); 
    

    //new profile
    @Modifying
    @Query(
        value = "insert into profile (username, full_name, email_address, home_address, password) values (:username, :full_name, :email_address, :home_address, :password)", 
        nativeQuery = true)
    void createProfile(@Param("username") String username, @Param("full_name") String full_name, @Param("email_address") String email_address, @Param("home_address") String home_address, @Param("password") String password);
    

    //void deleteProfile(String username);
    


   //modify user data
    @Modifying
    @Query("update profile u set u.full_name = :full_name where u.username = :username")
    void updateFullName(@Param("username") String username, @Param("full_name") String full_name);

    @Modifying
    @Query("update profile u set u.home_address = :home_address where u.username = :username")
    void updateHomeAddress(@Param("username") String username, @Param("home_address") String home_address);
    
    @Modifying
    @Query("update profile u set u.password = :password where u.username = :username")
    void updatePassword(@Param("username") String username, @Param("password") String password);
    
    @Modifying
    @Query("update profile u set u.username = :usrname where u.username = :username")
    void updateUsername(@Param("username") String username, @Param("usrname") String usrname );
}


    //List<Profile> getProfiles(); 
   