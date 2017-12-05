package projectplanner.project.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import projectplanner.project.persistence.Alerte;
import projectplanner.project.persistence.IdSubscribe;
import projectplanner.project.persistence.Personne;
import projectplanner.project.persistence.Projet;
import projectplanner.project.persistence.Ressources;
import projectplanner.project.persistence.SousProjet;
import projectplanner.project.persistence.Subscribe;
import projectplanner.project.persistence.Tache;
import projectplanner.project.persistence.rolee;





/**
 * Session Bean implementation class Services
 */
@Stateful
@LocalBean
public class Services implements ServicesLocal {
	
	@PersistenceContext
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public Services() {
        // TODO Auto-generated constructor stub
    	
    }

	@Override
	public Personne connect(String log, String pass) {
		try {
			String re="select p from Personne p";
			Query req= em.createQuery(re);
			List<Personne> pers = req.getResultList();
			Personne Per= null;
			
				for (Personne p : pers) {
					if(p.getPassword().equals(pass) && p.getLogin().equals(log)){
					Per=p;
					}
				}
				if(Per != null) return Per;
		} catch (NoResultException ex) {
			System.out.println("aucun objet avec ce login et ce mdp");
		}
		return null;
		
		
		
	}

	@Override
	public List<Projet> getProjects() {

		return (List<Projet>) em.createQuery("SELECT l FROM Projet l",Projet.class).getResultList();
	
	
	}

	@Override
	public void CreateUpdateProjet(Projet p) {
		// TODO Auto-generated method stub
		em.merge(p);
	}

	@Override
	public void Delete(Projet p) {
		// TODO Auto-generated method stub
		em.remove(em.merge(p));
	}
	

	@Override
	public List<Projet> getMyProjects(Personne p) {
		// TODO Auto-generated method stub
		List<Projet> projets = new ArrayList<Projet>();
		for (Projet pr : getProjects()) {
			Projet pp= pr;
			/*for (Personne per : pr.getPersonne()) {
				if(per.getId()==em.merge(p).getId()){
					projets.add(pp);
				}*/
			if(pr.getCreater().getId()==em.merge(p).getId())
				{projets.add(pp);
				}
			}
		return projets;
		}
		
	

	@Override
	public boolean CreateUpdatePersonne(Personne p) {
		// TODO Auto-generated method stub
		if(TestPersonne(p)){
		return false;		
		}
		
		Alerte a = new Alerte();
		a.setPersonne(em.merge(p));
		a.setMsg("Bienvenu dans le planneur de projet");
		em.merge(a);
		return true;
	}
	public boolean TestPersonne(Personne p){
		List<Personne> personnes = (List<Personne>) em.createQuery("SELECT p FROM Personne p",Personne.class).getResultList();
		for (Personne per : personnes) {
			if(per.getLogin().equals(p.getLogin()))
				return true;
		}
		return false;
	}

	@Override
	public List<Alerte> GetMyAlerts(Personne p) {
		// TODO Auto-generated method stub
		List<Alerte> alerts = (List<Alerte>) em.createQuery("SELECT a FROM Alerte a",Alerte.class).getResultList();
		List<Alerte> alrts = new ArrayList<Alerte>();
	
		for (Alerte a : alerts) {
			if(a.getPersonne().getId()==p.getId()){
				alrts.add(a);
			}
		}
		
		return alrts;
		
	}

	@Override
	public void AlertCreate(Alerte alert) {
		// TODO Auto-generated method stub
		em.merge(alert);
	}

	@Override
	public List<SousProjet> GetSousProjets(Projet projet) {
		// TODO Auto-generated method stub
		List<SousProjet> sousprojets = (List<SousProjet>) em.createQuery("SELECT s FROM SousProjet s",SousProjet.class).getResultList();
		List<SousProjet> rslt = new ArrayList<>();
		for (SousProjet s : sousprojets) {
			if(s.getProjet().getId()==projet.getId())
			{
				rslt.add(s);
			}
		}
		return rslt;
	}

