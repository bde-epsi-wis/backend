package fr.lamon.bde.commandes.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration //Cette classe est une configuration
@EnableWebSecurity //Spring Boot nous permet maintenant de modifier la configuration de sécurité
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        //authentifie toutes les requêtes http venant vers l'appli
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //désactive la gestion des sessions
        //http.formLogin(withDefaults());
        //authentification par formulaire avec les valeurs par défaut
        http.httpBasic(withDefaults());
        //authentification basique avec les valeurs par défaut
        return http.build();
    }
}