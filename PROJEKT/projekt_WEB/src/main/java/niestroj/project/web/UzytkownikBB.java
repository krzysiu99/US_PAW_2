package niestroj.project.web;

import java.io.IOException;
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
import niestroj.project.entities.Uzytkownik;

@Named
@RequestScoped
public class UzytkownikBB {
	private static final String PAGE_STAY_AT_THE_SAME = null;

	@Inject
	FacesContext ctx;
	
	@Inject
	Flash flash;
	
	@EJB
	UzytkownikDAO uzytkownikDAO;
	
	@EJB
	PostDAO postDAO;
	
	@EJB
	KomentarzDAO komentarzDAO;
	
	private Uzytkownik loaded = null;
	private Uzytkownik uzytkownik = new Uzytkownik();
	
	
	public Uzytkownik getUzytkownik() {
		return uzytkownik;
	}

	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}
	

	public Uzytkownik getLoaded() {
		return loaded;
	}
	

	public void setLoaded(Uzytkownik loaded) {
		this.loaded = loaded;
	}

	public List<Uzytkownik> getList(){
		List<Uzytkownik> list;
		if(loaded != null)
			list = uzytkownikDAO.lista(loaded.getUid());
		else
			list = uzytkownikDAO.lista(null);
		
		return list;
	}
	
	public String edytuj(Uzytkownik uzytkownik){

		flash.put("uzytkownik", uzytkownik);
		
		return "Uzytkownik?faces-redirect=true";
	}
	
	public String kasuj(Uzytkownik uzytkownik){
		uzytkownikDAO.kasuj(uzytkownik);
		return PAGE_STAY_AT_THE_SAME;
	}
	
	
	public void onLoad() throws IOException {
			if (uzytkownik.getUid() != null) {
				loaded = uzytkownikDAO.szukaj(uzytkownik.getUid());
			}
			if (loaded != null) {
				uzytkownik = loaded;
				
			}
	}
	
	public String saveData() {
		try {
			
			uzytkownikDAO.aktualizuj(uzytkownik);
			
		} catch (Exception e) {
			FacesContext context = FacesContext.getCurrentInstance();
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wyst¹pi³ b³¹d podczas zapisu", null));
			return null;
		}

		return "Uzytkownik.jsf?faces-redirect=true&uid="+uzytkownik.getUid();
	}
	
	public String ilePostow(Integer uid) {
		Integer i = postDAO.ile(uid);
		String ile = i.toString();
		return ile;
	}
	public String ileKomentarzy(Integer uid) {
		Integer i = komentarzDAO.ile(uid);
		String ile = i.toString();
		return ile;
	}
	
	
	
}
