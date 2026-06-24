package jar.controller;

import jar.dto.LoginRequest;
import jar.entity.Utilisateur;
import jar.service.UtilisateurService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://peaceful-biscuit-fcfa10.netlify.app", allowCredentials = "true")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @GetMapping
    public List<Utilisateur> findAll() {
        return utilisateurService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(utilisateurService.findById(id));
    }

    

    @PostMapping
    public ResponseEntity<Utilisateur> save(@RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(utilisateurService.save(utilisateur));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> update(@PathVariable Integer id, @RequestBody Utilisateur utilisateur) {
        utilisateur.setIdUtilisateur(id);
        return ResponseEntity.ok(utilisateurService.save(utilisateur));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        utilisateurService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    try {
        Utilisateur user = utilisateurService.findByEmailAndMotDePasse(request.getEmail(), request.getPassword());
        
        // Retourne seulement les infos nécessaires, pas l'objet complet
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getIdUtilisateur());
        response.put("email", user.getEmail());
        response.put("nom", user.getNomUtilisateur());
        response.put("prenom", user.getPrenomUtilisateur());
        response.put("role", user.getRole());
        
        return ResponseEntity.ok(response);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants incorrects");
    }
    }

}