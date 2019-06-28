package com.resanovic.filip.springwebservicedemo;

import com.resanovic.filip.springwebservicedemo.model.Card;
import com.resanovic.filip.springwebservicedemo.model.Cardholder;
import com.resanovic.filip.springwebservicedemo.repository.CardRepository;
import com.resanovic.filip.springwebservicedemo.repository.CardholderRepository;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.stream.LongStream;

@EnableEncryptableProperties
@SpringBootApplication
public class SpringWebserviceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebserviceDemoApplication.class, args);
    }

    @Bean
    @Profile(value = "bootstrap_test_data")
    CommandLineRunner init(CardholderRepository cardholderRepository, CardRepository cardRepository){

        return args -> {
            cardRepository.deleteAll();
            LongStream.range(1,2)
                    .mapToObj(i -> {
                        Card c1 = new Card();
                        c1.setCardNumber("1234567890123456");
                        c1.setCardBrand("MasterCard");
                        return c1;
                    })
                    .map(v -> cardRepository.save(v))
                    .forEach(System.out::println);

            cardholderRepository.deleteAll();
            LongStream.range(1,11)
                    .mapToObj(i -> {
                        Cardholder c = new Cardholder();
                        c.setName("Cardholder " + i);
                        c.setEmail("contact" + i + "@email.com");
                        c.setPhone("(111) 111-1111");
                        return c;
                    })
                    .map(v -> cardholderRepository.save(v))
                    .forEach(System.out::println);
        };
    }
}
