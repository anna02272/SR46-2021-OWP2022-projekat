package com.ftn.EUprava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class KorisnikUprave {

	private String email;
	private String lozinka;
	private String ime;
	private String prezime;
	private LocalDate datumRodjenja;
	private Integer JMBG;
	private String adresa;
	private Integer brojTelefona;
	private LocalDateTime datumIVremeRegistracije;
	private EnumUloga uloga;
	
	public KorisnikUprave() {
		super();
	}
	
	public KorisnikUprave(String email, String lozinka, String ime, String prezime, LocalDate datumRodjenja,
			Integer jMBG, String adresa, Integer brojTelefona, LocalDateTime datumIVremeRegistracije, EnumUloga uloga) {
		super();
		this.email = email;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.datumRodjenja = datumRodjenja;
		JMBG = jMBG;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.datumIVremeRegistracije = datumIVremeRegistracije;
		this.uloga = uloga;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
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
	public Integer getJMBG() {
		return JMBG;
	}
	public void setJMBG(Integer jMBG) {
		JMBG = jMBG;
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
	public EnumUloga getUloga() {
		return uloga;
	}
	public void setUloga(EnumUloga uloga) {
		this.uloga = uloga;
	}
	
	
	@Override
	public String toString() {
		return "KorisnikUprave [email=" + email + ", lozinka=" + lozinka + ", ime=" + ime + ", prezime=" + prezime
				+ ", datumRodjenja=" + datumRodjenja + ", JMBG=" + JMBG + ", adresa=" + adresa + ", brojTelefona="
				+ brojTelefona + ", datumIVremeRegistracije=" + datumIVremeRegistracije + ", uloga=" + uloga + "]";
	}
	
	
	
}
