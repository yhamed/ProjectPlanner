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
import projectplanner.project.persistence.Tache;
import projectplanner.project.services.Services;


@ManagedBean
@ViewScoped
public class TacheBean {
	
	@EJB
	Services service;
	private Projet projet=ChefBean.proj;
	Personne per = AuthenticationBean.pg;
	public SousProjet sousproj=SousProjetBean.sousproj;
	public static Tache tache;
	private List<SousProjet> sousp ;
	private boolean visibility;
	
	
	public TacheBean() {
		// TODO Auto-generated constructor stub
	tache=new Tache();
	}
void init(){
	try{
	service.CreateUpdateProjet(projet);
	} catch (Exception e){
		System.out.append("You have no tasks!");
	}
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

public Tache getTache() {
	return tache;
}public void setTache(Tache tache) {
	this.tache = tache;
}

public List<SousProjet> getSousp() {
	return sousp;
}
public void setSousp(List<SousProjet> sousp) {
	this.sousp = sousp;
}
public void showAdd(){
tache = new Tache();
	setVisibility(true);
}
public void doAddT(){
	
	java.util.Date dateobj = new java.util.Date();
	tache.setDatedeb(dateobj);
	tache.setDatefin(date);
	
	//service.CreateUpdateProjet(projet);
	service.AddTtoSP(tache, sousproj);
	List<Tache> taches= new ArrayList<>();
taches=sousproj.getTache();
	//taches.add(tache);
	sousproj.setTache(taches);
	tache=new Tache();
	visibility=false;
init();
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
public void showEdit(){
	if(tache!=null){
	visibility=true;
	}else
	{
		noSelect();
	}
}
public void doDelete(){
	if(tache!=null){
		service.DeleteTache(tache);
	}else
	{
		noSelect();
	}
}

public void noSelect() {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                    "Selectionner une tache avant","Rien n'est selectionner"));
}

}
