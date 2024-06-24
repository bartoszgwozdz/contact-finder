package dev.gwozdz.contactfinder.service;

import dev.gwozdz.contactfinder.model.regions.Gmina;
import dev.gwozdz.contactfinder.model.regions.Powiat;
import dev.gwozdz.contactfinder.model.regions.Wojewodztwo;
import dev.gwozdz.contactfinder.repository.regions.GminaRepository;
import dev.gwozdz.contactfinder.repository.regions.PowiatRepository;
import dev.gwozdz.contactfinder.repository.regions.WojewodztwoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionsServiceImpl implements RegionsService {

    private GminaRepository gminaRepository;
    private PowiatRepository powiatRepository;
    private WojewodztwoRepository wojewodztwoRepository;

    public RegionsServiceImpl(GminaRepository gminaRepository, PowiatRepository powiatRepository, WojewodztwoRepository wojewodztwoRepository) {
        this.gminaRepository = gminaRepository;
        this.powiatRepository = powiatRepository;
        this.wojewodztwoRepository = wojewodztwoRepository;
    }

    @Override
    public List<Gmina> getAllGminy() {
        return gminaRepository.findAll();
    }

    @Override
    public List<Gmina> getGminyByPowiat(Long powiatId) {
        Powiat powiatEntity = powiatRepository.getReferenceById(powiatId);
        if (powiatEntity != null) {
            return gminaRepository.findByPowiat(powiatEntity);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Gmina> getGminyByWojewodztwo(Long wojewodztwoId) {
        Wojewodztwo wojewodztwoEntity = wojewodztwoRepository.getReferenceById(wojewodztwoId);
        if (wojewodztwoEntity != null) {
            return gminaRepository.findByWojewodztwo(wojewodztwoEntity);
        } else {
            throw new IllegalArgumentException("Wojewodztwo doesn't exist");
        }
    }

    @Override
    public List<Powiat> getPowiatyByWojewodztwo(Long wojewodztwoId) {
        Wojewodztwo wojewodztwoEntity = wojewodztwoRepository.getReferenceById(wojewodztwoId);
        if (wojewodztwoEntity != null) {
            return powiatRepository.findByWojewodztwo(wojewodztwoEntity);
        } else {
            throw new IllegalArgumentException("Wojewodzztwo doesnt' exist");
        }
    }

    @Override
    public List<Powiat> getAllPowiaty() {
        return powiatRepository.findAll();
    }

    @Override
    public List<Wojewodztwo> getAllWojewodztwa() {
        return wojewodztwoRepository.findAll();
    }

    @Override
    public Gmina getGmina(Long id) {
        return gminaRepository.getReferenceById(id);
    }

    @Override
    public Powiat getPowiat(Long id) {
        return powiatRepository.getReferenceById(id);
    }

    @Override
    public Wojewodztwo getWojewodztwo(Long id) {
        return wojewodztwoRepository.getReferenceById(id);
    }

}
