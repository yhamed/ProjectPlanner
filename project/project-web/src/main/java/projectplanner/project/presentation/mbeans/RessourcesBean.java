package projectplanner.project.presentation.mbeans;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.SelectEvent;

import projectplanner.project.persistence.Personne;
import projectplanner.project.persistence.Projet;
import projectplanner.project.persistence.Ressources;
import projectplanner.project.persistence.SousProjet;
import projectplanner.project.persistence.Tache;
import projectplanner.project.services.Services;


@ManagedBean
@ViewScoped
public class RessourcesBean {
	
	@EJB
	Services service;
	public List<Tache> taches;
	Personne per = AuthenticationBean.pg;
	private Ressources ressource;
	private Ressources addr ;
	public List<Ressources> ressources;
	private boolean visibility;
	List<Ressources> dropedRessources;
	Map<SousProjet, List<Tache>> map;
	List<SousProjet> sps;
	
	private SousProjet sp;
	private Tache t;
	
	@PostConstruct
	public void init(){
		if(service.RessourcesDisponible()!=null){
			ressources=service.RessourcesDisponible();
			ressources.remove(dropedRessources);
		} else{
			ressources = new ArrayList<>();
		}
		if(service.GetTaches()!=null){
			taches=service.GetTaches();
		} else{
			taches= new ArrayList<>();
		}
		if(service.MapSP_T()!=null){
			map=service.MapSP_T();
		}else {
			map = new HashMap<SousProjet, List<Tache>>();
		}
		
		for (SousProjet s : map.keySet()){
			sps.add(s);
		}
	
	}
	
	public RessourcesBean() {
		// TODO Auto-generated constructor stub
		sps=new ArrayList<>();
	ressource = new Ressources();
	dropedRessources = new ArrayList<>();
	addr = new Ressources();
	}

	private Date date;
public Personne getPer() {
	return per;

}public void setPer(Personne per) {
	this.per = per;
}



public void doAddr(){
	service.CreateRessource(addr);
	init();
}



public boolean isVisibility() {
	return visibility;
}
public void setVisibility(boolean visibility) {
	this.visibility = visibility;
}


public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}

public Ressources getRessource() {
	return ressource;
}
public void setRessource(Ressources ressource) {
	this.ressource = ressource;
}
public List<Tache> getTaches() {
	return taches;
}public void setTaches(List<Tache> taches) {
	this.taches = taches;
}public List<Ressources> getRessources() {
	return ressources;
}public void setRessources(List<Ressources> ressources) {
	this.ressources = ressources;
}
public void onRDrop(DragDropEvent ddEvent) {
   
Ressources r = ((Ressources)ddEvent.getData());
   dropedRessources.add(r);
ressources.remove(r);
init();
}
public List<Ressources> getDropedRessources() {
	return dropedRessources;
}public void setDropedRessources(List<Ressources> dropedRessources) {
	this.dropedRessources = dropedRessources;
}

public Ressources getAddr() {
	return addr;
}

public void setAddr(Ressources addr) {
	this.addr = addr;
}
public void RtoT(){
	if(t!=null & dropedRessources!=null){
	
for (Ressources r : dropedRessources) {
	service.AddRtoT(r, t);
}
	}
}

public SousProjet getSp() {
	return sp;
}

public void setSp(SousProjet sp) {
	this.sp = sp;
}

public Tache getT() {
	return t;
}

public void setT(Tache t) {
	this.t = t;
}

public void onSPChange() {
    if(sp !=null && !sp.equals(""))
        taches = map.get(sp);
    
}

public List<SousProjet> getSps() {
	return sps;
}
public void setSps(List<SousProjet> sps) {
	this.sps = sps;
}
public void UpdateDroped(){
	if(t!=null)
for (Ressources r : service.GetRessources()) {
dropedRessources.add(r);	

}	
	init();
}
}
