package com.ftn.EUprava.model;

public class Vakcina {
	private String ime;
	private Integer dostupnaKolicina;
	private ProizvodjacVakcine proizvodjac;
	
	
	public Vakcina() {
		super();
	}
	public Vakcina(String ime, Integer dostupnaKolicina, ProizvodjacVakcine proizvodjac) {
		super();
		this.ime = ime;
		this.dostupnaKolicina = dostupnaKolicina;
		this.proizvodjac = proizvodjac;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public Integer getDostupnaKolicina() {
		return dostupnaKolicina;
	}
	public void setDostupnaKolicina(Integer dostupnaKolicina) {
		this.dostupnaKolicina = dostupnaKolicina;
	}
	public ProizvodjacVakcine getProizvodjac() {
		return proizvodjac;
	}
	public void setProizvodjac(ProizvodjacVakcine proizvodjac) {
		this.proizvodjac = proizvodjac;
	}
	@Override
	public String toString() {
		return "Vakcina [ime=" + ime + ", dostupnaKolicina=" + dostupnaKolicina + ", proizvodjac=" + proizvodjac + "]";
	}
	
	
}
