package com.ftn.EUprava.service;

import java.util.List;

import com.ftn.EUprava.model.KategorijaLeka;

public interface KategorijaLekaService {
	
	KategorijaLeka findOne(Long id);
	List<KategorijaLeka> findAll();
	KategorijaLeka save(KategorijaLeka kategorijaLeka);
	KategorijaLeka update(KategorijaLeka kategorijaLeka);
	KategorijaLeka delete(Long id);
}
