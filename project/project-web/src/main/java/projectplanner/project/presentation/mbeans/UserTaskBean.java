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
import org.primefaces.event.SelectEvent;

import projectplanner.project.persistence.Personne;
import projectplanner.project.persistence.Projet;
import projectplanner.project.persistence.Ressources;
import projectplanner.project.persistence.SousProjet;
import projectplanner.project.persistence.Tache;
import projectplanner.project.services.Services;


@ManagedBean
@SessionScoped
public class UserTaskBean {
	

	@EJB
	Services service;
	public List<Tache> taches;
	Personne per = AuthenticationBean.pg;
	Personne p ;
	public List<Ressources> ressources;
	

	Map<SousProjet, List<Tache>> map;
	List<SousProjet> sps;
	
	private SousProjet sp;
	private Tache t;
	
	public UserTaskBean() {
		// TODO Auto-generated constructor stub
		sps=new ArrayList<>();
		p = new Personne();
	}
	public void onSPChange() {
	    if(sp !=null && !sp.equals(""))
	        taches = map.get(sp);
	    
	}
	
	@PostConstruct
	public void init(){
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
	public Map<SousProjet, List<Tache>> getMap() {
		return map;
	}public Personne getPer() {
		return per;
	}public List<Ressources> getRessources() {
		return ressources;
	}public List<Tache> getTaches() {
		return taches;
	}public SousProjet getSp() {
		return sp;
	}public List<SousProjet> getSps() {
		return sps;
	}
	public void setMap(Map<SousProjet, List<Tache>> map) {
		this.map = map;
	}public void setPer(Personne per) {
		this.per = per;
	}public void setRessources(List<Ressources> ressources) {
		this.ressources = ressources;
	}public void setSp(SousProjet sp) {
		this.sp = sp;
	}public void setSps(List<SousProjet> sps) {
		this.sps = sps;
	}public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}public void setT(Tache t) {
		this.t = t;
	}public Tache getT() {
		return t;
	}
	public Personne getP() {
		return p;
	}public void setP(Personne p) {
		this.p = p;
	}
	public void sub(){
		if(t!=new Tache() && p != new Personne()){
			service.SubUtoT(p, t);
			new UserTaskBean();
		}
	}
}
