package niestroj.project.web;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import niestroj.project.dao.PostDAO;

@Named
@RequestScoped
public class KategoriaBB {

	@Inject
	FacesContext ctx;
	
	@Inject
	Flash flash;
	
	@EJB
	PostDAO postDAO;
	
	
	public List<String> getList(){
		List<String> list =  postDAO.listaKategorii();
		return list;
	}
	
	public String ilePostow(String kategoria) {
		Integer i = postDAO.ile2(kategoria);
		String ile = i.toString();
		return ile;
	}
}
