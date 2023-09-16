package au.edu.rmit.sept.app.Product.models;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpeningTime implements Serializable {
    private String storeName;
    private String dayOfWeek;
    private boolean isClosed;
    private String from;
    private String to;
}
