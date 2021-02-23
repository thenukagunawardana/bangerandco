package com.eirlss.bangerandco.Service;


import com.eirlss.bangerandco.Model.Vehicle;
import com.eirlss.bangerandco.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VehicleService
{


	@Autowired
	private VehicleRepository vehicleRepository;
	
	public void saveImage(Vehicle imageGallery) {
		vehicleRepository.save(imageGallery);
	}

	public List<Vehicle> getAllActiveImages()
	{
		return vehicleRepository.findAll();
	}

//	public List<Vehicle> listAll()
//	{
//		return vehicleRepository.findAll();
//	}

	public Optional<Vehicle> getImageById(Long id) {
		return vehicleRepository.findById(id);
	}

	public void deleteById(Long id)
	{
		vehicleRepository.deleteById(id);
	}

	public Vehicle getVehicleByID(long vehicleID)
	{

		return vehicleRepository.getByID(vehicleID);
	}
}

