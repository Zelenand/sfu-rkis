package pr6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pr6.model.Profile;
import pr6.model.ProfileRepo;

import java.util.Collections;

@Service
public class ProfileDetailService implements UserDetailsService {

    private final ProfileRepo profileRepo;

    @Autowired
    public ProfileDetailService(ProfileRepo profileRepository) {
        this.profileRepo = profileRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Profile profile = profileRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Профиль не найден"));

        return new User(
                profile.getUsername(),
                profile.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(profile.getRole()))
        );
    }
}
