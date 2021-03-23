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
import com.sdigitizers.hotel.model.DynamicPrice;
import com.sdigitizers.hotel.model.Hotel;
import com.sdigitizers.hotel.model.Room;
import com.sdigitizers.hotel.model.RoomImage;
import com.sdigitizers.hotel.model.User;
import com.sdigitizers.hotel.repository.DynamicPriceRepository;
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
	
	@Autowired
	private DynamicPriceRepository dynamicPriceRepository;
	
	/*@Autowired
	private RoomImage roomImage;*/
	
	@GetMapping("rooms/{id}/{checkIn}/{checkOut}")
	public Room retriveRoomByIdAndCheckinCheckOutDate(@PathVariable int id, 
			@PathVariable @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime checkIn,
			@PathVariable @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime checkOut){
		Optional<Room> optional = roomRepository.findById(id);
		Room r = optional.get();
		int fromDate = checkIn.getDayOfMonth();
		int uptoDate = checkOut.getDayOfMonth();
		
		List<Integer> days = new ArrayList<>();
		if(fromDate <= uptoDate) {
			for(int k=fromDate; k<uptoDate; k++) {
				days.add(k);
			}
		}else {
			for(int k=fromDate; k<=checkIn.getMonth().maxLength(); k++) {
				days.add(k);
			}
			for(int k=1; k<uptoDate; k++) {
				days.add(k);
			}
		}
		
		double amountSum = 0;
		for(int day : days) {
			DynamicPrice dp = dynamicPriceRepository.getOne(r.getId());
			switch(day) {
				case 1: amountSum += dp.getDay1(); break;
				case 2: amountSum += dp.getDay2(); break;
				case 3: amountSum += dp.getDay3(); break;
				case 4: amountSum += dp.getDay4(); break;
				case 5: amountSum += dp.getDay5(); break;
				case 6: amountSum += dp.getDay6(); break;
				case 7: amountSum += dp.getDay7(); break;
				case 8: amountSum += dp.getDay8(); break;
				case 9: amountSum += dp.getDay9(); break;
				case 10: amountSum += dp.getDay10(); break;
				case 11: amountSum += dp.getDay11(); break;
				case 12: amountSum += dp.getDay12(); break;
				case 13: amountSum += dp.getDay13(); break;
				case 14: amountSum += dp.getDay14(); break;
				case 15: amountSum += dp.getDay15(); break;
				case 16: amountSum += dp.getDay16(); break;
				case 17: amountSum += dp.getDay17(); break;
				case 18: amountSum += dp.getDay18(); break;
				case 19: amountSum += dp.getDay19(); break;
				case 20: amountSum += dp.getDay20(); break;
				case 21: amountSum += dp.getDay21(); break;
				case 22: amountSum += dp.getDay22(); break;
				case 23: amountSum += dp.getDay23(); break;
				case 24: amountSum += dp.getDay24(); break;
				case 25: amountSum += dp.getDay25(); break;
				case 26: amountSum += dp.getDay26(); break;
				case 27: amountSum += dp.getDay27(); break;
				case 28: amountSum += dp.getDay28(); break;
				case 29: amountSum += dp.getDay29(); break;
				case 30: amountSum += dp.getDay30(); break;
				case 31: amountSum += dp.getDay31(); break;
			}
		}
		r.setPrice(amountSum);
		double tariffPrice = r.getTariffPrice() * days.size();
		r.setTariffPrice(tariffPrice);
		double discAmount = tariffPrice - amountSum;
		r.setDiscountValue(discAmount);
		return r;
	}
	
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
    				}else {
    					int fromDate = from.getDayOfMonth();
    					int uptoDate = upto.getDayOfMonth();
    					
    					List<Integer> days = new ArrayList<>();
    					if(fromDate <= uptoDate) {
    						for(int k=fromDate; k<uptoDate; k++) {
    							days.add(k);
    						}
    					}else {
    						for(int k=fromDate; k<=from.getMonth().maxLength(); k++) {
    							days.add(k);
    						}
    						for(int k=1; k<uptoDate; k++) {
    							days.add(k);
    						}
    					}
    					
    					double amountSum = 0;
    					for(int day : days) {
    						DynamicPrice dp = dynamicPriceRepository.getOne(r.getId());
    						switch(day) {
    							case 1: amountSum += dp.getDay1(); break;
    							case 2: amountSum += dp.getDay2(); break;
    							case 3: amountSum += dp.getDay3(); break;
    							case 4: amountSum += dp.getDay4(); break;
    							case 5: amountSum += dp.getDay5(); break;
    							case 6: amountSum += dp.getDay6(); break;
    							case 7: amountSum += dp.getDay7(); break;
    							case 8: amountSum += dp.getDay8(); break;
    							case 9: amountSum += dp.getDay9(); break;
    							case 10: amountSum += dp.getDay10(); break;
    							case 11: amountSum += dp.getDay11(); break;
    							case 12: amountSum += dp.getDay12(); break;
    							case 13: amountSum += dp.getDay13(); break;
    							case 14: amountSum += dp.getDay14(); break;
    							case 15: amountSum += dp.getDay15(); break;
    							case 16: amountSum += dp.getDay16(); break;
    							case 17: amountSum += dp.getDay17(); break;
    							case 18: amountSum += dp.getDay18(); break;
    							case 19: amountSum += dp.getDay19(); break;
    							case 20: amountSum += dp.getDay20(); break;
    							case 21: amountSum += dp.getDay21(); break;
    							case 22: amountSum += dp.getDay22(); break;
    							case 23: amountSum += dp.getDay23(); break;
    							case 24: amountSum += dp.getDay24(); break;
    							case 25: amountSum += dp.getDay25(); break;
    							case 26: amountSum += dp.getDay26(); break;
    							case 27: amountSum += dp.getDay27(); break;
    							case 28: amountSum += dp.getDay28(); break;
    							case 29: amountSum += dp.getDay29(); break;
    							case 30: amountSum += dp.getDay30(); break;
    							case 31: amountSum += dp.getDay31(); break;
    						}
    					}
    					r.setPrice(amountSum);
    					double tariffPrice = r.getTariffPrice() * days.size();
    					r.setTariffPrice(tariffPrice);
    					double discAmount = tariffPrice - amountSum;
    					r.setDiscountValue(discAmount);
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
	public Room createRoom(@PathVariable int id, @RequestBody Room room) {
		
		Optional<Hotel> hotelOptional = hotelRepository.findById(id);
		
		if(!hotelOptional.isPresent()) {
			throw new HotelNotFoundException("id- "+id);
		}
		
		Hotel hotel = hotelOptional.get();
		
		
		room.setHotel(hotel);
		
		Room save = roomRepository.save(room);
		double price = save.getPrice();
		DynamicPrice dynamicPrice = new DynamicPrice();
		dynamicPrice.setRoomId(save.getId());
		dynamicPrice.setDay1(price);
		dynamicPrice.setDay2(price);
		dynamicPrice.setDay3(price);
		dynamicPrice.setDay4(price);
		dynamicPrice.setDay5(price);
		dynamicPrice.setDay6(price);
		dynamicPrice.setDay7(price);
		dynamicPrice.setDay8(price);
		dynamicPrice.setDay9(price);
		dynamicPrice.setDay10(price);
		dynamicPrice.setDay11(price);
		dynamicPrice.setDay12(price);
		dynamicPrice.setDay13(price);
		dynamicPrice.setDay14(price);
		dynamicPrice.setDay15(price);
		dynamicPrice.setDay16(price);
		dynamicPrice.setDay17(price);
		dynamicPrice.setDay18(price);
		dynamicPrice.setDay19(price);
		dynamicPrice.setDay20(price);
		dynamicPrice.setDay21(price);
		dynamicPrice.setDay22(price);
		dynamicPrice.setDay23(price);
		dynamicPrice.setDay24(price);
		dynamicPrice.setDay25(price);
		dynamicPrice.setDay26(price);
		dynamicPrice.setDay27(price);
		dynamicPrice.setDay28(price);
		dynamicPrice.setDay29(price);
		dynamicPrice.setDay30(price);
		dynamicPrice.setDay31(price);
		dynamicPriceRepository.save(dynamicPrice);
		
		return save;
		
	}
	
	
	
	@PostMapping("/{roomid}/uploadroomimage")
    public UploadFileResponse uploadFile(@PathVariable int roomid, @RequestParam MultipartFile file) {
		
		Optional<Room> roomOptional = roomRepository.findById(roomid);
		if(!roomOptional.isPresent())
			throw new UserNotFoundException("id- "+roomid);
		
		Room room = roomOptional.get();
		
		
		
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromHttpUrl("http://45.120.149.244")
        		//.fromPath("45.120.149.244")
        		//.fromCurrentContextPath()
                
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
