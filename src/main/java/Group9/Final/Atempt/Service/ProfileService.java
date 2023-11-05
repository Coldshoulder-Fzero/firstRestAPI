package Group9.Final.Atempt.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import Group9.Final.Atempt.Models.Profile;
import Group9.Final.Atempt.Repo.ProfileRepo;

import java.util.List;



@Service
public class ProfileService{
    private final ProfileRepo profileRepo; 

    @Autowired
    public ProfileService(ProfileRepo profileRepo){
        this.profileRepo = profileRepo;
    }

    public List<Profile> findProfileByUsername(String username) {
        boolean exist = profileRepo.existsById(username);
        
        if (exist){
            return profileRepo.findProfileByUsername(username);
        } else{
            return null;
        }
    }

    public void createProfile(String username, String full_name, String email_address, String home_address, String password){
        profileRepo.createProfile(username, full_name, email_address, home_address, password);
    }


    public void updateFullName(String username, String full_name){
         boolean exist = profileRepo.existsById(username);
        
        if (exist){
            profileRepo.updateFullName(username, full_name);
        } else {
            System.out.println("Profile does not exist");
        }
    }

    public void updateHomeAddress(String username, String home_address){
        boolean exist = profileRepo.existsById(username);
        if (exist){
            profileRepo.updateHomeAddress(username, home_address);
        } else {
            System.out.println("Profile does not exist");
        }
    }
        
    

    public void updatePassword(String username, String password){
        boolean exist = profileRepo.existsById(username);
        if (exist){
            profileRepo.updatePassword(username, password);
        } else {
            System.out.println("Profile does not exist");
        }
    }

    public void updateUsername(String username, String usrname){
        boolean exist = profileRepo.existsById(username);
        if (exist){
            profileRepo.updateUsername(username, usrname);
        } else {
            System.out.println("Profile does not exist");
        }
    }



    public void deleteProfile(String username, String password){
        boolean verification; 
        //if password & username exist on the same account, delete profile
        profileRepo.deleteById(username);
    }
}