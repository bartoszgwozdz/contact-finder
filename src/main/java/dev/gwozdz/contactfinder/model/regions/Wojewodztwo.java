package dev.gwozdz.contactfinder.model.regions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Wojewodztwo {

    public Wojewodztwo() {
    }

    public Wojewodztwo(String name) {
        this.name = name;
        this.powiaty = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "wojewodztwo", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonManagedReference
    private Set<Powiat> powiaty = new HashSet<>();


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

    public Set<Powiat> getPowiaty() {
        return powiaty;
    }

    public void setPowiaty(Set<Powiat> powiaty) {
        this.powiaty = powiaty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wojewodztwo that = (Wojewodztwo) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
