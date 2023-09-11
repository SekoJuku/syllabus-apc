package kz.syllabus.util;

import kz.syllabus.entity.user.User;
import kz.syllabus.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
@RequiredArgsConstructor
public class UserUtils {

    private final UserService userService;

    public User loadUser(Principal principal) {
        return userService.findByUsername(principal.getName());
    }
}
