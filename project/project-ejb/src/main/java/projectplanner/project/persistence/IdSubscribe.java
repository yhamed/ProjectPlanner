package projectplanner.project.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: idTache
 *
 */
@Embeddable
public class IdSubscribe implements Serializable {

	
	private int personne;
	private int tache;
	private static final long serialVersionUID = 1L;

	public IdSubscribe() {
		super();
	}

	public int getPersonne() {
		return personne;
	}

	public void setPersonne(int personne) {
		this.personne = personne;
	}

	public int getTache() {
		return tache;
	}

	public void setTache(int tache) {
		this.tache = tache;
	}   
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		IdSubscribe x = (IdSubscribe)obj;
		return (tache*10000+personne==x.personne+10000*x.tache);
	}
   
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return personne+10000*tache;
	}
}
