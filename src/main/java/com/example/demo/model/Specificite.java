package com.example.demo.model;


import com.example.demo.view.VueMarque;
import com.example.demo.view.VueMateriel;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Specificite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({VueMateriel.class})
    private Integer id;
    @Column(unique = true)
    @JsonView({VueMateriel.class})
    private String nom;

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

    @OneToMany(mappedBy = "marque")
    @JsonView(VueMarque.class)
    private List<Materiel> listemateriel = new ArrayList<>();

    public List<Materiel> getListemateriel() {
        return listemateriel;
    }

    public void setListemateriel(List<Materiel> listemateriel) {
        this.listemateriel = listemateriel;
    }
}

