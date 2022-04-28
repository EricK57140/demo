package com.example.demo.controller;

import com.example.demo.dao.UtilisateurDao;
import com.example.demo.model.Role;
import com.example.demo.model.Utilisateur;
import com.example.demo.security.JwtUtils;
import com.example.demo.security.UserDetailsDemo;
import com.example.demo.security.UserDetailsServiceDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class UtilisateurController {

    private UtilisateurDao utilisateurDao;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired


    UserDetailsServiceDemo userDetailsServiceDemo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    public UtilisateurController(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;

    }
    @PostMapping("/inscription")
    public String inscription(@RequestBody Utilisateur utilisateur) throws Exception{

        utilisateur.setMdp(encoder.encode(utilisateur.getMdp()));
        Role roleUser = new Role();
        roleUser.setId(1);
        utilisateur.getListeRole().add(roleUser);

        utilisateurDao.save(utilisateur);

        return "utilisateur créé";
    }



    @PostMapping("/connexion")
    public String connexion(@RequestBody Utilisateur utilisateur) throws Exception {
//
//      Optional<Utilisateur> optionalUtilisateur =
//                utilisateurDao.findByNom(utilisateur.getNom());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getNom(),
                            utilisateur.getMdp()
                    )
            );
        }catch(BadCredentialsException e){
            throw new Exception(e);
        }

        UserDetails userDetails = userDetailsServiceDemo
                .loadUserByUsername(utilisateur.getNom());
        return jwtUtils.generateToken(new UserDetailsDemo(utilisateur));

    }

    @GetMapping("/Liste-utilisateur")
    public List<Utilisateur> ListeUtilisateur() {

        return this.utilisateurDao.findAll();

    }

    @GetMapping("/utilisateur-par-nom/{nom}")
    public Utilisateur utilisateurParNom(@PathVariable String nom) {

        return this.utilisateurDao.findByNom(nom).orElse(null);

    }

    @GetMapping("/utilisateur/{id}")
    public Utilisateur utilisateur(@PathVariable Integer id) {

       /* System.out.println(id);
       HashMap<Integer, Utilisateur> ListeUtilisateur = new HashMap<>();
        Utilisateur eric = new Utilisateur(1, "Keibler", "Eric");
        Utilisateur sarah = new Utilisateur(2, "Codinera", "Sarah");

        ListeUtilisateur.put(1, eric);
        ListeUtilisateur.put(2, sarah);
        */


        return this.utilisateurDao.findById(id).orElse(null);

    }

    @PostMapping("/utilisateur")
    public String createUtilisateur(@RequestBody Utilisateur utilisateur) {

        this.utilisateurDao.save(utilisateur);
        System.out.println(utilisateur);
        return "ok";
    }

    @DeleteMapping("/utilisateur/{id}")

    public String deleteUtilisateur(@PathVariable int id) {



        this.utilisateurDao.deleteById(id);
        return "ok";
    }
}