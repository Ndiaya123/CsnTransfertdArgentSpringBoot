package examen.java.csntransfert.dao;

import examen.java.csntransfert.model.Client;
import examen.java.csntransfert.model.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransfertRepository extends JpaRepository<Transfert,Long> {
    public Transfert findByCodetransfert(String codetransfert);
    public List<Transfert> findByCaissier_Id(Long caisserId);
    public List<Transfert> findByEtat(String  etat);


}

