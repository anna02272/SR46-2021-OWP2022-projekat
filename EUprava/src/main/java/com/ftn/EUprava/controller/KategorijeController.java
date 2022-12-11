package com.ftn.EUprava.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
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
import org.springframework.web.context.ServletContextAware;

import com.ftn.EUprava.bean.SecondConfiguration.ApplicationMemory;
import com.ftn.EUprava.model.KategorijaLeka;
import com.ftn.EUprava.model.KategorijeLekova;
import com.ftn.EUprava.service.KategorijaLekaService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

@Controller
@RequestMapping(value="/kategorije")
public class KategorijeController implements ServletContextAware {

	public static final String KATEGORIJE_KEY = "kategorije";
	
	@Autowired
	private ServletContext servletContext;
	private  String bURL; 
	
	
	@Autowired
	private ApplicationMemory memorijaAplikacije;
	
	@Autowired
	private KategorijaLekaService kategorijaLekaService;
	
	@PostConstruct
	public void init() {
		bURL = servletContext.getContextPath()+"/";
	}


	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	
	@GetMapping 
	@ResponseBody
	public void index(HttpServletResponse response) throws IOException {	
		List<KategorijaLeka> kategorijeLekova = kategorijaLekaService.findAll();
		
		PrintWriter out;
		out = response.getWriter();
		File htmlFile = new ClassPathResource("static/template.html").getFile();
		Document doc = Jsoup.parse(htmlFile, "UTF-8");

		Element body = doc.select("body").first();
		
		Element ulTag = new Element(Tag.valueOf("ul"), "");
		Element liTagKategorije = new Element(Tag.valueOf("li"), "");
		Element aTagKategorije = new Element(Tag.valueOf("a"), "").attr("href", "/EUprava/kategorije").text("Kategorije");
		liTagKategorije.appendChild(aTagKategorije);
		ulTag.appendChild(liTagKategorije);
		
		Element table = new Element(Tag.valueOf("table"), "");
		Element caption = new Element(Tag.valueOf("caption"), "").text("Kategorije");
		Element trZaglavlje = new Element(Tag.valueOf("tr"), "");
		Element thRedniBroj = new Element(Tag.valueOf("th"), "").text("R. br.");
		Element thDetails = new Element(Tag.valueOf("th"), "").text("Details");
		
		Element thNaziv = new Element(Tag.valueOf("th"), "").text("Naziv");
		Element thNamena = new Element(Tag.valueOf("th"), "").text("Namena");
		Element thOpis = new Element(Tag.valueOf("th"), "").text("Opis");
		
		trZaglavlje.appendChild(thRedniBroj);
		trZaglavlje.appendChild(thDetails);
		trZaglavlje.appendChild(thNaziv);
		trZaglavlje.appendChild(thNamena);
		trZaglavlje.appendChild(thOpis);
		
		table.appendChild(caption);
		table.appendChild(trZaglavlje);
			
		for (int i=0; i < kategorijeLekova.size(); i++) {
				Element trKategorija = new Element(Tag.valueOf("tr"), "");
				Element tdRedniBroj = new Element(Tag.valueOf("td"), "").text(String.valueOf(i + 1));
				Element tdDetails = new Element(Tag.valueOf("td"), "");
				Element aDetails = new Element(Tag.valueOf("a"), "").attr("href","kategorije/details?id="+ kategorijeLekova.get(i).getId()).text(kategorijeLekova.get(i).getNaziv());
				Element tdNaziv = new Element(Tag.valueOf("td"), "").text(kategorijeLekova.get(i).getNaziv());
				Element tdNamena = new Element(Tag.valueOf("td"), "").text(kategorijeLekova.get(i).getNamena());
				Element tdOpis = new Element(Tag.valueOf("td"), "").text(kategorijeLekova.get(i).getOpis());
				
				tdDetails.appendChild(aDetails);
				trKategorija.appendChild(tdRedniBroj);
				trKategorija.appendChild(tdDetails);
				trKategorija.appendChild(tdNaziv);
				trKategorija.appendChild(tdNamena);
				trKategorija.appendChild(tdOpis);
				
				Element tdForm = new Element(Tag.valueOf("td"), "");
				Element form = new Element(Tag.valueOf("form"), "").attr("method", "post").attr("action", "kategorije/delete");
				Element inputHidden = new Element(Tag.valueOf("input"), "").attr("type", "hidden").attr("name", "id").attr("value", String.valueOf(kategorijeLekova.get(i).getId()));
				Element inputSubmit = new Element(Tag.valueOf("input"), "").attr("type", "submit").attr("value", "Obrisi");
				form.appendChild(inputHidden);
				form.appendChild(inputSubmit);
				tdForm.appendChild(form);
				trKategorija.appendChild(tdForm);
				table.appendChild(trKategorija);
			}
		
		
		Element ulTagDodajKategoriju = new Element(Tag.valueOf("ul"), "");
		Element liTagDodajKategoriju = new Element(Tag.valueOf("li"), "");
		Element aTagDodajKategoriju = new Element(Tag.valueOf("a"), "").attr("href", "kategorije/add").text("Dodaj kategriju");
		liTagDodajKategoriju.appendChild(aTagDodajKategoriju);
		ulTagDodajKategoriju.appendChild(liTagDodajKategoriju);
		
		body.appendChild(ulTag);
		body.appendChild(table);
		body.appendChild(ulTagDodajKategoriju);
		
		out.write(doc.html());
		return;
	}
	
	
	@GetMapping(value="/add")
	@ResponseBody
	public void create(HttpServletResponse response) throws IOException {	
		
		KategorijeLekova kategorijeLekova = (KategorijeLekova) memorijaAplikacije.get(KategorijeController.KATEGORIJE_KEY);
		PrintWriter out = response.getWriter();
		File htmlFile = new ClassPathResource("static/template.html").getFile();
		Document doc = Jsoup.parse(htmlFile, "UTF-8");

		Element body = doc.select("body").first();
		
		Element ulTag = new Element(Tag.valueOf("ul"), "");
		Element liTagKategorije = new Element(Tag.valueOf("li"), "");
		Element aTagKategorije = new Element(Tag.valueOf("a"), "").attr("href", "/EUprava/kategorije").text("Kategorije");
		liTagKategorije.appendChild(aTagKategorije);
		ulTag.appendChild(liTagKategorije);
		
		Element form = new Element(Tag.valueOf("form"), "").attr("method", "post").attr("action", "add");
		Element table = new Element(Tag.valueOf("table"), "");
		Element caption = new Element(Tag.valueOf("caption"), "").text("Kategorija");
		
		Element trNaziv = new Element(Tag.valueOf("tr"), "");
		Element thNaziv = new Element(Tag.valueOf("th"), "").text("Naziv");
		Element tdNaziv = new Element(Tag.valueOf("td"), "");
		Element inputNaziv = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "naziv");
		
