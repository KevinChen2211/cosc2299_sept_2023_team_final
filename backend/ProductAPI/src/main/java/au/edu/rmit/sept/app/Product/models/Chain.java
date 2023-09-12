package au.edu.rmit.sept.app.Product.models;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chain implements Serializable {
    private static final long serialVersionUID = 1L;


    private String name;
    private Double avgRating;

}
