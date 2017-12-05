package projectplanner.project.persistence;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "Personne")
@NamedQuery(name = "Personne.findBylogin", query = "select p from Personne p where p.login = :login")
public class Personne implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	public static final String FIND_BY_LOGIN = "Personne.findBylogin";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nom;
	private String sexe;
	private String login;
	private String password;
	private int telephone;
	@Enumerated(EnumType.STRING)
	private rolee role;

	@OneToMany(mappedBy="Creater")
	private List<Projet> projectsCreater;
	
	@OneToMany(mappedBy="personne")
private List<Alerte> alerts;

	public List<Alerte> getAlerts() {
		return alerts;
	}public List<Projet> getProjectsCreater() {
		return projectsCreater;
	}public void setAlerts(List<Alerte> alerts) {
		this.alerts = alerts;
	}public void setProjectsCreater(List<Projet> projectsCreater) {
		this.projectsCreater = projectsCreater;
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

	

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}


	
@Override
public String toString() {
	// TODO Auto-generated method stub
	return "Nom: "+nom+" Login: "+login;
}
	
	
	

	public rolee getRole() {
		return role;
	}

	public void setRole(rolee role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return getId();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Personne) {
			Personne personne = (Personne) obj;
			return personne.getId() == id;
		}

		return false;
	}

	public boolean isChef() {
		// TODO Auto-generated method stub
		 return rolee.chef.equals(role);
	}

	public boolean isMembre() {
		// TODO Auto-generated method stub
		return rolee.membre.equals(role);
	}
	public boolean isIntervenant() {
		// TODO Auto-generated method stub
		return rolee.intervenant.equals(role);
	}
	

}
