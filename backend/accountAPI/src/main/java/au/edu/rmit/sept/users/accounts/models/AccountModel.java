package au.edu.rmit.sept.users.accounts.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


public record AccountModel (Long customerID, String firstName, String lastName, String address, String email, String password, String phone) {

}
