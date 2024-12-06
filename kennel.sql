/*======================================================================
 * 
 *  NAME: Alexa Williams
 *  ASSIGN:  HW-2, Question 1
 *  COURSE:  CPSC 321, Fall 2024
 *  DESC: this creates four relations containing important information 
 *        for airline travel and populates the tables with examples
 * 
 *======================================================================*/

DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS dog;
DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS exercise;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS working;
DROP TABLE IF EXISTS veterinarian;
DROP TABLE IF EXISTS contact;

/*
customer :
    this table holds important identification information for different customers
*/
CREATE TABLE customer(
    id INT NOT NULL,
    name VARCHAR NOT NULL,
    balance int,
    card VARCHAR NOT NULL,
    PRIMARY KEY (id)
);

/*
dog :
    this table holds important identification information for different dogs
*/
CREATE TABLE dog(
    name VARCHAR NOT NULL,
    owner INT NOT NULL,
    vet VARCHAR NOT NULL,
    breed VARCHAR NOT NULL,
    birthday DATE NOT NULL,
    exercise INT NOT NULL,
    e_contact INT NOT NULL,
    color VARCHAR NOT NULL,
    food INT NOT NULL,
    PRIMARY KEY (name, owner),
    FOREIGN KEY (owner) REFERENCES customer(id),
    FOREIGN KEY (e_contact) REFERENCES contact(id),
    FOREIGN KEY (vet) REFERENCES veterinarian(name),
    FOREIGN KEY (exercise) REFERENCES exercise(id),
    FOREIGN KEY (food) REFERENCES food(id)
);

/*
food :
    this table holds important identification information for different food plans
*/
CREATE TABLE food(
    id INT NOT NULL,
    breakfast VARCHAR NOT NULL,
    lunch VARCHAR NOT NULL,
    dinner VARCHAR NOT NULL,
    PRIMARY KEY (id)
);

/*
exercise :
    this table holds important identification information for different exercise plans
*/
CREATE TABLE exercise(
    id INT NOT NULL,
    days INT NOT NULL,
    cost INT NOT NULL,
    time INT NOT NULL,
    alone BOOLEAN NOT NULL,
    employee INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (employee) REFERENCES employee(id)
);

/*
employee :
    this table holds important identification information for different employees
*/
CREATE TABLE employee(
    id INT NOT NULL,
    first VARCHAR NOT NULL,
    last VARCHAR NOT NULL
    start DATE NOT NULL,
    card VARCHAR NOT NULL,
    company VARCHAR NOT NULL,
    PRIMARY KEY (id),
);

/*
working :
    this table holds important identification information for employees working for different companies
*/
CREATE TABLE working(
    employee INT NOT NULL,
    company VARCHAR NOT NULL,
    PRIMARY KEY (employee, company)
);

/*
company :
    this table holds important identification information for different companies
*/
CREATE TABLE company(
    name VARCHAR NOT NULL,
    address VARCHAR NOT NULL,
    city VARCHAR NOT NULL,
    state VARCHAR NOT NULL,
    PRIMARY KEY (name)
);

/*
reservation :
    this table holds important identification information for different reservations
*/
CREATE TABLE reservation(
    confirm_num INT NOT NULL,
    kennel VARCHAR NOT NULL,
    dog VARCHAR NOT NULL,
    owner INT NOT NULL,
    start DATE NOT NULL,
    end DATE NOT NULL,
    PRIMARY KEY (confirm_num)
    FOREIGN KEY (kennel) REFERENCES company(name),
    FOREIGN KEY (owner) REFERENCES customer(id),
    FOREIGN KEY (dog, owner) REFERENCES dog(name, owner)
);

/*
veterinarian :
    this table holds important identification information for different veterinarians
*/
CREATE TABLE veterinarian(
    name VARCHAR NOT NULL,
    phone VARCHAR NOT NULL,
    PRIMARY KEY (name)
);

/*
contact :
    this table holds important identification information for different emergency contacts
*/
CREATE TABLE contact(
    id INT NOT NULL,
    name VARCHAR NOT NULL,
    phone VARCHAR NOT NULL,
    PRIMARY KEY (id)
);

