package dev.gwozdz.contactfinder.repository.regions;

import dev.gwozdz.contactfinder.model.regions.Wojewodztwo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WojewodztwoRepository extends JpaRepository<Wojewodztwo, Long> {
    Wojewodztwo findByName(String name);
    boolean existsByName(String name);
}
