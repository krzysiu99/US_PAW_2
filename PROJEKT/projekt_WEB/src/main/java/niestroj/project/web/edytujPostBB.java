package niestroj.project.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import niestroj.project.dao.PostDAO;
import niestroj.project.entities.Post;

@Named
@ViewScoped
public class edytujPostBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_MAIN = "Aktualnosci?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Post post = new Post();
	private Post loaded = null;

	@EJB
	PostDAO postDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Post getPost() {
		return post;
	}

	public void onLoad() throws IOException {

		loaded = (Post) flash.get("post");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			post = loaded;
			// session.removeAttribute("person");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³êdne u¿ycie systemu", null));
//			if (!context.isPostback()) { //possible redirect
//				context.getExternalContext().redirect("Aktualnosci.jsf");
//				context.responseComplete();
//			}
		}
		
		if(post.getDataPublikacji() == null) {
			post.setDataPublikacji(data());
		}

	}

	public String saveData() {
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (post.getPid() == null) {
				// new record
				postDAO.dodaj(post);
			} else {
				// existing record
				postDAO.aktualizuj(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wyst¹pi³ b³¹d podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_MAIN;
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
