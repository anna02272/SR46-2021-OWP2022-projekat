package com.ftn.EUprava.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.EUprava.model.Vest;
import com.ftn.EUprava.model.VestOObolelima;
import com.ftn.EUprava.service.VestOObolelimaService;
import com.ftn.EUprava.service.VestService;

@Controller
@RequestMapping(value="/vestiOObolelima")
public class VestOObolelimaController implements ServletContextAware {

		
		@Autowired
		private ServletContext servletContext;
		private  String bURL; 
		
		@Autowired
		private VestOObolelimaService vestOObolelimaService;
		
		
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
			List<VestOObolelima> vestiOObolelima = vestOObolelimaService.findAll();		
			
			ModelAndView rezultat = new ModelAndView("vestiOObolelima"); 
			rezultat.addObject("vestiOObolelima", vestiOObolelima); 

			return rezultat; 
		}

		@GetMapping(value="/add")
		public String create(HttpSession session, HttpServletResponse response){
			return "dodavanjeVestiOObolelima"; 
		}

		
		@SuppressWarnings("unused")
		@PostMapping(value="/add")
		public void create(@RequestParam int oboleliUPoslednja24h, @RequestParam int testiraniUPoslednja24h, 
				@RequestParam int ukupnoOboleliOdPocetkaPandemije, @RequestParam int hospitalizovani, 
				@RequestParam int naRespiratoru, 
				@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDateTime datumIVremeObjavljivanja, 
				HttpServletResponse response) throws IOException {		
			VestOObolelima vestOObolelima = new VestOObolelima(oboleliUPoslednja24h, testiraniUPoslednja24h,
					ukupnoOboleliOdPocetkaPandemije, hospitalizovani, naRespiratoru, datumIVremeObjavljivanja);
			VestOObolelima saved = vestOObolelimaService.save(vestOObolelima);
			response.sendRedirect(bURL+"vestiOObolelima");
		}
		
	
		@SuppressWarnings("unused")
		@PostMapping(value="/edit")
		public void Edit(@RequestParam Long id,@RequestParam int oboleliUPoslednja24h, @RequestParam int testiraniUPoslednja24h, 
				@RequestParam int ukupnoOboleliOdPocetkaPandemije, @RequestParam int hospitalizovani, 
				@RequestParam int naRespiratoru, 
				@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDateTime datumIVremeObjavljivanja, 
				HttpServletResponse response) throws IOException {	
			VestOObolelima vestOObolelima = vestOObolelimaService.findOne(id);
			if(vestOObolelima != null) {
				if(oboleliUPoslednja24h > 0)
					vestOObolelima.setOboleliUPoslednja24h(oboleliUPoslednja24h);
				if(testiraniUPoslednja24h > 0)
					vestOObolelima.setTestiraniUPoslednja24h(testiraniUPoslednja24h);
				if(ukupnoOboleliOdPocetkaPandemije > 0)
					vestOObolelima.setUkupnoOboleliOdPocetkaPandemije(ukupnoOboleliOdPocetkaPandemije);
				if(hospitalizovani> 0)
					vestOObolelima.setHospitalizovani(hospitalizovani);
				if(naRespiratoru > 0)
					vestOObolelima.setNaRespiratoru(naRespiratoru);
				if(datumIVremeObjavljivanja != null)
					vestOObolelima.setDatumIVremeObjavljivanja(datumIVremeObjavljivanja);
			}
			VestOObolelima saved = vestOObolelimaService.update(vestOObolelima);
			response.sendRedirect(bURL+"vestiOObolelima");
		}
		
	
		@SuppressWarnings("unused")
		@PostMapping(value="/delete")
		public void delete(@RequestParam Long id, HttpServletResponse response) throws IOException {		
			VestOObolelima deleted = vestOObolelimaService.delete(id);
			response.sendRedirect(bURL+"vestiOObolelima");
		}
		
		@GetMapping(value="/details")
		@ResponseBody
		public ModelAndView details(@RequestParam Long id) {	
			VestOObolelima vestOObolelima = vestOObolelimaService.findOne(id);
			
		
			ModelAndView rezultat = new ModelAndView("vestOObolelima"); 
			rezultat.addObject("vestOObolelima", vestOObolelima); 

			return rezultat; 
		}

	}
