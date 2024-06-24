package dev.gwozdz.contactfinder.components.utils;

import dev.gwozdz.contactfinder.model.regions.Gmina;
import dev.gwozdz.contactfinder.model.regions.Powiat;
import dev.gwozdz.contactfinder.model.regions.Wojewodztwo;
import dev.gwozdz.contactfinder.repository.regions.GminaRepository;
import dev.gwozdz.contactfinder.repository.regions.PowiatRepository;
import dev.gwozdz.contactfinder.repository.regions.WojewodztwoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class GeoDataFiller {

    @Autowired
    GminaRepository gminaRepository;
    @Autowired
    PowiatRepository powiatRepository;
    @Autowired
    WojewodztwoRepository wojewodztwoRepository;
    @Autowired
    XlsxReader xlsxReader;
    public void fillRawDataFromXlsx(String fileName) {
        try {
            Map<Integer, List<String>> dataFromFile = xlsxReader.readXlsxFile(fileName);
            dataFromFile.remove(1);
            for (Map.Entry<Integer, List<String>> entry : dataFromFile.entrySet()) {
                List<String> values = entry.getValue();

                String wojewodztwoName = values.get(0);
                String powiatName = values.get(1);
                String gminaName = values.get(2);
                String typ = values.get(3).toUpperCase().replace("-", "_");
                Gmina.Typ gminaTyp = Gmina.Typ.valueOf(typ);

                Wojewodztwo wojewodztwo = wojewodztwoRepository.findByName(wojewodztwoName);
                if (wojewodztwo == null) {
                    wojewodztwo = new Wojewodztwo();
                    wojewodztwo.setName(wojewodztwoName);
                    wojewodztwoRepository.save(wojewodztwo);
                }

                Powiat powiat = powiatRepository.findByName(powiatName);
                if (powiat == null) {
                    powiat = new Powiat();
                    powiat.setName(powiatName);
                    powiat.setWojewodztwo(wojewodztwo);
                    powiatRepository.save(powiat);
                }

                Gmina gmina = new Gmina();
                gmina.setName(gminaName);
                gmina.setTyp(gminaTyp);
                gmina.setPowiat(powiat);
                gmina.setWojewodztwo(wojewodztwo);
                gminaRepository.save(gmina);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long countDatabaseEntities() {
        return gminaRepository.count();
    }
}
