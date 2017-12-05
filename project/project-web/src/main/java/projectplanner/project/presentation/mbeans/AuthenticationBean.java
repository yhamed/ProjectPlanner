package projectplanner.project.presentation.mbeans;

import java.io.IOException;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import projectplanner.project.persistence.Personne;
import projectplanner.project.persistence.rolee;
import projectplanner.project.services.Services;





@ManagedBean
@SessionScoped
public class AuthenticationBean {

	
	@EJB
	Services service;
public static Personne pg;
	private String Login;
	private String Pass;
	

	
	public void logout() throws IOException{
		    setPg(null);
		    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		    redirect();
	}
	
	
	
	public void connect() throws IOException {
		pg= service.connect(Login, Pass);
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.getSessionMap().put("user", pg);
		
	
		if (pg != null){
		
if(pg.getRole().equals(rolee.chef))
		redirectChef();
		
if(pg.getRole().equals(rolee.intervenant))	
		redirectIntervenant();
		

if(pg.getRole().equals(rolee.membre))	
	redirectMembre();


		} else {
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                     "Erreur", "Mauvais login ou mot de passe !!"));

		}
		

			
		
		

	}
	public static Personne getPg() {
		return pg;
	}public static void setPg(Personne pg) {
		AuthenticationBean.pg = pg;
	}

		public void redirectChef() throws IOException {
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    externalContext.redirect("http://localhost:18080/project-web/dashboardChef.jsf?faces-redirect=true");
	}
		public void redirectIntervenant() throws IOException {


		    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		    externalContext.redirect("http://localhost:18080/project-web/dashboardIntervenant.jsf?faces-redirect=true");
		}

		public void redirectMembre() throws IOException {


		    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		    externalContext.redirect("http://localhost:18080/project-web/dashboardMembre.jsf?faces-redirect=true");
		}
	public void Acueil() throws IOException{
			
			if(pg.getRole().equals(rolee.chef))
					redirectChef();
					
			if(pg.getRole().equals(rolee.intervenant))	
					redirectIntervenant();
					
					
					
			if(pg.getRole().equals(rolee.membre))	
						redirectMembre();
						
		}

		public String getLogin() {
			return Login;
		}



		public void setLogin(String login) {
			Login = login;
		}



		public String getPass() {
			return Pass;
		}



		public void setPass(String pass) {
			Pass = pass;
		}
		public void redirect() throws IOException{

		if(pg==null){
			    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			    externalContext.redirect("http://localhost:18080/project-web/index.jsf");
		}
		}
	
		public void redirectC() throws IOException{

			if(pg.getRole()!=rolee.chef){
				if(pg.getRole()==rolee.intervenant)
					redirectIntervenant();
			if(pg.getRole()==rolee.membre)
				redirectMembre();
			}
			}
		public void redirectI() throws IOException{

			if(pg.getRole()!=rolee.intervenant){
				if(pg.getRole()==rolee.chef)
					redirectChef();
			if(pg.getRole()==rolee.membre)
				redirectMembre();
			}
			}
		
		public void redirectM() throws IOException{

			if(pg.getRole()!=rolee.membre){
				if(pg.getRole()==rolee.intervenant)
					redirectIntervenant();
			if(pg.getRole()==rolee.chef)
				redirectChef();
			}
			}

}
