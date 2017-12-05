package projectplanner.project.presentation.mbeans;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;

import projectplanner.project.persistence.Projet;
import projectplanner.project.services.Services;
@ManagedBean
@SessionScoped
public class MembreBean {
	public MembreBean() {
	projets= new ArrayList<Projet>();
	}
	@EJB
	Services service;
	
	
	private List<Projet> projets;
	
	public static Projet proj;
	private TimelineModel model; 
	private boolean selectable = true;  
    private boolean zoomable = true;  
    private boolean moveable = true;  
    private boolean stackEvents = true;  
    private String eventStyle = "box";  
    private boolean axisOnTop;  
    private boolean showCurrentTime = true;  
    private boolean showNavigation = false;  
	public TimelineModel getModel() {
		return model;
	}public void setModel(TimelineModel model) {
		this.model = model;
	}
	@PostConstruct
	void init() throws ParseException{
		try{
		projets = service.PartOf(AuthenticationBean.pg);
		}catch(Exception e){
			System.out.append("You have no projects!");
		}
		java.util.Date dateobj = new java.util.Date();
		
		model = new TimelineModel(); 
		if (projets!=null){
		for (Projet pr : projets) {
			 
			 Calendar cal = Calendar.getInstance(); // creates calendar
			   String ti="";
			    dateobj=pr.getDateachevement();
			    cal.setTime(dateobj);
			    ti="Fin: "+pr.getTitre();
			 TimelineEvent t = new TimelineEvent(ti,cal.getTime());
		     model.add(t);  
			 ti="Début: "+pr.getTitre();
			    dateobj=pr.getDatedebut();
			    cal.setTime(dateobj);
			 TimelineEvent t1 = new TimelineEvent(ti,cal.getTime());
 
      model.add(t1);
		}
	}     
	   
	}



	public List<Projet> getProjets() {
		return projets;
	}



	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}



	public Projet getProj() {
		return proj;
	}

	public static Calendar toCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
		}

	public void setProj(Projet proj) {
		this.proj = proj;
	}



	public boolean isSelectable() {
		return selectable;
	}



	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}



	public boolean isZoomable() {
		return zoomable;
	}



	public void setZoomable(boolean zoomable) {
		this.zoomable = zoomable;
	}



	public boolean isMoveable() {
		return moveable;
	}



	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}

	public void onSelect(TimelineSelectEvent e) {  
        TimelineEvent timelineEvent = e.getTimelineEvent();  
   
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected event:", timelineEvent.getData().toString()+" :p");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
       // code getData and redirect to simple view project
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        /*
         * 
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         *          * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         *          * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * 
         * 
         */
        
    }  

	public boolean isStackEvents() {
		return stackEvents;
	}



	public void setStackEvents(boolean stackEvents) {
		this.stackEvents = stackEvents;
	}



	public String getEventStyle() {
		return eventStyle;
	}



	public void setEventStyle(String eventStyle) {
		this.eventStyle = eventStyle;
	}



	public boolean isAxisOnTop() {
		return axisOnTop;
	}



	public void setAxisOnTop(boolean axisOnTop) {
		this.axisOnTop = axisOnTop;
	}



	public boolean isShowCurrentTime() {
		return showCurrentTime;
	}



	public void setShowCurrentTime(boolean showCurrentTime) {
		this.showCurrentTime = showCurrentTime;
	}



	public boolean isShowNavigation() {
		return showNavigation;
	}



	public void setShowNavigation(boolean showNavigation) {
		this.showNavigation = showNavigation;
	}
	public void redirectViewProj() throws IOException {
		
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    externalContext.redirect("http://localhost:18080/project-web/ViewProject.jsf?faces-redirect=true");
	}
	
}
