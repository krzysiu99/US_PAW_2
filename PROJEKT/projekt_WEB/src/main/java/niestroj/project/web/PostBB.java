package niestroj.project.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;

import niestroj.project.dao.KomentarzDAO;
import niestroj.project.dao.PostDAO;
import niestroj.project.entities.Komentarz;
import niestroj.project.entities.Post;

@Named
@RequestScoped
public class PostBB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Post post = new Post();
	private Post loaded = null;

	@Inject
	FacesContext context;
	
	@Inject
	Flash flash;
	
	@EJB
	KomentarzDAO komentarzDAO;
	
	@EJB
	PostDAO postDAO;
	
	
	public Post getPost() {
		return post;
	}

	public void onLoad() throws IOException {

		loaded = (Post) flash.get("post");
		if (loaded != null) {
			post = loaded;
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³êdne u¿ycie systemu", null));
		}
		
	}
	
	public List<Komentarz> getList(){
		List<Komentarz> list = komentarzDAO.lista();
		return list;
	}
	
	public String kasujKomentarz(Komentarz komentarz){
		komentarzDAO.kasuj(komentarz);
		return null;
	}
}
