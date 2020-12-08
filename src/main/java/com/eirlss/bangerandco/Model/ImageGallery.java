package com.eirlss.bangerandco.Model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "image_gallery")
public class ImageGallery {

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

	public ImageGallery() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

		public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

//	@Override
//	public String toString()
//	{
//		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", image="
//				+ Arrays.toString(image) + ", createDate=" + createDate + "]";
//	}


	@Override
	public String toString()
	{
		return "ImageGallery{" +
				"id=" + id +
				", name='" + name + '\'' +
				", transmission='" + transmission + '\'' +
				", manufacturer='" + manufacturer + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				", image=" + Arrays.toString(image) +
				", createDate=" + createDate +
				'}';
	}
}


