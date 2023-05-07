package com.yigitcanyontem.aboutme.users.socialmedia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yigitcanyontem.aboutme.users.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "socialmedia")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SocialMedia {
    @Id
    private Integer id;
    @OneToOne()
    @JoinColumn(name = "usersid")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Users usersid;
    @Column(name = "instagram")
    private String instagram;
    @Column(name = "pinterest")
    private String pinterest;
    @Column(name = "linkedin")
    private String linkedin;
    @Column(name = "twitter")
    private String twitter;
}
