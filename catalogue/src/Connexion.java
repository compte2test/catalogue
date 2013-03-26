import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Connexion {
	
	Connection connect;
    Statement transmission;
    ResultSet result;
    String req;
    String url;
    
    public static final boolean DEBUG_MODE = true;

    public Connexion(String url) 
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager.getConnection(url,"root","");
			transmission = connect.createStatement();
		} 
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
    
    public int modification(String req) 
	{
    	if (DEBUG_MODE)
    		System.out.println(req);
		int resultat=0;
		try 
		{
			resultat = transmission.executeUpdate(req);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	
		return resultat;
	}
	
	public int insert(String req) //Ecriture des données
	{
		return modification(req);
	}

	public int update(String req)//Ecriture des données
	{
		return modification(req);
	}
	
	public int delete(String req)//Ecriture des données
	{
		return modification(req);
	}
	
	public ResultSet select(String req)//Lecture des données
	{
		if(DEBUG_MODE)
			System.out.println(req);
		try
		{
			result = transmission.executeQuery(req);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return result;
	}
    
	
}
