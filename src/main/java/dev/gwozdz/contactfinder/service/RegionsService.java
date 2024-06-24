package dev.gwozdz.contactfinder.service;

import dev.gwozdz.contactfinder.model.regions.Gmina;
import dev.gwozdz.contactfinder.model.regions.Powiat;
import dev.gwozdz.contactfinder.model.regions.Wojewodztwo;

import java.util.List;

public interface RegionsService {
    List<Gmina> getAllGminy();

    List<Gmina> getGminyByPowiat(Long powiatId);

    List<Gmina> getGminyByWojewodztwo(Long wojewodztwoId);

    List<Powiat> getPowiatyByWojewodztwo(Long wojewodztwoId);

    List<Powiat> getAllPowiaty();

    List<Wojewodztwo> getAllWojewodztwa();

    Gmina getGmina(Long id);
    Powiat getPowiat(Long id);
    Wojewodztwo getWojewodztwo(Long id);
}
