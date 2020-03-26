package com.alina.mylibrary.model;


import com.alina.mylibrary.repository.BookUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name="app.db-init",havingValue = "true")
public class DBInitializer implements CommandLineRunner {

    @Autowired
    private BookUserRepository bookUserRepository;

    public DBInitializer(BookUserRepository bookUserRepository) {
        this.bookUserRepository = bookUserRepository;
    }

    @Override
    public void run(String... args) {
        this.bookUserRepository.deleteAll();
        BookUser b1=new BookUser(
                "Alina",
                "Olaru",
                "olarualina01@gmail.com",
                "0726080518",
                true,
                true,
                true,
                "Romania",
                "Bacau",
                "Bacau",
                "Calea Moldovei",
                179,
                "Happy ",
                2,
                57,
                "parola",
                "Username",
                null,
                null,
                null,
                null,
                null,
                null
                );

                this.bookUserRepository.save(b1);
    }
}