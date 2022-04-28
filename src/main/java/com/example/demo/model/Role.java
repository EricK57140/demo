package com.example.demo.model;

//ORM
//Object Relational Mapping


import com.example.demo.view.VueReservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)

public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(VueReservation.class)
    private Integer id;
    @JsonView(VueReservation.class)
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




    public Role(Integer id, String nom) {
        this.id = id;
        this.nom = nom;

    }

    public Role() {

    }
}
