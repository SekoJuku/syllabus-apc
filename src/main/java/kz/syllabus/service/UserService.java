package kz.syllabus.service;

import kz.syllabus.dto.responseDto.UserDtoResponse;
import kz.syllabus.repository.UserRepository;
import kz.syllabus.security.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;


@Service
public class UserService<RoleRepository> implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder encoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy AuthenticationManager authenticationManager,
                       JWTTokenProvider jwtTokenProvider, BCryptPasswordEncoder encoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent()) {
            return new UserPrincipal(user.get());
        } else {
            throw new UserNotFoundException("User not found by username: " + username);
        }
    }


    public ResponseEntity<UserDtoResponse> authorization(String username, String password, HttpServletRequest request) {
        authenticate(username, password);
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent()) {
            UserPrincipal userPrincipal = new UserPrincipal(user.get());
            String ipFromClient = jwtTokenProvider.getIpFromClient(request);
            HttpHeaders jwt = getJwtHeader(userPrincipal, ipFromClient);
            return new ResponseEntity<>(new UserFacade().userToUserDtoResponse(user.get()), jwt, HttpStatus.OK);
        }
        throw new UserNotFoundException("User not found by username: " + username);
    }

    private HttpHeaders getJwtHeader(UserPrincipal userPrincipal, String ipFromClient) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(SecurityConstant.JWT_TOKEN_HEADER, jwtTokenProvider.generateToken(userPrincipal, ipFromClient));
        return httpHeaders;
    }

    private void authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    public void verifyUser(User user) {
        user.setActive(true);
        userRepository.save(user);
    }
}