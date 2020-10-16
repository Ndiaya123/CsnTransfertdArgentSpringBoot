package examen.java.csntransfert.model;

import javax.persistence.*;


@Entity
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10, nullable = true)
    private int commission_systeme_total;
    @Column(length = 10, nullable = true)
    private int commission_taxe_etat_total;

    public Compte()
    {
        super();
    }

    public Compte(int commission_systeme_total, int commission_taxe_etat_total) {
        this.commission_systeme_total = commission_systeme_total;
        this.commission_taxe_etat_total = commission_taxe_etat_total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCommission_systeme_total() {
        return commission_systeme_total;
    }

    public void setCommission_systeme_total(int commission_systeme_total) {
        this.commission_systeme_total = commission_systeme_total;
    }

    public int getCommission_taxe_etat_total() {
        return commission_taxe_etat_total;
    }

    public void setCommission_taxe_etat_total(int commission_taxe_etat_total) {
        this.commission_taxe_etat_total = commission_taxe_etat_total;
    }
}
