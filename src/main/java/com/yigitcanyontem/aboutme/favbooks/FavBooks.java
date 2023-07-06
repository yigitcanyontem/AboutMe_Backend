package com.yigitcanyontem.aboutme.favbooks;

import com.yigitcanyontem.aboutme.users.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "favbooks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FavBooks {
    @Id
    @SequenceGenerator(
            name = "favbooks_id_seq",
            sequenceName = "favbooks_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "favbooks_id_seq"
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
            name = "bookid",
            nullable = false)
    private String bookid;

}