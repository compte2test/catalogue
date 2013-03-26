import java.sql.ResultSet;
import java.sql.SQLException;

public class Categorie implements Entite
{
	private int id;
	private String nom;
	private String description;
	private Root root;
	
	public Categorie(int id, String nom,String description) 
	{
		root = new Root();
		this.id = id;
		this.nom = nom;
		this.description = description;
		saveCategorie();
	}
	
	public Categorie(int id) 
	{
		this.id=id;
		root = new Root();
		ResultSet r = root.getEntite(this, 0);
		try {
			while(r.next())
			{
				setNom(r.getString("nom_categorie"));
				setDescription("description");
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
	
	public String getNom() 
	{
		return nom;
	}

	public void setNom(String nom) 
	{
		this.nom = nom;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public void saveCategorie() 
	{
		if(root.exist(this))
		{
			root.update(this);
		}
		else 
		{
			root.add(this);
		}
			
	}
	
	public void deleteCategorie() 
	{
		root.delete(this);
	}
	
	@Override
	public Object[] getEnsembleValeurAttributs() 
	{
		Object[] EnsembleValeurAttributs = new Object[]{getId(),getNom(),getDescription()};
		return EnsembleValeurAttributs;
	}
	
	@Override
	public String toString() {
		return "Categorie [id=" + id + ", nom=" + nom + ", description="
				+ description + "]";
	}

	@Override
	public Object[] getValCond()
	{
		Object[] valCond = new Object[]{this.id};
		return valCond;
	}
	
}
