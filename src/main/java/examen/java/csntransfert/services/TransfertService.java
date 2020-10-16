package examen.java.csntransfert.services;

import examen.java.csntransfert.dao.TransfertRepository;
import examen.java.csntransfert.model.Transfert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransfertService  implements ITransfert{
    @Autowired
    private TransfertRepository transfertRepository;

    @Override
    public Transfert findByCodetransfert(String codetransfert) {
        return transfertRepository.findByCodetransfert(codetransfert);
    }

    @Override
    public List<Transfert> findByCaissier_Id(Long caisserId) {
        return transfertRepository.findByCaissier_Id(caisserId);
    }
}
