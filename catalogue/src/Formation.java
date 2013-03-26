import java.sql.ResultSet;
import java.sql.SQLException;


public class Formation implements Entite
{
	private int id;
	private Categorie categorie;
	private String intitule;
	private String cout;
	private String contenue;
	private String preRequis;
	private Root root;
	
	public Formation(int id, Categorie categorie, String intitule, String cout,
			String contenue, String preRequis)
	{
		root = new Root();
		this.id = id;
		this.categorie = categorie;
		this.intitule = intitule;
		this.cout = cout;
		this.contenue = contenue;
		this.preRequis = preRequis;
		saveFormation();
	}
	
	public Formation(int idFormation)
	{
		this.id=idFormation;
		root = new Root();
		ResultSet r = root.getEntite(this, 2);
		try {
			while(r.next())
			{
				setCategorie(new Categorie(r.getInt("id_categorie")));
				setIntitule(r.getString("intitule"));
				setCout(r.getString("cout"));
				setContenue(r.getString("contenue"));
				setPreRequis(r.getString("pre_requis"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	public Categorie getCategorie() 
	{
		return categorie;
	}

	public void setCategorie(Categorie categorie) 
	{
		this.categorie = categorie;
	}

	public String getIntitule() 
	{
		return intitule;
	}

	public void setIntitule(String intitule)
	{
		this.intitule = intitule;
	}

	public String getCout() 
	{
		return cout;
	}

	public void setCout(String cout) 
	{
		this.cout = cout;
	}

	public String getContenue() 
	{
		return contenue;
	}

	public void setContenue(String contenue) 
	{
		this.contenue = contenue;
	}

	public String getPreRequis() 
	{
		return preRequis;
	}

	public void setPreRequis(String preRequis)
	{
		this.preRequis = preRequis;
	}

	public Object getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}

	public void saveFormation() 
	{
		if(root.exist(this))
		{
			System.out.println("exist");
			root.update(this);
		}	
		else 
		{
			root.add(this);
			System.out.println(" != exist");
		}
			
	}
	
	public void deleteFormation() 
	{
		root.delete(this);
	}
	
	@Override
	public String toString() 
	{
		return "Formation [id=" + id + ", categorie=" + categorie
				+ ", intitule=" + intitule + ", cout=" + cout + ", contenue="
				+ contenue + ", preRequis=" + preRequis + "]";
	}

	@Override
	public Object[] getEnsembleValeurAttributs() 
	{
		Object[] EnsembleValeurAttributs = new Object[]{getId(),getIntitule(),getCout(),getContenue(),
				getPreRequis(),getCategorie().getId()};
		return EnsembleValeurAttributs;
	}

	@Override
	public Object[] getValCond()
	{
		Object[] valCond = new Object[]{this.getId()};
		return valCond;
	}
}
