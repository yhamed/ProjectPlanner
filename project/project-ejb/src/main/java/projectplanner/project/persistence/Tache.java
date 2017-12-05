package projectplanner.project.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Tache  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	
	@OneToMany(mappedBy="tache")
	private List<Document> document;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List<Ressources> ressources;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private SousProjet sousprojet;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String libelle;
	private Date datedeb;
	private Date datefin;
	private float cout;
	private String etat;
	
	
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
public List<Ressources> getRessources() {
	return ressources;
}public void setRessources(List<Ressources> ressources) {
	this.ressources = ressources;
}
	public SousProjet getSousprojet() {
		return sousprojet;
	}
	public void setSousprojet(SousProjet sousprojet) {
		this.sousprojet = sousprojet;
	}

	public List<Document> getDocument() {
		return document;
	}
	public void setDocument(List<Document> document) {
		this.document = document;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Date getDatedeb() {
		return datedeb;
	}
	public void setDatedeb(Date datedeb) {
		this.datedeb = datedeb;
	}
	public Date getDatefin() {
		return datefin;
	}
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}
	public float getCout() {
		return cout;
	}
	public void setCout(float cout) {
		this.cout = cout;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Tache) {
			Tache tache = (Tache) obj;
			return tache.getId() == id;
		}

		return false;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return libelle;
	}

}
