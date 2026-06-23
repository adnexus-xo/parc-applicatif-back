package jar.repository;

import jar.entity.Utilisateur;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//@Repository
public interface UtilisateurRepository extends JpaRepository <Utilisateur, Integer> {
    Optional<Utilisateur> findByEmailAndMotDePasse(String email, String motDePasse);
}
