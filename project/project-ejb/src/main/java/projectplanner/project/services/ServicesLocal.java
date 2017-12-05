package projectplanner.project.services;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import projectplanner.project.persistence.Alerte;
import projectplanner.project.persistence.Personne;
import projectplanner.project.persistence.Projet;
import projectplanner.project.persistence.Ressources;
import projectplanner.project.persistence.SousProjet;
import projectplanner.project.persistence.Tache;

@Local
public interface ServicesLocal {
	public Personne connect(String login,String pass);
   public List<Projet> getProjects();
   public void CreateUpdateProjet(Projet p);
   public void Delete(Projet p);
   public List<Projet> getMyProjects(Personne p);
   public boolean CreateUpdatePersonne(Personne p);
   public List<Alerte> GetMyAlerts(Personne p);
   public void AlertCreate(Alerte alert);
   public List<SousProjet> GetSousProjets(Projet projet);
   public List<SousProjet> GetAllSousProjets();
  public List<Personne> GetAllPersonnes();
  public void AddSPtoP(Projet p,SousProjet s);
  public void AddTtoSP(Tache t,SousProjet s);
  public void DeleteTache(Tache t);
  public void UpdateTache(Tache t);
  public void CreateRessource(Ressources r);
  public void AddRtoT(Ressources r, Tache t);
  public List<Ressources> RessourcesDisponible();
  public List<Ressources> GetRessources();
  public void LibérerR(Ressources r);
  public List<Tache> GetTaches();
  public Map<SousProjet, List<Tache>> MapSP_T();
  public int NbreAlert(Personne p);
  public void AlerterChef(Personne p);
  public List<Projet> PartOf(Personne p);
  public Projet GetProjetTache(Tache t);
  public Tache GetTacheById(int id);
  public List<Tache> GetMyTasks(Personne p);
  public void UpdateP(Personne p);
  public void SubUtoT(Personne p,Tache t);
  public void TerminerT(Tache t);
  public void RetardAver();
  public void DeleteSP(Projet p,SousProjet sp);
  public void CheckTasks();
}
