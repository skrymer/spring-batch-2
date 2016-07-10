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

insert into owner values("sonni", 0);
insert into acquisition values("MSFT", 10, "sonni");
insert into acquisition values("YHOO", 100, "sonni");
insert into acquisition values("AAPL", 5, "sonni");
insert into owner values("kenneth", 0);
insert into acquisition values("YHOO", 50, "kenneth");
insert into owner values("roger", 0);
insert into acquisition values("AAPL", 200, "roger");
insert into owner values("scott", 0);
insert into acquisition values("MSFT", 40, "scott");
insert into acquisition values("YHOO", 80, "scott");

