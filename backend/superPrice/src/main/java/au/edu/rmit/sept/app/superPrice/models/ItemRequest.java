
package au.edu.rmit.sept.app.superPrice.models;

import java.io.Serializable;

public class ItemRequest implements Serializable {

    private static final long serialVersionUID = 7720978167137384733L;

    private Long id;
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
