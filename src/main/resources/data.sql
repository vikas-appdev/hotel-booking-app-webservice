insert into hotel(id, city, country, location_coordinates, pincode, state, street, contact_person, country_code, description, email, name, password, phone1, phone2)
values(10001, 'Jorhat', 'India', '19,19', 785001, 'Assam', 'Fancy Ali', 'Malik Hotel Ka', '+91', 'Description wala column hai', 'vikas.appdev@gmail.com', 'Lakshmi Hotel', 'qwerty', 8723992495, 7002132366);
insert into room(id, ac, active, category, clean_toilet, couple_allowed, discount_in_percentage, discount_value, family_allowed, name, no_of_persons, price, service24_7, tv, wifi, hotel_id)
values(11001, 1, 1, 1, 1, 1, 1, 10, 1, 'test', 1, 500, 1, 1, 1, 10001);
insert into room(id, ac, active, category, clean_toilet, couple_allowed, discount_in_percentage, discount_value, family_allowed, name, no_of_persons, price, service24_7, tv, wifi, hotel_id)
values(11002, 1, 1, 1, 1, 1, 1, 10, 1, 'test', 1, 500, 1, 1, 1, 10001);
