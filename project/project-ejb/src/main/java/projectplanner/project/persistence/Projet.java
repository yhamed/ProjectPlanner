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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Projet implements Serializable{
private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Personne Creater;
	

	
	@OneToMany(mappedBy="projet",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List<SousProjet> sousprojet;
	

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String titre;
	private String description;
	private float cout;
	
	private String site;
	private Date datedebut;
	private Date dateachevement;
	private String duree;
	
	public Personne getCreater() {
		return Creater;
	}public void setCreater(Personne creater) {
		Creater = creater;
	}

	public List<SousProjet> getSousprojet() {
		return sousprojet;
	}
	public void setSousprojet(List<SousProjet> sousprojet) {
		this.sousprojet = sousprojet;
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
	public float getCout() {
		return cout;
	}
	public void setCout(float cout) {
		this.cout = cout;
	}
	public String getDuree() {
		return duree;
	}
	public void setDuree(String duree) {
		this.duree = duree;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public Date getDateachevement() {
		return dateachevement;
	}
	public void setDateachevement(Date dateachevement) {
		this.dateachevement = dateachevement;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Projet) {
			Projet projet = (Projet) obj;
			return projet.getId() == id;
		}

		return false;
	}
	@Override
		public String toString() {
			// TODO Auto-generated method stub
			return titre;
		}
	public Date getDatedebut() {
		return datedebut;
	}
	public void setDatedebut(Date dateadebut) {
		this.datedebut = dateadebut;
	}
	
	
}
