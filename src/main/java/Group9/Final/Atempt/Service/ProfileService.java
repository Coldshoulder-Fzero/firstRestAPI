package Group9.Final.Atempt.Service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Group9.Final.Atempt.Models.Profile;
import Group9.Final.Atempt.Repo.ProfileRepo;
import jakarta.transaction.Transactional;

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

    @Transactional
    public void updateUsername(String oldUsername, String newUsername) {
        if (StringUtils.isAnyBlank(oldUsername, newUsername)) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        Profile existingProfile = profileRepo.findById(oldUsername).orElse(null);
        if (existingProfile == null) {
            throw new IllegalArgumentException("Profile with old username does not exist");
        }

        Profile newProfile = profileRepo.findById(newUsername).orElse(null);
        if (newProfile != null) {
            throw new IllegalArgumentException("New username already exists");
        }

        existingProfile.setUsername(newUsername);
        profileRepo.save(existingProfile);
    }



    public boolean deleteProfile(String username){
        //boolean verification; 
        //if password & username exist on the same account, delete profile
        profileRepo.deleteById(username);
        return true;
    }
}
