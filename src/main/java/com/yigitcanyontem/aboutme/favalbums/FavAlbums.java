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
    @SequenceGenerator(
            name = "favalbums_id_seq",
            sequenceName = "favalbums_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "favalbums_id_seq"
    )
    @Column(name = "id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(
            name = "usersid",
            nullable = false
    )
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Users usersid;

    @Column(
            name = "albumid",
            nullable = false
    )
    private String albumid;

}