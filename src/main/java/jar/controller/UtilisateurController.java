package jar.controller;

import jar.dto.LoginRequest;
import jar.entity.Utilisateur;
import jar.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    // Vérifie les credentials
    Utilisateur user = utilisateurService.findByEmailAndPassword(request.getEmail(), request.getPassword());
    if (user != null) {
        return ResponseEntity.ok(user);
    } else {
        return ResponseEntity.status(401).body("Identifiants incorrects");
    }
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
    

}