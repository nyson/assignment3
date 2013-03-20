drop table if exists konto;
drop table if exists gjordatrans;
drop table if exists person;

create table konto (
	kontonr char(13),
	kontotyp varchar(10),
	namn varchar(50),
	saldo double
);


create table gjordatrans (
	kontonr char(13),
	typ char(3),
	belopp double,
	OCRmeddelande varchar(70)
);


create table person (
	name varchar(50),
	gatuadress varchar(50),
	postnr char(5),
	stad varchar(50)
);

insert into person values('Sylvester','555 Suffering Succotash','49498','Tweetyville');
insert into person values('Yosemite Sam','14 Half Dome Blvd','90667','Yosemite');
insert into person values('Speedy Gonzales','32 Hot Sauce Road','33338','Mexico');
insert into person values('The Roadrunner','The Second Valley from the Left','98765','The Desert');
insert into person values('Daffy Duck','The Pond','99100','Walden');
insert into person values('Porky Pig','879 Stone House Ave','87645','Poughkeepsie');
insert into person values('Bugs Bunny','31 Rabbit Hole','87733','Brooklyn NY');
insert into person values('Yogi Bear','251 Picnic Basket Rd','53455','Yellowstone Park');
insert into person values('Elmer Fudd','Merry Melody Village','10901','Warner Brothers Lot');
insert into person values('Wile E. Coyote','The Second Valley from the Right','98764','The Desert');

insert into konto values('121223', 'spar', 'Sylvester', 16000.50);
insert into konto values('12034500', 'loen', 'Sylvester', 540.11);
insert into konto values('8264i33', 'spar', 'Elmer Fudd', 1000.50);

insert into gjordatrans values('121223', 'ins', 2009.10, 'Pengar från mormor');
insert into gjordatrans values('121223', 'utt', 2009.10, 'Kattmat');
insert into gjordatrans values('8264i33', 'utt', 7630.45, 'Gevär');
insert into gjordatrans values('8264i33', 'ins', 5600.40, 'Lottovinst');