-- Insert into customer
INSERT INTO customer (id, name, balance, card) VALUES
(1, 'Alice Smith', 200, '1234-5678-9012-3456'),
(2, 'Bob Johnson', 350, '2345-6789-0123-4567'),
(3, 'Carol White', 120, '3456-7890-1234-5678'),
(4, 'David Brown', 400, '4567-8901-2345-6789'),
(5, 'Eva Green', 500, '5678-9012-3456-7890'),
(6, 'Frank Black', 600, '6789-0123-4567-8901'),
(7, 'Grace Lee', 300, '7890-1234-5678-9012'),
(8, 'Hank Hill', 150, '8901-2345-6789-0123'),
(9, 'Ivy King', 250, '9012-3456-7890-1234'),
(10, 'Jack White', 320, '0123-4567-8901-2345'),
(11, 'Karen Adams', 180, '3456-7890-1234-5678'),
(12, 'Liam Green', 450, '5678-9012-3456-7890'),
(13, 'Mona Ray', 170, '6789-0123-4567-8901'),
(14, 'Nate Ford', 310, '7890-1234-5678-9012'),
(15, 'Olivia Black', 220, '8901-2345-6789-0123'),
(16, 'Paul Young', 190, '9012-3456-7890-1234'),
(17, 'Quinn Bell', 260, '0123-4567-8901-2345'),
(18, 'Rita Stone', 330, '1234-5678-9012-3456'),
(25, 'Olivia Johnson', 290, '7890-1234-5678-9012'),
(26, 'Lucas Evans', 360, '8901-2345-6789-0123'),
(27, 'Sophia Martinez', 420, '9012-3456-7890-1234'),
(28, 'James Wilson', 180, '0123-4567-8901-2345'),
(29, 'Emily Garcia', 350, '1234-5678-9012-3456'),
(30, 'Ava Robinson', 230, '2345-6789-0123-4567'),
(31, 'Mason Davis', 500, '3456-7890-1234-5678'),
(32, 'Isabella Lopez', 270, '4567-8901-2345-6789'),
(33, 'Liam Hernandez', 310, '5678-9012-3456-7890'),
(34, 'Emma Hall', 200, '6789-0123-4567-8901'),
(35, 'William Clark', 400, '7890-1234-5678-9012'),
(36, 'Amelia Lewis', 320, '8901-2345-6789-0123'),
(37, 'Evelyn Young', 240, '9012-3456-7890-1234'),
(38, 'Benjamin Hill', 380, '0123-4567-8901-2345'),
(39, 'Abigail Scott', 150, '1234-5678-9012-3456'),
(40, 'Alexander Adams', 450, '2345-6789-0123-4567');

-- Insert into veterinarian
INSERT INTO veterinarian (name, phone) VALUES
('Dr. Green', '555-1234'),
('Dr. Blue', '555-5678'),
('Dr. Brown', '555-8765'),
('Dr. Violet', '555-4321'),
('Dr. Indigo', '555-2468'),
('Dr. Orange', '555-1357');

-- Insert into contact
INSERT INTO contact (id, name, phone) VALUES
(1, 'John Doe', '555-1111'),
(2, 'Jane Doe', '555-2222'),
(3, 'Sarah Connor', '555-3333'),
(4, 'Peter Parker', '555-4444'),
(5, 'Mary Jane', '555-5555'),
(6, 'Tony Stark', '555-6666'),
(7, 'Bruce Wayne', '555-7777'),
(8, 'Clark Kent', '555-8888'),
(9, 'Diana Prince', '555-9999');

-- Insert into food
INSERT INTO food (id, breakfast, lunch, dinner) VALUES
(1, 'Kibble', 'Chicken', 'Lamb'),
(2, 'Puppy Food', 'Beef', 'Fish'),
(3, 'Special Diet', 'Turkey', 'Duck'),
(4, 'Premium Kibble', 'Salmon', 'Rabbit'),
(5, 'Low-Cal Kibble', 'Vegetables', 'Venison'),
(6, 'Grain-Free', 'Pork', 'Quail');

