package niestroj.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the post database table.
 * 
 */
@Entity
@NamedQuery(name="Post.findAll", query="SELECT p FROM Post p")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pid;

	@Lob
	@Column(name="data_publikacji")
	private String dataPublikacji;

	private String kategoria;

	@Lob
	private String tresc;

	private String tytul;

	//bi-directional many-to-one association to Komentarz
	@OneToMany(mappedBy="postBean")
	private List<Komentarz> komentarzs;

	//bi-directional many-to-one association to Uzytkownik
	@ManyToOne
	@JoinColumn(name="autor")
	private Uzytkownik uzytkownik;

	public Post() {
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getDataPublikacji() {
		return this.dataPublikacji;
	}

	public void setDataPublikacji(String dataPublikacji) {
		this.dataPublikacji = dataPublikacji;
	}

	public String getKategoria() {
		return this.kategoria;
	}

	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}

	public String getTresc() {
		return this.tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public String getTytul() {
		return this.tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public List<Komentarz> getKomentarzs() {
		return this.komentarzs;
	}

	public void setKomentarzs(List<Komentarz> komentarzs) {
		this.komentarzs = komentarzs;
	}

	public Komentarz addKomentarz(Komentarz komentarz) {
		getKomentarzs().add(komentarz);
		komentarz.setPostBean(this);

		return komentarz;
	}

	public Komentarz removeKomentarz(Komentarz komentarz) {
		getKomentarzs().remove(komentarz);
		komentarz.setPostBean(null);

		return komentarz;
	}

	public Uzytkownik getUzytkownik() {
		return this.uzytkownik;
	}

	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}

}