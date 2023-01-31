package com.ftn.EUprava.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.EUprava.model.ProizvodjacVakcine;
import com.ftn.EUprava.model.Vakcina;
import com.ftn.EUprava.service.ProizvodjacVakcineService;
import com.ftn.EUprava.service.VakcinaService;

@Controller
@RequestMapping(value="/vakcine")
public class VakcinaController implements ServletContextAware {

	@Autowired
	private ServletContext servletContext;
	private  String bURL; 
	
	@Autowired
	private VakcinaService vakcinaService;
	
	@Autowired
	private ProizvodjacVakcineService proizvodjacVakcineService;
	
	
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
		ModelAndView rezultat = new ModelAndView("vakcine"); 
		rezultat.addObject("vakcine", vakcine); 

		return rezultat;
	}
	
	@GetMapping(value="/add")
	public ModelAndView create() {
		List<ProizvodjacVakcine> proizvodjaciVakcine = proizvodjacVakcineService.findAll();
		
		ModelAndView rezultat = new ModelAndView("dodavanjeVakcine"); 
		rezultat.addObject("proizvodjaciVakcine", proizvodjaciVakcine); 

		return rezultat; 
	}

//	@PostMapping(value="/add")
//	public void create(@RequestParam String ime, @RequestParam int dostupnaKolicina,  
//			 @RequestParam Long idProizvodjacaVakcine, HttpServletResponse response) throws IOException {	
//		ProizvodjacVakcine proizvodjacVakcine  = proizvodjacVakcineService.findOne(idProizvodjacaVakcine);
//		if (proizvodjacVakcine == null) {
//			//todo domaci vrati gresku
//		}
//		
//		Vakcina vakcina = new Vakcina(ime, dostupnaKolicina, proizvodjacVakcine);
//		vakcinaService.save(vakcina);
//		response.sendRedirect(bURL+"vakcine");
//	}
	
	@PostMapping(value="/add")
	public void create(@RequestParam String ime,   
	     Long idProizvodjacaVakcine, HttpServletResponse response) throws IOException {	
	    ProizvodjacVakcine proizvodjacVakcine = null;
	    if (idProizvodjacaVakcine != 0) {
	        proizvodjacVakcine = proizvodjacVakcineService.findOne(idProizvodjacaVakcine);
	        if (proizvodjacVakcine == null) {
	            // Handle error
	        }
	    }
	    
	    Vakcina vakcina = new Vakcina(ime,  proizvodjacVakcine);
	    vakcinaService.save(vakcina);
	    response.sendRedirect(bURL+"vakcine");
	}

	
	@SuppressWarnings("unused")
	@PostMapping(value="/edit")
	public void Edit(@RequestParam Long id, @RequestParam String ime, @RequestParam int dostupnaKolicina,  
			 @RequestParam Long idProizvodjacaVakcine , HttpServletResponse response) throws IOException {	
		ProizvodjacVakcine proizvodjacVakcine  = proizvodjacVakcineService.findOne(idProizvodjacaVakcine);
		if (proizvodjacVakcine == null) {
			//todo domaci vrati gresku
		}
		Vakcina vakcina = vakcinaService.findOne(id);
		if(vakcina != null) {
			if(ime != null && !ime.trim().equals(""))
				vakcina.setIme(ime);
			if(dostupnaKolicina > 0)
				vakcina.setDostupnaKolicina(dostupnaKolicina);
			if(proizvodjacVakcine != null)
				vakcina.setProizvodjac(proizvodjacVakcine);
		}
		Vakcina saved = vakcinaService.update(vakcina);
		response.sendRedirect(bURL+"vakcine");
	}
	

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
		
		ModelAndView rezultat = new ModelAndView("vakcina"); 
		rezultat.addObject("vakcina", vakcina); 

		return rezultat; 
	}
	

}