-- Insert into exercise
INSERT INTO exercise (id, days, cost, time, alone, employee) VALUES
(1, 5, 100, 30, TRUE, 1),
(2, 3, 50, 60, FALSE, 2),
(3, 7, 150, 45, TRUE, 3),
(4, 2, 40, 20, TRUE, 4),
(5, 6, 130, 55, FALSE, 5),
(6, 4, 80, 35, FALSE, 6);

-- Insert into employee
INSERT INTO employee (id, first, last, start, card, company) VALUES
(1, 'Jake', 'Miller', '2023-01-15', '1234-5678-9012-3456', 'Happy Tails Inc.'),
(2, 'Emily', 'Davis', '2022-03-20', '2345-6789-0123-4567', 'Pawfect Fitness Co.'),
(3, 'Liam', 'Wilson', '2021-05-10', '3456-7890-1234-5678', 'Fur Friends Ltd.'),
(4, 'Sophia', 'Taylor', '2023-07-25', '4567-8901-2345-6789', 'Happy Tails Inc.'),
(5, 'Oliver', 'Brown', '2023-09-10', '5678-9012-3456-7890', 'Pawfect Fitness Co.'),
(6, 'Ella', 'Moore', '2023-11-15', '6789-0123-4567-8901', 'Fur Friends Ltd.')
(7, 'Nathan', 'Harris', '2023-05-10', '7890-2345-6789-1234', 'Happy Tails Inc.'),
(8, 'Emma', 'Clark', '2022-10-18', '8901-3456-7890-2345', 'Pawfect Fitness Co.'),
(9, 'Lucas', 'Martinez', '2021-09-25', '9012-4567-8901-3456', 'Fur Friends Ltd.'),
(10, 'Sophia', 'Nguyen', '2023-06-05', '0123-5678-9012-4567', 'Happy Tails Inc.'),
(11, 'Daniel', 'King', '2022-07-15', '1234-6789-0123-5678', 'Pawfect Fitness Co.'),
(12, 'Lily', 'Walker', '2023-03-20', '2345-7890-1234-6789', 'Fur Friends Ltd.'),
(13, 'Logan', 'Harris', '2021-12-15', '0123-5678-2345-7890', 'Happy Tails Inc.'),
(14, 'Ella', 'Ramirez', '2020-06-20', '1234-6789-3456-8901', 'Pawfect Fitness Co.'),
(15, 'Carter', 'Reed', '2023-04-10', '2345-7890-4567-9012', 'Fur Friends Ltd.'),
(16, 'Chloe', 'Perez', '2022-01-18', '3456-8901-5678-0123', 'Happy Tails Inc.'),
(17, 'Sophia', 'Bennett', '2021-03-12', '4567-9012-6789-1234', 'Pawfect Fitness Co.'),
(18, 'Jacob', 'Collins', '2023-09-05', '5678-0123-7890-2345', 'Fur Friends Ltd.');

-- Insert into working
INSERT INTO working (employee, company) VALUES
(1, 'Happy Tails Inc.'),
(1, 'Pawfect Fitness Co.')
(2, 'Pawfect Fitness Co.'),
(3, 'Fur Friends Ltd.'),
(4, 'Happy Tails Inc.'),
(5, 'Pawfect Fitness Co.'),
(6, 'Fur Friends Ltd.')
(7, 'Happy Tails Inc.'),
(8, 'Pawfect Fitness Co.'),
(9, 'Fur Friends Ltd.'),
(10, 'Happy Tails Inc.'),
(11, 'Pawfect Fitness Co.'),
(12, 'Fur Friends Ltd.'),
(13, 'Happy Tails Inc.'),
(14, 'Pawfect Fitness Co.'),
(15, 'Fur Friends Ltd.'),
(16, 'Happy Tails Inc.'),
(17, 'Pawfect Fitness Co.'),
(18, 'Fur Friends Ltd.');

