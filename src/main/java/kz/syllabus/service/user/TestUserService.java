package kz.syllabus.service.user;

import kz.syllabus.repository.TestUserRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestUserService {
    private final TestUserRepository repository;

}
