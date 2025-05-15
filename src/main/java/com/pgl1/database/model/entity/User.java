package com.pgl1.database.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name="Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="UserID")
    private Integer userId;

    @Column(name="UserName", length= 50)
    private String userName;

    @Column(name="UserPhone", length = 50)
    private String userPhone;

    @Column(name="UserLocation", length = 100)
    private String userLocation;

}
