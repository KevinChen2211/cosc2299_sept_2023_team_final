package au.edu.rmit.sept.app.Product.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import au.edu.rmit.sept.app.Product.models.OpeningTime;
import au.edu.rmit.sept.app.Product.services.OpenTimeService;

@RestController
@RequestMapping(value="time")
public class OpenTimeController {
    private final OpenTimeService service;

    @Autowired
    public OpenTimeController(OpenTimeService serv){
        this.service = serv;
    }

    @GetMapping("{store_name}")
    public ResponseEntity<Object> getChainByName(@PathVariable("store_name")String name){
        Collection<OpeningTime> OpenOptional = service.getByName(name);
        if (OpenOptional == null)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(OpenOptional,HttpStatus.OK);
    }
}
