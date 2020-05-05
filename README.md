
create table appointment( 
	ref_id int auto_increment primary key, 
    doctor_id int NOT NULL, 
    doctor_name varchar(255), 
    hospital_id int NOT NULL, 
    name varchar(255), 
    appointment_time varchar(30) NOT NULL, 
    appointment_date varchar(30) NOT NULL, 
    WardNo varchar(10) NOT NULL
);
