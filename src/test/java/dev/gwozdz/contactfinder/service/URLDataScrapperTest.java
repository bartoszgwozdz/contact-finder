package dev.gwozdz.contactfinder.service;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class URLDataScrapperTest {

    private URLDataScrapper urlDataScrapper = new URLDataScrapper();
    private final String urlWithEmail = "http://prety-gwinty.pl/kontakt/";
    private final String emailOnWebsite = "biuro@prety-gwinty.pl";
    private final String urlWithoutEmail = "http://www.example.org";

    @Test
    void shouldNotThrowWhenProperUrl() {
        assertDoesNotThrow(() -> urlDataScrapper.findEmail(urlWithEmail));
    }

    @Test
    void shouldThrowWhenProperUrl() {
        assertThrows(IOException.class, () -> urlDataScrapper.findEmail("http://hothin.ss"));
    }

    @Test
    void shouldFindEmailOnWebsite() throws IOException {
        Set<String> emails = urlDataScrapper.findEmail(urlWithEmail);
        assertEquals(1, emails.size());
        assertEquals(true, emails.contains(emailOnWebsite));
    }

    @Test
    void shouldNotFindEmailWhenNoExisting() throws IOException {
        Set<String> emails = urlDataScrapper.findEmail(urlWithoutEmail);
        assertEquals(0, emails.size());
    }


}
