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
import niestroj.project.dao.KontaktDAO;
import niestroj.project.dao.UzytkownikDAO;
import niestroj.project.entities.Kontakt;

@Named
@RequestScoped
public class KontaktBB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Kontakt kontakt = new Kontakt();

	@Inject
	FacesContext context;
	
	@Inject
	Flash flash;
	
	@EJB
	KontaktDAO kontaktDAO;
	
	@EJB
	UzytkownikDAO uzytkownikDAO;
	
	
	public Kontakt getKontakt() {
		return kontakt;
	}
	
	public void onLoad() throws IOException {
		kontakt.setCzasPublikacji(data());
	}
	
	public List<Kontakt> getList(){
		List<Kontakt> list = kontaktDAO.lista();
		return list;
	}
	
	public String kasuj(Kontakt kontakt){
		kontaktDAO.kasuj(kontakt);
		return "Kontakt.jsf?faces-redirect=true";
	}
	
	public String przeczytano(Kontakt kontakt){
		kontaktDAO.przeczytano(kontakt);
		return "Kontakt.jsf?faces-redirect=true";
	}
	
	public String saveData() {
		try {
			kontakt.setCzasPublikacji(data());
			
			kontaktDAO.dodaj(kontakt);
			
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wyst¹pi³ b³¹d podczas zapisu", null));
			return null;
		}

		return "Aktualnosci.jsf?faces-redirect=true";
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
	
	public Integer licznik() {
		return kontaktDAO.licznik();
	}
}
