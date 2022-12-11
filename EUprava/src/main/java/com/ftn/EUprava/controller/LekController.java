package com.ftn.EUprava.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftn.EUprava.bean.SecondConfiguration.ApplicationMemory;
import com.ftn.EUprava.model.Lek;
import com.ftn.EUprava.model.Lekovi;


@Controller
@RequestMapping(value="/lekovi")
public class LekController implements ApplicationContextAware{
	
	public static final String LEKOVI_KEY = "lekovi";
	
	@Autowired
	private ServletContext servletContex;
	private String bURL;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private ApplicationMemory memorijaAplikacije;
	
	public void setApplicationContext1(ApplicationContext applicationContext) throws BeansException{
		this.applicationContext = applicationContext;
	}
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		bURL = servletContex.getContextPath()+"/";
		memorijaAplikacije = ((BeanFactory) applicationContext).getBean(ApplicationMemory.class);
		Lekovi lekovi = new Lekovi();
		
		memorijaAplikacije.put(LekController.LEKOVI_KEY, lekovi);
	}
	
	@GetMapping
	@ResponseBody
	public void index(HttpServletResponse response) throws IOException {
		
		Lekovi lekovi = (Lekovi) memorijaAplikacije.get(LekController.LEKOVI_KEY);
		
		PrintWriter out;
		out = response.getWriter();
		File htmlFile = new ClassPathResource("static/template.html").getFile();
		Document doc = Jsoup.parse(htmlFile, "UTF-8");
		
		Element body = doc.select("body").first();
		
		Element ulTag = new Element(Tag.valueOf("ul"), "");
		Element liTagLeka = new Element(Tag.valueOf("li"), "");
		Element aTagLeka = new Element(Tag.valueOf("a"), "").attr("href", "/EUprava/lekovi").text("Lekovi");
		
		liTagLeka.appendChild(aTagLeka);
		ulTag.appendChild(liTagLeka);
		
		Element table = new Element(Tag.valueOf("table"), "");
		Element caption = new Element(Tag.valueOf("caption"), "").text("Lekovi");
		Element trZaglavlje = new Element(Tag.valueOf("tr"), "");
		Element thRedniBroj = new Element(Tag.valueOf("th"), "").text("R. br.");
		Element thDetails = new Element(Tag.valueOf("th"), "").text("Details");
		Element thNaziv = new Element(Tag.valueOf("th"), "").text("Naziv");
		Element thOpis= new Element(Tag.valueOf("th"), "").text("Opis");
		Element thKontraindikacije = new Element(Tag.valueOf("th"), "").text("Kontraindikacije");
		Element thOblik = new Element(Tag.valueOf("th"), "").text("Oblik");
		Element thOcena = new Element(Tag.valueOf("th"), "").text("Prosecna Ocena");
		Element thDostupnaKol = new Element(Tag.valueOf("th"), "").text("Dostupna kolicina");
		Element thCena = new Element(Tag.valueOf("th"), "").text("Cena");
		Element thProizvodjac = new Element(Tag.valueOf("th"), "").text("Proizvodjac");
		Element thKategorija = new Element(Tag.valueOf("th"), "").text("Kategorija");
		
		trZaglavlje.appendChild(thRedniBroj);
		trZaglavlje.appendChild(thDetails);
		trZaglavlje.appendChild(thNaziv);
		trZaglavlje.appendChild(thOpis);
		trZaglavlje.appendChild(thKontraindikacije);
		trZaglavlje.appendChild(thOblik);
		trZaglavlje.appendChild(thOcena);
		trZaglavlje.appendChild(thDostupnaKol);
		trZaglavlje.appendChild(thCena);
		trZaglavlje.appendChild(thProizvodjac);
		trZaglavlje.appendChild(thKategorija);
		
		
		table.appendChild(caption);
		table.appendChild(trZaglavlje);
		
		List<Lek> listaLekova = lekovi.findAll();
		for (int i=0; i< listaLekova.size(); i++) {
			Element trLek = new Element(Tag.valueOf("tr"), "");
			Element tdRedniBroj = new Element(Tag.valueOf("td"), "").text(String.valueOf(i+1));
			Element tdDetails = new Element(Tag.valueOf("td"), "");
			Element aDetails = new Element(Tag.valueOf("a"), "").attr("href", "lekovi/details?id=" +
			listaLekova.get(i).getId()).text(listaLekova.get(i).getNaziv());
			
			Element tdNaziv = new Element(Tag.valueOf("td"), "").text(listaLekova.get(i).getNaziv());
			Element tdOpis= new Element(Tag.valueOf("td"), "").text(listaLekova.get(i).getOpis());
			Element tdKontraindikacije = new Element(Tag.valueOf("td"), "").text(listaLekova.get(i).getKontraindikacije());
//			Element tdOblik = new Element(Tag.valueOf("td"), "").text(String.valueOf(listaLekova.get(i).getOblik()));
			Element tdOcena = new Element(Tag.valueOf("td"), "").text(String.valueOf(listaLekova.get(i).getProsecnaOcena()));
			Element tdDostupnaKol = new Element(Tag.valueOf("td"), "").text(String.valueOf(listaLekova.get(i).getDostupnaKolicinaNaStanju()));
			Element tdCena = new Element(Tag.valueOf("td"), "").text(String.valueOf(listaLekova.get(i).getCena()));
			Element tdProizvodjac = new Element(Tag.valueOf("td"), "").text(listaLekova.get(i).getProizvodjac());
			Element tdKategorija = new Element(Tag.valueOf("td"), "").text(listaLekova.get(i).getKategorija());
			
			tdDetails.appendChild(aDetails);
			trLek.appendChild(tdRedniBroj);
			trLek.appendChild(tdDetails);
			trLek.appendChild(tdNaziv);
			trLek.appendChild(tdOpis);
			trLek.appendChild(tdKontraindikacije);
//			trLek.appendChild(tdOblik);
			trLek.appendChild(tdOcena);
			trLek.appendChild(tdDostupnaKol);
			trLek.appendChild(tdCena);
			trLek.appendChild(tdProizvodjac);
			trLek.appendChild(tdKategorija);
			
			Element tdForm = new Element(Tag.valueOf("td"),"");
			Element form = new Element(Tag.valueOf("form"),"").attr("method", "post").attr("action", "lekovi/delete");
			Element inputHidden = new Element(Tag.valueOf("input"), "").attr("type", "hidden").attr("name", "id")
					.attr("value", String.valueOf(listaLekova.get(i).getId()));
			Element inputSubmit = new Element(Tag.valueOf("input"), "").attr("type", "submit").attr("value", "Obrisi");
			
			form.appendChild(inputHidden);
			form.appendChild(inputSubmit);
			tdForm.appendChild(form);
			trLek.appendChild(tdForm);
			table.appendChild(trLek);
		}
		Element ulTagDodajLek = new Element(Tag.valueOf("ul"), "");
		Element liTagDodajLek = new Element(Tag.valueOf("li"), "");
		Element aTagDodajLek = new Element(Tag.valueOf("a"), "").attr("href", "lekovi/add").text("Dodaj lek");
		
		liTagDodajLek.appendChild(aTagDodajLek);
		ulTagDodajLek.appendChild(liTagDodajLek);
		
		body.appendChild(ulTag);
		body.appendChild(table);
		body.appendChild(ulTagDodajLek);
		
		out.write(doc.html());
		return;
	}
	
	
	
	@GetMapping(value="/add")
	@ResponseBody
	public void create(HttpServletResponse response) throws IOException{
		
		PrintWriter out = response.getWriter();
		File htmlFile = new ClassPathResource("static/template.html").getFile();
		Document doc = Jsoup.parse(htmlFile, "UTF-8");
		
		Element body = doc.select("body").first();
		
		Element ulTag = new Element(Tag.valueOf("ul"),"");
		Element liTagLeka = new Element(Tag.valueOf("li"), "");
		Element aTagLeka = new Element(Tag.valueOf("a"), "").attr("href", "/EUprava/lekovi").text("Lekovi");
		
		
		liTagLeka.appendChild(aTagLeka);
		ulTag.appendChild(liTagLeka);
		
		Element form = new Element(Tag.valueOf("form"), "").attr("method","post").attr("action", "add");
		Element table = new Element(Tag.valueOf("table"),"");
		Element caption = new Element(Tag.valueOf("caption"), "").text("Lek");
		
		Element trNaziv = new Element(Tag.valueOf("tr"), "");
		Element thNaziv = new Element(Tag.valueOf("th"), "").text("Naziv");
		Element tdNaziv = new Element(Tag.valueOf("td"), "");
		Element inputNaziv = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "naziv");
		
		tdNaziv.appendChild(inputNaziv);
		trNaziv.appendChild(thNaziv);
		trNaziv.appendChild(tdNaziv);
		
		Element trOpis = new Element(Tag.valueOf("tr"), "");
		Element thOpis = new Element(Tag.valueOf("th"), "").text("Opis");
		Element tdOpis = new Element(Tag.valueOf("td"), "");
		Element inputOpis = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "opis");
		
		tdOpis.appendChild(inputOpis);
		trOpis.appendChild(thOpis);
		trOpis.appendChild(tdOpis);
		
		Element trKontraindikacije = new Element(Tag.valueOf("tr"), "");
		Element thKontraindikacije  = new Element(Tag.valueOf("th"), "").text("Kontraindikacije");
		Element tdKontraindikacije  = new Element(Tag.valueOf("td"), "");
		Element inputKontraindikacije  = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "kontraindikacije ");
		
		tdKontraindikacije.appendChild(inputKontraindikacije );
		trKontraindikacije.appendChild(thKontraindikacije );
		trKontraindikacije.appendChild(tdKontraindikacije );
		
