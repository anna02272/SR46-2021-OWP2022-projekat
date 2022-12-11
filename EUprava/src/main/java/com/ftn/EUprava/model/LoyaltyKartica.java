package com.ftn.EUprava.model;

public class LoyaltyKartica {
	private Long id;
	private Integer popust;
	private Integer brojPoena;
	
	public LoyaltyKartica() {
		super();
	}
	
	
	public LoyaltyKartica(Long id, Integer popust, Integer brojPoena) {
		super();
		this.id = id;
		this.popust = popust;
		this.brojPoena = brojPoena;
	}


	public LoyaltyKartica(Integer popust, Integer brojPoena) {
		super();
		this.popust = popust;
		this.brojPoena = brojPoena;
	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getPopust() {
		return popust;
	}

	public void setPopust(Integer popust) {
		this.popust = popust;
	}

	public Integer getBrojPoena() {
		return brojPoena;
	}

	public void setBrojPoena(Integer brojPoena) {
		this.brojPoena = brojPoena;
	}

	@Override
	public String toString() {
		return "LoyaltyKartica [popust=" + popust + ", brojPoena=" + brojPoena + "]";
	}
	
	
}
