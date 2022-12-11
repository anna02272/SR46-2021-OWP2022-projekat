package com.ftn.EUprava.model;

public class ProizvodjacLeka {
	private Long id;
	private String nazivFirme;
	private String drzavaSedista;
	
	public ProizvodjacLeka() {
		super();
	}
	
	public ProizvodjacLeka(Long id, String nazivFirme, String drzavaSedista) {
		super();
		this.id = id;
		this.nazivFirme = nazivFirme;
		this.drzavaSedista = drzavaSedista;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivFirme() {
		return nazivFirme;
	}

	public void setNazivFirme(String nazivFirme) {
		this.nazivFirme = nazivFirme;
	}

	public String getDrzavaSedista() {
		return drzavaSedista;
	}

	public void setDrzavaSedista(String drzavaSedista) {
		this.drzavaSedista = drzavaSedista;
	}

	@Override
	public String toString() {
		return "ProizvodjacLeka [id=" + id + ", nazivFirme=" + nazivFirme + ", drzavaSedista=" + drzavaSedista + "]";
	}
	
	
}
