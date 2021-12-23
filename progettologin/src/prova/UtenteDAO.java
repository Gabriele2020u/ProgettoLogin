package prova;

public interface UtenteDAO {
	
	public boolean controllautente(String username, String password);
	public Utente getutente(String username, String password);
	//public Utente update(); 
}
