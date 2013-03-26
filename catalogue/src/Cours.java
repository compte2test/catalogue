import java.sql.ResultSet;
import java.sql.SQLException;


public class Cours implements Entite
{
	private int id;
	private Session session;
	private String date;
	private String heureDebut;
	private String heureFin;
	private String adresse;
	private String salle;
	private Root root;
	
	public Cours(int id ,Session session, String date, String heureDebut,
			String heureFin, String adresse, String salle) 
	{
		root = new Root();
		this.id=id;
		this.session=new Session((int)session.getId());
		this.date=date;
		this.heureDebut=heureDebut;
		this.heureFin=heureFin;
		this.adresse=adresse;
		this.salle=salle;
		saveCours();
	}
	
	public Cours(int id)
	{
		this.id=id;
		root = new Root();
		ResultSet r = root.getEntite(this, 1);
		try {
			while(r.next())
			{
				setSession(new Session(r.getInt("cours_session")));
				setDate(r.getString("cours_date"));
				setHeureDebut(r.getString("cours_heure_debut"));
				setHeureFin(r.getString("cours_heure_fin"));
				setAdresse(r.getString("cours_adresse"));
				setSalle(r.getString("cours_salle"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	public Object getId() 
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
	public Session getSession() 
	{
		return session;
	}

	public void setSession(Session session) 
	{
		this.session = session;
	}

	public String getDate() 
	{
		return date;
	}

	public void setDate(String date) 
	{
		this.date = date;
	}

	public String getHeureDebut() 
	{
		return heureDebut;
	}

	public void setHeureDebut(String heureDebut)
	{
		this.heureDebut = heureDebut;
	}

	public String getHeureFin() 
	{
		return heureFin;
	}

	public void setHeureFin(String heureFin)
	{
		this.heureFin = heureFin;
	}

	public String getAdresse() 
	{
		return adresse;
	}

	public void setAdresse(String adresse) 
	{
		this.adresse = adresse;
	}

	public String getSalle() 
	{
		return salle;
	}
	
	public void setSalle(String salle) 
	{
		this.salle = salle;
	}
	
	public void saveCours() 
	{
		if(root.exist(this))
			root.update(this);
		else 
			root.add(this);
	}

	public void deleteCours() 
	{
		root.delete(this);
	}
	
	@Override
	public Object[] getEnsembleValeurAttributs()
	{
		Object[] EnsembleValeurAttributs = new Object[]{getId(),getSession(),getDate(),getHeureDebut(),getHeureFin(),getAdresse(),getSalle()};
		
		return EnsembleValeurAttributs;
	}
	
	@Override
	public String toString() 
	{
		return "Cours [id=" + id + ", session=" + session + ", date=" + date
				+ ", heureDebut=" + heureDebut + ", heureFin=" + heureFin
				+ ", adresse=" + adresse + ", salle=" + salle + "]";
	}

	@Override
	public Object[] getValCond()
	{
		Object[] valCond = new Object[]{this.session.getId(),this.getId()};
		return valCond;
	}

}
