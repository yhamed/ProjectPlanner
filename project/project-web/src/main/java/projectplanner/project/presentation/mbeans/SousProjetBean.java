package projectplanner.project.presentation.mbeans;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import projectplanner.project.persistence.Personne;
import projectplanner.project.persistence.Projet;
import projectplanner.project.persistence.SousProjet;
import projectplanner.project.services.Services;


@ManagedBean
@ViewScoped
public class SousProjetBean {
	
	@EJB
	Services service;
	private Projet projet=ChefBean.proj;
	Personne per = AuthenticationBean.pg;
	public static SousProjet sousproj;
	private List<SousProjet> sousp ;
	private boolean visibility;
	@PostConstruct
	public void init() {
		// TODO Auto-generated constructor stub
		sousproj=new SousProjet();
service.CreateUpdateProjet(projet);
	}
	
	private Date date;
public Personne getPer() {
	return per;
}public Projet getProjet() {
	return projet;
}public void setPer(Personne per) {
	this.per = per;
}public void setProjet(Projet projet) {
	this.projet = projet;
}



public List<SousProjet> getSousp() {
	return sousp;
}
public void setSousp(List<SousProjet> sousp) {
	this.sousp = sousp;
}
public void showAdd(){

	sousproj=new SousProjet();
	setVisibility(true);
	visibility=true;
}
public void showEdit(){

	setVisibility(true);
	visibility=true;
}
public void doDelete() {
	projet.getSousprojet().remove(sousp);
	service.DeleteSP(projet ,sousproj);
	init();
}
public void doAddSP(){
	
	java.util.Date dateobj = new java.util.Date();
	sousproj.setDatedebut(dateobj);  
	sousproj.setDateachevement(date);

	
	//service.CreateUpdateProjet(projet);
	service.AddSPtoP(projet, sousproj);
	projet.getSousprojet().add(sousproj);
	sousproj= new SousProjet();
	visibility=false;
	init();
}
public void doUpdateSP(){
	service.AddSPtoP(projet, sousproj);
	cancel();
	init();
	sousproj = new SousProjet();
}
public void cancel() {
	setVisibility(false);
}
public boolean isVisibility() {
	return visibility;
}
public void setVisibility(boolean visibility) {
	this.visibility = visibility;
}
public SousProjet getSousproj() {
	return sousproj;
}
public void setSousproj(SousProjet sousproj) {
	this.sousproj = sousproj;
}

public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}

public void redirectViewTache() throws IOException {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    externalContext.redirect("http://localhost:18080/project-web/sousprojet_tache.jsf?faces-redirect=true");
}
}