-- Insert into company
INSERT INTO company (name, address, city, state) VALUES
('Happy Tails Inc.', '123 Dog St.', 'New York', 'NY'),
('Pawfect Fitness Co.', '456 Bark Ave.', 'Los Angeles', 'CA'),
('Fur Friends Ltd.', '789 Woof Rd.', 'Chicago', 'IL');

-- Insert into dog
INSERT INTO dog (name, owner, vet, breed, birthday, exercise, e_contact, color, food) VALUES
('Buddy', 1, 'Dr. Green', 'Golden Retriever', '2018-05-10', 1, 1, 'Golden', 1),
('Max', 2, 'Dr. Blue', 'Labrador', '2019-07-15', 2, 2, 'Black', 2),
('Bella', 3, 'Dr. Brown', 'Beagle', '2020-09-20', 3, 3, 'Brown', 3),
('Rocky', 4, 'Dr. Violet', 'Bulldog', '2021-11-25', 4, 4, 'White', 4),
('Daisy', 5, 'Dr. Indigo', 'Poodle', '2017-04-12', 5, 5, 'Cream', 5),
('Charlie', 6, 'Dr. Orange', 'German Shepherd', '2016-08-18', 6, 6, 'Tan', 6)
('Luna', 7, 'Dr. Green', 'Husky', '2018-02-15', 1, 2, 'Gray', 4),
('Bailey', 8, 'Dr. Blue', 'Boxer', '2019-03-25', 2, 3, 'Brindle', 2),
('Cooper', 9, 'Dr. Brown', 'Dachshund', '2020-05-05', 3, 1, 'Brown', 5),
('Milo', 10, 'Dr. Violet', 'Cocker Spaniel', '2021-06-10', 4, 4, 'Black', 6),
('Rosie', 11, 'Dr. Indigo', 'Chihuahua', '2017-09-20', 5, 5, 'Fawn', 3),
('Duke', 12, 'Dr. Orange', 'Doberman', '2016-12-25', 6, 6, 'Black', 1),
('Ruby', 1, 'Dr. Green', 'Pug', '2019-01-14', 2, 2, 'Fawn', 4),
('Leo', 2, 'Dr. Blue', 'Rottweiler', '2018-04-11', 3, 3, 'Black and Tan', 5),
('Zoe', 4, 'Dr. Green', 'Shih Tzu', '2019-03-30', 1, 4, 'White', 4),
('Mochi', 5, 'Dr. Blue', 'Corgi', '2020-08-12', 2, 5, 'Tan', 5),
('Kona', 6, 'Dr. Brown', 'Australian Shepherd', '2018-10-20', 3, 6, 'Black', 6),
('Ollie', 7, 'Dr. Violet', 'Border Collie', '2021-01-15', 4, 7, 'White and Black', 3),
('Penny', 8, 'Dr. Indigo', 'Cavalier King Charles Spaniel', '2022-02-10', 5, 8, 'Chestnut', 2),
('Finn', 9, 'Dr. Orange', 'Dalmatian', '2020-11-05', 6, 9, 'White with Black Spots', 1),
('Loki', 10, 'Dr. Green', 'Pomeranian', '2021-05-25', 1, 1, 'Golden', 4),
('Scout', 11, 'Dr. Blue', 'Weimaraner', '2019-06-30', 2, 2, 'Gray', 5),
('Bear', 12, 'Dr. Brown', 'Akita', '2020-03-10', 3, 3, 'Red', 6),
('Pepper', 13, 'Dr. Violet', 'Boston Terrier', '2018-07-04', 4, 4, 'Black and White', 3),
('Coco', 14, 'Dr. Indigo', 'French Bulldog', '2021-12-12', 5, 5, 'Fawn', 2),
('Maggie', 15, 'Dr. Orange', 'German Shorthaired Pointer', '2020-04-18', 6, 6, 'Brown', 1),
('Riley', 16, 'Dr. Green', 'Pit Bull', '2019-01-22', 1, 7, 'Blue', 4),
('Harper', 17, 'Dr. Blue', 'Sheltie', '2021-03-16', 2, 8, 'Sable', 5),
('Baxter', 18, 'Dr. Brown', 'Basset Hound', '2018-09-30', 3, 9, 'Tri-Color', 6),
('Buddy', 25, 'Dr. Green', 'German Shepherd', '2020-03-11', 1, 1, 'Black and Tan', 4),
('Bella', 26, 'Dr. Blue', 'Labrador Retriever', '2019-05-20', 2, 2, 'Yellow', 5),
('Charlie', 27, 'Dr. Brown', 'Boxer', '2018-11-13', 3, 3, 'Brindle', 6),
('Lucy', 28, 'Dr. Violet', 'Pomeranian', '2021-02-24', 4, 4, 'Cream', 3),
('Max', 29, 'Dr. Indigo', 'Border Collie', '2017-09-30', 5, 5, 'Black and White', 2),
('Luna', 30, 'Dr. Orange', 'Siberian Husky', '2016-12-12', 6, 6, 'Gray', 1),
('Rocky', 31, 'Dr. Green', 'French Bulldog', '2021-08-15', 1, 7, 'Black', 4),
('Milo', 32, 'Dr. Blue', 'Beagle', '2018-06-10', 2, 8, 'Tri-Color', 5),
('Sadie', 33, 'Dr. Brown', 'Golden Retriever', '2019-10-21', 3, 9, 'Golden', 6),
('Bailey', 34, 'Dr. Violet', 'Chihuahua', '2020-04-18', 4, 10, 'Fawn', 3),
('Daisy', 35, 'Dr. Indigo', 'Pit Bull', '2017-07-14', 5, 11, 'Blue', 2),
('Oliver', 36, 'Dr. Orange', 'Rottweiler', '2016-02-28', 6, 12, 'Black and Tan', 1),
('Zoey', 37, 'Dr. Green', 'Cocker Spaniel', '2021-01-30', 1, 13, 'Golden', 4),
('Toby', 38, 'Dr. Blue', 'Dalmatian', '2019-09-25', 2, 14, 'White with Black Spots', 5),
('Lily', 39, 'Dr. Brown', 'Shih Tzu', '2020-11-11', 3, 15, 'White', 6),
('Leo', 40, 'Dr. Violet', 'Doberman', '2018-08-09', 4, 16, 'Black', 3);

