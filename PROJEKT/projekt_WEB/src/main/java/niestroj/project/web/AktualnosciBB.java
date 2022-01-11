package niestroj.project.web;

import java.io.IOException;
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
	
	private String kategoria = "";
	private Integer autor = null;
	private Integer strona = 0;
	private Integer stron = 0;
	
	
	
	public Integer getAutor() {
		return autor;
	}

	public void setAutor(Integer autor) {
		this.autor = autor;
	}

	public String getKategoria() {
		return kategoria;
	}

	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}

	public List<Post> getList(){
		List<Post> list =  postDAO.lista(kategoria,autor,strona);
		return list;
	}
	
	public void setStrona(Integer strona) {
		if(strona>=0)
			this.strona = strona;
	}
	
	public Integer getStrona() {
		return strona;
	}
	
	

	public Integer getStron() {
		return stron;
	}

	public String nowyPost(){
		
		Post post = new Post();
		flash.put("post", post);
		
		return PAGE_POST_EDIT;
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
	
	public void onLoad() throws IOException {
		this.stron = postDAO.stron(kategoria, autor);
	}
	
}
