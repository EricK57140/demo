package com.example.demo.controller;

import com.example.demo.dao.MarqueDao;
import com.example.demo.model.Marque;
import com.example.demo.view.VueMarque;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class MarqueController {

    private MarqueDao marqueDao;

    @Autowired
    public MarqueController(MarqueDao marqueDao) {
        this.marqueDao = marqueDao;


    }

    @GetMapping("/admin/Liste-marque")
    @JsonView(VueMarque.class)
    public List<Marque> ListeMarque() {

        return this.marqueDao.findAll();

    }





    @GetMapping("/marque/{id}")
    @JsonView(VueMarque.class)
    public Marque marque(@PathVariable Integer id) {

       /* System.out.println(id);
       HashMap<Integer, Marque> ListeMarque = new HashMap<>();
        Marque eric = new Marque(1, "Keibler", "Eric");
        Marque sarah = new Marque(2, "Codinera", "Sarah");

        ListeMarque.put(1, eric);
        ListeMarque.put(2, sarah);
        */


        return this.marqueDao.findById(id).orElse(null);

    }

    @PostMapping("/admin/marque")
    public String createMarque(@RequestBody Marque marque) {

        this.marqueDao.save(marque);
        System.out.println(marque);
        return "ok";
    }

    @DeleteMapping("/admin/marque/{id}")

    public String deleteMarque(@PathVariable int id) {



        this.marqueDao.deleteById(id);
        return "ok";
    }
}