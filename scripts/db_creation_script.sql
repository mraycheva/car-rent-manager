create table person(
id identity,
f_name nvarchar(30) not null,
l_name nvarchar(30) not null,
age int not null,
salary decimal(7,2) not null,
gender varchar(6) not null,
check(age>0 and age<120),
check(salary>0)
);

create table car(
id identity,
brand nvarchar(30) not null,
model nvarchar(30) not null,
year int not null,
engine nvarchar(30) not null,
check(year>1900 and year<2030)
);

create table rent(
id identity,
person_id int not null,
car_id int not null,
finish_date date,
start_date date not null,
constraint fk_rent_person foreign key (person_id) references person(id)
on delete no action
on update cascade,
constraint fk_rent_car foreign key (car_id) references car(id)
on delete no action
on update cascade
);