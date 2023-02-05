package com.ftn.EUprava.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.EUprava.model.EDoza;
import com.ftn.EUprava.model.EStatus;
import com.ftn.EUprava.model.Korisnik;
import com.ftn.EUprava.model.Nabavka;
import com.ftn.EUprava.model.PrijavaZaVakcinaciju;
import com.ftn.EUprava.model.ProizvodjacVakcine;
import com.ftn.EUprava.model.Vakcina;
import com.ftn.EUprava.service.KorisnikService;
import com.ftn.EUprava.service.PrijavaZaVakcinacijuService;
import com.ftn.EUprava.service.VakcinaService;

@Controller
@RequestMapping(value="/prijaveZaVakcinaciju")
public class PrijavaZaVakcinacijuController implements ServletContextAware {

	@Autowired
	private ServletContext servletContext;
	private  String bURL; 
	
	@Autowired
	private PrijavaZaVakcinacijuService prijavaZaVakcinacijuService;
	
	@Autowired
	private VakcinaService vakcinaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@PostConstruct
	public void init() {	
		bURL = servletContext.getContextPath()+"/";
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	} 

//	@GetMapping
//	public ModelAndView index() {
//		List<PrijavaZaVakcinaciju> prijaveZaVakcinaciju = prijavaZaVakcinacijuService.findAll();	
//		
//		ModelAndView rezultat = new ModelAndView("prijaveZaVakcinaciju"); 
//		rezultat.addObject("prijaveZaVakcinaciju", prijaveZaVakcinaciju); 
//
//		return rezultat; 
//	}
	@GetMapping
	public ModelAndView Index(
			@RequestParam(required=false) String ime, 
			@RequestParam(required=false) String prezime, 
			@RequestParam(required=false) String jmbg,
			
			HttpSession session) throws IOException {
		
		
		
		List<PrijavaZaVakcinaciju> prijaveZaVakcinaciju = prijavaZaVakcinacijuService.find(ime, prezime, jmbg);
		List<Korisnik> korisnici = korisnikService.findAll();

		ModelAndView rezultat = new ModelAndView("prijaveZaVakcinaciju");
		rezultat.addObject("prijaveZaVakcinaciju", prijaveZaVakcinaciju);
		rezultat.addObject("korisnici", korisnici);
		return rezultat;
		
	}
	
	@GetMapping(value="/add")
	public String create(HttpSession session, HttpServletResponse response ) {
		
		List<Korisnik> korisnici = korisnikService.findAll();
		session.setAttribute("korisnici", korisnici);
		
		List<Vakcina> vakcine = vakcinaService.findAll();
		session.setAttribute("vakcine", vakcine);
		

		return "dodavanjePrijaveZaVakcinaciju"; 
	}
	
	@SuppressWarnings("unused")
	@PostMapping(value="/add")
	public void create
			(@RequestParam(value = "korisnikId") @PathVariable("id") Long korisnikId,
			@RequestParam(value = "vakcinaId") @PathVariable("id") Long vakcinaId,
			@RequestParam EDoza doza,
			
	HttpServletResponse response) throws IOException {	
	  
	     Korisnik korisnik = korisnikService.findOneById(korisnikId);
	     Vakcina vakcina = vakcinaService.findOne(vakcinaId);
	    
	     PrijavaZaVakcinaciju prijavaZaVakcinaciju = new PrijavaZaVakcinaciju(korisnik, vakcina, doza);
	     System.out.println(prijavaZaVakcinaciju);
	     PrijavaZaVakcinaciju saved = prijavaZaVakcinacijuService.save(prijavaZaVakcinaciju);
	    response.sendRedirect(bURL+"prijaveZaVakcinaciju");
	}

	
	@SuppressWarnings("unused")
	@PostMapping(value="/delete")
	public void delete(@RequestParam Long id, HttpServletResponse response) throws IOException {		
		PrijavaZaVakcinaciju deleted = prijavaZaVakcinacijuService.delete(id);
		response.sendRedirect(bURL+"prijaveZaVakcinaciju");
	}
	@GetMapping(value="/details")
	@ResponseBody
	public ModelAndView details(@RequestParam Long id) {	
		PrijavaZaVakcinaciju prijavaZaVakcinaciju  = prijavaZaVakcinacijuService.findOne(id);
		List<Korisnik> korisnici = korisnikService.findAll();
		List<Vakcina> vakcine = vakcinaService.findAll();
		ModelAndView rezultat = new ModelAndView("prijavaZaVakcinaciju"); 
		rezultat.addObject("prijavaZaVakcinaciju", prijavaZaVakcinaciju); 
		rezultat.addObject("korisnici", korisnici); 
		rezultat.addObject("vakcine", vakcine); 
		return rezultat; 
	}
	
	@PostMapping(value = "/search")
	public ModelAndView search(@RequestParam(required=false) String ime, 
	                           @RequestParam(required=false) String prezime, 
	                           @RequestParam(required=false) String jmbg) {
		
		 ModelAndView rezlutat = new ModelAndView("prijaveZaVakcinaciju");
	  List<Korisnik> korisnici = korisnikService.findAll();
	  List<PrijavaZaVakcinaciju> prijaveFilter = prijavaZaVakcinacijuService.find(ime, prezime, jmbg);
		
	  rezlutat.addObject("prijaveZaVakcinaciju", prijaveFilter);
	  rezlutat.addObject("korisnici", korisnici);
	  
	  
	  return rezlutat;
	}
	
	
	@PostMapping(value = "/dajVakcinu")
	public String dajVakcinu(@RequestParam("korisnikId") Long korisnikId,
	                        @RequestParam("vakcinaId") Long vakcinaId,
	                        @RequestParam("doza") EDoza doza) {
		
	    jdbcTemplate.update("INSERT INTO primljeneVakcine(korisnikId, vakcinaId, EDoza) values(?, ?, ?)",
                korisnikId, vakcinaId, doza.toString());

	    jdbcTemplate.update("UPDATE vakcine SET dostupnaKolicina = dostupnaKolicina - 1 WHERE id = ?",
	    		vakcinaId);


	    jdbcTemplate.update("DELETE FROM prijaveZaVakcinaciju WHERE korisnikid = ?", korisnikId);

	
	   return "redirect:/prijaveZaVakcinaciju";
	}

	
}
