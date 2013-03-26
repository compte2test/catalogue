import java.sql.ResultSet;
import java.sql.SQLException;


public class Support implements Entite
{
	private int id;// On a enlever Auteur parce que l'on avez pas besoin enlevez login_utilisateur dans la base de donnee
	private String titre;
	private String format;
	private String dateCreation;
	private String dateModifcation;
	private String url;
	private String description;
	private int nbTelechargement;
	private Root root;
	
	public Support(int id, String titre, String format,
			String dateCreation, String dateModifcation, String url,
			String description, int nbTelechargement)
	{
		root = new Root();
		this.id = id;
		this.titre = titre;
		this.format = format;
		this.dateCreation = dateCreation;
		this.dateModifcation = dateModifcation;
		this.url = url;
		this.description = description;
		this.nbTelechargement = nbTelechargement;
		saveSupport();
	}

	public Support(int id)
	{
		this.id=id;
		root = new Root();
		ResultSet r = root.getEntite(this, id);
		try {
			while(r.next())
			{
				setTitre(r.getString("support_titre"));
				setFormat(r.getString("support_format"));
				setDateCreation(r.getString("support_date_de_creation"));
				setDateModifcation(r.getString("support_date_modification"));
				setUrl(r.getString("support_url"));
				setDescription(r.getString("support_description"));
				setNbTelechargement(r.getInt("support_nb_telechargement"));
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

	public String getTitre() 
	{
		return titre;
	}

	public void setTitre(String titre) 
	{
		this.titre = titre;
	}

	public String getFormat() 
	{
		return format;
	}

	public void setFormat(String format) 
	{
		this.format = format;
	}

	public String getDateCreation() 
	{
		return dateCreation;
	}

	public void setDateCreation(String dateCreation)
	
	{
		this.dateCreation = dateCreation;
	}

	public String getDateModifcation()
	{
		return dateModifcation;
	}

	public void setDateModifcation(String dateModifcation) 
	{
		this.dateModifcation = dateModifcation;
	}

	public String getUrl() 
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public int getNbTelechargement()
	{
		return nbTelechargement;
	}

	public void setNbTelechargement(int nbTelechargement)
	{
		this.nbTelechargement = nbTelechargement;
	}
	
	public void saveSupport() 
	{
		if(root.exist(this))
			root.update(this);
		else 
			root.add(this);
	}
	
	public void deleteSupport() 
	{
		root.delete(this);
	}

	@Override
	public String toString() 
	{
		return "Support [id=" + id + ", titre=" + titre + ", format=" + format
				+ ", dateCreation=" + dateCreation + ", dateModifcation="
				+ dateModifcation + ", url=" + url + ", description="
				+ description + ", nbTelechargement=" + nbTelechargement + "]";
	}

	@Override
	public Object[] getEnsembleValeurAttributs()
	{
		Object[] EnsembleValeurAttributs = new Object[]{getId(),getTitre(),getFormat()
				,getDateCreation(),getDateModifcation(),getUrl(),getDescription(),getNbTelechargement()};
		return EnsembleValeurAttributs;
	}

	@Override
	public Object[] getValCond()
	{
		Object[] valCond = new Object[]{this.getId()};
		return valCond;
	}

}
