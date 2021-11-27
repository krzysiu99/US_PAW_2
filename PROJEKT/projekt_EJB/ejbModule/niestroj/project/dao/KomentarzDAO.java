package niestroj.project.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import niestroj.project.entities.Komentarz;

@Stateless
public class KomentarzDAO {

	@PersistenceContext
	EntityManager em;
	
	public void dodaj(Komentarz komentarz) {
		em.persist(komentarz);
	}
	
	public Komentarz aktualizuj(Komentarz komentarz) {
		if(komentarz != null)
			return em.merge(komentarz);
		else 
			return null;
	}

	public void kasuj(Komentarz komentarz) {
		if(komentarz != null)
			em.remove(em.merge(komentarz));
	}

	public Komentarz szukaj(Object id) {
		return em.find(Komentarz.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Komentarz> lista() {
		List<Komentarz> list = null;

		Query query = em.createQuery("select k from Komentarz k");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
