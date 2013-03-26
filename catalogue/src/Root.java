import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Root
{
	ArrayList<String> tableName;
	ArrayList<String[]> nomChamps;
	ArrayList<Object[]> valChamps;// pas besoin de valChamps
	ArrayList<String[]> nomCond;// Cond des update
	ArrayList<Object[]> valCond;// pas besoin de valCond
	Class<?>[] nomClass;
	Connexion connexion;

	public Root()
	{
		tableName = new ArrayList<String>();
		nomChamps = new ArrayList<String[]>();
		valChamps = new ArrayList<Object[]>();
		nomCond = new ArrayList<String[]>();
		valCond = new ArrayList<Object[]>();
		connexion = new Connexion("jdbc:mysql://localhost/m2l");

		nomClass = new Class<?>[] { Categorie.class, Cours.class,
				Formation.class, Session.class, Support.class,
				Inscription.class,Commentaire.class};
		String[] tabName = new String[] { "categorie", "cours", "formation",
				"session", "support", "inscription","commente"};
		for (String s : tabName)
			tableName.add(s);// remplissage de tableName

		String[] nomChampsCours = new String[] { "id_cours", "cours_date",
				"cours_heure_debut", "cours_heure_fin", "cours_adresse",
				"cours_salle", "cours_session" };
		String[] nomChampsUtilisateur = new String[] { "login", "mot_de_passe",
				"nom", "prenom", "mail", "adresse", "code_postal", "ville",
				"telephone_fixe", "telephone_portable", "date_inscription",
				"derniere_connexion", "statut" };
		String[] nomChampsFormation = new String[] { "id_formation",
				"intitule", "cout", "contenue", "pre_requis", "id_categorie" };
		String[] nomChampsCategorie = new String[] { "id_categorie","nom_categorie", "description" };
		
		String[] nomChampsCommentaire = new String[] { "commentaire",
				"date_ajout", "statut", "id_session", "login_utilisateur" };
		String[] nomChampsInscription = new String[] { "paiement",
				"id_session", "login_utilisateur" };
		String[] nomChampsSession = new String[] { "id_session", "date_debut",
				"date_fin", "date_limite", "planning", "lieu","nbre_place", "id_formation" };
		String[] nomChampsSupport = new String[] { "id_support",
				"support_titre", "support_format", "support_date_dreation",
				"support_date_modification", "support_url",
				"support_description", "support_nb_telechargement",
				"login_utilisateur" };

		nomChamps.add(nomChampsCategorie);
		nomChamps.add(nomChampsCours);
		nomChamps.add(nomChampsFormation);
		nomChamps.add(nomChampsSession);
		nomChamps.add(nomChampsSupport);
		nomChamps.add(nomChampsInscription);
		nomChamps.add(nomChampsCommentaire);// remplissage de nomChamps
		nomChamps.add(nomChampsUtilisateur);
		
		
		

		/*
		 * Object[] valChampsCours = new
		 * Object[]{c.getId(),c.getDate(),c.getHeureDebut
		 * (),c.getHeureFin(),c.getAdresse(),c.getSalle(),
		 * c.getSession().getId()}
		 */

		String[] nomCondCours = new String[] { "id_cours", "cours_session" };// remplissage
																				// de
																				// nomCond
		
		String[] nomCondUtilisateur = new String[] { "login" };
		
		String[] nomCondCategorie = new String[] {"id_categorie"};
		
		String[] nomCondCommentaire = new String[] { "id_session","login_utilisateur" };
		String[] nomCondFormation = new String[] {"id_formation"};
		String[] nomCondInscription = new String[] {"id_session","login_utilisateur"};
		String[] nomCondSession = new String[] {"id_session"/*,"id_formation"*/};
		String[] nomCondSupport = new String[] {"id_support"};
		
		nomCond.add(nomCondCategorie);
		nomCond.add(nomCondCours);
		nomCond.add(nomCondFormation);
		nomCond.add(nomCondSession);
		nomCond.add(nomCondSupport);
		nomCond.add(nomCondInscription);
		nomCond.add(nomCondCommentaire);
		nomCond.add(nomCondUtilisateur);
		

		// Object[] valCond = new Object[]{c.getId(),c.getSession().getId()}
	}

	/*public ResultSet getEntite(Entite entite, Object IdDeEntite)// Lecture des
																// propriete
																// dans la base
																// de donn�e
	{
		String req = "";
		if (entite.getClass().isAssignableFrom(Categorie.class))
			req = "Select * from categorie where id_categorie =\"" + IdDeEntite
					+ "\"";
		else if (entite.getClass().isAssignableFrom(Cours.class))
			req = "Select * from cours where id_cours=" + IdDeEntite;
		else if (entite.getClass().isAssignableFrom(Formation.class))
			req = "Select * from formation where id_formation=\"" + IdDeEntite
					+ "\"";
		else if (entite.getClass().isAssignableFrom(Session.class))
			req = "Select * from session where id_session=\"" + IdDeEntite
					+ "\"";
		else if (entite.getClass().isAssignableFrom(Support.class))
			req = "Select * from support where id_support=" + IdDeEntite + "";
		else if(entite.getClass().isAssignableFrom(Utilisateur.class))
			req = "Select * from utilisateur where login=\"" + IdDeEntite + "\"";
		return connexion.select(req);
	}

	public ResultSet getEntite(Entite entite, Object IdDeEntite1,
			Object IdDeEntite2)// Lecture des propriete dans la base de donn�e
	{
		String req = "";
		if (entite.getClass().isAssignableFrom(Commentaire.class))
			req = "Select * from commente where id_session=\"" + IdDeEntite1
					+ "\" and login_utilisateur=\"" + IdDeEntite2 + "\"";
		else if (entite.getClass().isAssignableFrom(Inscription.class))
			req = "Select * from inscription where id_session=\"" + IdDeEntite1
					+ "\" and login_utilisateur=\"" + IdDeEntite2 + "\" ";
		return connexion.select(req);
	}*/
	
	public ResultSet getEntite(Entite entite,int numTab)//Lecture des propriete dans la base de donnée
    {
	 String req =  "Select * from "+tableName.get(numTab)+" where ";
		for (int i = 0 ; i < entite.getValCond().length; i++)
		{
			req += ((i == 0) ? "" : " and ") + nomCond.get(numTab)[i] + " = '" + 
						entite.getValCond()[i] + "'";
		}
	 return connexion.select(req); 
    }

	public boolean exist(Entite entite)
	{
		int i = 0;
		boolean b = false;
		while (!entite.getClass().isAssignableFrom(nomClass[i])
				&& i < nomClass.length)
			i++;
			
		b = makeExist(entite);
		return b;
	}

	public void add(Entite entite)
	{
		int i = 0;
		while (!entite.getClass().isAssignableFrom(nomClass[i]) && i < nomClass.length)
			i++;
		connexion.insert(makeAdd(entite, i));// la position des element dans nomClass et dans
							// tabName dois etre synchronise
	}

	public void update(Entite entite)
	{
		for(Class<?> c : nomClass)
		{
			if (entite.getClass().isAssignableFrom(c))
			{
				int i = 0;
				while (!entite.getClass().isAssignableFrom(nomClass[i]) && i < nomClass.length)
					i++;
				System.out.println(nomClass[i].getName());
				connexion.update(makeUpdate(entite, i));
			}
		}
			/*;
		makeUpdate(entite, 1);*/
	}

	public void delete(Entite entite)
	{
		String req1 = "", req2 = "", req3 = "", req4 = "";
		if (entite.getClass().isAssignableFrom(Categorie.class))
		{
			req1 = "delete from categorie where id_categorie = "
					+ entite.getId() + "";
			req2 = "delete from formation where id_categorie = "
					+ entite.getId() + "";
		}
		else if (entite.getClass().isAssignableFrom(Cours.class))
		{
			req1 = "delete from assiste where id_cours= " + entite.getId();
			req2 = "delete from cours where id_cours= " + entite.getId();
		}

		else if (entite.getClass().isAssignableFrom(Formation.class))
		{
			req1 = "delete from session where id_formation= " + entite.getId();
			req2 = "delete from formation where id_formation= "
					+ entite.getId();
		}
		else if (entite.getClass().isAssignableFrom(Session.class))
		{
			req1 = "delete from commente where id_session= " + entite.getId();
			req2 = "delete from inscription where id_session= "
					+ entite.getId();
			req3 = "delete from illustre where id_session= " + entite.getId();
			req4 = "delete from session where id_session= " + entite.getId();
		}

		else if (entite.getClass().isAssignableFrom(Support.class))
		{
			req1 = "delete from illustre,telecharge where id_support= "
					+ entite.getId();
			req2 = "delete from support where id_support= " + entite.getId();
		}

		else if (entite.getClass().isAssignableFrom(Inscription.class))
			req1 = "delete from inscription where id_inscription= "
					+ entite.getId();

		connexion.delete(req1);
		if (!req2.equals(""))
			connexion.delete(req2);
		if (!req3.equals(""))
			connexion.delete(req3);
		if (!req4.equals(""))
			connexion.delete(req4);
	}

	public boolean makeExist(Entite entite)
	{
		boolean b = false;
		int i = 0;
		while (!entite.getClass().isAssignableFrom(nomClass[i]) && i < nomClass.length)
			i++;
		if(i<0 && i>nomClass.length)
		{
			ResultSet res = getEntite(entite,i);
			try
			{
				b = res.next();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return b;
	}

	private String makeUpdate(Entite entite, int numTab)
	{
		String res = "update " + tableName.get(numTab) + " set ";
		for (int i = 0; i < nomChamps.get(numTab).length; i++)
		{
			System.out.println(res);
			res += ((i == 0) ? "" : ", ") + nomChamps.get(numTab)[i] + " = '"
					+ entite.getEnsembleValeurAttributs()[i] + "'";
			//System.out.println(res);
		}
		res += " where ";
		for (int i = 0; i < nomCond.get(numTab).length; i++)
		{
			res += ((i == 0) ? "" : " and ") + nomCond.get(numTab)[i] + " = '"
					+ entite.getValCond()[i] + "'";// valcond est vide
			System.out.println(res);
		}
		return res;
	}

	private String makeAdd(Entite entite, int numTab)
	{
		String res = "INSERT " + " into " + tableName.get(numTab);
		res += "(";
		for (int i = 0; i < nomChamps.get(numTab).length; i++)
		{
			
			res += ((i != nomChamps.get(numTab).length - 1) ? (nomChamps
					.get(numTab)[i] + ",") : nomChamps.get(numTab)[i]);
			
		}
		res += ")";
		res += " VALUES(";
		for (int i = 0; i < entite.getEnsembleValeurAttributs().length; i++)
			res += ((i != entite.getEnsembleValeurAttributs().length - 1) ? ("'"
					+ entite.getEnsembleValeurAttributs()[i] + "'" + ",")
					: "'" + entite.getEnsembleValeurAttributs()[i] + "'");
		res += ")";
		return res;

	}

	/*----------------------------------------------------------Couche d'acces Commentaire a garder car cas particulier(il n'a pas d'Id)---------------------------------------------------*/

	public boolean exist(Commentaire c)
	{
		ResultSet res = getCommentaire((int)c.getSession().getId(),(String)c.getAuteur().getId());
		boolean b = false;
		try
		{
			b = res.next();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return b;
	}

	public ResultSet getCommentaire(int idSession, String idUtilisateur)// Lecture
																		// des
																		// propriete
																		// commentaire
																		// dans
																		// la
																		// base
																		// de
																		// donn�e
	{
		String req = "Select * from commente where id_session=\"" + idSession
				+ "\" and login_utilisateur=\"" + idUtilisateur + "\"";
		return connexion.select(req);
	}

	/*public void add(Commentaire c)
	{
		connexion
				.insert("INSERT into commente(commentaire,date_ajout,id_session,login_utilisateur,statut)"
						+ " VALUES(\""
						+ c.getContenue()
						+ "\","
						+ "\""
						+ c.getDateAjout()
						+ "\","
						+ c.getSession().getId()
						+ ","
						+ "'"+c.getAuteur().getId() + "'"
						+ ",\"" + c.getStatut() + "\")");// remplissage de a
															// base de donne en
															// fonction des
															// valeurs des
															// attribut de la
															// class
	}*/

	/*public void update(Commentaire c)
	{
		connexion.update("UPDATE commente set commentaire=\""
				+ c.getContenue() + "\",date_ajout=\""
				+ c.getDateAjout() + "\"," + "id_session="
				+ c.getSession().getId() + ",login_utilisateur="
				+ c.getAuteur().getId() + ",statut=\"" + c.getStatut() + "\" "
				+ "where id_session=" + c.getSession().getId()
				+ " and login_utilisateur=" + c.getAuteur().getId());
	}*/

	public void deleteCommentaire(Commentaire c)
	{
		String req = "delete from commente where id_session="
				+ c.getSession().getId() + " and login_utilisateur="
				+ c.getAuteur().getId();
		connexion.delete(req);
	}

	/*----------------------------------------------------------Couche d'acces Utilisateur(a garder car cas particulier(l'Id est un login)---------------------------------------------------*/

	public void readCours(Utilisateur user)// Lecture dans la base de donnee des
											// id de cours de la table assiste
											// et cree et ajoute des cours(avec
											// pour id ceux trouve) et dans
											// l'Arraylist assiste de la class
											// Utilisateur
	{
		ResultSet r = connexion
				.select("SELECT * from assiste where login_utilisateur =\""
						+ user.getLogin() + "\"");
		try
		{
			while (r.next())
			{
				user.addCours(new Cours(r.getInt("id_cours")));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public ResultSet getUtilisateur(String login)// Lecture des propriete
													// utilisateur dans la base
													// de donn�e
	{
		String req = "Select * from utilisateur where login=\"" + login + "\"";
		return connexion.select(req);
	}

	public void add(Utilisateur u)
	{
		String req = "INSERT into utilisateur(login,mot_de_passe,nom,prenom,mail,adresse,code_postal,ville,"
				+ "telephone_fixe,telephone_portable,date_inscription,derniere_connexion,statut)"
				+ " VALUES(\""
				+ u.getLogin()
				+ "\",\""
				+ u.getMotDePasse()
				+ "\",\""
				+ u.getNom()
				+ "\","
				+ "\""
				+ u.getPrenom()
				+ "\",\""
				+ u.getMail()
				+ "\",\""
				+ u.getAdresse()
				+ "\",\""
				+ u.getCodePostal()
				+ "\""
				+ ",\""
				+ u.getVille()
				+ "\",\""
				+ u.getTelephoneFixe()
				+ "\",\""
				+ u.getTelephonePortable()
				+ "\","
				+ "\""
				+ u.getDateInscription()
				+ "\",\""
				+ u.getDerniereConnexion() + "\",\"" + u.getStatut() + "\")";
		System.out.println(req);
		connexion.insert(req);
	}

	public void update(Utilisateur u)
	{
		connexion.update("UPDATE utilisateur set login=\"" + u.getLogin()
				+ "\", mot_de_passe =\"" + u.getMotDePasse() + "\","
				+ "nom =\"" + u.getNom() + "\",prenom =\"" + u.getPrenom()
				+ "\",mail =\"" + u.getMail() + "\",adresse =\""
				+ u.getAdresse() + "\"" + ",code_postal =\""
				+ u.getCodePostal() + "\",ville =\"" + u.getVille()
				+ "\",telephone_fixe =\"" + u.getTelephoneFixe() + "\","
				+ "telephone_portable =\"" + u.getTelephonePortable()
				+ "\",date_inscription =\"" + u.getDateInscription() + "\""
				+ ",derniere_connexion =\"" + u.getDerniereConnexion()
				+ "\",statut =\"" + u.getStatut() + "\"" + "  where login =\""
				+ u.getLogin() + "\"");
	}

	public void deleteUtilisateur(Utilisateur u)
	{
		String req = "delete from utilisateur where login =\"" + u.getLogin()
				+ "\"";
		System.out.println(req);
		connexion.delete(req);
	}

	public void readSession(Utilisateur user)
	{
		ResultSet r = connexion
				.select("SELECT * from dispense where login_utilisateur =\""
						+ user.getLogin() + "\"");
		try
		{
			while (r.next())
			{
				user.addSession(new Session(r.getInt("id_session")));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void readSupport(Utilisateur user)
	{
		ResultSet r = connexion
				.select("SELECT * from telecharge where login_utilisateur =\""
						+ user.getLogin() + "\"");
		try
		{
			while (r.next())
			{
				user.addSupport(new Support(r.getInt("id_support")));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
