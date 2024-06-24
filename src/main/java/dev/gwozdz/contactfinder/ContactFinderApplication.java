package dev.gwozdz.contactfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ContactFinderApplication {

    public static void main(String[] args) {
        ApplicationContext context =
                 SpringApplication.run(ContactFinderApplication.class, args);
    }

}
