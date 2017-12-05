package projectplanner.project.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	
	
	@ManyToOne
	private Tache tache;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int uniqueId;
	private String nom;
	
	
	
	
	
	public Tache getTache() {
		return tache;
	}
	public void setTache(Tache tache) {
		this.tache = tache;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Document) {
			Document document = (Document) obj;
			return document.getId() == id;
		}

		return false;
	}
	

}
