package dev.gwozdz.contactfinder.model.regions;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.gwozdz.contactfinder.model.Contact;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Gmina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Typ typ;
    @ManyToOne
    @JoinColumn(name = "wojewodztwo_id")
    @JsonBackReference
    private Wojewodztwo wojewodztwo;
    @ManyToOne
    @JoinColumn(name = "powiat_id")
    @JsonBackReference
    private Powiat powiat;

    @OneToMany(mappedBy = "gmina", cascade = CascadeType.ALL)
    private final Set<Contact> kontakty = new HashSet<>();

    public Gmina() {
    }

    public enum Typ {
        MIEJSKA(10),
        MIEJSKO_WIEJSKA(5),
        WIEJSKA(3);

        public final int searchResultsNum;

        Typ(int searchResultsNum){
            this.searchResultsNum = searchResultsNum;
        }
    }

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

    public Typ getTyp() {
        return typ;
    }

    public void setTyp(Typ typ) {
        this.typ = typ;
    }

    public Powiat getPowiat() {
        return powiat;
    }

    public void setPowiat(Powiat powiat) {
        this.powiat = powiat;
    }

    public Wojewodztwo getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(Wojewodztwo wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gmina gmina = (Gmina) o;
        return name.equals(gmina.name) && typ.equals(gmina.typ);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, typ);
    }
}
