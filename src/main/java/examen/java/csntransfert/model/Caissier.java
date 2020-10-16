package examen.java.csntransfert.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Caissier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 13, nullable = true)
    private int cni;
    @Column(length = 10, nullable = true)
    private int matricule;
    @Column(length = 50, nullable = true)
    private String username;
    @Column(length = 50, nullable = true)
    private String prenom;
    @Column(length = 50, nullable = true)
    private String nom;
    @Column(length = 20,nullable = true)
    private String  date_naissance;
    @Column(length = 50, nullable = true)
    private String lieu_naissance;
    @Column(length = 50, nullable = true)
    private String adresse;
    @Column(length = 9, nullable = true)
    private int tel;
    @Column(length = 50, nullable = true)
    private String email;
    @Column(length = 255, nullable = true)
    private String photo;
    @Column(length = 10,nullable = true)
    private int montant_compte;
    @Column(length = 9, nullable = true)
    private int commission_total;
    @Column(length = 11,nullable = false)
    private int ninea;
    @Column(length = 255, nullable = true)
    private String motpasse;
    @Column(length = 20,nullable = true)
    private String date_creation;
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Admin admin;
    @OneToMany(mappedBy = "caissier", cascade = CascadeType.ALL)
    private List<Transfert> transferts;

    @OneToMany(mappedBy = "caissier", cascade = CascadeType.ALL)
    private List<Client> clients;
    // Upload files.
    @Transient
    private MultipartFile[] fileDatas;

    @Transient
    private MultipartFile[] parts;

    private boolean changed;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @Column(length = 50, nullable = true)
    private String login;

    public Caissier()
    {
        super();
    }

    public Caissier(int cni, int matricule, String username, String prenom, String nom, String date_naissance, String lieu_naissance, String adresse, int tel, String email, String photo, int montant_compte, int commission_total, int ninea, String motpasse, String date_creation,  boolean changed, Role role) {
        this.cni = cni;
        this.matricule = matricule;
        this.username = username;
        this.prenom = prenom;
        this.nom = nom;
        this.date_naissance = date_naissance;
        this.lieu_naissance = lieu_naissance;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.photo = photo;
        this.montant_compte = montant_compte;
        this.commission_total = commission_total;
        this.ninea = ninea;
        this.motpasse = motpasse;
        this.date_creation = date_creation;

//        this.admin = admin;
        this.changed = changed;
        this.role = role;
    }

    public Caissier(String username, String motpasse, Collection roles) {
        this.username = username;
        this.motpasse = motpasse;

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

    public int getMontant_compte() {
        return montant_compte;
    }

    public void setMontant_compte(int montant_compte) {
        this.montant_compte = montant_compte;
    }

    public int getCommission_total() {
        return commission_total;
    }

    public void setCommission_total(int commission_total) {
        this.commission_total = commission_total;
    }

    public int getNinea() {
        return ninea;
    }

    public void setNinea(int ninea) {
        this.ninea = ninea;
    }

    public String getMotpasse() {
        return motpasse;
    }

    public void setMotpasse(String motpasse) {
        this.motpasse = motpasse;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String  date_creation) {
        this.date_creation = date_creation;
    }

//    public Admin getAdmin() {
//        return admin;
//    }
//
//    public void setAdmin(Admin admin) {
//        this.admin = admin;
//    }


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Transfert> getTransferts() {
        return transferts;
    }

    public void setTransferts(List<Transfert> transferts) {
        this.transferts = transferts;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}

