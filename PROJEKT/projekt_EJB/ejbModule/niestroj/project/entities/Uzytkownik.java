package niestroj.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the uzytkownik database table.
 * 
 */
@Entity
@NamedQuery(name="Uzytkownik.findAll", query="SELECT u FROM Uzytkownik u")
public class Uzytkownik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int uid;

	@Temporal(TemporalType.DATE)
	@Column(name="data_rejestracji")
	private Date dataRejestracji;

	@Lob
	private String haslo;

	private String nick;

	private int rola;

	//bi-directional many-to-one association to Komentarz
	@OneToMany(mappedBy="uzytkownik")
	private List<Komentarz> komentarzs;

	//bi-directional many-to-one association to Post
	@OneToMany(mappedBy="uzytkownik")
	private List<Post> posts;

	public Uzytkownik() {
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Date getDataRejestracji() {
		return this.dataRejestracji;
	}

	public void setDataRejestracji(Date dataRejestracji) {
		this.dataRejestracji = dataRejestracji;
	}

	public String getHaslo() {
		return this.haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getRola() {
		return this.rola;
	}

	public void setRola(int rola) {
		this.rola = rola;
	}

	public List<Komentarz> getKomentarzs() {
		return this.komentarzs;
	}

	public void setKomentarzs(List<Komentarz> komentarzs) {
		this.komentarzs = komentarzs;
	}

	public Komentarz addKomentarz(Komentarz komentarz) {
		getKomentarzs().add(komentarz);
		komentarz.setUzytkownik(this);

		return komentarz;
	}

	public Komentarz removeKomentarz(Komentarz komentarz) {
		getKomentarzs().remove(komentarz);
		komentarz.setUzytkownik(null);

		return komentarz;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setUzytkownik(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setUzytkownik(null);

		return post;
	}

}