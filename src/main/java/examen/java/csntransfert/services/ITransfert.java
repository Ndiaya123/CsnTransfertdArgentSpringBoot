package examen.java.csntransfert.services;

import examen.java.csntransfert.model.Transfert;

import java.util.List;

public interface ITransfert {
    public Transfert findByCodetransfert(String codetransfert);
    public List<Transfert> findByCaissier_Id(Long caisserId);


}
