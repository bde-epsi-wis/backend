package fr.lamon.bde.commandes.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration //Cette classe est une configuration
@EnableWebSecurity //Spring Boot nous permet maintenant de modifier la configuration de sécurité
@EnableMethodSecurity//Permet d'utiliser les annotations de sécurité
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //authentifie toutes les requêtes http venant vers l'appli
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        //désactive la gestion des sessions
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //authentification par formulaire avec les valeurs par défaut
        //http.formLogin(withDefaults());
        //authentification basique avec les valeurs par défaut
        http.httpBasic(withDefaults());
        return http.build();
    }

    /**
     * permet de gérer l'authentification in memory
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("user")
                .password("{noop}password") //noop signifie que le mot de passe n'est pas crypté
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}password")
                .roles("ADMIN")
                .build();

        //l'objet aura comme paramètres les credentials de l'utilisatisateur
        return new InMemoryUserDetailsManager(user1, admin);
    }
}