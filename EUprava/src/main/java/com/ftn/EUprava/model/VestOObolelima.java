package com.ftn.EUprava.model;

import java.time.LocalDateTime;

public class VestOObolelima {
	
	private Integer oboleliUPoslednja24h;
	private Integer testiraniUPoslednja24h;
	private Integer ukupnoOboleliOdPočetkaPandemije;
	private Integer hospitalizovani;
	private Integer naRespiratoru;
	private LocalDateTime datumIVRemeObjavljivanja;
	
	
	public VestOObolelima() {
		super();
	}
	public VestOObolelima(Integer oboleliUPoslednja24h, Integer testiraniUPoslednja24h,
			Integer ukupnoOboleliOdPočetkaPandemije, Integer hospitalizovani, Integer naRespiratoru,
			LocalDateTime datumIVRemeObjavljivanja) {
		super();
		this.oboleliUPoslednja24h = oboleliUPoslednja24h;
		this.testiraniUPoslednja24h = testiraniUPoslednja24h;
		this.ukupnoOboleliOdPočetkaPandemije = ukupnoOboleliOdPočetkaPandemije;
		this.hospitalizovani = hospitalizovani;
		this.naRespiratoru = naRespiratoru;
		this.datumIVRemeObjavljivanja = datumIVRemeObjavljivanja;
	}
	public Integer getOboleliUPoslednja24h() {
		return oboleliUPoslednja24h;
	}
	public void setOboleliUPoslednja24h(Integer oboleliUPoslednja24h) {
		this.oboleliUPoslednja24h = oboleliUPoslednja24h;
	}
	public Integer getTestiraniUPoslednja24h() {
		return testiraniUPoslednja24h;
	}
	public void setTestiraniUPoslednja24h(Integer testiraniUPoslednja24h) {
		this.testiraniUPoslednja24h = testiraniUPoslednja24h;
	}
	public Integer getUkupnoOboleliOdPočetkaPandemije() {
		return ukupnoOboleliOdPočetkaPandemije;
	}
	public void setUkupnoOboleliOdPočetkaPandemije(Integer ukupnoOboleliOdPočetkaPandemije) {
		this.ukupnoOboleliOdPočetkaPandemije = ukupnoOboleliOdPočetkaPandemije;
	}
	public Integer getHospitalizovani() {
		return hospitalizovani;
	}
	public void setHospitalizovani(Integer hospitalizovani) {
		this.hospitalizovani = hospitalizovani;
	}
	public Integer getNaRespiratoru() {
		return naRespiratoru;
	}
	public void setNaRespiratoru(Integer naRespiratoru) {
		this.naRespiratoru = naRespiratoru;
	}
	public LocalDateTime getDatumIVRemeObjavljivanja() {
		return datumIVRemeObjavljivanja;
	}
	public void setDatumIVRemeObjavljivanja(LocalDateTime datumIVRemeObjavljivanja) {
		this.datumIVRemeObjavljivanja = datumIVRemeObjavljivanja;
	}
	@Override
	public String toString() {
		return "VestOObolelima [oboleliUPoslednja24h=" + oboleliUPoslednja24h + ", testiraniUPoslednja24h="
				+ testiraniUPoslednja24h + ", ukupnoOboleliOdPočetkaPandemije=" + ukupnoOboleliOdPočetkaPandemije
				+ ", hospitalizovani=" + hospitalizovani + ", naRespiratoru=" + naRespiratoru
				+ ", datumIVRemeObjavljivanja=" + datumIVRemeObjavljivanja + "]";
	}
	
}
