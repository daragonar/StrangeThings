package netflix.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import netflix.PropertyEditors.GeneroPropertyEditors;
import netflix.modelo.entidades.Genero;
import netflix.modelo.entidades.Pelicula;
import netflix.modelo.entidades.Persona;
import netflix.modelo.repositorio.RepositorioGeneros;
import netflix.modelo.repositorio.RepositorioPeliculas;
import netflix.modelo.repositorio.RepositorioPersona;


@Controller
@RequestMapping("/peliculas")
public class ControllerPeliculas {
	@Autowired
	private RepositorioGeneros repogene;
	@Autowired
	private GeneroPropertyEditors geneproped;
	@Autowired
	private RepositorioPeliculas repopel;
	@Autowired
	private RepositorioPersona repousu;
	
	@RequestMapping(method = RequestMethod.POST)
	public String InicioPorPost(Model model, @Valid @ModelAttribute Pelicula peli,BindingResult bindingResult) {
		repopel.save(peli);
		model.addAttribute("peliculas", repopel.findAll());
		model.addAttribute("generos", repogene.findAll());
		return "pages/pelicula";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/peli/{id}")
	@ResponseBody
	public Pelicula editarPeli(@PathVariable Long id)
	{
		Pelicula peli=repopel.findOne(id);
		return peli;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String InicioPorGet(Model model) {
		model.addAttribute("peliculas", repopel.findAll());
		model.addAttribute("generos", repogene.findAll());
		return "pages/pelicula";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public String detalle(Model model, @PathVariable Long id)
	{
		model.addAttribute("pelicula",repopel.findOne(id));
		return "pages/detalle";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/alq/{idp}-{idu}")
	public String alquilar(Model model, @PathVariable Long idp, @PathVariable Long idu)
	{
		Pelicula newpel= repopel.findOne(idp);
		Persona newper= repousu.findOne(idu);
		newpel.addPersona(newper);
		repopel.save(newpel);
		model.addAttribute("pelicula",repopel.findOne(idp));
		return "pages/detalle";
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<String> borrar(Model model, @PathVariable Long id)
	{
		try {
			repopel.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception ex){
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Genero.class, geneproped);
	}

	
}
