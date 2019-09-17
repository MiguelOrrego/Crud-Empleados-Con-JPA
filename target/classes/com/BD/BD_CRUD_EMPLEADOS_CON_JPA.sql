create database empleados_crud;
use empleados_crud;

create table empleados(
documento varchar(30) primary key,
nombre varchar(50),
apellido varchar(50),
direccion varchar(50),
telefono varchar(20)
);
