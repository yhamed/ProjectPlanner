package projectplanner.project.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Ressources implements Serializable{
private static final long serialVersionUID = 1L;
	
	
	@ManyToOne
	private Tache tache;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nom;
	public Tache getTache() {
		return tache;
	}public void setTache(Tache tache) {
		this.tache = tache;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Ressources) {
			Ressources ressources = (Ressources) obj;
			return ressources.getId() == id;
		}

		return false;
	}
	
	
	

}
