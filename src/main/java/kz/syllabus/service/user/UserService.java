package kz.syllabus.service.user;

import kz.syllabus.persistence.UserRepository;
import kz.syllabus.persistence.model.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("User with this %s not found", username)));
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.findByUsername(username);
    }
}
