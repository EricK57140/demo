package com.example.demo.dao;

import com.example.demo.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur,Integer> {

    Optional<Utilisateur> findByNom(String nom);

    @Query("from Utilisateur u join fetch u.listeRole where u.nom = :nom")
    Optional<Utilisateur> findByNomWithRoles(@Param("nom")String nom);

}
