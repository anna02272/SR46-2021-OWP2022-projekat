package com.ftn.EUprava.model;

import java.time.LocalDateTime;

public class Vest {
	
	private String nazivVesti;
	private String sadrzajVesti;
	private LocalDateTime datumIVremeObjavljivanja;
	
	
	public Vest() {
		super();
	}
	public Vest(String nazivVesti, String sadrzajVesti, LocalDateTime datumIVremeObjavljivanja) {
		super();
		this.nazivVesti = nazivVesti;
		this.sadrzajVesti = sadrzajVesti;
		this.datumIVremeObjavljivanja = datumIVremeObjavljivanja;
	}
	public String getNazivVesti() {
		return nazivVesti;
	}
	public void setNazivVesti(String nazivVesti) {
		this.nazivVesti = nazivVesti;
	}
	public String getSadrzajVesti() {
		return sadrzajVesti;
	}
	public void setSadrzajVesti(String sadrzajVesti) {
		this.sadrzajVesti = sadrzajVesti;
	}
	public LocalDateTime getDatumIVremeObjavljivanja() {
		return datumIVremeObjavljivanja;
	}
	public void setDatumIVremeObjavljivanja(LocalDateTime datumIVremeObjavljivanja) {
		this.datumIVremeObjavljivanja = datumIVremeObjavljivanja;
	}
	@Override
	public String toString() {
		return "Vest [nazivVesti=" + nazivVesti + ", sadrzajVesti=" + sadrzajVesti + ", datumIVremeObjavljivanja="
				+ datumIVremeObjavljivanja + "]";
	}
	
	
	
}