		tdNaziv.appendChild(inputNaziv);
		trNaziv.appendChild(thNaziv);
		trNaziv.appendChild(tdNaziv);
		
		Element trNamena = new Element(Tag.valueOf("tr"), "");
		Element thNamena = new Element(Tag.valueOf("th"), "").text("Namena");
		Element tdNamena = new Element(Tag.valueOf("td"), "");
		Element inputNamena = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "namena");
		
		tdNamena.appendChild(inputNamena);
		trNamena.appendChild(thNamena);
		trNamena.appendChild(tdNamena);
		
		Element trOpis = new Element(Tag.valueOf("tr"), "");
		Element thOpis = new Element(Tag.valueOf("th"), "").text("Opis");
		Element tdOpis = new Element(Tag.valueOf("td"), "");
		Element inputOpis = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "opis");
		
		tdOpis.appendChild(inputOpis);
		trOpis.appendChild(thOpis);
		trOpis.appendChild(tdOpis);
		
	
		
		Element trSubmit = new Element(Tag.valueOf("tr"), "");
		Element thSubmit = new Element(Tag.valueOf("th"), "");
		Element tdSubmit = new Element(Tag.valueOf("td"), "");
		Element inputSubmit = new Element(Tag.valueOf("input"), "").attr("type", "submit").attr("value", "Dodaj");
		
		tdSubmit.appendChild(inputSubmit);
		trSubmit.appendChild(thSubmit);
		trSubmit.appendChild(tdSubmit);
		
		table.appendChild(caption);
		
		table.appendChild(trNaziv);
		table.appendChild(trNamena);
		table.appendChild(trOpis);
		table.appendChild(trSubmit);
		
		form.appendChild(table);
		
		body.appendChild(ulTag);
		body.appendChild(form);
		
		out.write(doc.html());
		return;
	}
	
	
	@PostMapping(value="/add")
	public void create(@RequestParam String naziv, @RequestParam String namena, 
			@RequestParam String opis, HttpServletResponse response) throws IOException {		
		KategorijeLekova kategorijeLekova = (KategorijeLekova) memorijaAplikacije.get(KategorijeController.KATEGORIJE_KEY);
			
		KategorijaLeka kategorijaLeka = new KategorijaLeka(naziv, namena, opis);
		KategorijaLeka saved = kategorijeLekova.save(kategorijaLeka);
		response.sendRedirect(bURL+"kategorije");
	}
	
	
	@PostMapping(value="/edit")
	public void Edit(@ModelAttribute KategorijaLeka kategorijaEdited , HttpServletResponse response) throws IOException {	
		
		KategorijaLeka kategorijaLeka = kategorijaLekaService.findOne(kategorijaEdited.getId());
		if(kategorijaLeka != null) {
			if(kategorijaEdited.getNaziv() != null && !kategorijaEdited.getNaziv().trim().equals(""))
				kategorijaLeka.setNaziv(kategorijaEdited.getNaziv());
			if(kategorijaEdited.getNamena() != null && !kategorijaEdited.getNamena().trim().equals(""))
				kategorijaLeka.setNamena(kategorijaEdited.getNamena());
			if(kategorijaEdited.getOpis() != null && !kategorijaEdited.getOpis().trim().equals(""))
				kategorijaLeka.setOpis(kategorijaEdited.getOpis());
		}
		KategorijaLeka saved = kategorijaLekaService.update(kategorijaLeka);
		response.sendRedirect(bURL+"kategorije");
	}
	
	
	@PostMapping(value="/delete")
	public void delete(@RequestParam Long id, HttpServletResponse response) throws IOException {
		//preuzimanje vrednosti iz konteksta
		
		KategorijaLeka deleted = kategorijaLekaService.delete(id);
		response.sendRedirect(bURL+"kategorije");
	}
	
	
	@GetMapping(value="/details")
	@ResponseBody
	public void details(@RequestParam Long id, HttpServletResponse response) throws IOException {	
		//preuzimanje vrednosti iz konteksta

		
		KategorijaLeka kategorijaLeka = kategorijaLekaService.findOne(id);
		
		PrintWriter out;
		out = response.getWriter();
		File htmlFile = new ClassPathResource("static/template.html").getFile();
		Document doc = Jsoup.parse(htmlFile, "UTF-8");

		Element body = doc.select("body").first();
		
		Element ulTag = new Element(Tag.valueOf("ul"), "");
		Element liTagKategorije = new Element(Tag.valueOf("li"), "");
		Element aTagKategorije = new Element(Tag.valueOf("a"), "").attr("href", "/EUprava/kategorije").text("Kategorije");
		liTagKategorije.appendChild(aTagKategorije);
		ulTag.appendChild(liTagKategorije);
		
		Element form = new Element(Tag.valueOf("form"), "").attr("method", "post").attr("action", "edit");
		Element table = new Element(Tag.valueOf("table"), "");
		Element caption = new Element(Tag.valueOf("caption"), "").text("Kategorija");
		
		Element inputHidden = new Element(Tag.valueOf("input"), "").attr("type", "hidden").attr("name", "id").attr("value", String.valueOf(kategorijaLeka.getId()));
		
		Element trNaziv = new Element(Tag.valueOf("tr"), "");
		Element thNaziv = new Element(Tag.valueOf("th"), "").text("Naziv");
		Element tdNaziv = new Element(Tag.valueOf("td"), "");
		Element inputNaziv = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "naziv").attr("value", kategorijaLeka.getNaziv());
		
		tdNaziv.appendChild(inputNaziv);
		trNaziv.appendChild(thNaziv);
		trNaziv.appendChild(tdNaziv);
		
		Element trNamena = new Element(Tag.valueOf("tr"), "");
		Element thNamena = new Element(Tag.valueOf("th"), "").text("Namena");
		Element tdNamena = new Element(Tag.valueOf("td"), "");
		Element inputNamena = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "namena").attr("value", kategorijaLeka.getNamena());
		
		tdNamena.appendChild(inputNamena);
		trNamena.appendChild(thNamena);
		trNamena.appendChild(tdNamena);
		
		Element trOpis = new Element(Tag.valueOf("tr"), "");
		Element thOpis = new Element(Tag.valueOf("th"), "").text("Opis");
		Element tdOpis = new Element(Tag.valueOf("td"), "");
		Element inputOpis = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "opis").attr("value", kategorijaLeka.getOpis());
		
		tdOpis.appendChild(inputOpis);
		trOpis.appendChild(thOpis);
		trOpis.appendChild(tdOpis);
		

		
		Element trSubmit = new Element(Tag.valueOf("tr"), "");
		Element thSubmit = new Element(Tag.valueOf("th"), "");
		Element tdSubmit = new Element(Tag.valueOf("td"), "");
		Element inputSubmit = new Element(Tag.valueOf("input"), "").attr("type", "submit").attr("value", "Izmeni");
		
		tdSubmit.appendChild(inputSubmit);
		trSubmit.appendChild(thSubmit);
		trSubmit.appendChild(tdSubmit);
		
		table.appendChild(caption);
		
		table.appendChild(trNaziv);
		table.appendChild(trNamena);
		table.appendChild(trOpis);
		table.appendChild(trSubmit);

		form.appendChild(inputHidden);
		form.appendChild(table);
		
		Element formBrisanje = new Element(Tag.valueOf("form"), "").attr("method", "post").attr("action", "delete");
		Element inputSubmitBrisanje = new Element(Tag.valueOf("input"), "").attr("type", "submit").attr("value", "Obrisi");
		Element inputHiddenBrisanje = new Element(Tag.valueOf("input"), "").attr("type", "hidden").attr("name", "id").attr("value", String.valueOf(kategorijaLeka.getId()));;
		formBrisanje.appendChild(inputHiddenBrisanje);
		formBrisanje.appendChild(inputSubmitBrisanje);

		body.appendChild(ulTag);
		body.appendChild(form);
		body.appendChild(formBrisanje);

		
		out.write(doc.html());
		return;		
	}

	
	
}
