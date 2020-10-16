package examen.java.csntransfert.services;

import examen.java.csntransfert.dao.CaissieRepository;
import examen.java.csntransfert.model.Caissier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CaissierService  implements  ICaissier{

    @Autowired
    private CaissieRepository caissieRepository ;

    @Override
    public Caissier findByMatricule(String matricule) {
        return caissieRepository.findByMatricule(matricule);
    }

    @Override
    public Caissier findByMotpasse(String motpasse) {
        return caissieRepository.findByMotpasse(motpasse);
    }

    @Override
    public Caissier findByEmail(String email) {

        return caissieRepository.findByEmail(email);
    }

    @Override
    public List<Caissier> findAll() {
        return caissieRepository.findAll();
    }

    @Override
    public Caissier findByUsername(String username) {
        return caissieRepository.findByUsername(username);
    }
}
