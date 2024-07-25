package pr6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pr6.model.Profile;
import pr6.model.ProfileRepo;

@Service
@Transactional(readOnly = true)
public class ProfileService {
    private final ProfileRepo profileRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileService(ProfileRepo profileRepository, PasswordEncoder passwordEncoder) {
        this.profileRepo = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerUser(String username, String password) {
        Profile profile = new Profile();
        profile.setUsername(username);
        profile.setPassword(passwordEncoder.encode(password));
        profile.setRole("user");
        profileRepo.save(profile);
    }

    @Transactional
    public void registerAdmin(String username, String password) {
        Profile profile = new Profile();
        profile.setUsername(username);
        profile.setPassword(passwordEncoder.encode(password));
        profile.setRole("admin");
        profileRepo.save(profile);
    }
}