-- Insert into reservation
INSERT INTO reservation (confirm_num, kennel, dog, owner, start, end) VALUES
(101, 'Happy Tails Inc.', 'Buddy', 1, '2024-01-01', '2024-01-10'),
(102, 'Pawfect Fitness Co.', 'Max', 2, '2024-02-01', '2024-02-15'),
(103, 'Fur Friends Ltd.', 'Bella', 3, '2024-03-01', '2024-03-07'),
(104, 'Happy Tails Inc.', 'Rocky', 4, '2024-04-01', '2024-04-10'),
(105, 'Pawfect Fitness Co.', 'Daisy', 5, '2024-05-01', '2024-05-15'),
(106, 'Fur Friends Ltd.', 'Charlie', 6, '2024-06-01', '2024-06-07')
(107, 'Happy Tails Inc.', 'Luna', 7, '2024-07-01', '2024-07-15'),
(108, 'Pawfect Fitness Co.', 'Bailey', 8, '2024-08-01', '2024-08-10'),
(109, 'Fur Friends Ltd.', 'Cooper', 9, '2024-09-05', '2024-09-20'),
(110, 'Happy Tails Inc.', 'Milo', 10, '2024-10-01', '2024-10-10'),
(111, 'Pawfect Fitness Co.', 'Rosie', 11, '2024-11-01', '2024-11-15'),
(112, 'Fur Friends Ltd.', 'Duke', 12, '2024-12-01', '2024-12-10'),
(113, 'Happy Tails Inc.', 'Ruby', 1, '2024-06-01', '2024-06-07'),
(114, 'Pawfect Fitness Co.', 'Leo', 2, '2024-06-10', '2024-06-17'),
(115, 'Fur Friends Ltd.', 'Buddy', 1, '2024-07-20', '2024-07-30'),
(116, 'Happy Tails Inc.', 'Max', 2, '2024-08-15', '2024-08-25'),
(117, 'Happy Tails Inc.', 'Zoe', 4, '2024-01-15', '2024-01-20'),
(118, 'Pawfect Fitness Co.', 'Mochi', 5, '2024-02-10', '2024-02-15'),
(119, 'Fur Friends Ltd.', 'Kona', 6, '2024-03-05', '2024-03-12'),
(120, 'Happy Tails Inc.', 'Ollie', 7, '2024-04-01', '2024-04-07'),
(121, 'Pawfect Fitness Co.', 'Penny', 8, '2024-05-10', '2024-05-17'),
(122, 'Fur Friends Ltd.', 'Finn', 9, '2024-06-01', '2024-06-08'),
(123, 'Happy Tails Inc.', 'Loki', 10, '2024-07-05', '2024-07-12'),
(124, 'Pawfect Fitness Co.', 'Scout', 11, '2024-08-15', '2024-08-22'),
(125, 'Fur Friends Ltd.', 'Bear', 12, '2024-09-10', '2024-09-15'),
(126, 'Happy Tails Inc.', 'Pepper', 13, '2024-10-20', '2024-10-25'),
(127, 'Pawfect Fitness Co.', 'Coco', 14, '2024-11-01', '2024-11-07'),
(128, 'Fur Friends Ltd.', 'Maggie', 15, '2024-12-05', '2024-12-10'),
(129, 'Happy Tails Inc.', 'Riley', 16, '2024-01-25', '2024-01-30'),
(130, 'Pawfect Fitness Co.', 'Harper', 17, '2024-02-15', '2024-02-20'),
(131, 'Fur Friends Ltd.', 'Baxter', 18, '2024-03-10', '2024-03-15'),
(132, 'Happy Tails Inc.', 'Buddy', 1, '2024-04-05', '2024-04-12'),
(133, 'Pawfect Fitness Co.', 'Max', 2, '2024-05-01', '2024-05-08'),
(134, 'Fur Friends Ltd.', 'Bella', 3, '2024-06-15', '2024-06-22'),
(135, 'Happy Tails Inc.', 'Buddy', 25, '2024-01-05', '2024-01-12'),
(136, 'Pawfect Fitness Co.', 'Bella', 26, '2024-02-15', '2024-02-22'),
(137, 'Fur Friends Ltd.', 'Charlie', 27, '2024-03-10', '2024-03-17'),
(138, 'Happy Tails Inc.', 'Lucy', 28, '2024-04-01', '2024-04-08'),
(139, 'Pawfect Fitness Co.', 'Max', 29, '2024-05-20', '2024-05-27'),
(140, 'Fur Friends Ltd.', 'Luna', 30, '2024-06-15', '2024-06-22'),
(141, 'Happy Tails Inc.', 'Rocky', 31, '2024-07-10', '2024-07-17'),
(142, 'Pawfect Fitness Co.', 'Milo', 32, '2024-08-05', '2024-08-12'),
(143, 'Fur Friends Ltd.', 'Sadie', 33, '2024-09-01', '2024-09-08'),
(144, 'Happy Tails Inc.', 'Bailey', 34, '2024-10-15', '2024-10-22'),
(145, 'Pawfect Fitness Co.', 'Daisy', 35, '2024-11-10', '2024-11-17'),
(146, 'Fur Friends Ltd.', 'Oliver', 36, '2024-12-01', '2024-12-08'),
(147, 'Happy Tails Inc.', 'Zoey', 37, '2024-01-20', '2024-01-27'),
(148, 'Pawfect Fitness Co.', 'Toby', 38, '2024-02-25', '2024-03-03'),
(149, 'Fur Friends Ltd.', 'Lily', 39, '2024-03-15', '2024-03-22'),
(150, 'Happy Tails Inc.', 'Leo', 40, '2024-04-10', '2024-04-17');
