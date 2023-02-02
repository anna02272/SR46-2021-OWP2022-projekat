DROP SCHEMA IF EXISTS EUprava;
CREATE SCHEMA  EUprava DEFAULT CHARACTER SET utf8;
USE EUprava;

CREATE TABLE Korisnici (
	Id bigint  auto_increment PRIMARY KEY,
	Email VARCHAR(50) NOT NULL UNIQUE,
	Lozinka VARCHAR(50) NOT NULL,
    Ime VARCHAR(50) NOT NULL,
    Prezime VARCHAR(50) NOT NULL,
    DatumRodjenja DATE NOT NULL,
    JMBG VARCHAR(13) NOT NULL UNIQUE,
    Adresa VARCHAR(50) NOT NULL,
    BrojTelefona VARCHAR(50) NOT NULL,
    DatumIVremeRegistracije DATETIME NOT NULL DEFAULT NOW(),
    EUloga ENUM('MEDICINSKOOSOBLJE', 'PACIJENT', 'ADMINISTRATOR') DEFAULT 'PACIJENT'
);

CREATE TABLE ProizvodjaciVakcine(
	Id BIGINT auto_increment PRIMARY KEY,
	Proizvodjac VARCHAR(50) NOT NULL,
    DrzavaProizvodnje VARCHAR(50) NOT NULL
);

CREATE TABLE Vakcine (
	Id BIGINT  auto_increment PRIMARY KEY,
	Ime VARCHAR(50) NOT NULL,
    DostupnaKolicina INT default 0,
    ProizvodjacId BIGINT,
	FOREIGN KEY (ProizvodjacId) REFERENCES ProizvodjaciVakcine (Id) 	
    ON DELETE CASCADE
);

CREATE TABLE Vesti (
	Id BIGINT auto_increment PRIMARY KEY,
	NazivVesti VARCHAR(50) NOT NULL,
    SadrzajVesti VARCHAR(50) NOT NULL,
    DatumIVremeObjavljivanja datetime
    
    );
    
CREATE TABLE VestiOObolelima (
	Id BIGINT auto_increment PRIMARY KEY,
	OboleliUPoslednja24h INT,
    TestiraniUPoslednja24h INT,
    Hospitalizovani INT,
    NaRespiratoru INT,
    DatumIVremeObjavljivanja datetime
    
    );
insert into korisnici(email, lozinka, ime, prezime, datumRodjenja, jmbg, adresa, brojTelefona) values 
('domonjianna@gmail.com', 'ana123', 'Ana', 'Domonji', '2002-07-02', 1234567891234, 'Vinogradska 1, Stara Pazova', "+38166949449");
insert into korisnici(email, lozinka, ime, prezime, datumRodjenja, jmbg, adresa, brojTelefona, eUloga) values 
('admin@gmail.com', 'admin123', 'Admin', 'Adminko', '1998-06-08', 9858685849385, 'Bulevar oslobodjenja 1,Novi Sad', "+381649222822", 'ADMINISTRATOR');
insert into korisnici(email, lozinka, ime, prezime, datumRodjenja, jmbg, adresa, brojTelefona, eUloga) values 
('mila@gmail.com', 'mila123', 'Mila', 'Milanko', '1997-07-02', 1247658493069, 'Bulevar oslobodjenja 1,Novi Sad', "+3810628449559", 'MEDICINSKOOSOBLJE');

insert into ProizvodjaciVakcine( proizvodjac, drzavaproizvodnje) values ('Proizvodjac', 'Srbija');

insert into vakcine(ime,  ProizvodjacId) values ('Vakcina',  1);
insert into vakcine(ime, ProizvodjacId) values ('Fajzer', 1);

insert into vesti(nazivVesti, sadrzajVesti, DatumIVremeObjavljivanja ) values ('Naziv', 'Sadrzaj', '2023-01-01 23:30:11' );

insert into vestiOOBolelima(OboleliUPoslednja24h,TestiraniUPoslednja24h , Hospitalizovani,
NaRespiratoru, DatumIVremeObjavljivanja) values (10, 20,  1, 1, '2023-01-09 11:11:11' );

insert into vestiOOBolelima(OboleliUPoslednja24h,TestiraniUPoslednja24h , Hospitalizovani,
NaRespiratoru, DatumIVremeObjavljivanja) values (5, 10,  1, 1, '2023-01-09 11:11:11' );

SELECT * FROM KORISNICI;
select * from ProizvodjaciVakcine;
select * from vakcine;
select * from vesti;
select * from vestiOObolelima;
