package com.yigitcanyontem.aboutme.favmovies;

import com.yigitcanyontem.aboutme.users.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "favmovie")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FavMovie {
    @Id
    @SequenceGenerator(
            name = "favmovie_id_seq",
            sequenceName = "favmovie_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "favmovie_id_seq"
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
            name = "movieid",
            nullable = false
    )
    private Integer movieid;

}