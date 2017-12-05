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
public class SousProjet implements Serializable{
private static final long serialVersionUID = 1L;
	
	
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Projet projet;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="sousprojet",cascade=CascadeType.ALL)
	private List<Tache> tache;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String titre;
	private String description;
	private Date dateachevement;
	private Date datedebut;
	
	
	
	
	public Projet getProjet() {
		return projet;
	}
	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	
	
	
	public List<Tache> getTache() {
		return tache;
	}
	public void setTache(List<Tache> tache) {
		this.tache = tache;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateachevement() {
		return dateachevement;
	}
	public void setDateachevement(Date dateachevement) {
		this.dateachevement = dateachevement;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SousProjet) {
			SousProjet sousprojet = (SousProjet) obj;
			return sousprojet.getId() == id;
		}

		return false;
	}
	public Date getDatedebut() {
		return datedebut;
	}
	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}
	
@Override
	public String toString() {
		// TODO Auto-generated method stub
		return titre;
	}
}
