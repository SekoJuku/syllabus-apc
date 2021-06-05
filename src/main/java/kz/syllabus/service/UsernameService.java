package kz.syllabus.service;


import kz.syllabus.entity.security.VerificationToken;
import kz.syllabus.repository.UserRepository;
import kz.syllabus.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsernameService {

    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;


    private String createVerificationLink(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return DOMAIN + "api/v1/auth/user-verification?token=" + verificationToken.getToken();
    }

    public VerificationToken getVerificationTokenByToken(String token) {

        List<VerificationToken> verificationTokenCheck = verificationTokenRepository.findAll();
        for(VerificationToken verificationToken: verificationTokenCheck) {
            if (verificationToken.getCreatedDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() + 7200000 <= System.currentTimeMillis()) {
                verificationTokenRepository.delete(verificationToken);
            }
        }

        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken.isPresent()) {
            return verificationToken.get();
        }
        throw new EmailTokenIsNotValidException("Token is not valid");
    }
}
