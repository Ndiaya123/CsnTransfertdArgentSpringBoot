package examen.java.csntransfert.services;


//import examen.java.csntransfert.model.Admin;
import examen.java.csntransfert.model.Caissier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICaissier {

    public Caissier findByMatricule(String matricule);
    public Caissier findByMotpasse(String motpasse);
    public Caissier findByEmail(String email);
    public List<Caissier> findAll();
    public Caissier findByUsername(String username);

}
