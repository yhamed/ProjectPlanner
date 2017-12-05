package projectplanner.project.presentation.mbeans;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.chart.PieChartModel;

import projectplanner.project.persistence.Personne;
import projectplanner.project.persistence.Projet;
import projectplanner.project.persistence.Ressources;
import projectplanner.project.persistence.SousProjet;
import projectplanner.project.persistence.Tache;
import projectplanner.project.persistence.rolee;
import projectplanner.project.services.Services;

@ManagedBean
@ViewScoped
public class ViewProjectBean{
	private TreeNode root;
	@EJB
	Services service;
	private Projet projet=ChefBean.proj;
	private boolean role;
	private int advance;
	//private List<TreeNode> trees;
public ViewProjectBean() {
	// TODO Auto-generated constructor stub
}




 
@PostConstruct
public void init() {
    root = new DefaultTreeNode(projet.getTitre(), null);
  if(projet.getSousprojet()!=null)
    for (SousProjet s : projet.getSousprojet()) {
    	TreeNode ss =new DefaultTreeNode(s.getTitre(),root);
    	if(s.getTache()!=null)
    	 for (Tache t : s.getTache()) {
    		 TreeNode node00 = new DefaultTreeNode(t.getLibelle(), ss);
    		 if(t.getRessources()!=null)
    		 for (Ressources r : t.getRessources()) {
    			 node00.getChildren().add(new DefaultTreeNode(r.getNom()));
			}
		}
		
	}
  if(AuthenticationBean.pg.getRole()==rolee.membre){
	  role=true;
  }
  
}

public TreeNode getRoot() {
    return root;
}



public Projet getProjet() {
	return projet;
}

public void setProjet(Projet projet) {
	this.projet = projet;
}





public boolean isRole() {
	return role;
}





public void setRole(boolean role) {
	this.role = role;
}


public void sP() throws IOException{


ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
externalContext.redirect("http://localhost:18080/project-web/projet_sousprojet.jsf");
}





public int getAdvance() {
	return advance;
}





public void setAdvance(int advance) {
	this.advance = advance;
}






}
	
	

    
    
     


