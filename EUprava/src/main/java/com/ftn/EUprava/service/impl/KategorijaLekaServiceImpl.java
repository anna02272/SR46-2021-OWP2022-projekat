package com.ftn.EUprava.service.impl;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ftn.EUprava.model.KategorijaLeka;
import com.ftn.EUprava.service.KategorijaLekaService;

@Service
@Qualifier("fajloviKategorijaLeka")
public class KategorijaLekaServiceImpl implements KategorijaLekaService{

	
	@Value("${kategorijeLeka.pathToFile}")
	private String pathToFile;
	
    private Map<Long, KategorijaLeka> readFromFile() {

    	Map<Long, KategorijaLeka> kategorijeLekova = new HashMap<>();
    	Long nextId = 1L;
    	
    	try {
			Path path = Paths.get(pathToFile);
			System.out.println(path.toFile().getAbsolutePath());
			List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));

			for (String line : lines) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				
				String[] tokens = line.split(";");
				Long id = Long.parseLong(tokens[0]);
				String naziv = tokens[1];
				String namena = tokens[2];
				String opis = tokens[3];
					
				kategorijeLekova.put(id, new KategorijaLeka(id, naziv, namena, opis));
				
				
				if(nextId<id)
					nextId=id;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return kategorijeLekova;
    }
    
    private Map<Long, KategorijaLeka> saveToFile(Map<Long, KategorijaLeka> kategorijeLekova) {
    	
    	Map<Long, KategorijaLeka> ret = new HashMap<>();
    	
    	try {
			Path path = Paths.get(pathToFile);
			System.out.println(path.toFile().getAbsolutePath());
			List<String> lines = new ArrayList<>();
			
			for (KategorijaLeka kategorijaLeka : kategorijeLekova.values()) {
				String line = kategorijaLeka.toString(); 
				lines.add(line);
				ret.put(kategorijaLeka.getId(), kategorijaLeka);
			}
			Files.write(path, lines, Charset.forName("UTF-8"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ret;
    }

    private Long nextId(Map<Long, KategorijaLeka> kategorijeLekova) {
    	Long next = 1L;
    	for(Long id : kategorijeLekova.keySet()) {
    		if (next < id) {
    			next = id;
    		}
    	}
    	return ++next;
    }
    
	@Override
	public KategorijaLeka findOne(Long id) {
		Map <Long, KategorijaLeka> kategorijeLekova = readFromFile();
		return kategorijeLekova.get(id);
	}

	@Override
	public List<KategorijaLeka> findAll() {
		Map <Long, KategorijaLeka> kategorijeLekova = readFromFile();
		return new ArrayList<>(kategorijeLekova.values());
	}

	@Override
	public KategorijaLeka save(KategorijaLeka kategorijaLeka) {
		Map <Long, KategorijaLeka> kategorijeLekova = readFromFile();
		Long id = nextId(kategorijeLekova);
		
		if(kategorijaLeka.getId() == null) {
			kategorijaLeka.setId(id);
		}
		kategorijeLekova.put(id, kategorijaLeka);
		saveToFile(kategorijeLekova);
		return kategorijaLeka;
	}

	@Override
	public KategorijaLeka update(KategorijaLeka kategorijaLeka) {
		Map <Long, KategorijaLeka> kategorijeLekova = readFromFile();
		
		kategorijeLekova.put(kategorijaLeka.getId(), kategorijaLeka);
		saveToFile(kategorijeLekova);
		return kategorijaLeka;
	}

	@Override
	public KategorijaLeka delete(Long id) {
		Map <Long, KategorijaLeka> kategorijeLekova = readFromFile();
		if (!kategorijeLekova.containsKey(id)) {
			throw new IllegalArgumentException("Pokusaj brisanja nepostojeceg objekta");
		}
		KategorijaLeka kategorijaLeka = kategorijeLekova.remove(id);
		saveToFile(kategorijeLekova);
		return kategorijaLeka;
	}



}