	@Override
	public List<Personne> GetAllPersonnes() {

		List<Personne> personnes = (List<Personne>) em.createQuery("SELECT p FROM Personne p",Personne.class).getResultList();
				return personnes;
	}

	@Override
	public void AddSPtoP(Projet p, SousProjet s) {
		// TODO Auto-generated method stub
		SousProjet ss=em.merge(s);
		ss.setProjet(em.merge(p));
		em.merge(ss);
	
	}

	@Override
	public void AddTtoSP(Tache t, SousProjet s) {
		// TODO Auto-generated method stub
		Tache tt = em.merge(t);
		tt.setSousprojet(em.merge(s));
		em.merge(tt);
	}

	@Override
	public void DeleteTache(Tache t) {
		// TODO Auto-generated method stub
		em.remove(em.merge(t));
		
	}

	@Override
	public void UpdateTache(Tache t) {
		// TODO Auto-generated method stub
		em.merge(t);
	}

	@Override
	public void CreateRessource(Ressources r) {
		// TODO Auto-generated method stub
		em.merge(r);
		
	}

	@Override
	public void AddRtoT(Ressources r, Tache t) {
		// TODO Auto-generated method stub
		Ressources rr = em.merge(r);
		rr.setTache(t);
		em.merge(rr);
	}

	@Override
	public List<Ressources> RessourcesDisponible() {
		// TODO Auto-generated method stub
		List<Ressources> ressources = (List<Ressources>) em.createQuery("SELECT r FROM Ressources r",Ressources.class).getResultList();
		List<Ressources> rdispo = new ArrayList<>();
		for (Ressources r : ressources) {
			if(r.getTache()==null)
				rdispo.add(r);
		}
		return rdispo;
	}

	@Override
	public void LibérerR(Ressources r) {
		// TODO Auto-generated method stub
		Ressources rr = em.merge(r);
		rr.setTache(new Tache());
		em.merge(rr);
	}

	@Override
	public List<Tache> GetTaches() {
		// TODO Auto-generated method stub
		List<Tache> taches = (List<Tache>) em.createQuery("SELECT t FROM Tache t",Tache.class).getResultList();
		
		return taches;
	}

	@Override
	public Map<SousProjet,List<Tache> > MapSP_T() {
		// TODO Auto-generated method stub
		List<SousProjet> sousp = GetAllSousProjets();
		Map<SousProjet, List<Tache>> rslt = new HashMap<SousProjet, List<Tache>>();
	
		for (SousProjet s : sousp) {
			
			rslt.put(s, s.getTache());
		}
		return rslt;
	}

	@Override
	public List<SousProjet> GetAllSousProjets() {
		// TODO Auto-generated method stub
		List<SousProjet> sousprojets = (List<SousProjet>) em.createQuery("SELECT s FROM SousProjet s",SousProjet.class).getResultList();
	
		return sousprojets;
	}

	@Override
	public List<Ressources> GetRessources() {
		// TODO Auto-generated method stub
		List<Ressources> ressources = (List<Ressources>) em.createQuery("SELECT r FROM Ressources r",Ressources.class).getResultList();
		
		return ressources;
	}

	@Override
	public int NbreAlert(Personne p) {
		// TODO Auto-generated method stub
		int rslt=0;
		for (Alerte a : GetMyAlerts(p)){
			if(a.isSeen()==0)
		rslt++;
		}
		return rslt;
	}

	@Override
	public void AlerterChef(Personne p) {
		// TODO Auto-generated method stub
		String re="select p from Personne p";
		Query req= em.createQuery(re);
		List<Personne> pers = req.getResultList();
	em.merge(p);
		for (Personne per : pers) {
			Alerte a = new Alerte();
			a.setRaison("Pad d'activiter");
		if(per.getRole()==rolee.chef){
			a.setMsg(p.getNom());
			a.setPersonne(em.merge(per));
		}
		AlertCreate(a);
	}
	}

