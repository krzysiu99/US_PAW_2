package niestroj.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the kontakt database table.
 * 
 */
@Entity
@NamedQuery(name="Kontakt.findAll", query="SELECT k FROM Kontakt k")
public class Kontakt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int wid;

	@Column(name="czas_publikacji")
	private String czasPublikacji;

	@Column(name="czy_przeczytano")
	private int czyPrzeczytano;

	private String nadawca;

	@Lob
	private String tresc;

	public Kontakt() {
	}

	public int getWid() {
		return this.wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}

	public String getCzasPublikacji() {
		return this.czasPublikacji;
	}

	public void setCzasPublikacji(String czasPublikacji) {
		this.czasPublikacji = czasPublikacji;
	}

	public int getCzyPrzeczytano() {
		return this.czyPrzeczytano;
	}

	public void setCzyPrzeczytano(int czyPrzeczytano) {
		this.czyPrzeczytano = czyPrzeczytano;
	}

	public String getNadawca() {
		return this.nadawca;
	}

	public void setNadawca(String nadawca) {
		this.nadawca = nadawca;
	}

	public String getTresc() {
		return this.tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

}