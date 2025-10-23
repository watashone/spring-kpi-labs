package com.luv2code.springlab.controller;


import com.luv2code.springlab.model.Apartment;
import com.luv2code.springlab.model.ApartmentParams;
import com.luv2code.springlab.service.ActorInteractionService;
import com.luv2code.springlab.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/apartments")
public class ApartmentController {

    // field injection
    @Autowired
    private ActorInteractionService actorInteractionService;
    private final ApartmentService service;

    public ApartmentController(ApartmentService service) {
        this.service = service;
    }

    @GetMapping
    public String listApartments(Model model) {
        List<Apartment> apartments = service.findAll();
        model.addAttribute("apartments", apartments);
        return "apartments/list";
    }

    @GetMapping("/search")
    public String searchForm(Model model) {
        model.addAttribute("params", new ApartmentParams());
        return "apartments/search";
    }

    @PostMapping("/search")
    public String searchApartments(@ModelAttribute("params") ApartmentParams params, Model model) {
        List<Apartment> apartments = service.searchByParams(params);
        model.addAttribute("apartments", apartments);
        return "apartments/list";
    }

    @GetMapping("/add")
    public String addApartmentForm(Model model) {
        model.addAttribute("apartment", new Apartment());
        return "apartments/add";
    }

    @PostMapping("/add")
    public String addApartmentSubmit(@ModelAttribute Apartment apartment) {
        service.save(apartment);
        return "redirect:/apartments";
    }

    @GetMapping("/delete")
    public String deleteApartment(@RequestParam int id) {
        service.delete(id);
        return "redirect:/apartments";
    }

    @GetMapping("/edit")
    public String editApartment(@RequestParam int id, Model model) {
        Apartment apartment = service.findById(id);
        model.addAttribute("apartment", apartment);
        return "apartments/edit";
    }

    @PostMapping("/edit")
    public String updateApartment(@ModelAttribute("apartment") Apartment apartment) {
        service.save(apartment);
        return "redirect:/apartments";
    }

    @GetMapping("/actors")
    public String demoActors(Model model) {
        model.addAttribute("message", actorInteractionService.demonstrateInteraction().replace("\n", "<br/>"));
        return "apartments/actors";
    }


}
