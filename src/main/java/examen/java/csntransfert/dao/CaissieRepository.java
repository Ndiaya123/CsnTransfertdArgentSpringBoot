package examen.java.csntransfert.dao;

import examen.java.csntransfert.model.Caissier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaissieRepository  extends JpaRepository<Caissier,Long> {

    public List<Caissier> findAll();
    public Caissier findByMatricule(String matricule);
    public Caissier findByMotpasse(String motpasse);
    public Caissier findByEmail(String email);
    public Caissier findByUsername(String username);
    public Caissier findByLogin(String login);

}
