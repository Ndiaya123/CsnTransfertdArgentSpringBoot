//package examen.java.csntransfert.model;
//
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//public class Admin {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(length = 13, nullable = false)
//    private int cni;
//    @Column(length = 10, nullable = false)
//    private int matricule;
//    @Column(length = 50, nullable = false)
//    private String prenom;
//    @Column(length = 50, nullable = false)
//    private String nom;
//    @Column(length = 50,nullable = false)
//    private String date_naissance;
//    @Column(length = 50, nullable = false)
//    private String lieu_naissance;
//    @Column(length = 50, nullable = false)
//    private String adresse;
//    @Column(length = 9, nullable = false)
//    private int tel;
//    @Column(length = 50, nullable = false)
//    private String email;
//    @Column(length = 50, nullable = false)
//    private String username;
//    @Column(length = 255, nullable = false)
//    private String photo;
//    @Column(length = 50, nullable = true)
//    private String motpasse;
//    @Column(length = 50,nullable = true)
//    private String date_creation;
//    @OneToMany(mappedBy = "admin")
//    private List<Caissier> caissiers;
//
//    // Upload files.
//    @Transient
//    private MultipartFile[] fileDatas;
//
//
//    public Admin()
//    {
//        super();
//    }
//
//    public Admin(int cni, int matricule, String prenom, String nom, String date_naissance, String lieu_naissance, String adresse, int tel, String email, String photo, String motpasse, String date_creation, String username) {
//        this.cni = cni;
//        this.matricule = matricule;
//        this.prenom = prenom;
//        this.nom = nom;
//        this.date_naissance = date_naissance;
//        this.lieu_naissance = lieu_naissance;
//        this.adresse = adresse;
//        this.tel = tel;
//        this.email = email;
//        this.photo = photo;
//        this.motpasse = motpasse;
//        this.date_creation = date_creation;
//        this.caissiers = caissiers;
//        this.username = username;
//
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public int getCni() {
//        return cni;
//    }
//
//    public void setCni(int cni) {
//        this.cni = cni;
//    }
//
//    public int getMatricule() {
//        return matricule;
//    }
//
//    public void setMatricule(int matricule) {
//        this.matricule = matricule;
//    }
//
//    public String getPrenom() {
//        return prenom;
//    }
//
//    public void setPrenom(String prenom) {
//        this.prenom = prenom;
//    }
//
//    public String getNom() {
//        return nom;
//    }
//
//    public void setNom(String nom) {
//        this.nom = nom;
//    }
//
//    public String getDate_naissance() {
//        return date_naissance;
//    }
//
//    public void setDate_naissance(String date_naissance) {
//        this.date_naissance = date_naissance;
//    }
//
//    public String getLieu_naissance() {
//        return lieu_naissance;
//    }
//
//    public void setLieu_naissance(String lieu_naissance) {
//        this.lieu_naissance = lieu_naissance;
//    }
//
//    public String getAdresse() {
//        return adresse;
//    }
//
//    public void setAdresse(String adresse) {
//        this.adresse = adresse;
//    }
//
//    public int getTel() {
//        return tel;
//    }
//
//    public void setTel(int tel) {
//        this.tel = tel;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(String photo) {
//        this.photo = photo;
//    }
//
//    public String getMotpasse() {
//        return motpasse;
//    }
//
//    public void setMotpasse(String motpasse) {
//        this.motpasse = motpasse;
//    }
//
//    public String getDate_creation() {
//        return date_creation;
//    }
//
//    public void setDate_creation(String date_creation) {
//        this.date_creation = date_creation;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public MultipartFile[] getFileDatas() {
//        return fileDatas;
//    }
//
//    public void setFileDatas(MultipartFile[] fileDatas) {
//        this.fileDatas = fileDatas;
//    }
//}
