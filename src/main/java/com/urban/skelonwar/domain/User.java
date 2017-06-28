package com.urban.skelonwar.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by hernan.urban on 5/24/2017.
 */
@Entity
public class User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7854284496467867373L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long accountId;

    @Column(nullable = false)
    @NotBlank(message = "Username must not be blank!")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Password cannot be blank!")
    @Size(min = 6, message = "Password must be min 6 chars!")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "First Name must not be blank!")
    private String firstname;

    @Column(nullable = false)
    @NotBlank(message = "Last name must not be blank!")
    private String lastname;

    @Column(nullable = false)
    private boolean blocked;

    @Column(nullable = false)
    private boolean active;

    public User(){}

    public User(AccountBuilder builder) {
        this.username = builder.getUsername();
        this.active = builder.isActive();
        this.blocked = builder.isBlocked();
        this.firstname = builder.getFirstname();
        this.lastname = builder.getLastname();
        this.password = builder.getPassword();
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean isActive() {
        return active;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static class AccountBuilder {

        private String username;

        private String password;

        private String firstname;

        private String lastname;

        private boolean blocked;

        private boolean active;

        public String getUsername() {
            return username;
        }

        public AccountBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public AccountBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public String getFirstname() {
            return firstname;
        }

        public AccountBuilder withFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public String getLastname() {
            return lastname;
        }

        public AccountBuilder withLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public boolean isBlocked() {
            return blocked;
        }

        public AccountBuilder withBlocked(boolean blocked) {
            this.blocked = blocked;
            return this;
        }

        public boolean isActive() {
            return active;
        }

        public AccountBuilder withActive(boolean active) {
            this.active = active;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
