package examen.java.csntransfert.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import examen.java.csntransfert.model.Caissier;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String libRole;

    @OneToMany(mappedBy = "role")
    private List<Caissier> caissiers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibRole() {
        return libRole;
    }

    public void setLibRole(String libRole) {
        this.libRole = libRole;
    }

    public List<Caissier> getCaissiers() {
        return caissiers;
    }

    public void setCaissiers(List<Caissier> caissiers) {
        this.caissiers = caissiers;
    }
}
