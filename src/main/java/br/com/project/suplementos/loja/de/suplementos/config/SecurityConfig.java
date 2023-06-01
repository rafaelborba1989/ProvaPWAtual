package br.com.project.suplementos.loja.de.suplementos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/admin").hasRole("ADMIN");
                    auth.requestMatchers("/cadastro").hasRole("ADMIN");
                    auth.requestMatchers("/cadastrarItem").hasRole("ADMIN");

                    auth.requestMatchers("/salvar").hasRole("ADMIN");
                    auth.requestMatchers("/admin/editar/**").hasRole("ADMIN");
                    auth.requestMatchers("/admin/excluir/**").hasRole("ADMIN");


                    auth.requestMatchers("/verCarrinho").hasRole("USER");
                    auth.requestMatchers("/adicionarCarrinho").hasRole("USER");
                    auth.requestMatchers("/finalizarCompra").hasRole("USER");


                    auth.anyRequest().permitAll();
                })
                .formLogin( login -> login.loginPage("/login").permitAll())
                .logout( logout -> logout.logoutUrl("/logout"))
                .build();
    }

    @Bean
    BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
