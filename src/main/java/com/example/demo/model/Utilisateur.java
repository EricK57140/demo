package com.example.demo.model;

//ORM
//Object Relational Mapping


import com.example.demo.view.VueReservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Utilisateur
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(VueReservation.class)
    private Integer id;
    @JsonView(VueReservation.class)
    private String nom;
    @JsonView(VueReservation.class)
    private String prenom;
    /*@JsonIgnore*/
    private String mdp;

/*    @JsonView(VueReservation.class)
    private boolean admin;*/

    @ManyToMany
    @JoinTable(
            name = "role_utilisateur",
            joinColumns = @JoinColumn(name="utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private List<Role> listeRole = new ArrayList<>();

  /*  public List<Role> getListeRole() {
        return listeRole;
    }

    public void setListeRole(List<Role> listeRole) {
        this.listeRole = listeRole;
    }*/

/*   public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }*/

   /* public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
*/




    public Utilisateur() {

    }
}
