import java.sql.ResultSet;
import java.sql.SQLException;

public class Session implements Entite
{
	private int id;
	private String nom;
	private Formation formation;
	private String dateDebut;
	private String dateFin;
	private int nbrePlace;
	private String dateLimite;
	private String planning;
	private String lieu;
	private Root root;

	public Session(int id, String nom, Formation formation, String dateDebut,
			String dateFin, int nbrePlace, String dateLimite, String planning,
			String lieu)
	{

		root = new Root();
		this.id = id;
		this.nom = nom;
		setFormation(formation);
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbrePlace = nbrePlace;
		this.dateLimite = dateLimite;
		this.planning = planning;
		this.lieu = lieu;
		saveSession();
	}

	public Session(int idSession)
	{
		this.id = idSession;
		root = new Root();
		ResultSet r = root.getEntite(this, 3);
		try
		{
			if (r.next())
			{
				setNom(nom);
				setFormation(new Formation(r.getInt("id_formation")));
				System.out.println(formation);
				System.out.println(r.getInt("id_formation"));
				setDateDebut(r.getString("date_debut"));
				setDateFin(r.getString("date_fin"));
				setNbrePlace(r.getInt("nbre_place"));
				setDateLimite(r.getString("date_limite"));
				setPlanning(r.getString("planning"));
				setLieu(r.getString("lieu"));
			}
			else
			{
				System.out.println("ne retrouve pas");
			}
		}
		catch (SQLException e)
		{

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

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public Formation getFormation()
	{
		return formation;
	}

	public void setFormation(Formation formation)
	{
		System.out.println(this + "\n" + formation);
		this.formation = formation;
	}

	public String getDateDebut()
	{
		return dateDebut;
	}

	public void setDateDebut(String dateDebut)
	{
		this.dateDebut = dateDebut;
	}

	public String getDateFin()
	{
		return dateFin;
	}

	public void setDateFin(String dateFin)
	{
		this.dateFin = dateFin;
	}

	public int getNbrePlace()
	{
		return nbrePlace;
	}

	public void setNbrePlace(int nbrePlace)
	{
		this.nbrePlace = nbrePlace;
	}

	public String getDateLimite()
	{
		return dateLimite;
	}

	public void setDateLimite(String dateLimite)
	{
		this.dateLimite = dateLimite;
	}

	public String getPlanning()
	{
		return planning;
	}

	public void setPlanning(String planning)
	{
		this.planning = planning;
	}

	public String getLieu()
	{
		return lieu;
	}

	public void setLieu(String lieu)
	{
		this.lieu = lieu;
	}

	public void saveSession()
	{
		if (root.exist(this))
			root.update(this);
		else
			root.add(this);
	}

	public void deleteSession()
	{
		root.delete(this);
	}

	@Override
	public String toString()
	{
		return "Session [id=" + id + ", nom=" + nom + ", formation="
				+ formation + ", dateDebut=" + dateDebut + ", dateFin="
				+ dateFin + ", nbrePlace=" + nbrePlace + ", dateLimite="
				+ dateLimite + ", planning=" + planning + ", lieu=" + lieu
				+ "]";
	}

	@Override
	public Object[] getEnsembleValeurAttributs()
	{
		return new Object[] { getId(), getDateDebut(), getDateFin(),
				getDateLimite(), getPlanning(), getLieu(), getNbrePlace(),
				getFormation().getId() };
	}

	@Override
	public Object[] getValCond()
	{
		System.out.println(this + "\n" + formation);
		Object[] val = new Object[] { getId()/*, getFormation().getId()*/ };
		return val;
	}
}
