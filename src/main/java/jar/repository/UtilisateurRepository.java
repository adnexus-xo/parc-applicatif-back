package jar.repository;

import jar.entity.Utilisateur;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    @Query("SELECT u FROM Utilisateur u WHERE u.email = :email AND u.motDePasse = :motDePasse")
    Optional<Utilisateur> findByEmailAndMotDePasse(@Param("email") String email, @Param("motDePasse") String motDePasse);
}
