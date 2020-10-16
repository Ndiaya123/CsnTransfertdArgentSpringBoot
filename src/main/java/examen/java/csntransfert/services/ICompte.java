package examen.java.csntransfert.services;

import examen.java.csntransfert.model.Caissier;
import examen.java.csntransfert.model.Compte;

import java.util.List;

public interface ICompte {
    public Compte findById(long id);
    public List<Compte> findAll();
}
