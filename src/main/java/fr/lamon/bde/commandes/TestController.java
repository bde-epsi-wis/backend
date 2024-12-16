package fr.lamon.bde.commandes;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
// Controller pour tester le bon fonctionnement de la sécurité
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World";
    }

    @PreAuthorize("hasRole('USER')") //n'autorise que les utilisateurs ayant le rôle ROLE_USER
    @GetMapping("/user")
    public String userEndpoint(){
        return "Hello User";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminEndpoint(){
        return "Hello Admin";
    }
}
