package niestroj.project.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import niestroj.project.entities.Kontakt;

@Stateless
public class KontaktDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public void dodaj(Kontakt kontakt) {
		em.persist(kontakt);
	}

	public Kontakt aktualizuj(Kontakt kontakt) {
		return em.merge(kontakt);
	}

	public void kasuj(Kontakt kontakt) {
		if(kontakt != null)
			em.remove(em.merge(kontakt));
	}

	public Kontakt szukaj(Object id) {
		return em.find(Kontakt.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Kontakt> lista() {
		List<Kontakt> list = null;
		Query query;

		query = em.createQuery("select k from Kontakt k order by czy_przeczytano ASC, WID desc");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public void przeczytano(Kontakt kontakt) {
		Query query;

		query = em.createQuery("update Kontakt set czy_przeczytano = 1 where WID = :wid");
		query.setParameter("wid", kontakt.getWid());

		try {
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Integer licznik() {
		Integer licznik = 0;
		Query query;

		query = em.createQuery("select COUNT(*) from Kontakt where czy_przeczytano = 0");

		try {
			licznik = (int) (long) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return licznik;
	}
}
