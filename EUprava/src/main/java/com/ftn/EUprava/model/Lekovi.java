package com.ftn.EUprava.model;

import java.awt.image.BufferedImage;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lekovi {
	private Map<Long, Lek> lekovi = new HashMap<>();
	private long nextId = 1L;
	
	public Lekovi() {
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("lekovi.txt").toURI()) ;
			System.out.println(path.toFile().getAbsolutePath());
			List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));
			
			for (String line : lines) {
				line = line.trim();
				if (line.equals("") || line.indexOf("#")== 0) 
					continue;
				
			String tokens[] = line.split(";");
			String naziv = tokens[1];
			Long id = Long.parseLong(tokens[0]);
			String opis = tokens[2];
			String kontraindikacije  = tokens[3];
//			String oblik = tokens[4];
//			EOblik oblikk = EOblik.SIRUP;
//			for (EOblik o: EOblik.values()) {
//				if(o.name().equalsIgnoreCase(oblik)){
//					oblikk = o;
//				}
//			}
		    Integer prosecnaOcena  = Integer.parseInt(tokens[4]);
//		    BufferedImage slika;
			Integer dostupnaKolicinaNaStanju  = Integer.parseInt(tokens[5]);
			Double cena  = Double.parseDouble(tokens[6]);
		    String proizvodjac  = tokens[7];
			String kategorija = tokens[8];
			
			lekovi.put(Long.parseLong(tokens[0]), new Lek(id, naziv, opis, kontraindikacije, prosecnaOcena
					, dostupnaKolicinaNaStanju, cena, proizvodjac, kategorija));
			if (nextId < id)
				nextId = id;
			}
		}catch (Exception e){
			e.printStackTrace();
			
		}
	}
	public Lek findOne(Long id) {
		return lekovi.get(id);
	}
	
	public List <Lek> findAll(){
		return new ArrayList<Lek> (lekovi.values());
	}
	
	public Lek save(Lek lek) {
		if (lek.getId() == null) {
			lek.setId(++nextId);
		}
		lekovi.put(lek.getId(), lek);
		return lek;
	}
	
	public List<Lek> save(List<Lek> lekovi){
		List<Lek> ret = new ArrayList<>();
		for(Lek l : lekovi) {
			Lek saved = save(l);
			if (saved != null) {
				ret.add(saved);
			}
		}
		return ret;
	}
	
	public Lek delete(Long id) {
		if (!lekovi.containsKey(id)) {
			throw new IllegalArgumentException("tried to remove not existing");
		}
		Lek lek = lekovi.get(id);
		if (lek != null) {
			lekovi.remove(id);
			
		}
		return lek;
	}
	
	public void delete(List<Long> ids) {
		for (Long id: ids) {
			delete(id);
		}
	}
	
	public List<Lek> findByNaziv(String naziv){
		List<Lek> ret = new ArrayList<>();
		for(Lek l : lekovi.values()) {
			if(naziv.startsWith(l.getNaziv())) {
				ret.add(l);
			}
		}
		return ret;
	}
	
	
	
	
}

























