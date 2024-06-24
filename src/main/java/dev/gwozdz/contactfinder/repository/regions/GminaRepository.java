package dev.gwozdz.contactfinder.repository.regions;

import dev.gwozdz.contactfinder.model.regions.Gmina;
import dev.gwozdz.contactfinder.model.regions.Powiat;
import dev.gwozdz.contactfinder.model.regions.Wojewodztwo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GminaRepository extends JpaRepository<Gmina, Long> {
    Gmina findByName(String name);
    List<Gmina> findByPowiat(Powiat powiat);
    List<Gmina> findByWojewodztwo(Wojewodztwo wojewodztwo);
}
