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
    JMBG INT(13) NOT NULL UNIQUE,
    Adresa VARCHAR(50) NOT NULL,
    BrojTelefona INT NOT NULL,
    DatumIVremeRegistracije DATETIME,
    EUloga VARCHAR(20) NOT NULL
);

CREATE TABLE ProzivodjaciVakcine(
	Id BIGINT auto_increment PRIMARY KEY,
	Proizvodjac VARCHAR(50) NOT NULL,
    DrzavaProizvodnje VARCHAR(50) NOT NULL
);

CREATE TABLE Vakcine (
	Id BIGINT  auto_increment PRIMARY KEY,
	Ime VARCHAR(50) NOT NULL,
    DostupnaKolicina INT,
    ProizvodjacId BIGINT,
	FOREIGN KEY (ProizvodjacId) REFERENCES ProzivodjaciVakcine (Id) 	
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
    UkupnoOboleliOdPočetkaPandemije INT,
    Hospitalizovani INT,
    NaRespiratoru INT,
    DatumIVremeObjavljivanja datetime
    
    );


insert into korisnici(email, lozinka, ime, prezime, datumRodjenja, jmbg, adresa, brojTelefona, datumivremeregistracije, EUloga) values 
('domonjianna@gmail.com', 'ana123', 'Ana', 'Domonji', '2002-07-02', 1234567, 'Vinogradska 1', 066949449, '2023-01-05', 'PACIJENT');

insert into ProzivodjaciVakcine( proizvodjac, drzavaproizvodnje) values ('Proizvodjac', 'Srbija');

insert into vakcine(ime, dostupnakolicina, ProizvodjacId) values ('Vakcina', 50, 1);

insert into vesti(nazivVesti, sadrzajVesti, DatumIVremeObjavljivanja ) values ('Naziv', 'Sadrzaj', '2023-01-01 23:30:11' );

insert into vestiOOBolelima(OboleliUPoslednja24h,TestiraniUPoslednja24h, UkupnoOboleliOdPočetkaPandemije, Hospitalizovani,
NaRespiratoru, DatumIVremeObjavljivanja) values (10, 20, 100, 10, 1, '2023-01-09 11:11:11' );
