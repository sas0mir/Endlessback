package com.example.Endlessback.player;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PlayerConfig {

    @Bean
    CommandLineRunner commandLineRunner(PlayerRepository repository) {
        return args -> {
            Player test1 = new Player("test1", "test1@icloud.com", 1.0, new Integer[]{1, 2}, LocalDate.of(2021, Month.SEPTEMBER, 15), "test1.png", 10);
            Player test2 = new Player("test2", "test2@icloud.com", 1.0, null, LocalDate.of(2021, Month.SEPTEMBER, 15), "test2.jpg", 5);
            repository.saveAll(List.of(test1, test2));
        };
    }
}
