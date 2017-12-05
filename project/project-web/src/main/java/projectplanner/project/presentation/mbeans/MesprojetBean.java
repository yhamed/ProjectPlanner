package projectplanner.project.presentation.mbeans;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import projectplanner.project.persistence.Personne;
import projectplanner.project.persistence.Projet;
import projectplanner.project.persistence.rolee;
import projectplanner.project.services.Services;


@ManagedBean
@SessionScoped
public class MesprojetBean {
	private Date date;
	@EJB
	Services service;
	private String query="";
	public static Projet proj;
	Personne per = AuthenticationBean.pg;
	private boolean visibility;
	private List<Projet> projets;
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
	@PostConstruct
	void init(){
		if(per.getRole()==rolee.chef)
	projets = service.getMyProjects(per);
		if(per.getRole()==rolee.membre)
			projets = service.PartOf(per);
	proj=new Projet();
	}
     
	public Projet getProj() {
		return proj;
	}public void setProj(Projet proj) {
		this.proj = proj;
	}
	
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
	public void showAdd(){
		init();
		proj = new Projet();
		setVisibility(true);
	}
	public void showRemove(){
		init();
		visibility=true;
	}
	public boolean getvisibility(){
		return visibility;
	}
	public void showEdit(){
		visibility=true;
	}
	public void Cancel(){
		visibility=false;
	}


	public void doAddModif(){
		java.util.Date dateobj = new java.util.Date();
		proj.setDatedebut(dateobj);
		proj.setDateachevement(date);
		proj.setCreater(AuthenticationBean.pg);
		service.CreateUpdateProjet(proj);
		Cancel();
	}
	public void doDelete(){
		service.Delete(proj);
		projets.remove(proj);
	}
	public Personne getPer() {
		return per;
	}public void setPer(Personne per) {
		this.per = per;
	}
	public List<Projet> getProjets() {
		return projets;
	}
	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}
	public List<String> completeArea(String query) {
        List<String> results = new ArrayList<String>();
         for (Projet pr : projets) {
			if(pr.getTitre().startsWith(query)){
				results.add(pr.getTitre()+"");
			}
			if(pr.getDescription().startsWith(query)){
				results.add(pr.getDescription()+"");
			}
		}
       
             return results;
    }
	public void FindProjects() throws IOException {
        Set<Projet> results = new HashSet<Projet>();
       String s=query;
         for (Projet pr : projets) {
			if(pr.getTitre().startsWith(s)){
				results.add(pr);
			}
			if(pr.getDescription().startsWith(s)){
				results.add(pr);
			}
		
     	
		}int i = 0;
         for (Projet pr : results) {
			i++;
		}
        if(i==1){
        	ChefBean.proj=results.stream().findAny().get();
        	ViewProjet();
        }
    }
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public void ViewProjet() throws IOException {
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    externalContext.redirect("http://localhost:18080/project-web/ViewProject.jsf?faces-redirect=true");
	}
}
