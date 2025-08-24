package com.logistics.system.entity;


import jakarta.persistence.Entity;         
import jakarta.persistence.Id;                  
import jakarta.persistence.GeneratedValue;      
import jakarta.persistence.GenerationType;      
import jakarta.persistence.Column;              
import lombok.Data;                             
import lombok.NoArgsConstructor;                
import lombok.AllArgsConstructor;  
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
 


@Entity
@Table(name = "Drivers")
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Long driverId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles = new ArrayList<>();

}
