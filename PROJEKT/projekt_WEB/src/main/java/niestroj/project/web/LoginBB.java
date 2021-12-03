package niestroj.project.web;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
//import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import niestroj.project.dao.UzytkownikDAO;
import niestroj.project.entities.Uzytkownik;

@Named
@RequestScoped
public class LoginBB {
	private static final String PAGE_MAIN = "/Aktualnosci?faces-redirect=true";
	private static final String PAGE_LOGIN = "/Logowanie";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String login;
	private String pass;

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

	@Inject
	UzytkownikDAO uzytkownikDAO;

	public String doLogin() {
		FacesContext ctx = FacesContext.getCurrentInstance();

		// 1. verify login and password - get User from "database"
		Uzytkownik user = uzytkownikDAO.getUserFromDatabase(login, pass);

		// 2. if bad login or password - stay with error info
		if (user == null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Niepoprawny login lub has�o", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("user", user.getUid());

		// 3. if logged in: get User roles, save in RemoteClient and store it in session
		
		RemoteClient<Uzytkownik> client = new RemoteClient<Uzytkownik>(); //create new RemoteClient
		client.setDetails(user);
		
		
		List<String> roles = uzytkownikDAO.getUserRolesFromDatabase(user); //get User roles 
		
		if (roles != null) { //save roles in RemoteClient
			for (String role: roles) {
				client.getRoles().add(role);
			}
		}
	
		//store RemoteClient with request info in session (needed for SecurityFilter)
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		client.store(request);

		// and enter the system (now SecurityFilter will pass the request)
		return PAGE_MAIN;
	}
	
	public String doLogout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		//Invalidate session
		// - all objects within session will be destroyed
		// - new session will be created (with new ID)
		session.invalidate();
		return PAGE_LOGIN;
	}
	
}
