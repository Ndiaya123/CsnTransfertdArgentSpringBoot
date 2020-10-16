package examen.java.csntransfert.services;


import examen.java.csntransfert.dao.CaissieRepository;
import examen.java.csntransfert.dao.ClientRepository;
import examen.java.csntransfert.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientService  implements  ICLient{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client findByCni(int cni) {
        return clientRepository.findByCni(cni);
    }
}
