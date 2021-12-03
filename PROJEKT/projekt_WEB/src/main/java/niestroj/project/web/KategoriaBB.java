package niestroj.project.web;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import niestroj.project.dao.PostDAO;
import niestroj.project.entities.Post;

@Named
@RequestScoped
public class KategoriaBB {
	private static final String PAGE_POST_EDIT = "NowyPost?faces-redirect=true";
	private static final String PAGE_POST = "Post?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

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
}
