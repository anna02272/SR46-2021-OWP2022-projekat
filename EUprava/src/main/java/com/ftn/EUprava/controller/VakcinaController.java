package com.ftn.EUprava.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.EUprava.model.ProizvodjacVakcine;
import com.ftn.EUprava.model.Vakcina;
import com.ftn.EUprava.service.VakcinaService;

@Controller
@RequestMapping(value="/vakcine")
public class VakcinaController implements ServletContextAware {

	@Autowired
	private ServletContext servletContext;
	private  String bURL; 
	
	@Autowired
	private VakcinaService vakcinaService;
	
	/** inicijalizacija podataka za kontroler */
	@PostConstruct
	public void init() {	
		bURL = servletContext.getContextPath()+"/";
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	} 
	
	@GetMapping
	public ModelAndView index() {
		List<Vakcina> vakcine = vakcinaService.findAll();
		// podaci sa nazivom template-a
		ModelAndView rezultat = new ModelAndView("vakcine"); // naziv template-a
		rezultat.addObject("vakcine", vakcine); // podatak koji se šalje template-u

		return rezultat; // prosleđivanje zahteva zajedno sa podacima template-u
	}
	
	@GetMapping(value="/add")
	public String create(HttpSession session, HttpServletResponse response){
		return "dodavanjeVakcine"; // stranica za dodavanje knjige
	}

	/** obrada podataka forme za unos novog entiteta, post zahtev */
	// POST: vakcine/add
	@SuppressWarnings("unused")
	@PostMapping(value="/add")
	public void create(@RequestParam String ime, @RequestParam int dostupnaKolicina,  
			@RequestParam ProizvodjacVakcine proizvodjac, HttpServletResponse response) throws IOException {		
		Vakcina vakcina = new Vakcina(ime, dostupnaKolicina, proizvodjac);
		Vakcina saved = vakcinaService.save(vakcina);
		response.sendRedirect(bURL+"vakcine");
	}
	
	/** obrada podataka forme za izmenu postojećeg entiteta, post zahtev */
	// POST: vakcine/edit
	@SuppressWarnings("unused")
	@PostMapping(value="/edit")
	public void Edit(@RequestParam Long id, @RequestParam String ime, @RequestParam int dostupnaKolicina,  
			@RequestParam ProizvodjacVakcine proizvodjac , HttpServletResponse response) throws IOException {	
		Vakcina vakcina = vakcinaService.findOne(id);
		if(vakcina != null) {
			if(ime != null && !ime.trim().equals(""))
				vakcina.setIme(ime);
			if(dostupnaKolicina > 0)
				vakcina.setDostupnaKolicina(dostupnaKolicina);
			if(proizvodjac != null)
				vakcina.setProizvodjac(proizvodjac);
		}
		Vakcina saved = vakcinaService.update(vakcina);
		response.sendRedirect(bURL+"vakcine");
	}
	
	/** obrada podataka forme za za brisanje postojećeg entiteta, post zahtev */
	// POST: vakcine/delete
	@SuppressWarnings("unused")
	@PostMapping(value="/delete")
	public void delete(@RequestParam Long id, HttpServletResponse response) throws IOException {		
		Vakcina deleted = vakcinaService.delete(id);
		response.sendRedirect(bURL+"vakcine");
	}
	
	@GetMapping(value="/details")
	@ResponseBody
	public ModelAndView details(@RequestParam Long id) {	
		Vakcina vakcina  = vakcinaService.findOne(id);
		
		// podaci sa nazivom template-a
		ModelAndView rezultat = new ModelAndView("vakcina"); // naziv template-a
		rezultat.addObject("vakcina", vakcina); // podatak koji se šalje template-u

		return rezultat; // prosleđivanje zahteva zajedno sa podacima template-u
	}
	

}
