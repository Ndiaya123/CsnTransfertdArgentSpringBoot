package examen.java.csntransfert.dao;

import examen.java.csntransfert.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte,Long> {
    public Optional<Compte> findById(long id);

}
