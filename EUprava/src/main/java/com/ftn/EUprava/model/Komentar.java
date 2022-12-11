package com.ftn.EUprava.model;

import java.time.LocalDate;
import java.util.List;

public class Komentar {
	private Long id;
	private String tekstKomentara;
	private Integer ocena;
	private LocalDate datumPostavljanjaKomentara;
	private Korisnik autorKomentara;
	private List <Lek> lekNaKojiSeOdnosiKomentar;
	private Boolean anoniman;
	
	public Komentar() {
		super();
		
	}
	
	
	public Komentar(Long id, String tekstKomentara, Integer ocena, LocalDate datumPostavljanjaKomentara,
			Korisnik autorKomentara, List<Lek> lekNaKojiSeOdnosiKomentar, Boolean anoniman) {
		super();
		this.id = id;
		this.tekstKomentara = tekstKomentara;
		this.ocena = ocena;
		this.datumPostavljanjaKomentara = datumPostavljanjaKomentara;
		this.autorKomentara = autorKomentara;
		this.lekNaKojiSeOdnosiKomentar = lekNaKojiSeOdnosiKomentar;
		this.anoniman = anoniman;
	}


	public Komentar(String tekstKomentara, Integer ocena, LocalDate datumPostavljanjaKomentara, Korisnik autorKomentara,
			List<Lek> lekNaKojiSeOdnosiKomentar, Boolean anoniman) {
		super();
		this.tekstKomentara = tekstKomentara;
		this.ocena = ocena;
		this.datumPostavljanjaKomentara = datumPostavljanjaKomentara;
		this.autorKomentara = autorKomentara;
		this.lekNaKojiSeOdnosiKomentar = lekNaKojiSeOdnosiKomentar;
		this.anoniman = anoniman;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getTekstKomentara() {
		return tekstKomentara;
	}

	public void setTekstKomentara(String tekstKomentara) {
		this.tekstKomentara = tekstKomentara;
	}

	public Integer getOcena() {
		return ocena;
	}

	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}

	public LocalDate getDatumPostavljanjaKomentara() {
		return datumPostavljanjaKomentara;
	}

	public void setDatumPostavljanjaKomentara(LocalDate datumPostavljanjaKomentara) {
		this.datumPostavljanjaKomentara = datumPostavljanjaKomentara;
	}

	public Korisnik getAutorKomentara() {
		return autorKomentara;
	}

	public void setAutorKomentara(Korisnik autorKomentara) {
		this.autorKomentara = autorKomentara;
	}

	
	public List<Lek> getLekNaKojiSeOdnosiKomentar() {
		return lekNaKojiSeOdnosiKomentar;
	}

	public void setLekNaKojiSeOdnosiKomentar(List<Lek> lekNaKojiSeOdnosiKomentar) {
		this.lekNaKojiSeOdnosiKomentar = lekNaKojiSeOdnosiKomentar;
	}

	public Boolean getAnoniman() {
		return anoniman;
	}

	public void setAnoniman(Boolean anoniman) {
		this.anoniman = anoniman;
	}

	@Override
	public String toString() {
		return "Komentar [tekstKomentara=" + tekstKomentara + ", ocena=" + ocena + ", datumPostavljanjaKomentara="
				+ datumPostavljanjaKomentara + ", autorKomentara=" + autorKomentara + ", lekNaKojiSeOdnosiKomentar="
				+ lekNaKojiSeOdnosiKomentar + ", anoniman=" + anoniman + "]";
	}
	
	
}
