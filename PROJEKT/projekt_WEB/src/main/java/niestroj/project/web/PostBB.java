package niestroj.project.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import niestroj.project.dao.UzytkownikDAO;
import niestroj.project.entities.Komentarz;
import niestroj.project.entities.Post;
import niestroj.project.entities.Uzytkownik;

@Named
@RequestScoped
public class PostBB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Post post = new Post();
	private Komentarz komentarz = new Komentarz();
	private Post loaded = null;

	@Inject
	FacesContext context;
	
	@Inject
	Flash flash;
	
	@EJB
	KomentarzDAO komentarzDAO;
	
	@EJB
	UzytkownikDAO uzytkownikDAO;
	
	@EJB
	PostDAO postDAO;
	
	private int aktywnyPost;
	
	
	public Post getPost() {
		return post;
	}
	
	public Komentarz getKomentarz() {
		return komentarz;
	}
	
	public void onLoad() throws IOException {
		if (!context.isPostback()) {
			if (!context.isValidationFailed() && post.getPid() != null) {
				loaded = postDAO.szukaj(post.getPid());
			}
			if (loaded != null) {
				post = loaded;
				aktywnyPost = post.getPid();
				komentarz.setCzasPublikacji(data());
				
				FacesContext context = FacesContext.getCurrentInstance();
				Object user2 = context.getExternalContext().getSessionMap().get("user");
				if(user2 != null) {
					Uzytkownik user = uzytkownikDAO.szukaj(user2);
					komentarz.setUzytkownik(user);
					komentarz.setPostBean(post);
				}
			} else {
				context.getApplication().getNavigationHandler().
					handleNavigation(FacesContext.getCurrentInstance(), null, "Aktualnosci.jsf?faces-redirect=true");
			}
		}
	}
	
	public List<Komentarz> getList(){
		List<Komentarz> list = komentarzDAO.lista(aktywnyPost);
		return list;
	}
	
	public String kasuj(Komentarz komentarz){
		komentarzDAO.kasuj(komentarz);
		return "Post.jsf?faces-redirect=true&p="+post.getPid();
	}
	
	public String saveData() {
		try {
			komentarz.setPostBean(post);
			komentarz.setCzasPublikacji(data());
			
			FacesContext context = FacesContext.getCurrentInstance();
			Object user2 = context.getExternalContext().getSessionMap().get("user");
			Uzytkownik user = uzytkownikDAO.szukaj(user2);
			komentarz.setUzytkownik(user);
			
			komentarzDAO.dodaj(komentarz);
			
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wyst¹pi³ b³¹d podczas zapisu", null));
			return null;
		}

		return "Post.jsf?faces-redirect=true&p="+post.getPid();
	}
	
	public String data() {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String dp = Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
		return dp;
	}
}
