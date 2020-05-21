import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
		
	public void nacitajZDatabazy() {
		String url = "jdbc:mysql://localhost:3306/martinus?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
		String user = "root";
		String password = "MARK949494tremonti";
		
		try (	
				Connection spojenie = DriverManager.getConnection(url, user, password);
				Statement prikaz = spojenie.createStatement();
			){
				String sqlSelect = "select nazov, cena, mnozstvo from knihy";
				System.out.println("SQL prikaz: " + sqlSelect + "\n"); // pre ucely debugovania
				
				ResultSet vysledky = prikaz.executeQuery(sqlSelect);
				
				System.out.println("Ziskane zaznamy: ");
				int pocetRiadkov = 0;
				
				while(vysledky.next()) 
				{
					String nazov = vysledky.getString("nazov");
					double cena = vysledky.getDouble("cena");
					int mnozstvo = vysledky.getInt("mnozstvo");
					System.out.println(nazov + ", " + cena + ", " + mnozstvo);
					pocetRiadkov++;
				}
				System.out.println("Celkovy pocet zaznamov: " + pocetRiadkov);
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			} // 5.krok: Uzatvorenie vsetkych resources, spojenia a prikaz
		
		
	}
	
	public void ulozDoDatabazy() {
			String url = "jdbc:mysql://localhost:3306/martinus?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
			String user = "root";
			String password = "MARK949494tremonti";
				
			try (	
				Connection spojenie = DriverManager.getConnection(url, user, password);
				Statement prikaz = spojenie.createStatement();
				){
				String sqlUpdate = "update knihy set cena = cena * 0.7"; 
		 		System.out.println("SQL prikaz: " + sqlUpdate + "\n"); 
					
				int pocetZmenenychZaznamov = prikaz.executeUpdate(sqlUpdate);
				System.out.println("Zmena nastala v " + pocetZmenenychZaznamov + " zaznamoch");
					
					
				String sqlSelect = "select * from knihy";
					
				ResultSet vysledky = prikaz.executeQuery(sqlSelect);
				System.out.println("Ziskane zaznamy: ");
				int pocetRiadkov = 0;
					
				while(vysledky.next()) 
				{
					int id = vysledky.getInt("id");
					String nazov = vysledky.getString("nazov");
					String autor = vysledky.getString("autor");
					double cena = vysledky.getDouble("cena");
					int mnozstvo = vysledky.getInt("mnozstvo");
					System.out.println(id + ", " + autor + ", " + nazov + ", " + cena + ", " + mnozstvo);
					pocetRiadkov++;
				}
				System.out.println("Celkovy pocet zaznamov: " + pocetRiadkov);
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			} 
	}
}
