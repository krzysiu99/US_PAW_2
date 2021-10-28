package niestroj.project.dao;

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
	
}
