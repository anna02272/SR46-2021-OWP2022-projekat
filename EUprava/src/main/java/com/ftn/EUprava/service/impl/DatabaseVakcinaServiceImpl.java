package com.ftn.EUprava.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.EUprava.dao.VakcinaDAO;
import com.ftn.EUprava.model.ProizvodjacVakcine;
import com.ftn.EUprava.model.Vakcina;
import com.ftn.EUprava.service.VakcinaService;


@Service
public class DatabaseVakcinaServiceImpl implements VakcinaService {
	
	@Autowired
	private VakcinaDAO vakcinaDAO;
	
	
	
	@Override
	public Vakcina findOne(Long id) {
		return vakcinaDAO.findOne(id);
	}

	@Override
	public List<Vakcina> findAll() {
		return vakcinaDAO.findAll();
	}

	@Override
	public Vakcina save(Vakcina  vakcina) {
		vakcinaDAO.save(vakcina);
		return vakcina;
	}

	@Override
	public Vakcina update(Vakcina vakcina) {
		vakcinaDAO.update(vakcina);
		return vakcina;
	}

	@Override
	public Vakcina delete(Long id) {
		Vakcina vakcina = vakcinaDAO.findOne(id);
		if(vakcina != null) {
			vakcinaDAO.delete(id);
		}
		return vakcina;
	}
	
	@Override
	public List<Vakcina> find(String ime, Integer dostupnaKolicinaMin, Integer dostupnaKolicinaMax, Long proizvodjacId) {
		
		return vakcinaDAO.find( ime,  dostupnaKolicinaMin,  dostupnaKolicinaMax,  proizvodjacId);

	}
	
//	@Override
//	public List<Vakcina> find(String ime, Integer dostupnaKolicinaMin, Integer dostupnaKolicinaMax, Long proizvodjacId) {
//		
//		HashMap<String, Object> mapaArgumenata = new HashMap<String,Object>();
//	
//		if(proizvodjacId!=null) 
//			mapaArgumenata.put("proizvodjacId", proizvodjacId);
//		
//		if(ime!=null)
//			mapaArgumenata.put("ime", ime);
//		
//		
//		if(dostupnaKolicinaMin!=null)
//			mapaArgumenata.put("dostupnaKolicinaMin", dostupnaKolicinaMin);
//		
//		if(dostupnaKolicinaMax!=null) 
//			mapaArgumenata.put("dostupnaKolicinaMax", dostupnaKolicinaMax);
//		
//		return vakcinaDAO.find(mapaArgumenata);
//	}
//

	
}
