package dev.gwozdz.contactfinder.model;

import dev.gwozdz.contactfinder.model.regions.Gmina;
import jakarta.persistence.*;

import java.util.Set;
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String type;
    @ManyToOne
    @JoinColumn(name = "gmina_id")
    private Gmina gmina;
    private String url;
    private Set<String> phone;
    private Set<String> email;

    public Contact() {
    }

    public Contact(String type, String url) {
        this.type = type;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<String> getPhone() {
        return phone;
    }

    public void setPhone(Set<String> phone) {
        this.phone = phone;
    }

    public Set<String> getEmail() {
        return email;
    }

    public void setEmail(Set<String> email) {
        this.email = email;
    }

    public Gmina getGmina() {
        return gmina;
    }

    public void setGmina(Gmina gmina) {
        this.gmina = gmina;
    }
}
