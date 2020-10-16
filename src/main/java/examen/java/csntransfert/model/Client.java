package examen.java.csntransfert.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 13, nullable = false)
    private int cni;
    @Column(length = 50, nullable = true)
    private String prenom;
    @Column(length = 50, nullable = true)
    private String nom;
    @Column(length = 50, nullable = true)
    private String adresse;
    @Column(nullable = true)
    private String date_naissance;
    @Column(length = 50, nullable = true)
    private String lieu_naissance;
    @Column(length = 9, nullable = true)
    private int tel;
    @Column(length = 50, nullable = true)
    private String email;
    @Column(length = 255, nullable = true)
    private String photo;
    @Column(nullable = true)
    private String date_creation;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Transfert> transferts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private Caissier caissier;


    // Upload files.
    @Transient
    private MultipartFile[] fileDatas;

    @Transient
    private MultipartFile[] parts;

    public Client() {
        super();
    }

    public Client(Long id, int cni, String prenom, String nom, String adresse, String date_naissance, String lieu_naissance, int tel, String email, String photo, String date_creation, MultipartFile[] fileDatas, MultipartFile[] parts) {
        this.id = id;
        this.cni = cni;
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.date_naissance = date_naissance;
        this.lieu_naissance = lieu_naissance;
        this.tel = tel;
        this.email = email;
        this.photo = photo;
        this.date_creation = date_creation;
        this.fileDatas = fileDatas;
        this.parts = parts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getLieu_naissance() {
        return lieu_naissance;
    }

    public void setLieu_naissance(String lieu_naissance) {
        this.lieu_naissance = lieu_naissance;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public MultipartFile[] getFileDatas() {
        return fileDatas;
    }

    public void setFileDatas(MultipartFile[] fileDatas) {
        this.fileDatas = fileDatas;
    }

    public MultipartFile[] getParts() {
        return parts;
    }

    public void setParts(MultipartFile[] parts) {
        this.parts = parts;
    }

    public Caissier getCaissier() {
        return caissier;
    }

    public void setCaissier(Caissier caissier) {
        this.caissier = caissier;
    }
}
