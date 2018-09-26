insert into hotel(rating,terms,locality, image_link, id, city, country, latitude, longitude, pincode, state, street, contact_person, country_code, description, email, name, password, phone1, phone2)
values(1,'Kuchh terms and conditions ','Khanapara', 'https://images.pexels.com/photos/261102/pexels-photo-261102.jpeg', 10001, 'Jorhat', 'India', '19.2565', '20.2535', 785001, 'Assam', 'Fancy Ali', 'Malik Hotel Ka', '+91', 'Description wala column hai', 'vikas.appdev@gmail.com', 'Lakshmi Hotel', 'qwerty', 8723992495, 7002132366);
insert into hotel(rating,terms,locality, image_link, id, city, country, latitude, longitude, pincode, state, street, contact_person, country_code, description, email, name, password, phone1, phone2)
values(1,'Kuchh terms and conditions ','Khanapara', 'https://images.pexels.com/photos/261102/pexels-photo-261102.jpeg', 10002, 'Jorhat', 'India', '19.2565', '20.2535', 785001, 'Assam', 'Fancy Ali', 'Malik Hotel Ka', '+91', 'Description wala column hai', 'vikas.appdev@gmail.com', 'Lakshmi Hotel', 'qwerty', 8723992495, 7002132366);

insert into room(id, ac, active, category,cctv,complementry_breakfast,couple_allowed,dining, discount_in_percentage, discount_value, family_allowed,geyser,heater,living_room,mini_fridge,name, no_of_persons,parking, price,resturant, service24_7, tv, wifi, hotel_id)
values(11001, 1, 1, 1, 1, 1,1,1,1,100,1,1,1,1,1, 'test', 1,1, 500, 1, 1, 1,1,10001);
insert into room(id, ac, active, category, clean_toilet, couple_allowed, discount_in_percentage, discount_value, family_allowed, name, no_of_persons, price, service24_7, tv, wifi, hotel_id)
values(11002, 1, 1, 1, 1, 1, 0, 100, 1, 'test', 1, 500, 1, 1, 1, 10001);

insert into hotel_image(id, url, hotel_id) values(5001, 'https://www.flightexpert.com/blog/wp-content/uploads/2017/11/Dhaka-Hotels.jpg', 10001);
insert into hotel_image(id, url, hotel_id) values(5002, 'https://media-cdn.tripadvisor.com/media/photo-s/0e/b9/8a/3e/gomang-boutique-hotel.jpg', 10001);
insert into hotel_image(id, url, hotel_id) values(5003, 'https://imgcld.yatra.com/ytimages/image/upload/t_seo_Hotel_w_930_h_550_c_fill_g_auto_q_40_f_jpg/v1390501242/Domestic%20Hotels/Hotels_Goa/Hotel%20Heritage%20Village%20Club%20Goa/Pool~86.jpg', 10001);
insert into hotel_image(id, url, hotel_id) values(5004, 'https://cdn.pixabay.com/photo/2016/12/13/14/15/swimming-pool-1904161_960_720.jpg', 10001);
insert into hotel_image(id, url, hotel_id) values(5005, 'https://blog.smiley360.com/images/swimming-pool-girl.jpg', 10001);
insert into hotel_image(id, url, hotel_id) values(5006, 'http://pds18.egloos.com/pds/201008/30/49/b0047449_4c7b5b0e4c223.jpg', 10001);
insert into hotel_image(id, url, hotel_id) values(5007, 'https://media-cdn.tripadvisor.com/media/photo-s/05/af/58/df/saigon-soul-pool-parties.jpg', 10001);
insert into hotel_image(id, url, hotel_id) values(5008, 'http://www.qygjxz.com/data/out/111/4134769-hotel-images.jpg', 10001);
insert into hotel_image(id, url, hotel_id) values(5009, 'https://www.flightexpert.com/blog/wp-content/uploads/2017/11/Dhaka-Hotels.jpg', 10001);
insert into hotel_image(id, url, hotel_id) values(50010, 'https://www.flightexpert.com/blog/wp-content/uploads/2017/11/Dhaka-Hotels.jpg', 10001);


