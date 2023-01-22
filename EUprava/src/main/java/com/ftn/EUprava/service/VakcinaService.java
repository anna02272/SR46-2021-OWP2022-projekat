package com.ftn.EUprava.service;

import java.util.List;

import com.ftn.EUprava.model.Vakcina;


public interface VakcinaService {
	Vakcina findOne(Long id); 
	List<Vakcina> findAll(); 
	Vakcina save(Vakcina vakcina); 
	Vakcina update(Vakcina vakcina); 
	Vakcina delete(Long id); 
}