//		Element trOblik = new Element(Tag.valueOf("tr"), "");
//		Element thOblik = new Element(Tag.valueOf("th"), "").text("Oblik");
//		Element tdOblik = new Element(Tag.valueOf("td"), "");
//		Element inputOblik = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "oblik");
//		
//		tdOblik.appendChild(inputOblik);
//		trOblik.appendChild(thOblik);
//		trOblik.appendChild(tdOblik);
		
		Element trOcena = new Element(Tag.valueOf("tr"), "");
		Element thOcena = new Element(Tag.valueOf("th"), "").text("Ocena");
		Element tdOcena = new Element(Tag.valueOf("td"), "");
		Element inputOcena = new Element(Tag.valueOf("input"), "").attr("type", "number").attr("name", "ocena");
		
		tdOcena.appendChild(inputOcena);
		trOcena.appendChild(thOcena);
		trOcena.appendChild(tdOcena);
		
		Element trKolicina = new Element(Tag.valueOf("tr"), "");
		Element thKolicina = new Element(Tag.valueOf("th"), "").text("Kolicina");
		Element tdKolicina = new Element(Tag.valueOf("td"), "");
		Element inputKolicina = new Element(Tag.valueOf("input"), "").attr("type", "number").attr("name", "kolicina");
		
		tdKolicina.appendChild(inputKolicina);
		trKolicina.appendChild(thKolicina);
		trKolicina.appendChild(tdKolicina);
		
		Element trCena = new Element(Tag.valueOf("tr"), "");
		Element thCena = new Element(Tag.valueOf("th"), "").text("Cena");
		Element tdCena = new Element(Tag.valueOf("td"), "");
		Element inputCena= new Element(Tag.valueOf("input"), "").attr("type", "number").attr("name", "cena");
		
		tdCena.appendChild(inputCena);
		trCena.appendChild(thCena);
		trCena.appendChild(tdCena);
		
		Element trProizvodjac = new Element(Tag.valueOf("tr"), "");
		Element thProizvodjac = new Element(Tag.valueOf("th"), "").text("Proizvodjac");
		Element tdProizvodjac = new Element(Tag.valueOf("td"), "");
		Element inputProizvodjac= new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "proizvodjac");
		
		tdProizvodjac.appendChild(inputProizvodjac);
		trProizvodjac.appendChild(thProizvodjac);
		trProizvodjac.appendChild(tdProizvodjac);
		
		Element trKategorija = new Element(Tag.valueOf("tr"), "");
		Element thKategorija = new Element(Tag.valueOf("th"), "").text("Kategorija");
		Element tdKategorija = new Element(Tag.valueOf("td"), "");
		Element inputKategorija = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "kategorija");
		
		tdKategorija.appendChild(inputKategorija);
		trKategorija.appendChild(thKategorija);
		trKategorija.appendChild(tdKategorija);
		
		Element trSubmit = new Element(Tag.valueOf("tr"), "");
		Element thSubmit = new Element(Tag.valueOf("th"), "");
		Element tdSubmit = new Element(Tag.valueOf("td"), "");
		Element inputSubmit = new Element(Tag.valueOf("input"), "").attr("type", "submit").attr("value", "Dodaj");
		
		tdSubmit.appendChild(inputSubmit);
		trSubmit.appendChild(thSubmit);
		trSubmit.appendChild(tdSubmit);
		
		table.appendChild(caption);
		
		table.appendChild(trNaziv);
		table.appendChild(trOpis);
		table.appendChild(trKontraindikacije);
