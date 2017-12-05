package projectplanner.project.presentation.mbeans;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import projectplanner.project.persistence.Personne;
import projectplanner.project.persistence.rolee;
import projectplanner.project.services.Services;

@ManagedBean
@ViewScoped
public class UserManagementBean{
	
	@EJB
	Services service;
	public static Personne personne;
	private Personne user;
	private boolean vU;
	private boolean vUU;
	private List<Personne> pers;
	public UserManagementBean() {
		// TODO Auto-generated constructor stub
	personne = new Personne();
	user = new Personne();
	vUU=true;
	}
	@PostConstruct
	public void init(){
		service.GetAllPersonnes();
		
	}
	 public Personne[] getPer() {
		 Personne[] pers=new Personne[service.GetAllPersonnes().size()];
		 int i=0;
		 for (Personne pe : service.GetAllPersonnes()) {
			
			 pers[i]=pe;
			 i++;
		}
	     return pers;
	    }
	 public rolee[] getStatuses() {
	        return rolee.values();
	    }

	
	public Personne getPersonne() {
		return personne;
	}public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	
	public void doAdd(){

		if(service.CreateUpdatePersonne(personne)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"La personne "+personne.getNom()+" "+personne.getLogin(), "Crée avec succés"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erreur !!", "La personne avec le login: "+personne.getLogin()+" existe déja !"));
	
		}
		
	}
public void doUpdate(){
	service.UpdateP(personne);
}
	public Personne getUser() {
		return user;
	}

	public void setUser(Personne user) {
		this.user = user;
	}

	
	public List<Personne> getPers() {
		return pers;
	}
	public void setPers(List<Personne> pers) {
		this.pers = pers;
	}
	public boolean isvU() {
		return vU;
	}
	public void setvU(boolean vU) {
		this.vU = vU;
	}
	public boolean isvUU() {
		return vUU;
	}
	public void setvUU(boolean vUU) {
		this.vUU = vUU;
	}
public void swap(){
vU=true;
	personne=user;
}
}
	
	

    
    
     