	@Override
	public List<Projet> PartOf(Personne p) {
		// TODO Auto-generated method stub
		em.merge(p);
		String re="select s from Subscribe s";
		Query req= em.createQuery(re);
		List<Subscribe>  subs = req.getResultList();
		
		String ret="select t from Tache t";
		Query reqt= em.createQuery(re);
		List<Tache>  ts = req.getResultList();
		
		
		List<Projet> projets = new ArrayList<>();
		
		for (Subscribe s : subs) {
			if(s.getId().getPersonne()==p.getId()){
				Tache t = GetTacheById(s.getId().getTache());
				
				projets.add(GetProjetTache(t));
			}
		}
	
		return projets;
	}

	@Override
	public Projet GetProjetTache(Tache t) {
		// TODO Auto-generated method stub
		em.merge(t);
		SousProjet sp = t.getSousprojet();
		em.merge(sp);
		return sp.getProjet();
	}

	@Override
	public Tache GetTacheById(int id) {
		// TODO Auto-generated method stub
		
		String re="select t from Tache t where t.id= :id";
		Query req= em.createQuery(re).setParameter("id",  id);
	Tache t = (Tache) req.getSingleResult();
	
		return t;
	}

	@Override
	public List<Tache> GetMyTasks(Personne p) {
		// TODO Auto-generated method stub
		String re="select s from Subscribe s";
		Query req= em.createQuery(re);
		List<Subscribe>  subs = req.getResultList();
		
		String ret="select t from Tache t";
		Query reqt= em.createQuery(re);
	//	List<Tache>  ts = req.getResultList();
		List<Tache>  ts = new ArrayList<>();
		for (Subscribe s : subs) {
			if(s.getId().getPersonne()==p.getId()){
				Tache t = GetTacheById(s.getId().getTache());
				ts.add(t);
			}
		
		}
	
		
	
		return ts;
	}

	@Override
	public void UpdateP(Personne p) {
		// TODO Auto-generated method stub
		em.merge(p);
	}

	@Override
	public void SubUtoT(Personne p, Tache t) {
		// TODO Auto-generated method stub
		Subscribe sub = new Subscribe();
		IdSubscribe ids = new IdSubscribe();
		ids.setPersonne(em.merge(p).getId());
		ids.setTache(em.merge(t).getId());
		sub.setId(ids);
		sub.setState("...");
		em.merge(sub);
		Alerte a = new Alerte();
		a.setPersonne(em.merge(p));
		a.setRaison("Nouvel tache");
		a.setMsg("La tache "+t.getLibelle()+" vous a attribuer");
		em.merge(a);
	}


	

	@Override
	public void TerminerT(Tache t) {
		// TODO Auto-generated method stub
		em.merge(t);
		t.setEtat("Terminer");
	    em.merge(t);
	}

	@Override
	public void RetardAver() {
		// TODO Auto-generated method stub
		// ici
	}

	@Override
	public void DeleteSP(Projet p,SousProjet sp) {
		// TODO Auto-generated method stub
	SousProjet spp=em.merge(sp);
	Projet pp = em.merge(p);
	pp.getSousprojet().remove(sp);
	em.merge(pp);
	em.remove(em.merge(sp));
		
	}

	@Override
	public void CheckTasks() {
		// TODO Auto-generated method stub
		String re="select s from Subscribe s";
		Query req= em.createQuery(re);
		List<Subscribe>  subs = req.getResultList();
		if(subs!=null)
	for (Subscribe subscribe : subs) {
		Tache t= new Tache();
		try{
		t=GetTacheById(subscribe.getId().getTache());
		} catch(Exception e){
			t=null;
		
		if(t==null){
			em.remove(em.merge(subscribe));
		}
		}
	}
		}

}
