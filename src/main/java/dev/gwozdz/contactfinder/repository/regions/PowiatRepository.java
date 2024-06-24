package dev.gwozdz.contactfinder.repository.regions;

import dev.gwozdz.contactfinder.model.regions.Powiat;
import dev.gwozdz.contactfinder.model.regions.Wojewodztwo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PowiatRepository extends JpaRepository<Powiat, Long> {
    Powiat findByName(String name);
    List<Powiat> findByWojewodztwo(Wojewodztwo wojewodztwo);
}
