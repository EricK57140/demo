package com.example.demo.security;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecuriteDemo extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource datasource;

    @Autowired
    UserDetailsServiceDemo userDetailsServiceDemo;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

 /*       auth.inMemoryAuthentication()
                .withUser("Eric")
                .password("1980")
                .roles("USER")
                .and()
                .withUser("Sarah")
                .password("1983")
                .roles("ADMIN");*/
 /*       auth.jdbcAuthentication().dataSource(datasource)
                .usersByUsernameQuery("SELECT nom , mdp , 1 from utilisateur where nom = ?")
                .authoritiesByUsernameQuery(
                        "SELECT u.nom, r.nom FROM utilisateur u " +
                                "JOIN role_utilisateur ru ON u.id = ru.utilisateur_id " +
                                "JOIN role r ON r.id = ru.role_id " +
                                "WHERE u.nom = ?");*/




        auth.userDetailsService(userDetailsServiceDemo);


    }
    @Override
    protected  void configure(HttpSecurity http) throws Exception{

        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/connexion").permitAll()
                    .antMatchers("/inscription").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/**").hasAnyRole("USER","ADMIN")
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
              /*  .and()
                    .formLogin();*/

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();

    }
    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return  super.authenticationManager();
    }

}
