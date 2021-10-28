package niestroj.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the komentarz database table.
 * 
 */
@Entity
@NamedQuery(name="Komentarz.findAll", query="SELECT k FROM Komentarz k")
public class Komentarz implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int kid;

	@Column(name="czas_publikacji")
	private Timestamp czasPublikacji;

	private String tresc;

	//bi-directional many-to-one association to Post
	@ManyToOne
	@JoinColumn(name="post")
	private Post postBean;

	//bi-directional many-to-one association to Uzytkownik
	@ManyToOne
	@JoinColumn(name="autor")
	private Uzytkownik uzytkownik;

	public Komentarz() {
	}

	public int getKid() {
		return this.kid;
	}

	public void setKid(int kid) {
		this.kid = kid;
	}

	public Timestamp getCzasPublikacji() {
		return this.czasPublikacji;
	}

	public void setCzasPublikacji(Timestamp czasPublikacji) {
		this.czasPublikacji = czasPublikacji;
	}

	public String getTresc() {
		return this.tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public Post getPostBean() {
		return this.postBean;
	}

	public void setPostBean(Post postBean) {
		this.postBean = postBean;
	}

	public Uzytkownik getUzytkownik() {
		return this.uzytkownik;
	}

	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}

}