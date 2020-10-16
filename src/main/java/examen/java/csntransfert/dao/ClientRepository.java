package examen.java.csntransfert.dao;

//import examen.java.csntransfert.model.Admin;
import examen.java.csntransfert.model.Caissier;
import examen.java.csntransfert.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {

    public Client findByCni(int cni);
}
