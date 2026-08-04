CREATE DATABASE vehiclemaintenanceDB;

use vehiclemaintenanceDB;

create table user (
userID int primary key AUTO_INCREMENT,
full_name varchar(100) not null,
phone varchar(50) not null unique,
email varchar(100) not null unique,
user_name varchar(100) not null unique,
password_hash varchar(200) not null,
role varchar(50) not null,
created_at timestamp default current_timestamp

);

create table vehicle(
vehicleID int primary key AUTO_INCREMENT,
userID int not null,
owner_name varchar(100) not null,
reg_number varchar(50) not null unique,
brand varchar(50) not null,
model varchar(50) not null,
vehicle_type varchar(50) not null,

foreign key (userID) references user(userID)
);

create table maintenance_record(
maintenanceID int primary key AUTO_INCREMENT,
vehicleID int not null,
mechanic_name varchar(100) not null,
service_type varchar(200) not null,
cost int,
description varchar(250),
service_date timestamp default current_timestamp,

foreign key(vehicleID) references vehicle(vehicleID) 
);
select * from user;
show tables;
drop table users