package fr.lamon.bde.commandes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
// Controller pour tester le bon fonctionnement de la sécurité
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World";
    }
}
