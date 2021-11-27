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
public class AktualnosciBB {
	private static final String PAGE_POST_EDIT = "NowyPost?faces-redirect=true";
	private static final String PAGE_POST = "Post?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	@Inject
	FacesContext ctx;
	
	@Inject
	Flash flash;
	
	@EJB
	PostDAO postDAO;
	
	
	public List<Post> getList(){
		List<Post> list =  postDAO.lista();
		return list;
	}
	
	public String nowyPost(){
		
		Post post = new Post();
		flash.put("post", post);
		
		return PAGE_POST;
	}
	
	public String edytujPost(Post post){

		flash.put("post", post);
		
		return PAGE_POST_EDIT;
	}
	
	public String wyswietlPost(Post post){

		flash.put("post", post);
		
		return PAGE_POST;
	}

	public String kasujPost(Post post){
		postDAO.kasuj(post);
		return PAGE_STAY_AT_THE_SAME;
	}
}
