package com.yigitcanyontem.aboutme.users.passwords;

import com.yigitcanyontem.aboutme.users.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "passwords")
@NoArgsConstructor
public class Passwords {
    @Id
    @SequenceGenerator(
            name = "passwords_id_seq",
            sequenceName = "passwords_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "passwords_id_seq"
    )
    @Column(name = "id")
    private Integer id;

    @OneToOne()
    @JoinColumn(
            name = "usersid",
            nullable = false
    )
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Users usersid;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    public Passwords(Users usersid, String password) {
        this.usersid = usersid;
        this.password = password;
    }

    public Passwords(Integer id, Users usersid, String password) {
        this.id = id;
        this.usersid = usersid;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Passwords{" +
                "id=" + id +
                ", usersid=" + usersid +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUsersid() {
        return usersid;
    }

    public void setUsersid(Users usersid) {
        this.usersid = usersid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
