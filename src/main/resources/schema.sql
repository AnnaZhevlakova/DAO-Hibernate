create table IF NOT EXISTS netology.persons(
name varchar(255) not null,
surname varchar(255) not null,
age int not null,
phone_number varchar(255),
city_of_living varchar(255),
PRIMARY KEY (name, surname, age)
);




MERGE INTO netology.persons AS target
USING (VALUES
('Cveta','Ivanova',20,'+79826342536','MOSCOW'),
('Anna','Petrova',25,'+79828735452','SPB'),
('Sergey', 'Sidorov', 35, '+79161234569', 'MOSCOW'),
('Dmitry', 'Kozlov', 40, '+79161234571', 'EKATERINBURG'),
('Alexey', 'Popov', 32, '+79161234572', 'MOSCOW'),
('Maria', 'Volkova', 30, '+79161234568', 'MOSCOW')
) AS source(name, surname, age, phone_number,city_of_living)
ON target.phone_number = source.phone_number
WHEN NOT MATCHED THEN
  INSERT (name, surname, age, phone_number,city_of_living)
  VALUES (source.name, source.surname, source.age, source.phone_number, source.city_of_living);

