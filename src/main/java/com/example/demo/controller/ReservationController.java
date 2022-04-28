package com.example.demo.controller;

import com.example.demo.dao.ReservationDao;
import com.example.demo.model.Reservation;
import com.example.demo.view.VueReservation;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class ReservationController {

    private ReservationDao reservationDao;

    @Autowired
    public ReservationController(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;


    }

    @GetMapping("/Liste-reservation")
    @JsonView(VueReservation.class)
    public List<Reservation> ListeReservation() {

        return this.reservationDao.findAll();

    }



    @GetMapping("/reservation/{emprunteurId}/{materielId}/{date}")
    @JsonView(VueReservation.class)
    public Reservation reservation (

        @PathVariable Integer emprunteurId,
        @PathVariable Integer materielId,
        @PathVariable String date
) throws ParseException {

            Date  dateReservation = new SimpleDateFormat("yyyy-MM-dd").parse(date);

        return this.reservationDao.findByEmprunteurIdAndMaterielIdAndDate(emprunteurId,materielId,dateReservation).orElse(null);

    }

    @PostMapping("/reservation")
    public String createReservation(@RequestBody Reservation reservation) {

        this.reservationDao.save(reservation);
        System.out.println(reservation);
        return "ok";
    }

    @DeleteMapping("/reservation/{idUtilisateur}/{idMateriel}/{date}")

    public String deleteReservation(

            @PathVariable Integer emprunteurId,
            @PathVariable Integer materielId,
            @PathVariable Date date)

            {



      this.reservationDao.deleteByEmprunteurIdAndMaterielIdAndDate(emprunteurId,materielId,date);
        return "ok";
    }
}