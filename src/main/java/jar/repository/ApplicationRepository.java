package jar.repository;

import jar.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ApplicationRepository extends JpaRepository <Application, Integer> {
    List<Application> findByEquipeUtilisatriceIdEquipe(Integer idEquipe);
    List<Application> findByCategorieIdCategorie(Integer idCategorie);
    List<Application> findByEtat(String etat);
}