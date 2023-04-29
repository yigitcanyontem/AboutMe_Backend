package com.yigitcanyontem.aboutme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "instagram")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Instagram {
    @Id
    @Column(name = "usersid")
    private Integer usersid;

    @Column(name = "instagramuser")
    private String instagramuser;

}
