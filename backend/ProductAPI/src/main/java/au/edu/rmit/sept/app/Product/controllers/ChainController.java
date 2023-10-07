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

    /**
     * Fetches all available chains.
     * 
     * @return ResponseEntity<Object> Returns a list of all chains with a status
     *         code of 200 (OK).
     *         If there are no chains, an empty list will be returned.
     */
    @GetMapping("all")
    public ResponseEntity<Object> getAllChains(){
        return new ResponseEntity<>(this.service.getChains(),HttpStatus.OK);
    }

    /**
     * Retrieves a specific chain based on its name.
     * 
     * @param name The name of the chain to be retrieved.
     * 
     * @return ResponseEntity<Object> Returns the chain details with a status code
     *         of 200 (OK).
     *         If the chain with the specified name is not found, a "not found"
     *         message is returned with a status code of 404 (NOT FOUND).
     */
    @GetMapping("name/{chain_name}")
    public ResponseEntity<Object> getChainByName(@PathVariable("chain_name")String name){
        Chain ChainOptional = service.getByName(name);
        if (ChainOptional == null)
            return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ChainOptional,HttpStatus.OK);
    }
}
