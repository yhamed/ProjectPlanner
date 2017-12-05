package projectplanner.project.presentation.mbeans;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import projectplanner.project.persistence.Personne;
import projectplanner.project.persistence.Projet;
import projectplanner.project.persistence.Ressources;
import projectplanner.project.persistence.SousProjet;
import projectplanner.project.persistence.Tache;
import projectplanner.project.persistence.rolee;
import projectplanner.project.services.Services;

@ManagedBean
@ViewScoped
public class MyTasksBean{

	@EJB
	Services service;
	private Tache t;
	private List<Tache> ts;

public MyTasksBean() {
	// TODO Auto-generated constructor stub
}





 
@PostConstruct
public void init() {
	service.CheckTasks();
 ts=service.GetMyTasks(AuthenticationBean.pg);

  for (Tache t : service.GetMyTasks(AuthenticationBean.pg)) {
	if(t.getEtat().equals("Terminer"))
		ts.remove(t);
}

}
public void terminer(){
	service.TerminerT(t);
}





public List<Tache> getTs() {
	return ts;
}






public void setTs(List<Tache> ts) {
	this.ts = ts;
}






public Tache getT() {
	return t;
}






public void setT(Tache t) {
	this.t = t;
}











}
	
	

    
    
     


