package projectplanner.project.presentation.mbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import projectplanner.project.persistence.rolee;
import projectplanner.project.services.Services;
 
@ManagedBean
public class IdleMonitorView {
	@EJB
	Services service;
    public void onIdle() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                                        "No activity.", "What are you doing over there?"));
       
      //  if(AuthenticationBean.pg.getRole()==rolee.membre)
       // service.AlerterChef(AuthenticationBean.pg);
    }
 
    public void onActive() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                        "Welcome Back", "Well, that's a long coffee break!"));
    }
    

    
}