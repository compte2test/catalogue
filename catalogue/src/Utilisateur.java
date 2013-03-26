
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Utilisateur implements Entite//Pour chaque class , il faut rajouter l'id en attribut de la base de donn�e et les faire normalement,tout les les requetes seront dans la class Connexion 
{																												//ou il y aura chaque methode d'ecriture/lecture pour chaque class
	private int id;
	private ArrayList<Cours> assiste = null;
	private ArrayList<Session> dispense = null;
	private ArrayList<Support> telecharge = null;
	private String login;
	private String motDePasse;
	private String nom;
	private String prenom;
	private String mail;
	private String adresse;
	private String codePostal;
	private String ville;
	private String telephoneFixe;
	private String telephonePortable;
	private String dateInscription;
	private String 	derniereConnexion;
	private int statut;
	private Root root;
	
	public Utilisateur(String login, String motDePasse,String nom, //Constructeur qui instancie les attribut de la class sans passer par la base de donne�, a utiliser donc pour crer un utilisateur dans la base de donn�e(par exemple formulaire d'inscription) 
			String prenom, String mail, String adresse, String codePostal,
			String ville, String telephoneFixe, String telephonePortable,
			String dateInscription, String derniereConnexion, int statut) 
	{
		 root = new Root(); 
		 this.login = login;
		 this.motDePasse=motDePasse;
		 this.nom=nom;
		 this.prenom=prenom;
		 this.mail=mail;
		 this.adresse=adresse;
		 this.codePostal=codePostal;
		 this.ville=ville;
		 this.telephoneFixe=telephoneFixe;
		 this.telephonePortable=telephonePortable;
		 this.dateInscription=dateInscription;
		 this.derniereConnexion=derniereConnexion;
		 this.statut=statut;
		 saveUtilisateur();
	}
	
	public Utilisateur(String login) //instanciation des attributs utilisateurs en fonction de la base de donn�es, a utiliser pour un utilisateur qui existe deja dans la base de donn�e (par exemple pour  portail de connexion)
	{
		root = new Root();
		this.login = login;
		ResultSet r = root.getUtilisateur(login);
		try {
			while(r.next())
			{
				setAdresse(r.getString("adresse"));
				setCodePostal(r.getString("code_postal"));
				setMotDePasse(r.getString("mot_de_passe"));
				setNom(r.getString("nom"));
				setPrenom(r.getString("prenom"));
				setMail(r.getString("mail"));
				setVille(r.getString("ville"));
				setTelephoneFixe(r.getString("telephone_fixe"));
				setTelephonePortable(r.getString("telephone_portable"));
				setDateInscription(r.getString("date_inscription"));
				setDerniereConnexion(r.getString("derniere_connexion"));
				setStatut(r.getInt("statut"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	public Object getId() 
	{
		return login;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}

	private void readCours()//Instanciation d' assiste  et lecture dans la base de donnee des id de cours de la table assiste et cree et ajoute des cours(avec pour id ceux trouve) et dans l'Arraylist assiste de la class Utilisateur
	{
		if (assiste == null)
		{
			assiste = new ArrayList<Cours>();
			root.readCours(this);
		}		
	}
	
	public int getNbCours()
	{
		readCours();
		return assiste.size();
	}
	
	public Cours getCours(int index)
	{
		readCours();
		return assiste.get(index);
	}
	
	public void addCours(Cours c) 
	{
		readCours();
		assiste.add(c);
	}
	
	public void removeCours(Cours c) 
	{
		readCours();
		assiste.remove(c);
	}
	
	private void readSession()
	{
		if (dispense == null)
		{
			dispense = new ArrayList<Session>();
			root.readSession(this);
		}		
	}
	
	public int getNbSession()
	{
		readSession();
		return dispense.size();
	}
	
	public Session getSession(int index)
	{
		readSession();
		return dispense.get(index);
	}
	
	public void addSession(Session s) 
	{
		readSession();
		dispense.add(s);
	}

	public void removeSession(Session s) 
	{
		readSession();
		dispense.remove(s);
	}
	
	private void readSupport()
	{
		if (telecharge == null)
		{
			telecharge = new ArrayList<Support>();
			root.readSupport(this);
		}		
	}
	
	public int getNbSupport()
	{
		readSupport();
		return telecharge.size();
	}
	
	public Support getSupport(int index)
	{
		readSupport();
		return telecharge.get(index);
	}
	
	public void addSupport(Support s) 
	{
		readSupport();
		telecharge.add(s);
	}
	
	public void removeSupport(Support s) 
	{
		readSupport();
		telecharge.remove(s);
	}
	
	public void saveUtilisateur() 
	{
		if(root.exist(this))
			root.update(this);
		else 
			root.add(this);
	}
	
	public void deleteUtilisateur() 
	{
		root.deleteUtilisateur(this);
	}
	
	public String getLogin() 
	{
		return login;
	}
	
	public void setLogin(String login) 
	{
		this.login = login;
	}
	
	public String getMotDePasse()
	{
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse)
	{
		this.motDePasse = motDePasse;
	}

	public String getNom() 
	{
		return nom;
	}

	public void setNom(String nom) 
	{
		this.nom = nom;
	}
	
	public String getPrenom() 
	{
		return prenom;
	}

	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}

	public String getMail()
	{
		return mail;
	}
	
	public void setMail(String mail)
	{
		this.mail = mail;
	}

	public String getAdresse() 
	{
		return adresse;
	}

	public void setAdresse(String adresse) 
	{
		this.adresse=adresse;
	}

	public String getCodePostal() 
	{
		return codePostal;
	}

	public void setCodePostal(String codePostal) 
	{
		this.codePostal = codePostal;
	}

	public String getVille() 
	{
		return ville;
	}

	public void setVille(String ville) 
	{
		this.ville = ville;
	}

	public String getTelephoneFixe() 
	{
		return telephoneFixe;
	}

	public void setTelephoneFixe(String telephoneFixe) 
	{
		this.telephoneFixe = telephoneFixe;
	}

	public String getTelephonePortable()
	{
		return telephonePortable;
	}

	public void setTelephonePortable(String telephonePortable) 
	{
		this.telephonePortable = telephonePortable;
	}

	public String getDateInscription() 
	{
		return dateInscription;
	}

	public void setDateInscription(String dateInscription)
	{
		this.dateInscription = dateInscription;
	}

	public String getDerniereConnexion() 
	{
		return derniereConnexion;
	}
	
	public void setDerniereConnexion(String derniereConnexion) 
	{
		this.derniereConnexion = derniereConnexion;
	}

	public int getStatut()
	{
		return statut;
	}
	
	public void setStatut(int statut) 
	{
		this.statut = statut;
	}
	
	@Override
	public Object[] getEnsembleValeurAttributs()
	{
		Object[] EnsembleValeurAttributs = new Object[]{getId(),getLogin(),getMotDePasse(),getNom(),getPrenom(),getMail(),getAdresse(),
				getCodePostal(),getVille(),getTelephoneFixe(),getTelephonePortable(),getDateInscription()
				,getDerniereConnexion(),getStatut()};
		
		return EnsembleValeurAttributs;
		
	}
	
	public void addCommentaire(Commentaire c)
	{
		//permet de rajouter un commentaire
	}
	
	public void updateCommentaire(Commentaire c)
	{
		//mise à jour (cond = this.login)
	}
	
	@Override
	public String toString() 
	{
		return "Utilisateur [id=" + id + ", assiste=" + assiste + ", dispense="
				+ dispense + ", telecharge=" + telecharge + ", login=" + login
				+ ", motDePasse=" + motDePasse + ", nom=" + nom + ", prenom="
				+ prenom + ", mail=" + mail + ", adresse=" + adresse
				+ ", codePostal=" + codePostal + ", ville=" + ville
				+ ", telephoneFixe=" + telephoneFixe + ", telephonePortable="
				+ telephonePortable + ", dateInscription=" + dateInscription
				+ ", derniereConnexion=" + derniereConnexion + ", statut="
				+ statut + "]";
	}

	@Override
	public Object[] getValCond()
	{
		Object[] valCond = new Object[]{this.login};
		return valCond;
	}
}
