package projectplanner.project.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Alerte
 *
 */
@Entity

public class Alerte implements Serializable {
	@Id
	private int id;
	private String msg;
	private int seen;
	@ManyToOne
	private Personne personne;
	private String raison;
	private static final long serialVersionUID = 1L;

	public Alerte() {
		super();
	}
public int getSeen() {
	return seen;
}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public String getRaison() {
		return raison;
	}

	public void setRaison(String raison) {
		this.raison = raison;
	}

	public int isSeen() {
		return seen;
	}

	public void setSeen(int seen) {
		this.seen = seen;
	}
   
}
