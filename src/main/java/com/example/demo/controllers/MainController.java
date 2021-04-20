package com.example.demo.controllers;

import com.example.demo.models.Kompis;
import com.example.demo.repositories.KompisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/demo")
public class MainController {

    @Autowired
    private KompisRepository kompisRepository;

    @GetMapping(path = "/add")
    public String addNewKompis (@RequestParam String name, @RequestParam String email, @RequestParam String mobile) {
        Kompis kompis = new Kompis(name, email, mobile);
        kompisRepository.save(kompis);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public Iterable<Kompis> getAllKompisar() {
        return kompisRepository.findAll();
    }

    @GetMapping(path = "/allname")
    public List<Kompis> getAllKompisarByName(@RequestParam String name) {
        return kompisRepository.findByName(name);
    }

    @GetMapping(path = "/allmobile")
    public List<Kompis> getAllKompisarByMobile(@RequestParam String mobile) {
        return kompisRepository.findByMobile(mobile);
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteKompis(@PathVariable long id) {
        for (Kompis kompis: kompisRepository.findAll()) {
            if (kompis.getId() == id) {
                kompisRepository.delete(kompis);
                return "Deleted";
            }
        }
        return "Failed to delete";
    }

    @GetMapping(path = "/updatemobile/{id}")
    public String updateMobile(@PathVariable long id, @RequestParam String mobile) {
        for (Kompis kompis:kompisRepository.findAll()) {
            if (kompis.getId() == id) {
                kompis.setMobile(mobile);
                return "Updated";
            }
        }
        return "Failed to update";
    }
}
