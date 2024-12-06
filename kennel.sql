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