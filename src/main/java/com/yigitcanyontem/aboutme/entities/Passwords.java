package com.yigitcanyontem.aboutme.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "passwords")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Passwords {
    @Id
    @Column(name = "id")
    private Integer id;
    @OneToOne()
    @JoinColumn(name = "usersid")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Users usersid;
    @Column(name = "password")
    private String password;
}
