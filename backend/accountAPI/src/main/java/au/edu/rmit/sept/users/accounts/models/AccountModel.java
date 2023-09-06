package au.edu.rmit.sept.users.accounts.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountModel implements Serializable {
        private String customerID;
        private String name;
        private String address;
        private String email;
        private String password;
        private String phone;

}
