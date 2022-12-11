package com.ftn.EUprava.model;
//package com.ftn.PrviMavenVebProjekat.model;
//
//import java.nio.charset.Charset;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class Komentari {
//
//	private Map<Long, Komentar> komentari = new HashMap<>();
//	private long nextId = 1L;
//
//	/** Cita knjige iz datoteke i smesta ih u asocijativnu listu knjiga. */
//	public Komentari() {
//
//		try {
//			Path path = Paths.get(getClass().getClassLoader().getResource("komentari.txt").toURI());
//			System.out.println(path.toFile().getAbsolutePath());
//			List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));
//
//			for (String line : lines) {
//				line = line.trim();
//				if (line.equals("") || line.indexOf('#') == 0)
//					continue;
//				String[] tokens = line.split(";");
//				Long id = Long.parseLong(tokens[0]);
//				String tekstKomentara = (tokens[1]);
//				Integer ocena = Integer.parseInt(tokens[2]);
//				LocalDate datumPostavljanjaKomentara = LocalDate.parse(tokens[3]);
//				Korisnik autorKomentara = tokens[4];
//				List <Lek> lekNaKojiSeOdnosiKomentar = (tokens[5]);
//				Boolean anoniman = Boolean.parseBoolean(tokens[6]);
//				
//				
//				komentari.put(Long.parseLong(tokens[0]), new Komentar(id, tekstKomentara, ocena, datumPostavljanjaKomentara,
//						autorKomentara, lekNaKojiSeOdnosiKomentar, anoniman));
//				if(nextId<id)
//					nextId=id;
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	/** vraca knjigu u odnosu na zadati id */
//	public Komentar findOne(Long id) {
//		return komentari.get(id);
//	}
//
//	/** vraca sve knjige */
//	public List<Komentar> findAll() {
//		return new ArrayList<Komentar>(komentari.values());
//	}
//
//	/** cuva podatke o knjizi */
//	public Komentar save(Komentar komentar) {
//		if (komentar.getId() == null) {
//			komentar.setId(++nextId);
//		}
//		komentari.put(komentar.getId(), komentar);
//		return komentar;
//	}
//
//	/** cuva podatke o svim knjigama */
//	public List<Knjiga> save(List<Knjiga> knjige) {
//		List<Knjiga> ret = new ArrayList<>();
//
//		for (Knjiga k : knjige) {
//			// za svaku prosleđenu knjigu pozivamo save
//			// metodu koju smo već napravili i testirali -
//			// ona će sepobrinuti i za dodelu ID-eva
//			// ako je to potrebno
//			Knjiga saved = save(k);
//
//			// uspešno snimljene dodajemo u listu za vraćanje
//			if (saved != null) {
//				ret.add(saved);
//			}
//		}
//		return ret;
//	}
//
//	/** brise knjigu u odnosu na zadati id */
//	public Knjiga delete(Long id) {
//		if (!knjige.containsKey(id)) {
//			throw new IllegalArgumentException("tried to remove non existing book");
//		}
//		Knjiga knjiga = knjige.get(id);
//		if (knjiga != null) {
//			knjige.remove(id);
//		}
//		return knjiga;
//	}
//
//	/** brise knjige u odnosu na zadate ids */
//	public void delete(List<Long> ids) {
//		for (Long id : ids) {
//			// pozivamo postojeću metodu za svaki
//			delete(id);
//		}
//	}
//
//	/** vraca sve knjige ciji naziv zapocinje sa tekstom*/
//	public List<Knjiga> findByNaziv(String naziv) {
//		List<Knjiga> ret = new ArrayList<>();
//
//		for (Knjiga k : knjige.values()) {
//			if (naziv.startsWith(k.getNaziv())) {
//				ret.add(k);
//			}
//		}
//
//		return ret;
//	}
//}
