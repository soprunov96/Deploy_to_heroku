
USE mydb;

SELECT * FROM users; 

SELECT * FROM requests;

SELECT * FROM flights where driver_id = null and status ='open' ;


 SELECT flights.id, flights.name, flights.date,flights.departure_point,flights.arrival_point,
 users.first_name,vehicles.model,flights.status,flights.request_id,flights.driver_id,flights.vehicle_id
 FROM ((flights
LEFT JOIN users ON flights.driver_id=users.id
LEFT JOIN vehicles ON flights.vehicle_id=vehicles.id))  order by name  limit 0,40;

SELECT * FROM roles;