package com.ftn.EUprava.model;

import java.time.LocalDateTime;

public class PrimljenaVakcina {
	
	private Long id;
	private Korisnik pacijent;
	private Vakcina vakcina;
	private EDoza doza;
	private LocalDateTime datumIVremeVakcinacije;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Korisnik getPacijent() {
		return pacijent;
	}
	public void setPacijent(Korisnik pacijent) {
		this.pacijent = pacijent;
	}
	public Vakcina getVakcina() {
		return vakcina;
	}
	public void setVakcina(Vakcina vakcina) {
		this.vakcina = vakcina;
	}
	public EDoza getDoza() {
		return doza;
	}
	public void setDoza(EDoza doza) {
		this.doza = doza;
	}
	public LocalDateTime getDatumIVremeVakcinacije() {
		return datumIVremeVakcinacije;
	}
	public void setDatumIVremeVakcinacije(LocalDateTime datumIVremeVakcinacije) {
		this.datumIVremeVakcinacije = datumIVremeVakcinacije;
	}
	public PrimljenaVakcina(Long id, Korisnik pacijent, Vakcina vakcina, EDoza doza,
			LocalDateTime datumIVremeVakcinacije) {
		super();
		this.id = id;
		this.pacijent = pacijent;
		this.vakcina = vakcina;
		this.doza = doza;
		this.datumIVremeVakcinacije = datumIVremeVakcinacije;
	}
	public PrimljenaVakcina(Korisnik pacijent, Vakcina vakcina, EDoza doza, LocalDateTime datumIVremeVakcinacije) {
		super();
		this.pacijent = pacijent;
		this.vakcina = vakcina;
		this.doza = doza;
		this.datumIVremeVakcinacije = datumIVremeVakcinacije;
	}
	@Override
	public String toString() {
		return "PrimljenaVakcina [id=" + id + ", pacijent=" + pacijent + ", vakcina=" + vakcina + ", doza=" + doza
				+ ", datumIVremeVakcinacije=" + datumIVremeVakcinacije + "]";
	}
	
	
	
	
	
	
}
