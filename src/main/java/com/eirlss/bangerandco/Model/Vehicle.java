package com.eirlss.bangerandco.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle") //image_gallery
public class Vehicle
{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "transmission", nullable = false)
	private String transmission;

	@Column(name = "manufacturer", nullable = false)
	private String manufacturer;
	
	@Column(name = "description", nullable = false)
	private String description;	
	
	@Column(name = "price",nullable = false, precision = 10, scale = 2)
    private double price;

	@Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "fueltype",nullable = false)
    private String fuelType;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = Booking.class)
	private List<Booking> bookingList;


}


