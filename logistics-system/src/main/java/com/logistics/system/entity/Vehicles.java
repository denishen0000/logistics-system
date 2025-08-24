package com.logistics.system.entity;


import jakarta.persistence.Entity;         
import jakarta.persistence.Id;                  
import jakarta.persistence.GeneratedValue;      
import jakarta.persistence.GenerationType;      
import jakarta.persistence.Column;              
import lombok.Data;                             
import lombok.NoArgsConstructor;                
import lombok.AllArgsConstructor;  
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
 


@Entity
@Table(name = "Vehicles")
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long vehicleId;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;
}
