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
		em.remove(em.merge(post));
	}

	public Post szukaj(Object id) {
		return em.find(Post.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> lista() {
		List<Post> list = null;

		Query query = em.createQuery("select p from Post p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
