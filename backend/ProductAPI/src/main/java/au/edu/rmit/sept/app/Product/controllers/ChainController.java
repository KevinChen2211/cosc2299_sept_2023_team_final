package au.edu.rmit.sept.app.Product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.edu.rmit.sept.app.Product.models.Chain;
import au.edu.rmit.sept.app.Product.models.Product;
import au.edu.rmit.sept.app.Product.services.ChainService;

@RestController
@RequestMapping(value="chain")
public class ChainController {
    private final ChainService service;

    @Autowired
    public ChainController(ChainService serv){
        this.service = serv;
    }

    @GetMapping("all")
    public ResponseEntity<Object> getAllChains(){
        return new ResponseEntity<>(this.service.getChains(),HttpStatus.OK);
    }

    @GetMapping("name/{chain_name}")
    public ResponseEntity<Object> getChainByName(@PathVariable("chain_name")String name){
        Chain ChainOptional = service.getByName(name);
        if (ChainOptional == null)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ChainOptional,HttpStatus.OK);
    }
}
