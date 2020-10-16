package examen.java.csntransfert.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Transfert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10, nullable = true)
    private String codetransfert;
    @Column(length = 10, nullable = true)
    private int montant;
    @Column(length = 50, nullable = true)
    private String etat;
    @Column(nullable = true)
    private String date_creation;
    @Column(nullable = true)
    private String  date_retrait;
    @Column(length = 10,nullable = true)
    private int  commission_systeme;
    @Column(length = 10,nullable = true)
    private int commission_taxe_etat;
    @Column(length = 10,nullable = true)
    private int commission_caissier;

    @Column(length = 13, nullable = true)
    private int cni;
    @Column(length = 50, nullable = true)
    private String prenom;
    @Column(length = 50, nullable = true)
    private String nom;
    @Column(length = 50, nullable = true)
    private String adresse;
    @Column(length = 50, nullable = true)
    private String email;
    @Column(length = 9, nullable = true)
    private int tel;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private Client client;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private Caissier caissier;

    public Transfert(String code_transfert, int montant, String etat, String date_creation, String date_retrait, int commission_systeme, int commission_taxe_etat, int commission_caissier, int cni, String prenom, String nom, String adresse, String email, int tel, Client client, Caissier caissier) {
        this.codetransfert = code_transfert;
        this.montant = montant;
        this.etat = etat;
        this.date_creation = date_creation;
        this.date_retrait = date_retrait;
        this.commission_systeme = commission_systeme;
        this.commission_taxe_etat = commission_taxe_etat;
        this.commission_caissier = commission_caissier;
        this.cni = cni;
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.tel = tel;
        this.client = client;
        this.caissier = caissier;
    }

    public Transfert()
  {
      super();
  }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodetransfert() {
        return codetransfert;
    }

    public void setCodetransfert(String codetransfert) {
        this.codetransfert = codetransfert;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public String getDate_retrait() {
        return date_retrait;
    }

    public void setDate_retrait(String date_retrait) {
        this.date_retrait = date_retrait;
    }

    public int getCommission_systeme() {
        return commission_systeme;
    }

    public void setCommission_systeme(int commission_systeme) {
        this.commission_systeme = commission_systeme;
    }

    public int getCommission_taxe_etat() {
        return commission_taxe_etat;
    }

    public void setCommission_taxe_etat(int commission_taxe_etat) {
        this.commission_taxe_etat = commission_taxe_etat;
    }

    public int getCommission_caissier() {
        return commission_caissier;
    }

    public void setCommission_caissier(int commission_caissier) {
        this.commission_caissier = commission_caissier;
    }

    public int getCni() {
        return cni;
    }

    public void setCni(int cni) {
        this.cni = cni;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public Client getClient() {

        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Caissier getCaissier() {
        return caissier;
    }

    public void setCaissier(Caissier caissier) {
        this.caissier = caissier;
    }
}
