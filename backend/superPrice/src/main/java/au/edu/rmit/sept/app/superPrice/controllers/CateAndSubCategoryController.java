package au.edu.rmit.sept.app.superPrice.controllers;



import java.util.Collection;
import java.util.List;
import java.util.Optional;

import au.edu.rmit.sept.app.superPrice.models.Product;
import au.edu.rmit.sept.app.superPrice.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "cate")
public class CateAndSubCategoryController {
    private final ProductService service;

    @Autowired
    public CateAndSubCategoryController(ProductService serv)
    {
        this.service = serv;
    }

    @GetMapping("all/sub")
    public ResponseEntity<Object> getAllCate() {
        return null;
        // return new ResponseEntity<>(this.service.getProducts(), HttpStatus.OK);
    }

    @GetMapping("all/cate")
    public ResponseEntity<Object> getAllSubCate() {
        return null;
        // return new ResponseEntity<>(this.service.getProducts(), HttpStatus.OK);
    }

    @GetMapping("sub/{cate_sub}")
    public ResponseEntity<Object> getAllSubCateByCate(@PathVariable("cate_sub")String CateName) {
        return null;
        // return new ResponseEntity<>(this.service.getProducts(), HttpStatus.OK);
    }
}