//		table.appendChild(trOblik);
		table.appendChild(trOcena);
		table.appendChild(trKolicina);
		table.appendChild(trCena);
		table.appendChild(trProizvodjac);
		table.appendChild(trKategorija);
		table.appendChild(trSubmit);
		
		form.appendChild(table);
		
		body.appendChild(ulTag);
		body.appendChild(form);
		
		out.write(doc.html());
		return;
	}
	
	
	@PostMapping(value="/add")
	public void create(@RequestParam String naziv, @RequestParam String opis, 
			@RequestParam(required=false,name="kontraindikacije")  String kontraindikacije, @RequestParam
			(value = "ocena", required = false)
			Integer prosecnaOcena, @RequestParam (required=false,name="kolicina") 
	       Integer dostupnaKolicinaNaStanju, @RequestParam(required=false,name="cena") 
			Double cena, @RequestParam(required=false,name="proizvodjac")  String proizvodjac,
			@RequestParam (required=false,name="kategorija") String kategorija,
			HttpServletResponse response) throws IOException {		
	
		Lekovi lekovi = (Lekovi) memorijaAplikacije.get(LekController.LEKOVI_KEY);
		
		Lek lek = new Lek (naziv,opis, kontraindikacije,  prosecnaOcena
					, dostupnaKolicinaNaStanju, cena, proizvodjac, kategorija);
		Lek saved = lekovi.save(lek);
		response.sendRedirect(bURL + "lekovi");
	}
	
	@PostMapping(value="/edit")
	public void edit(@ModelAttribute Lek lekEdited , HttpServletResponse response) throws IOException {	
		Lekovi lekovi = (Lekovi) memorijaAplikacije.get(LekController.LEKOVI_KEY);
		
		Lek lek = lekovi.findOne(lekEdited.getId());
		if (lek != null) {
			if(lekEdited.getNaziv()!= null && !lekEdited.getNaziv().trim().equals(""))
				lek.setNaziv(lekEdited.getNaziv());
			
			if(lekEdited.getOpis()!= null && !lekEdited.getOpis().trim().equals(""))
				lek.setOpis(lekEdited.getOpis());
			
			if(lekEdited.getKontraindikacije()!= null && !lekEdited.getKontraindikacije().trim().equals(""))
				lek.setKontraindikacije(lekEdited.getKontraindikacije());
			
//			if(lekEdited.getOblik()!= null && !lekEdited.getOblik().toString().trim().equals(""))
//				lek.setOblik(lekEdited.getOblik());
			
			if(lekEdited.getProsecnaOcena() != null)
				lek.setProsecnaOcena(lekEdited.getProsecnaOcena());
			
			if(lekEdited.getDostupnaKolicinaNaStanju() != null)
				lek.setDostupnaKolicinaNaStanju(lekEdited.getDostupnaKolicinaNaStanju());
			
			if(lekEdited.getCena() != null )
				lek.setCena(lekEdited.getCena());
			
			if(lekEdited.getProizvodjac()!= null && !lekEdited.getProizvodjac().trim().equals(""))
				lek.setProizvodjac(lekEdited.getProizvodjac());
			
			if(lekEdited.getKategorija()!= null && !lekEdited.getKategorija().trim().equals(""))
				lek.setKategorija(lekEdited.getKategorija());
		}
		Lek saved = lekovi.save(lek);
		response.sendRedirect(bURL+"lekovi");
	}
	
	
	@PostMapping(value="/delete")
	public void delete(@RequestParam Long id, HttpServletResponse response) throws IOException {
		Lekovi lekovi = (Lekovi) memorijaAplikacije.get(LekController.LEKOVI_KEY);
		
		Lek deleted = lekovi.delete(id);
		response.sendRedirect(bURL + "lekovi");
	}
	

	@GetMapping(value="/details")
	@ResponseBody
	public void details(@RequestParam Long id, HttpServletResponse response) throws IOException {	
		Lekovi lekovi = (Lekovi) memorijaAplikacije.get(LekController.LEKOVI_KEY);
		
		Lek lek = lekovi.findOne(id);
		
		PrintWriter out;
		out = response.getWriter();
		File htmlFile = new ClassPathResource("static/template.html").getFile();
		Document doc = Jsoup.parse(htmlFile, "UTF-8");
		
     	Element body = doc.select("body").first();
		
		Element ulTag = new Element(Tag.valueOf("ul"),"");
		Element liTagLeka = new Element(Tag.valueOf("li"), "");
		Element aTagLeka = new Element(Tag.valueOf("a"), "").attr("href", "/EUprava/lekovi").text("Lekovi");
		
		
		liTagLeka.appendChild(aTagLeka);
		ulTag.appendChild(liTagLeka);
		
		Element form = new Element(Tag.valueOf("form"), "").attr("method","post").attr("action", "edit");
		Element table = new Element(Tag.valueOf("table"),"");
		Element caption = new Element(Tag.valueOf("caption"), "").text("Lek");
		
		Element inputHidden = new Element(Tag.valueOf("input"), "").attr("type", "hidden").attr("name", "id").attr("value", String.valueOf(lek.getId()));
		
		
		Element trNaziv = new Element(Tag.valueOf("tr"), "");
		Element thNaziv = new Element(Tag.valueOf("th"), "").text("Naziv");
		Element tdNaziv = new Element(Tag.valueOf("td"), "");
		Element inputNaziv = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "naziv").attr("value", lek.getNaziv());
		
		tdNaziv.appendChild(inputNaziv);
		trNaziv.appendChild(thNaziv);
		trNaziv.appendChild(tdNaziv);
		
		Element trOpis = new Element(Tag.valueOf("tr"), "");
		Element thOpis = new Element(Tag.valueOf("th"), "").text("Opis");
		Element tdOpis = new Element(Tag.valueOf("td"), "");
		Element inputOpis = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "opis").attr("value", lek.getOpis());
		
		tdOpis.appendChild(inputOpis);
		trOpis.appendChild(thOpis);
		trOpis.appendChild(tdOpis);
		
		Element trKontraindikacije = new Element(Tag.valueOf("tr"), "");
		Element thKontraindikacije  = new Element(Tag.valueOf("th"), "").text("Kontraindikacije ");
		Element tdKontraindikacije  = new Element(Tag.valueOf("td"), "");
		Element inputKontraindikacije  = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "kontraindikacije ").attr("value", lek.getKontraindikacije());
		
		tdKontraindikacije .appendChild(inputKontraindikacije );
		trKontraindikacije .appendChild(thKontraindikacije );
		trKontraindikacije .appendChild(tdKontraindikacije );
		
