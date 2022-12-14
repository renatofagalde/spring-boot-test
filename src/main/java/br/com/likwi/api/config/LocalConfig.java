package br.com.likwi.api.config;

import br.com.likwi.api.domain.User;
import br.com.likwi.api.repositoy.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
@Profile("local")
public class LocalConfig {

    private Faker faker = new Faker();

    @Autowired
    private UserRepository userRepository;

    @Bean
    public void startDB() {
        this.userRepository.saveAll(this.getUsers());
    }

    private User createUser() {
        return new User(
                this.faker.name().fullName(),
                this.faker.internet().emailAddress(),
                UUID.randomUUID().toString());

    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();

        for (int i = 0; i <= 3; i++) {
            users.add(this.createUser());
        }
        return users;
    }

}
