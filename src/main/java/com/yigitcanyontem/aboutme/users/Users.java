package com.yigitcanyontem.aboutme.users;

import com.yigitcanyontem.aboutme.country.Country;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
        @UniqueConstraint(
                name = "user_email_unique",
                columnNames = "email"
        ),
        @UniqueConstraint(
                name = "user_username_unique",
                columnNames = "username"
        )
})
@NoArgsConstructor
public class Users {

    @Id
    @SequenceGenerator(
            name = "user_id_seq",
            sequenceName = "user_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_seq"
    )
    @Column(name = "id")
    private Integer id;

    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;

    @Column(
            name = "date_of_birth",
            nullable = false
    )
    private Date date_of_birth;

    @ManyToOne()
    @JoinColumn(
            name = "country",
            nullable = false
    )
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Country country;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    @Column(
            name = "username",
            nullable = false
    )
    private String username;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    public Users(String firstName, String lastName, Date date_of_birth, Country country, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.date_of_birth = date_of_birth;
        this.country = country;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Users(Integer id, String firstName, String lastName, Date date_of_birth, Country country, String email, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date_of_birth = date_of_birth;
        this.country = country;
        this.email = email;
        this.username = username;
        this.password = password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) && Objects.equals(firstName, users.firstName) && Objects.equals(lastName, users.lastName) && Objects.equals(date_of_birth, users.date_of_birth) && Objects.equals(country, users.country) && Objects.equals(email, users.email) && Objects.equals(username, users.username) && Objects.equals(password, users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, date_of_birth, country, email, username, password);
    }
}
