package au.edu.rmit.sept.app.superPrice.models;

import java.io.Serializable;
import java.util.List;

public class Store implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String address;
    private String postcode;
    private Chain chain;
    private List<OpeningTime> openingTimes;

    public Store(String name, String address, String postcode, Chain chain, List<OpeningTime> openingTimes) {
        this.name = name;
        this.address = address;
        this.postcode = postcode;
        this.chain = chain;
        this.openingTimes = openingTimes;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Chain getChain() {
        return chain;
    }

    public void setChain(Chain chain) {
        this.chain = chain;
    }

    public List<OpeningTime> getOpeningTimes() {
        return openingTimes;
    }

    public void setOpeningTimes(List<OpeningTime> openingTimes) {
        this.openingTimes = openingTimes;
    }
}
