package com.example.demo.model;


import com.example.demo.view.VueMarque;
import com.example.demo.view.VueMateriel;
import com.example.demo.view.VueReservation;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(CleReservation.class)
public class Reservation {
    @Id
    private Integer emprunteurId;

    @Id
    private Integer materielId;

    @Id
    @JsonView(VueReservation.class)
    private Date date;

    @ManyToOne
    @MapsId("emprunteur_id")
    @JsonView(VueReservation.class)
    private Utilisateur emprunteur;

    public Utilisateur getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(Utilisateur emprunteur) {
        this.emprunteur = emprunteur;
    }

    @ManyToOne
    @MapsId("materiel_id")
    @JsonView(VueReservation.class)
    private Materiel materiel;


    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public Integer getEmprunteurId() {
        return emprunteurId;
    }

    public void setEmprunteurId(Integer emprunteurId) {
        this.emprunteurId = emprunteurId;
    }

    public Integer getMaterielId() {
        return materielId;
    }

    public void setMaterielId(Integer materielId) {
        this.materielId = materielId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