insert into room_image(id, url, room_id) values(5001, 'https://www.flightexpert.com/blog/wp-content/uploads/2017/11/Dhaka-Hotels.jpg', 11001);
insert into room_image(id, url, room_id) values(5002, 'https://media-cdn.tripadvisor.com/media/photo-s/0e/b9/8a/3e/gomang-boutique-hotel.jpg', 11001);
insert into room_image(id, url, room_id) values(5003, 'https://imgcld.yatra.com/ytimages/image/upload/t_seo_Hotel_w_930_h_550_c_fill_g_auto_q_40_f_jpg/v1390501242/Domestic%20Hotels/Hotels_Goa/Hotel%20Heritage%20Village%20Club%20Goa/Pool~86.jpg', 11001);
insert into room_image(id, url, room_id) values(5004, 'https://cdn.pixabay.com/photo/2016/12/13/14/15/swimming-pool-1904161_960_720.jpg', 11001);
insert into room_image(id, url, room_id) values(5005, 'https://blog.smiley360.com/images/swimming-pool-girl.jpg', 11001);
insert into room_image(id, url, room_id) values(5006, 'http://pds18.egloos.com/pds/201008/30/49/b0047449_4c7b5b0e4c223.jpg', 11001);
insert into room_image(id, url, room_id) values(5007, 'https://media-cdn.tripadvisor.com/media/photo-s/05/af/58/df/saigon-soul-pool-parties.jpg', 11001);
insert into room_image(id, url, room_id) values(5008, 'http://www.qygjxz.com/data/out/111/4134769-hotel-images.jpg', 11001);
insert into room_image(id, url, room_id) values(5009, 'https://www.flightexpert.com/blog/wp-content/uploads/2017/11/Dhaka-Hotels.jpg', 11001);
insert into room_image(id, url, room_id) values(50010, 'https://www.flightexpert.com/blog/wp-content/uploads/2017/11/Dhaka-Hotels.jpg', 11001);


insert into room_image(id, url, room_id) values(50011, 'https://www.flightexpert.com/blog/wp-content/uploads/2017/11/Dhaka-Hotels.jpg', 11002);
insert into room_image(id, url, room_id) values(50012, 'https://media-cdn.tripadvisor.com/media/photo-s/0e/b9/8a/3e/gomang-boutique-hotel.jpg', 11002);
insert into room_image(id, url, room_id) values(50013, 'https://imgcld.yatra.com/ytimages/image/upload/t_seo_Hotel_w_930_h_550_c_fill_g_auto_q_40_f_jpg/v1390501242/Domestic%20Hotels/Hotels_Goa/Hotel%20Heritage%20Village%20Club%20Goa/Pool~86.jpg', 11002);
insert into room_image(id, url, room_id) values(50014, 'https://cdn.pixabay.com/photo/2016/12/13/14/15/swimming-pool-1904161_960_720.jpg', 11002);
insert into room_image(id, url, room_id) values(50015, 'https://blog.smiley360.com/images/swimming-pool-girl.jpg', 11002);
insert into room_image(id, url, room_id) values(50016, 'http://pds18.egloos.com/pds/201008/30/49/b0047449_4c7b5b0e4c223.jpg', 11002);
insert into room_image(id, url, room_id) values(50017, 'https://media-cdn.tripadvisor.com/media/photo-s/05/af/58/df/saigon-soul-pool-parties.jpg', 11002);
insert into room_image(id, url, room_id) values(50018, 'http://www.qygjxz.com/data/out/111/4134769-hotel-images.jpg', 11002);
insert into room_image(id, url, room_id) values(50019, 'https://www.flightexpert.com/blog/wp-content/uploads/2017/11/Dhaka-Hotels.jpg', 11002);
insert into room_image(id, url, room_id) values(50020, 'https://www.flightexpert.com/blog/wp-content/uploads/2017/11/Dhaka-Hotels.jpg', 11002);

insert into user
(id, account_creation_time, active, city,country,latitude,longitude,pincode,state,street,country_code,date_of_birth,email,gender,image_link,name,password,phone) 
values
(111,'2018-09-07 15:40:53.599',1,'Jorhat','India','19','19',785001,'Assam','Fancy Ali','91','2018-08-30','manmundra00@gmail.com','male','http:','name','qwerty',	0);

insert into admin(id, email, password) values(1001, 'test@test.com', 'test');
insert into fab_room(id, room_id, user_id) values(1222, 11001, 111);

insert into location(id, address, image_link, latitude, longitude, name) values
(1,'airport','https://www.sanatandigitizers.com/thirdparty/hotelapp/guwahati_airport.jpg','19.2565','20.2535','airport');

insert into location(id, address, image_link, latitude, longitude, name) values
(2,'airport','https://www.sanatandigitizers.com/thirdparty/hotelapp/guwahati_isbt.jpg','19.2565','20.2535','isbt');

insert into location(id, address, image_link, latitude, longitude, name) values
(3,'airport','https://www.sanatandigitizers.com/thirdparty/hotelapp/guwahati_jalukbari.jpg','19','19','jalukbari');

insert into location(id, address, image_link, latitude, longitude, name) values
(4,'airport','https://www.sanatandigitizers.com/thirdparty/hotelapp/guwahati_khanapara.jpg','19','19','khanapara');

insert into location(id, address, image_link, latitude, longitude, name) values
(5,'airport','https://www.sanatandigitizers.com/thirdparty/hotelapp/guwahati_paltan_bazar.jpg','19','19','paltanbazar');

insert into location(id, address, image_link, latitude, longitude, name) values
(6,'airport','https://www.sanatandigitizers.com/thirdparty/hotelapp/guwahati_railway.jpg','19','19','railway');


