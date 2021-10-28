package niestroj.project.web;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import niestroj.project.dao.PostDAO;
import niestroj.project.entities.Post;

@Named
@RequestScoped
public class AktualnosciBB {

	@Inject
	FacesContext ctx;
	
	@EJB
	PostDAO postDAO;
	
	
	public List<Post> getList(){
		List<Post> list =  postDAO.lista();
		return list;
	}
}
