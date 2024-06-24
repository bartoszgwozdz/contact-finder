package dev.gwozdz.contactfinder.controller;


import dev.gwozdz.contactfinder.model.regions.Gmina;
import dev.gwozdz.contactfinder.model.regions.Powiat;
import dev.gwozdz.contactfinder.model.regions.Wojewodztwo;
import dev.gwozdz.contactfinder.service.RegionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("regions")
public class RegionsResource {

    private final RegionsService regionsService;

    @Autowired
    public RegionsResource(RegionsService regionsService) {
        this.regionsService = regionsService;
    }


    @GetMapping("/wojewodztwa")
    public List<Wojewodztwo> getAllWojewodztwa() {
        return regionsService.getAllWojewodztwa();
    }

    @GetMapping("/wojewodztwa/{id}")
    public Wojewodztwo getWojewodztwo(@PathVariable Long id) {
        return regionsService.getWojewodztwo(id);
    }

    @GetMapping("/wojewodztwa/{wojewodztwoId}/powiaty")
    public List<Powiat> getPowiatyOfWojewodztwo(@PathVariable Long wojewodztwoId) {
        return regionsService.getPowiatyByWojewodztwo(wojewodztwoId);
    }

    @GetMapping("/wojewodztwa/{wojewodztwoId}/gminy")
    public List<Gmina> getGminyOfWojewodztwo(@PathVariable Long wojewodztwoId) {
        return regionsService.getGminyByWojewodztwo(wojewodztwoId);
    }

    @GetMapping("/powiaty")
    public List<Powiat> getAllPowiaty() {
        return regionsService.getAllPowiaty();
    }

    @GetMapping("/powiaty/{id}")
    public Powiat getPowiat(@PathVariable Long id) {
        return regionsService.getPowiat(id);
    }

    @GetMapping("/powiaty/{id}/gminy")
    public List<Gmina> getGminyOfPowiat(@PathVariable Long id) {
        return regionsService.getGminyByPowiat(id);
    }

    @GetMapping("/gminy")
    public List<Gmina> getAllGminy() {
        return regionsService.getAllGminy();
    }

    @GetMapping("/gminy/{id}")
    public Gmina getGmina(@PathVariable Long id) {
        return regionsService.getGmina(id);
    }
}
