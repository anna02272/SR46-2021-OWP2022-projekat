package com.ftn.EUprava.model;

import java.awt.image.BufferedImage;

public class Lek {
	private String naziv;
	private Long id ;
	private String opis;
	private String kontraindikacije;
//	private EOblik oblik;
	private Integer prosecnaOcena;
//	private BufferedImage slika;
	private Integer dostupnaKolicinaNaStanju;
	private Double cena;
	private String proizvodjac;
	private String kategorija;
	
	
	public Lek() {
		super();
	}
	
	public Lek( Long id,String naziv, String opis, String kontraindikacije,  Integer prosecnaOcena,
			 Integer dostupnaKolicinaNaStanju, Double cena, String proizvodjac, String kategorija) {
		super();
		this.naziv = naziv;
		this.id = id;
		this.opis = opis;
		this.kontraindikacije = kontraindikacije;
//		this.oblik = oblik;
		this.prosecnaOcena = prosecnaOcena;
//		this.slika = slika;
		this.dostupnaKolicinaNaStanju = dostupnaKolicinaNaStanju;
		this.cena = cena;
		this.proizvodjac = proizvodjac;
		this.kategorija = kategorija;
	}
	
	public Lek(String naziv, String opis, String kontraindikacije, Integer prosecnaOcena,
			 Integer dostupnaKolicinaNaStanju, Double cena, String proizvodjac, String kategorija) {
		super();
		this.naziv = naziv;
		this.opis = opis;
		this.kontraindikacije = kontraindikacije;
//		this.oblik = oblik;
		this.prosecnaOcena = prosecnaOcena;
//		this.slika = slika;
		this.dostupnaKolicinaNaStanju = dostupnaKolicinaNaStanju;
		this.cena = cena;
		this.proizvodjac = proizvodjac;
		this.kategorija = kategorija;
		
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getKontraindikacije() {
		return kontraindikacije;
	}
	public void setKontraindikacije(String kontraindikacije) {
		this.kontraindikacije = kontraindikacije;
	}
//	public EOblik getOblik() {
//		return oblik;
//	}
//	public void setOblik(EOblik oblik) {
//		this.oblik = oblik;
//	}
	public Integer getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(Integer prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
//	public BufferedImage getSlika() {
//		return slika;
//	}
//	public void setSlika(BufferedImage slika) {
//		this.slika = slika;
//	}
	public Integer getDostupnaKolicinaNaStanju() {
		return dostupnaKolicinaNaStanju;
	}
	public void setDostupnaKolicinaNaStanju(Integer dostupnaKolicinaNaStanju) {
		this.dostupnaKolicinaNaStanju = dostupnaKolicinaNaStanju;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	public String getProizvodjac() {
		return proizvodjac;
	}
	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}
	public String getKategorija() {
		return kategorija;
	}
	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

	@Override
	public String toString() {
		return "Lek [naziv=" + naziv + ", id=" + id + ", opis=" + opis + ", kontraindikacije=" + kontraindikacije
				+ ", prosecnaOcena=" + prosecnaOcena 
				+ ", dostupnaKolicinaNaStanju=" + dostupnaKolicinaNaStanju + ", cena=" + cena + ", proizvodjac="
				+ proizvodjac + ", kategorija=" + kategorija + "]";
	}
	
	
}
