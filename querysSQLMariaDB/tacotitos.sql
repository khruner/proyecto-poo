create database if not exists tacotitos;
use tacotitos;
#drop database tacotitos;
create table if not exists tipoIngrediente(
	id int not null auto_increment,
    descripcion varchar(100) not null,
    cantMax int not null,
    PRIMARY KEY(id)
) engine innoDB;

create table if not exists Ingredientes(
	id int not null AUTO_INCREMENT,
    nombre varchar(100) not null,
    precio decimal not null,
    fk_tipo int not null,
    PRIMARY KEY(id),
    FOREIGN KEY(fk_tipo) REFERENCES tipoIngrediente(id)
) engine INNODB;

create table if not exists Taco(
	id int not null auto_increment,
    fk_ing int not null,
    PRIMARY KEY(id),
    FOREIGN KEY(fk_ing) REFERENCES Ingredientes(id)
) engine innoDB;