package com.sdigitizers.hotel.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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

import com.sdigitizers.hotel.HotelNotFoundException;
import com.sdigitizers.hotel.UserNotFoundException;
import com.sdigitizers.hotel.exception.NotFounWalaException;
import com.sdigitizers.hotel.fileupload.FileStorageService;
import com.sdigitizers.hotel.fileupload.UploadFileResponse;
import com.sdigitizers.hotel.model.Hotel;
import com.sdigitizers.hotel.model.Room;
import com.sdigitizers.hotel.model.RoomImage;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.HotelRepository;
import com.sdigitizers.hotel.repository.RoomImageRepository;
import com.sdigitizers.hotel.repository.RoomRepository;

@RestController
public class RoomController {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
    private FileStorageService fileStorageService;
	
	@Autowired
	private RoomImageRepository roomImageRepository;
	
	/*@Autowired
	private RoomImage roomImage;*/
	
	@GetMapping("hotels/{lat}/{lung}/{dist}/{from}/{upto}/{category}")
	public List<Hotel> retriveHotelbyLocation(@PathVariable double lat, @PathVariable double lung, @PathVariable int dist,
			@PathVariable @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime from, @PathVariable @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime upto, @PathVariable int category) {
		
		List<Hotel> hotelsFound = hotelRepository.findHotelByLocation(lat, lung, dist, category);
		List<Integer> busyRoomsId = hotelRepository.findBusyRooms(from, upto);
		
		for(Hotel h : hotelsFound) {
		     List<Room> rooms = new ArrayList<>();
		     for(Room r : h.getRooms()) {
		    	 rooms.add(r);
		     }
            for(Room r : rooms) {
            	for(int i : busyRoomsId) {
    				if(r.getId()==i) {
    					h.getRooms().remove(new Room(i));
    				//	rooms.remove(r);
    				}
    			}		
            }
           // h.setRooms(rooms);
		}
		
		return hotelsFound;
	}
	
	
	
	@GetMapping("rooms")
	public List<Room> retriveAllRoom(){
		return roomRepository.findAll();
	}
	
	/*@PostMapping("rooms")
	public ResponseEntity<Object> createHotel(@RequestBody Room room) {
		Room save = roomRepository.save(room);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(save.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}*/
	
	@PostMapping("/hotel/{id}/rooms")
	public ResponseEntity<Object> createRoom(@PathVariable int id, @RequestBody Room room) {
		
		Optional<Hotel> hotelOptional = hotelRepository.findById(id);
		
		if(!hotelOptional.isPresent()) {
			throw new HotelNotFoundException("id- "+id);
		}
		
		Hotel hotel = hotelOptional.get();
		
		
		room.setHotel(hotel);
		
		roomRepository.save(room);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(room.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	
	
	@PostMapping("/{roomid}/uploadroomimage")
    public UploadFileResponse uploadFile(@PathVariable int roomid, @RequestParam MultipartFile file) {
		
		Optional<Room> roomOptional = roomRepository.findById(roomid);
		if(!roomOptional.isPresent())
			throw new UserNotFoundException("id- "+roomid);
		
		Room room = roomOptional.get();
		
		
		
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder
        		.fromPath("http://45.120.149.244")
        		//.fromCurrentContextPath()
                .path("/images/")
                .path(fileName)
                .toUriString();
        
        RoomImage image = new RoomImage();
        
        image.setRoom(room);
        image.setUrl(fileDownloadUri);
        
        roomImageRepository.save(image);
        
        

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
	
	@DeleteMapping("room/{id}")
	public void deleteRoom(@PathVariable int id) {
		roomRepository.deleteById(id);
	}
	
	@GetMapping("/rooms/{id}")
	public Optional<Room> retriveRoomById(@PathVariable int id) {
		Optional<Room> room = roomRepository.findById(id);
		if(!room.isPresent())
			throw new NotFounWalaException("id- "+id);
		
		return room;
		
	}

}
