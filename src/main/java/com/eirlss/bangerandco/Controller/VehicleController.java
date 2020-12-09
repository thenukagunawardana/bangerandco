package com.eirlss.bangerandco.Controller;


import com.eirlss.bangerandco.Model.Vehicle;
import com.eirlss.bangerandco.Service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Controller
public class VehicleController
{
	
	@Value("${uploadDir}")
	private String uploadFolder;

	@Autowired
	private VehicleService vehicleService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = {"/imageIndex"})
	public String addProductPage()
	{
		return "vehicleIndex";
	}

	@PostMapping("/image/saveImageDetails")
	public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("name") String name,@RequestParam("manufacturer") String manufacturer,
														 @RequestParam("transmission") String transmission,
			@RequestParam("price") double price, @RequestParam("description") String description, Model model, HttpServletRequest request
			,final @RequestParam("image") MultipartFile file)
	{
		try {
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory:: " + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
			}
			String[] names = name.split(",");
			String[] manufacturers=manufacturer.split(",");
			String [] transmissions=transmission.split(",");
			String[] descriptions = description.split(",");
			Date createDate = new Date();
			log.info("Name: " + names[0]+" "+filePath);
			log.info("Manufacturer: "+manufacturers[0]);
			log.info("Transmission: "+transmissions[0]);
			log.info("description: " + descriptions[0]);
			log.info("price: " + price);
			try
			{
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			byte[] imageData = file.getBytes();
			Vehicle vehicle = new Vehicle();
			vehicle.setName(names[0]);
			vehicle.setManufacturer(manufacturers[0]);
			vehicle.setTransmission(transmissions[0]);
			vehicle.setImage(imageData);
			vehicle.setPrice(price);
			vehicle.setDescription(descriptions[0]);
			vehicle.setCreateDate(createDate);
			vehicleService.saveImage(vehicle);
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.info("Exception: " + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/image/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Vehicle> vehicle)
			throws ServletException, IOException {
		log.info("Id :: " + id);
		vehicle = vehicleService.getImageById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(vehicle.get().getImage());
		response.getOutputStream().close();
	}

	@GetMapping("/image/imageDetails")
	String showProductDetails(@RequestParam("id") Long id, Optional<Vehicle> vehicle, Model model) {
		try {
			log.info("Id :: " + id);
			if (id != 0)
			{
				vehicle = vehicleService.getImageById(id);
			
				log.info("products :: " + vehicle);
				if (vehicle.isPresent())
				{
					model.addAttribute("id", vehicle.get().getId());
					model.addAttribute("description", vehicle.get().getDescription());
					model.addAttribute("transmission",vehicle.get().getTransmission());
					model.addAttribute("manufacturer",vehicle.get().getManufacturer());
					model.addAttribute("name", vehicle.get().getName());
					model.addAttribute("price", vehicle.get().getPrice());
					return "vehicleDetails";
				}
				return "redirect:/vehicleIndex";
			}
		return "redirect:/vehicleIndex";
		} catch (Exception e)
		{
			e.printStackTrace();
			return "redirect:/vehicleIndex";
		}	
	}

	@GetMapping("/image/show")
	String show(Model map)
	{
		List<Vehicle> images = vehicleService.getAllActiveImages();
		map.addAttribute("images", images);
		return "vehicles";
	}

	@GetMapping("/deleteImage/{id}")
	public String deleteImage(@PathVariable(value = "id") Long id)
	{
		this.vehicleService.deleteById(id);
		return "redirect:/image/show";
	}
}	

