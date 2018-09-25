package com.sdigitizers.hotel.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sdigitizers.hotel.UserNotFoundException;
import com.sdigitizers.hotel.fileupload.FileStorageService;
import com.sdigitizers.hotel.fileupload.UploadFileResponse;
import com.sdigitizers.hotel.model.Hotel;
import com.sdigitizers.hotel.model.HotelImage;
import com.sdigitizers.hotel.model.Room;
import com.sdigitizers.hotel.model.RoomImage;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.HotelImageRepository;
import com.sdigitizers.hotel.repository.HotelRepository;
import com.sdigitizers.hotel.repository.UserRepository;

@RestController
public class HotelController {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HotelImageRepository hotelImageRepository;
	
	@Autowired
    private FileStorageService fileStorageService;

	@GetMapping("hotels")
	public List<Hotel> retriveAllHotels() {
		return hotelRepository.findAll();
	}
	
	@GetMapping("usse")
	public List<User> retriveAllUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping("hotels")
	
	public ResponseEntity<Object> createHotel(@RequestBody Hotel hotel) {
		Hotel savedHotel = hotelRepository.save(hotel);
		
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedHotel.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/hotels/{id}")
	public Optional<Hotel> retriveUserByEmail(@PathVariable int id) {
		Optional<Hotel> hotel = hotelRepository.findById(id);
		/*if(!user.isPresent())
			throw new UserNotFoundException("id- "+id);*/
		
		return hotel;
		
	}
	
	@PostMapping("/{hotelid}/uploadroomimage")
    public UploadFileResponse uploadFile(@PathVariable int hotelid, @RequestParam MultipartFile file) {
		
		Optional<Hotel> hotelOptional = hotelRepository.findById(hotelid);
		if(!hotelOptional.isPresent())
			throw new UserNotFoundException("id- "+hotelid);
		
		Hotel hotel = hotelOptional.get();
		
		
		
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder
        		//.fromPath("https://www.sanatandigitizers.com")
        		.fromCurrentContextPath()
                .path("/images/")
                .path(fileName)
                .toUriString();
        
        HotelImage image = new HotelImage();
        
        image.setHotel(hotel);
        image.setUrl(fileDownloadUri);
        
        hotelImageRepository.save(image);
        
        

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
	
	@DeleteMapping("hotel/{id}")
	public void deleteRoom(@PathVariable int id) {
		hotelRepository.deleteById(id);
	}

}