//		Element trOblik = new Element(Tag.valueOf("tr"), "");
//		Element thOblik= new Element(Tag.valueOf("th"), "").text("Oblik");
//		Element tdOblik = new Element(Tag.valueOf("td"), "");
//		Element inputOblik = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "oblik").attr("value", lek.getOblik());
//		
//		tdOblik.appendChild(inputOblik);
//		trOblik.appendChild(thOblik);
//		trOblik.appendChild(tdOblik);
		
		Element trOcena = new Element(Tag.valueOf("tr"), "");
		Element thOcena= new Element(Tag.valueOf("th"), "").text("Ocena");
		Element tdOcena = new Element(Tag.valueOf("td"), "");
		Element inputOcena = new Element(Tag.valueOf("input"), "").attr("type", "number").attr("name", "ocena").attr("value", String.valueOf(lek.getProsecnaOcena()));
		
		tdOcena.appendChild(inputOcena);
		trOcena.appendChild(thOcena);
		trOcena.appendChild(tdOcena);
		
		Element trKolicina = new Element(Tag.valueOf("tr"), "");
		Element thKolicina= new Element(Tag.valueOf("th"), "").text("Kolicina");
		Element tdKolicina = new Element(Tag.valueOf("td"), "");
		Element inputKolicina = new Element(Tag.valueOf("input"), "").attr("type", "number").attr("name", "kolicina").attr("value", String.valueOf(lek.getDostupnaKolicinaNaStanju()));
		
		tdKolicina.appendChild(inputKolicina);
		trKolicina.appendChild(thKolicina);
		trKolicina.appendChild(tdKolicina);
		
		Element trCena = new Element(Tag.valueOf("tr"), "");
		Element thCena= new Element(Tag.valueOf("th"), "").text("Cena");
		Element tdCena = new Element(Tag.valueOf("td"), "");
		Element inputCena= new Element(Tag.valueOf("input"), "").attr("type", "number").attr("name", "cena").attr("value", String.valueOf(lek.getCena()));
		
		tdCena.appendChild(inputCena);
		trCena.appendChild(thCena);
		trCena.appendChild(tdCena);
		
		Element trProizvodjac = new Element(Tag.valueOf("tr"), "");
		Element thProizvodjac= new Element(Tag.valueOf("th"), "").text("Proizvodjac");
		Element tdProizvodjac = new Element(Tag.valueOf("td"), "");
		Element inputProizvodjac= new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "proizvodjac").attr("value", lek.getProizvodjac());
		
		tdProizvodjac.appendChild(inputProizvodjac);
		trProizvodjac.appendChild(thProizvodjac);
		trProizvodjac.appendChild(tdProizvodjac);
		
		Element trKategorija = new Element(Tag.valueOf("tr"), "");
		Element thKategorija = new Element(Tag.valueOf("th"), "").text("Kategorija");
		Element tdKategorija = new Element(Tag.valueOf("td"), "");
		Element inputKategorija = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "kategorija").attr("value", lek.getKategorija());;
		
		tdKategorija.appendChild(inputKategorija);
		trKategorija.appendChild(thKategorija);
		trKategorija.appendChild(tdKategorija);
		
		Element trSubmit = new Element(Tag.valueOf("tr"), "");
		Element thSubmit = new Element(Tag.valueOf("th"), "");
		Element tdSubmit = new Element(Tag.valueOf("td"), "");
		Element inputSubmit = new Element(Tag.valueOf("input"), "").attr("type", "submit").attr("value", "Izmeni");
		
		tdSubmit.appendChild(inputSubmit);
		trSubmit.appendChild(thSubmit);
		trSubmit.appendChild(tdSubmit);
		
		table.appendChild(caption);
		
		table.appendChild(trNaziv);
		table.appendChild(trOpis);
		table.appendChild(trKontraindikacije);
//		table.appendChild(trOblik);
		table.appendChild(trOcena);
		table.appendChild(trKolicina);
		table.appendChild(trCena);
		table.appendChild(trProizvodjac);
		table.appendChild(trKategorija);
		table.appendChild(trSubmit);
		
		form.appendChild(inputHidden);
		form.appendChild(table);
		
		Element formBrisanje = new Element(Tag.valueOf("form"), "").attr("method", "post").attr("action", "delete");
		Element inputSubmitBrisanje = new Element(Tag.valueOf("input"), "").attr("type", "submit").attr("value", "Obrisi");
		Element inputHiddenBrisanje = new Element(Tag.valueOf("input"), "").attr("type", "hidden").attr("name", "id").attr("value", String.valueOf(lek.getId()));;
		formBrisanje.appendChild(inputHiddenBrisanje);
		formBrisanje.appendChild(inputSubmitBrisanje);

		
		body.appendChild(ulTag);
		body.appendChild(form);
		body.appendChild(formBrisanje);
		
		out.write(doc.html());
		return;
	}

	@Override
	public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		
	}
}

	


