package examen.java.csntransfert.services;

import examen.java.csntransfert.dao.CaissieRepository;
import examen.java.csntransfert.dao.CompteRepository;
import examen.java.csntransfert.model.Caissier;
import examen.java.csntransfert.model.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteService  implements  ICompte{


    @Autowired
    private CompteRepository compteRepository ;
    @Override
    public Compte findById(long id)
    {
        return null;
    }

    @Override
    public List<Compte> findAll()
    {
        return compteRepository.findAll();
    }
}
