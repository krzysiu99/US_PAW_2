package niestroj.project.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import niestroj.project.entities.Post;

@Stateless
public class PostDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public void dodaj(Post post) {
		em.persist(post);
	}

	public Post aktualizuj(Post post) {
		return em.merge(post);
	}

	public void kasuj(Post post) {
		if(post != null)
			em.remove(em.merge(post));
	}

	public Post szukaj(Object id) {
		return em.find(Post.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> lista(String kategoria, Integer autor, Integer strona) {
		List<Post> list = null;
		Query query;

		if(kategoria.equals("")) {
			if(autor == null)
				query = em.createQuery("select p from Post p order by pid desc");
			else {
				query = em.createQuery("select p from Post p where autor = :autor order by pid desc");
				query.setParameter("autor", autor);
			}
		}
		else {
			query = em.createQuery("select p from Post p where kategoria = :kategoria order by pid desc");
			query.setParameter("kategoria", kategoria);
		}

		try {
			query.setMaxResults(5);
			query.setFirstResult(5*strona);
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> listaKategorii() {
		List<String> list = null;

		Query query = em.createQuery("select DISTINCT kategoria from Post group by kategoria");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public Integer ile(Integer user) {
		Integer i = 0;

		Query query = em.createQuery("select COUNT(*) FROM Post where autor = :user");
		query.setParameter("user", user);

		try {
			i = (int) (long) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}
	
	public Integer ile2(String kategoria) {
		Integer i = 0;

		Query query = em.createQuery("select COUNT(*) FROM Post where kategoria = :kategoria");
		query.setParameter("kategoria", kategoria);

		try {
			i = (int) (long) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}
	
	public Integer stron(String kategoria, Integer autor) {
		Integer stron = 0;
		Query query;

		if(kategoria.equals("")) {
			if(autor == null)
				query = em.createQuery("select count(*) from Post");
			else {
				query = em.createQuery("select count(*) from Post p where autor = :autor");
				query.setParameter("autor", autor);
			}
		}
		else {
			query = em.createQuery("select count(*) from Post p where kategoria = :kategoria");
			query.setParameter("kategoria", kategoria);
		}

		try {
			stron = (int) (long) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		double wynik = (double) stron;
		
		wynik = stron/5.0;

		return (int) Math.ceil(wynik);
	}
}
