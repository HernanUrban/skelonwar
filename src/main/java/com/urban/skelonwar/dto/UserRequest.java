package com.urban.skelonwar.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * Created by hernan.urban on 5/20/2017.
 */
public class UserRequest {

    @NotBlank(message = "Username must not be blank!")
    private String username;

    @NotBlank(message = "Password cannot be blank!")
    @Size(min = 6, message = "Password must be min 6 chars!")
    private String password;

    @NotBlank(message = "First Name must not be blank!")
    private String firstname;

    @NotBlank(message = "Last name must not be blank!")
    private String lastname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
