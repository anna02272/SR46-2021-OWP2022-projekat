package com.ftn.EUprava.model;

import java.time.LocalDateTime;

public class PrijavaZaVakcinaciju {

	private Long id;
	private Korisnik pacijent;
	private Vakcina vakcina;
	private EDoza doza;
	private LocalDateTime datumIVremePrijave;
	
	
	public PrijavaZaVakcinaciju(Korisnik pacijent, Vakcina vakcina, EDoza doza) {
		super();
		this.pacijent = pacijent;
		this.vakcina = vakcina;
		this.doza = doza;
	}
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
	public LocalDateTime getDatumIVremePrijave() {
		return datumIVremePrijave;
	}
	public void setDatumIVremePrijave(LocalDateTime datumIVremePrijave) {
		this.datumIVremePrijave = datumIVremePrijave;
	}
	public PrijavaZaVakcinaciju(Long id, Korisnik pacijent, Vakcina vakcina, EDoza doza,
			LocalDateTime datumIVremePrijave) {
		super();
		this.id = id;
		this.pacijent = pacijent;
		this.vakcina = vakcina;
		this.doza = doza;
		this.datumIVremePrijave = datumIVremePrijave;
	}
	public PrijavaZaVakcinaciju(Korisnik pacijent, Vakcina vakcina, EDoza doza, LocalDateTime datumIVremePrijave) {
		super();
		this.pacijent = pacijent;
		this.vakcina = vakcina;
		this.doza = doza;
		this.datumIVremePrijave = datumIVremePrijave;
	}
	@Override
	public String toString() {
		return "PrijavaZaVakcinaciju [id=" + id + ", pacijent=" + pacijent + ", vakcina=" + vakcina + ", doza=" + doza
				+ ", datumIVremePrijave=" + datumIVremePrijave + "]";
	}
	
	
}
