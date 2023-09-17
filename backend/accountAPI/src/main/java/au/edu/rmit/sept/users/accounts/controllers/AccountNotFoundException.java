package au.edu.rmit.sept.users.accounts.controllers;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String email) {
        super("Could not find Account with email = " + email);
    }
}
