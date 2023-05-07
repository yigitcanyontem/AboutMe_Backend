package com.yigitcanyontem.aboutme.favalbums;

import com.yigitcanyontem.aboutme.users.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "favalbums")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FavAlbums {
    @Id
    @Column(name = "id")
    private Integer id;
    @ManyToOne()
    @JoinColumn(name = "usersid")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Users usersid;
    @Column(name = "albumid")
    private String albumid;

}