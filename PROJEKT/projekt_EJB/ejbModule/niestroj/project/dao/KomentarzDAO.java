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
	

	public void kasuj(Komentarz komentarz) {	
		Query query;
		query = em.createQuery("delete from Komentarz where KID = :komentarz");
		query.setParameter("komentarz", komentarz.getKid());
		try {
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Komentarz szukaj(Object id) {
		return em.find(Komentarz.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Komentarz> lista(int post) {
		List<Komentarz> list = null;

		Query query = em.createQuery("select k from Komentarz k where post like :post order by kid desc");
		query.setParameter("post", post);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public Integer ile(Integer user) {
		Integer i = 0;

		Query query = em.createQuery("select COUNT(*) FROM Komentarz where autor = :user");
		query.setParameter("user", user);

		try {
			i = (int) (long) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}
}
