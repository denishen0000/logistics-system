package com.logistics.system.entity;


import jakarta.persistence.Entity;         
import jakarta.persistence.Id;                  
import jakarta.persistence.GeneratedValue;      
import jakarta.persistence.GenerationType;      
import jakarta.persistence.Column;              
import lombok.Data;                             
import lombok.NoArgsConstructor;                
import lombok.AllArgsConstructor;               


@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String role;
    @Column(name = "password_hash")
    private String passwordHash;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Driver driver;

}
