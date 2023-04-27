package com.yigitcanyontem.aboutme.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.sql.Date;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private Date date_of_birth;
    @ManyToOne()
    @JoinColumn(name = "country")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Country country;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;


}
