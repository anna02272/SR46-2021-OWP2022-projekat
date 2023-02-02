package com.ftn.EUprava.dao;

import java.util.HashMap;
import java.util.List;

import com.ftn.EUprava.model.Vakcina;

public interface VakcinaDAO {
	
	public Vakcina findOne(Long id);

	public List<Vakcina> findAll();
	
	public int save(Vakcina vakcina);

	public int update(Vakcina vakcina);

	public int delete(Long id);
	
	public List<Vakcina> find(String ime, Integer dostupnaKolicinaMin, Integer dostupnaKolicinaMax,
			Long proizvodjacId);

	List<Vakcina> find(HashMap<String, Object> mapaArgumenata);
	
	
}
