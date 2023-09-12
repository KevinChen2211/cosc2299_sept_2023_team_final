package au.edu.rmit.sept.app.Product.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import au.edu.rmit.sept.app.Product.models.Store;
import au.edu.rmit.sept.app.Product.services.StoreService;

@RestController
@RequestMapping(value = "store")
public class StoreController {

    private final StoreService service;

    @Autowired
    public StoreController(StoreService serv) {
        this.service = serv;
    }

    @GetMapping("all")
    public ResponseEntity<Object> getAllStores() {
        return new ResponseEntity<>(this.service.getStores(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Object> getStoresByPostcodeAndChain(
            @RequestParam(required = false) List<String> postcode,
            @RequestParam(required = false) List<String> chain) {

        List<Store> stores = service.getStoresByPostcodeAndChain(postcode, chain);
        if (stores == null || stores.isEmpty())
            return new ResponseEntity<>("Stores not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }
}
