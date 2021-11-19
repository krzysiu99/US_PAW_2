package niestroj.project.web;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import niestroj.project.dao.UzytkownikDAO;
import niestroj.project.entities.Uzytkownik;

@Named
@RequestScoped
public class RegisterBB {
	private static final String PAGE_LOGIN = "/Logowanie?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String login;
	private String pass;
	private String pass2;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getPass2() {
		return pass2;
	}

	public void setPass2(String pass) {
		this.pass2 = pass;
	}

	@Inject
	UzytkownikDAO uzytkownikDAO;

	public String doRegister() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		
		if(!pass.equals(pass2)){
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"has³a nie s¹ takie same", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		
		if(uzytkownikDAO.checkExists(login)){
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ten nick jest ju¿ zajêty", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		
		Uzytkownik user = new Uzytkownik();
		user.setNick(login);
		user.setHaslo(pass);
		user.setRola(1);
		user.setDataRejestracji(new Date());
		uzytkownikDAO.dodaj(user);
		
		return PAGE_LOGIN;
	}
}
