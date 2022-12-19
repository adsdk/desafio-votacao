package com.br.vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class VoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoteApplication.class, args);
    }
}
