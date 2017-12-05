package projectplanner.project.presentation.mbeans;

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
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import projectplanner.project.persistence.Alerte;
import projectplanner.project.persistence.Personne;
import projectplanner.project.persistence.Projet;
import projectplanner.project.services.Services;


@ManagedBean
@RequestScoped
public class AlertesBean {
	  private int nbAlerts;
	@EJB
	Services service;
	Personne per = AuthenticationBean.pg;
	private List<Alerte> alerts;
	public AlertesBean() {
		// TODO Auto-generated constructor stub
	}
	@PostConstruct
	void init(){
		if(null!=service.GetMyAlerts(per))
		alerts=service.GetMyAlerts(per);
		setNbAlerts(service.NbreAlert(AuthenticationBean.pg));
		
	}
	 
	public List<Alerte> getAlerts() {
		return alerts;
	}public void setAlerts(List<Alerte> alerts) {
		this.alerts = alerts;
	}
	public int getNbAlerts() {
		return nbAlerts;
	}
	public void setNbAlerts(int nbAlerts) {
		this.nbAlerts = nbAlerts;
	}
	
}
