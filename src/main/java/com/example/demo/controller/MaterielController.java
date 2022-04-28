package com.example.demo.controller;

import com.example.demo.dao.MaterielDao;
import com.example.demo.model.Materiel;
import com.example.demo.view.VueMateriel;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MaterielController {

    private MaterielDao materielDao;

    @Autowired
    public MaterielController(MaterielDao materielDao) {
        this.materielDao = materielDao;


    }

    @GetMapping("/Liste-materiel")
    @JsonView(VueMateriel.class)
    public List<Materiel> ListeMateriel() {

        return this.materielDao.findAll();

    }



    @GetMapping("/materiel/{id}")
    @JsonView(VueMateriel.class)
    public Materiel materiel(@PathVariable Integer id) {

       /* System.out.println(id);
       HashMap<Integer, Materiel> ListeMateriel = new HashMap<>();
        Materiel eric = new Materiel(1, "Keibler", "Eric");
        Materiel sarah = new Materiel(2, "Codinera", "Sarah");

        ListeMateriel.put(1, eric);
        ListeMateriel.put(2, sarah);
        */


        return this.materielDao.findById(id).orElse(null);

    }

    @PostMapping("/materiel")
    public String createMateriel(@RequestBody Materiel materiel) {

        this.materielDao.save(materiel);
        System.out.println(materiel);
        return "ok";
    }

    @DeleteMapping("/materiel/{id}")

    public String deleteMateriel(@PathVariable int id) {



        this.materielDao.deleteById(id);
        return "ok";
    }
}