package niestroj.project.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import niestroj.project.dao.PostDAO;
import niestroj.project.entities.Post;

@Named
@RequestScoped
public class PostBB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Post post = new Post();
	private Post loaded = null;

	@Inject
	FacesContext ctx;
	
	@EJB
	PostDAO postDAO;
	
	
	public Post getPost() {
		return post;
	}

	public void onLoad() throws IOException {
		if (!ctx.isPostback()) {
			if (!ctx.isValidationFailed() && post.getPid() != null) {
				loaded = postDAO.szukaj(post.getPid());
			}
			if (loaded != null) {
				post = loaded;
			} //else {
				//post.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
				// if (!context.isPostback()) { // possible redirect
				// context.getExternalContext().redirect("personList.xhtml");
				// context.responseComplete();
				// }
			//}
		}
	}
}
