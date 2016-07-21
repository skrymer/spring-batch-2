drop table if exists stock;
drop table if exists acquisition;
drop table if exists owner;

Create table stock (
	name varchar(50),
	price varchar(50),
	symbol varchar(50),
	ts varchar(50),
	type varchar(50),
	utctime varchar(50),
	volume varchar(50)
);

ALTER TABLE stock
ADD PRIMARY KEY (name);

Create table owner (
	name varchar(50),
    dailyBalance varchar(50)
);

ALTER TABLE owner
ADD PRIMARY KEY (name);

Create table acquisition (
	stockSymbol varchar(20),
    acquired int,
    owner_name varchar(50)
);

ALTER TABLE acquisition
ADD CONSTRAINT fk_OwnerName
FOREIGN KEY (owner_name)
REFERENCES owner(name);

