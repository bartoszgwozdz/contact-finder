package dev.gwozdz.contactfinder.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class URLDataScrapper {

    private final String PHONE_REGEXP = "[5-7]{1}\\d{2}\\s*\\-?\\s*\\d{3}\\s*\\-?\\s*\\d{3}";
    private final String MAIL_REGEXP = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
    private final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEXP);
    private final Pattern MAIL_PATTERN = Pattern.compile(MAIL_REGEXP);

    public Set<String> findEmail(String url)
            throws IOException
    {
        Objects.requireNonNull(url);

        Document document = Jsoup.connect(url).get();
        String html = document.html();

        return extractEmailAddresses(html);
    }

    public Set<String> findPhoneNumber(String url)
            throws IOException
    {
        Objects.requireNonNull(url);

        Document document = Jsoup.connect(url).get();
        String html = document.html();

        return extractPhoneNumber(html);
    }

    HashSet<String> extractEmailAddresses(String text)
    {
        HashSet<String> emails = new HashSet<>();
        Matcher matcher = MAIL_PATTERN.matcher(text);

        while (matcher.find()) {
            emails.add(matcher.group());
        }
        return emails;
    }

    HashSet<String> extractPhoneNumber(String text)
    {
        HashSet<String> phoneNumbers = new HashSet<>();
        Matcher matcher = PHONE_PATTERN.matcher(text);

        while (matcher.find()) {
            phoneNumbers.add(matcher.group());
        }
        return phoneNumbers;
    }
}
