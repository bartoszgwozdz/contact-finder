package dev.gwozdz.contactfinder.model.regions;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Powiat {

    public Powiat() {
    }

    public Powiat(String name) {
        this.name = name;
        this.gminy = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "wojewodztwo_id")
    @JsonBackReference
    private Wojewodztwo wojewodztwo;
    @OneToMany(mappedBy = "powiat", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonManagedReference
    private Set<Gmina> gminy = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wojewodztwo getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(Wojewodztwo wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    public Set<Gmina> getGminy() {
        return gminy;
    }

    public void setGminy(Set<Gmina> gminy) {
        this.gminy = gminy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Powiat powiat = (Powiat) o;
        return name.equals(powiat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
