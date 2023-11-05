/*package Group9.Final.Atempt.Profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController

@RequestMapping(path = "/profile")
public class ProfileController {
    @Autowired
    ProfileService profileService;
    
    
    public ProfileController(ProfileService profileService){this.profileService = profileService;}



    @GetMapping(path = "/get-user {username}")
    public List<Profile> findProfileByUsername(String username){return profileService.findProfileByUsername(username);}   
    

     @PostMapping
    public void registerNewProfile(@RequestBody Profile profile){profileService.addProfile(profile);}

    @DeleteMapping(path = "/delete-user {username}")
    public void deleteProfile(@PathVariable("username") String username){
        profileService.deleteProfile(username);
    }
}
*/