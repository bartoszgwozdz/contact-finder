package dev.gwozdz.contactfinder;


import dev.gwozdz.contactfinder.repository.regions.GminaRepository;
import dev.gwozdz.contactfinder.service.URLDataScrapper;
import dev.gwozdz.contactfinder.components.utils.GeoDataFiller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Debug implements CommandLineRunner {


    @Autowired
    URLDataScrapper urlDataScrapper;
    @Autowired
    GeoDataFiller geoDataFiller;
    @Autowired
    private GminaRepository gminaRepository;

//    @PostConstruct
//    public void init(){
//        dataFiller.fillRawDataFromXlsx("src/main/resources/Lista-gmin-Excel-2018.xlsx");
//
//        System.out.println(dataFiller.countDatabaseEntities());
//        String searchPhrase = "Kraków ośrodek kultury kontakt";
//        SearchEngineResult urlFromGoogler = googler.googleCustomSearch(searchPhrase, 10
//        );
//        StringBuilder sb = new StringBuilder();
//        for (SearchResultItem sri : urlFromGoogler.items){
//            sb.append(sri);
//        }
//
//        try (BufferedWriter writer = new BufferedWriter( new FileWriter("output.txt"))){
//            writer.write(sb.toString());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


//        List<String> email = null;
//        try {
//            email = emailFinder.findEmail(urlFromGoogler).stream().collect(Collectors.toList());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        for(String  s : email){System.out.println(s);}

//    }

    @Override
    public void run(String... args) throws Exception {
        geoDataFiller.fillRawDataFromXlsx("src/main/resources/Lista-gmin-Excel-2018.xlsx");

        System.out.printf("Successfully read %d gminy from Xlsx file.\n", geoDataFiller.countDatabaseEntities());
//        dataFiller.scrapAllContacts("Ośrodek kultury");


    }
}
