package au.edu.rmit.sept.app.superPrice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/superPrice")
public class SuperPriceController {
    @GetMapping
    public String all(){
        return "Wellcom here";
    }
}