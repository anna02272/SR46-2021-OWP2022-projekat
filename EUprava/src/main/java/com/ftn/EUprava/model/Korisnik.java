package com.ftn.EUprava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Korisnik {
	private Long id;
	private String korisnickoIme;
	private String lozinka;
	private String email;
	private String ime;
	private String prezime;
	private LocalDate datumRodjenja;
	private String adresa;
	private Integer brojTelefona;
	private LocalDateTime datumIVremeRegistracije;
	private EUloga uloga;
	
	public Korisnik() {
		super();
	}
	
	public Korisnik(Long id, String korisnickoIme, String lozinka, String email, String ime, String prezime,
			LocalDate datumRodjenja, String adresa, Integer brojTelefona, LocalDateTime datumIVremeRegistracije,
			EUloga uloga) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.email = email;
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.datumIVremeRegistracije = datumIVremeRegistracije;
		this.uloga = uloga;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public Integer getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(Integer brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public LocalDateTime getDatumIVremeRegistracije() {
		return datumIVremeRegistracije;
	}
	public void setDatumIVremeRegistracije(LocalDateTime datumIVremeRegistracije) {
		this.datumIVremeRegistracije = datumIVremeRegistracije;
	}
	public EUloga getUloga() {
		return uloga;
	}
	public void setUloga(EUloga uloga) {
		this.uloga = uloga;
	}
	@Override
	public String toString() {
		return "Korisnik [id=" + id + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + ", email=" + email
				+ ", ime=" + ime + ", prezime=" + prezime + ", datumRodjenja=" + datumRodjenja + ", adresa=" + adresa
				+ ", brojTelefona=" + brojTelefona + ", datumIVremeRegistracije=" + datumIVremeRegistracije + ", uloga="
				+ uloga + "]";
	}
	
	
	
}
