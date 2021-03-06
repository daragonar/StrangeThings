package netflix.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import netflix.PropertyEditors.PermisoPropertyEditors;
import netflix.modelo.entidades.Genero;
import netflix.modelo.entidades.Permiso;
import netflix.modelo.entidades.Persona;
import netflix.modelo.repositorio.RepositorioPermisos;
import netflix.modelo.repositorio.RepositorioPersona;
import netflix.modelo.repositorio.RepositorioGeneros;

@Controller
public class ControllerHome {
	@Autowired
	private RepositorioPermisos repoPer;
	@Autowired
	private RepositorioGeneros repogene;
	@Autowired
	private RepositorioPersona repoUsu;
	@Autowired
	private PermisoPropertyEditors permipro;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, @AuthenticationPrincipal Persona perso,HttpServletRequest request) {
		WebDataBinder webdat;
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		repoPer.save(new Permiso((long) 1, "ADMIN"));
		repoPer.save(new Permiso((long) 2, "USER"));
		repogene.save(new Genero((long) 1, "Terror"));
		repogene.save(new Genero((long) 2, "Comedia"));
		repogene.save(new Genero((long) 3, "Tristona"));
		repogene.save(new Genero((long) 4, "Netflix"));
		repogene.save(new Genero((long) 5, "Fumeta"));
		repogene.save(new Genero((long) 6, "De autor"));
		if (null != perso) {
			 request.getSession().setAttribute("persona", perso);
		}
		return "index";
	}

}
