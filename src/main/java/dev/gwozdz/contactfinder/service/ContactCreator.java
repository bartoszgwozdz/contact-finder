package dev.gwozdz.contactfinder.service;

import dev.gwozdz.contactfinder.model.regions.Gmina;
import dev.gwozdz.contactfinder.model.google.SearchEngineResult;
import dev.gwozdz.contactfinder.model.google.SearchResultItem;
import dev.gwozdz.contactfinder.model.Contact;
import dev.gwozdz.contactfinder.repository.regions.GminaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContactCreator {
    @Autowired
    Googler googler;
    @Autowired
    OpenAIClient openAIClient;
    @Autowired
    URLDataScrapper urlDataScrapper;
    @Autowired
    GminaRepository gminaRepository;

    public void scrapAllContacts(String unitType) {
        List<Gmina> fromDB = gminaRepository.findAll();
        //debug purpose one gmina
//        Gmina tempGmina = fromDB.get(1);
        Gmina tempGmina = new Gmina();
        tempGmina.setName("Biłgoraj");
        tempGmina.setTyp(Gmina.Typ.WIEJSKA);
        String resultsFromGoogle = searchInGoogleForContact(tempGmina, unitType);
//        String openAIResponse = openAIClient.filterProperUnitsFromSearchResults(resultsFromGoogle, unitType);
//        System.out.println(openAIResponse);
        List<Contact> rawContacts = convertAIResponseToContacts(resultsFromGoogle);
        scrapURL(rawContacts);
    }

    private void scrapURL(List<Contact> contacts) {
        contacts.stream().forEach(c -> {
            try {
                c.setEmail(urlDataScrapper.findEmail(c.getUrl()));
                c.setPhone(urlDataScrapper.findPhoneNumber(c.getUrl()));
            } catch (IOException e) {
                System.out.println(e);
            }
        });
    }

    private List<Contact> convertAIResponseToContacts(String openAIResponse) {
        List<Contact> result = new ArrayList<>();
        String[] rows = openAIResponse.split("\n");
        for (String row : rows) {
            String[] parts = row.split("\\|");
            if (parts.length == 2) {
                Contact temp = new Contact(parts[0].trim(), parts[1].trim());
                result.add(temp);
            }
        }
        return result;
    }

    private String searchInGoogleForContact(Gmina tempGmina, String unitType) {
        String contactPostfix = " kontakt";
        String googleSearchPhrase = tempGmina.getName() + " " + unitType + " " + contactPostfix;
        SearchEngineResult searchEngineResult = googler.googleCustomSearch(googleSearchPhrase, tempGmina.getTyp().searchResultsNum);
        StringBuilder sb = new StringBuilder();
        for (SearchResultItem item : searchEngineResult.items) {
            String fixedUrl = fixUrl(item.formattedUrl);
            sb.append(item.title).append(" | ").append(fixedUrl).append("\n");
        }
        return sb.toString();
    }

    private String fixUrl(String url) {
        if (!url.startsWith("http")) {
            url = "http://" + url;
        }
        return url;
    }
}
