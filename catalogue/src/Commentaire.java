import java.sql.ResultSet;
import java.sql.SQLException;

public class Commentaire implements Entite
{
	private Utilisateur auteur;
	private String statut;
	private String contenue;
	private String dateAjout;
	private Session session;
	private Root root;

	public Commentaire(String login, String statut, String dateAjout,
			int session)
	{
		root = new Root();
		this.auteur = new Utilisateur(login);
		this.statut = statut;
		this.dateAjout = dateAjout;
		this.session = new Session(session);
		saveCommentaire();
	}

	public Commentaire(Utilisateur u, Session s)
	{
		this.auteur = u;
		this.session = s;
		root = new Root();
		ResultSet r = root.getEntite(this, 6);
		try
		{
			if (r.next())
			{
				//setCommentaire(r.getString("commentaire"));
				System.out.println(r.getString("commentaire"));
				//setDateAjout(r.getString("date_ajout"));
				System.out.println(r.getString("date_ajout"));
				//setStatut(r.getString("statut"));
				System.out.println(r.getString("statut"));
			}
			else
				System.out.println("trouve pas");
		}
		catch (SQLException e)
		{

			e.printStackTrace();
		}
	}

	public Utilisateur getAuteur()
	{
		return auteur;
	}

	public String getContenue()
	{
		return contenue;
	}

	public void setCommentaire(String contenueCommentaire)
	{
		this.contenue = contenueCommentaire;
	}

	public void setAuteur(Utilisateur auteur)
	{
		this.auteur = auteur;
	}

	public String getStatut()
	{
		return statut;
	}

	public void setStatut(String statut)
	{
		this.statut = statut;
	}

	public String getDateAjout()
	{
		return dateAjout;
	}

	public void setDateAjout(String dateAjout)
	{
		this.dateAjout = dateAjout;
	}

	public Session getSession()
	{
		return session;
	}

	public void setSession(Session session)
	{
		this.session = session;
	}

	/*
	 * private void setSession(int session) { this.session = new
	 * Session(session); }
	 */

	public void saveCommentaire()
	{
		if (root.exist(this))
		{
			System.out.println("exist");
			root.update(this);
		}
		else
		{
			System.out.println("no");
			root.add(this);
		}
	}

	public void deleteCommentaire()
	{
		root.deleteCommentaire(this);
	}

	@Override
	public String toString()
	{
		return "Commentaire [auteur=" + auteur + ", statut=" + statut
				+ ", contenueCommentaire=" + contenue + ", dateAjout="
				+ dateAjout + ", session=" + session + "]";
	}

	@Override
	public Object getId()
	{
		// TODO Auto-generated method stub
		return (Integer) null;
	}

	@Override
	public void setId(int id)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getEnsembleValeurAttributs()
	{
		return new Object[] { getContenue(), getDateAjout(), getStatut(),
				getSession().getId(), getAuteur().getId() };
	}

	@Override
	public Object[] getValCond()
	{
		Object[] valCond = new Object[] { this.session.getId(),
				this.auteur.getId() };
		// for(Object o : valCond)
		// System.out.println(o);
		return valCond;
	}

}
