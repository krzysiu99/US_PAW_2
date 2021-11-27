package niestroj.project.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import niestroj.project.entities.Uzytkownik;

@Stateless
public class UzytkownikDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public void dodaj(Uzytkownik uzytkownik) {
		em.persist(uzytkownik);
	}

	public Uzytkownik aktualizuj(Uzytkownik uzytkownik) {
		return em.merge(uzytkownik);
	}

	public void kasuj(Uzytkownik uzytkownik) {
		em.remove(em.merge(uzytkownik));
	}

	public Uzytkownik szukaj(Object id) {
		return em.find(Uzytkownik.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Uzytkownik> lista() {
		List<Uzytkownik> list = null;

		Query query = em.createQuery("select u from Uzytkownik u");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public Boolean checkExists(String login) {
		Boolean ex = false;
		
		List<Uzytkownik> lista = lista();
		
		for(Uzytkownik userek : lista){
			String nick = userek.getNick();
			
			if(nick.equals(login)){
				ex = true;
				break;
			}
		}
		
		return ex;
	}
	
	public Uzytkownik getUserFromDatabase(String login, String pass) {
		
		Uzytkownik u = null;
		
		List<Uzytkownik> lista = lista();
		
		for(Uzytkownik userek : lista){
			String nick = userek.getNick();
			String haslo = userek.getHaslo();
			
			if(nick.equals(login) && haslo.equals(pass)){
				u = userek;
				break;
			}
		}

		return u;
	}

	public List<String> getUserRolesFromDatabase(Uzytkownik user) {
		
		ArrayList<String> roles = new ArrayList<String>();
		
		if (user.getRola() <= 2) {
			roles.add("komentator");
		}
		if (user.getRola() <= 1) {
			roles.add("autor");
		}
		if (user.getRola() == 0) {
			roles.add("admin");
		}
		
		return roles;
	}
	
}
