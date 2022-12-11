package com.ftn.EUprava.model;

public class ProizvodjacVakcine {
	private String proizvodjac;
	private String drzavaProizvodnje;
	
	public ProizvodjacVakcine() {
		super();
	}
	public ProizvodjacVakcine(String proizvodjac, String drzavaProizvodnje) {
		super();
		this.proizvodjac = proizvodjac;
		this.drzavaProizvodnje = drzavaProizvodnje;
	}
	public String getProizvodjac() {
		return proizvodjac;
	}
	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}
	public String getDrzavaProizvodnje() {
		return drzavaProizvodnje;
	}
	public void setDrzavaProizvodnje(String drzavaProizvodnje) {
		this.drzavaProizvodnje = drzavaProizvodnje;
	}
	@Override
	public String toString() {
		return "ProizvodjacVakcine [proizvodjac=" + proizvodjac + ", drzavaProizvodnje=" + drzavaProizvodnje + "]";
	}
	
	
}
