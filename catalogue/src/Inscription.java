
public class Inscription implements Entite
{
	private int id;
	private Utilisateur utilisateur;
	private Session session;
	private boolean paiement;
	private Root root;

	public Inscription(Utilisateur utilisateur, Session session,
			boolean paiement) 
	{
		root = new Root();
		this.utilisateur = utilisateur;
		this.session = session;
		this.paiement = paiement;
		saveInscription();
	}

	public Object getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public Utilisateur getUtilisateur()
	{
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) 
	{
		this.utilisateur = utilisateur;
	}

	public Session getSession() 
	{
		return session;
	}

	public void setSession(Session session) 
	{
		this.session = session;
	}

	public boolean isPaiement()
	{
		return paiement;
	}

	public void setPaiement(boolean paiement)
	{
		this.paiement = paiement;
	}
	
	public void saveInscription() 
	{
		if(root.exist(this))
			root.update(this);
		else 
			root.add(this);
	}
	
	public void deleteInscription() 
	{
		root.delete(this);
	}

	@Override
	public String toString() 
	{
		return "Inscription [id=" + id + ", utilisateur=" + utilisateur
				+ ", session=" + session + ", paiement=" + paiement + "]";
	}

	@Override
	public Object[] getEnsembleValeurAttributs() 
	{
		Object[] EnsembleValeurAttributs = new Object[]{getId(),getUtilisateur(),getSession(),isPaiement()};
		return EnsembleValeurAttributs;
	}

	@Override
	public Object[] getValCond()
	{
		Object[] valCond = new Object[]{this.session.getId(),this.utilisateur.getId()};
		return valCond;
	}
}
