package prova;

import java.sql.*;

public class UtenteDAOimpl implements UtenteDAO {
	
	
	@Override 
	public Utente getutente(String username,String password) {
		
		Connection connection = null;
		PreparedStatement mystate = null;
		ResultSet resultSet = null;
	try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemascrutinio?user=root&password=Progetto99!");
		mystate = connection.prepareStatement( "SELECT * FROM utenti WHERE Username =? AND Password =? ");
		mystate.setString(1, username);
		mystate.setString(2, password);
		resultSet = mystate.executeQuery();
		resultSet.next();
		String nome = resultSet.getString("nome");
		String cognome = resultSet.getString("cognome");
		Utente utente = new Utente(nome, cognome);
		connection.close();
		resultSet.close();
		return utente;
	} catch (SQLException e) {
		
		System.out.println("problema sql in getutente CLASSE UtenteDAOimpl");
		e.printStackTrace();
	}
	return null;
	
		
}
	
	
	@Override
	public boolean controllautente(String username, String password) {
			Connection connection = null;
			PreparedStatement mystate = null;
			ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemascrutinio?user=root&password=Progetto99!");
			mystate = connection.prepareStatement( "SELECT * FROM utenti WHERE Username =? AND Password =? ");
			mystate.setString(1, username);
			mystate.setString(2, password);
			resultSet = mystate.executeQuery();
			if (resultSet.next()) {
				connection.close();
				resultSet.close();
				return true;
			}	
			connection.close();
			resultSet.close();
		} catch (SQLException e) {
			
			System.out.println("problema sql in controllautente CLASSE UtenteDAOimpl ");
			e.printStackTrace();
			
		}
		
		
		return false;
			
	}
	/*
	@Override 
	public Utente Update() {
		
		
	}*/
}