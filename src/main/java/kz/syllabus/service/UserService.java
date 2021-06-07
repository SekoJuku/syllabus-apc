package kz.syllabus.service;


import kz.syllabus.entity.User;
import kz.syllabus.entity.UserPrincipal;
import kz.syllabus.repository.RoleRepository;
import kz.syllabus.repository.UserRepository;
import kz.syllabus.security.JWTProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    public static final String AUTHORIZATION = "Authorization";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JWTProvider jwtProvider;


    public User findByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(()->new UsernameNotFoundException("User with this username not found"));
    }
    public User findByUsernameAndPassword(String username, String password){
        Optional<User> user = userRepository.findByUsernameAndPassword(username,password);
        return  user.orElseThrow(()-> new UsernameNotFoundException("User with this credentials not found"));

    }

    public ResponseEntity<?> auth(String username, String password){
        Optional<User> user = userRepository.findByUsernameAndPassword(username,password);
        if (user.isPresent()) {
            UserPrincipal userPrincipal = new UserPrincipal(user.get());
            HttpHeaders authorizationHeader = getJwtHeader(userPrincipal);
            return new ResponseEntity<>(user, authorizationHeader, HttpStatus.OK);
        }
        throw new BadCredentialsException("Username and password incorrect");
    }

    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(AUTHORIZATION, jwtProvider.generateToken(userPrincipal));
        return httpHeaders;
    }


    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return new UserPrincipal(user.orElseThrow(() -> new UsernameNotFoundException("User now found")));
    }
}
