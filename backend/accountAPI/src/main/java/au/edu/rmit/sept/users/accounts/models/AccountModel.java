package au.edu.rmit.sept.users.accounts.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

public record AccountModel (String firstName, String lastName, String address, String email, String password, String phone, Boolean isNotified) implements Serializable{

}
