package au.edu.rmit.sept.app.Product.models;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store implements Serializable {

    private String name;
    private String address;
    private String postcode;
    private String chain;

}
