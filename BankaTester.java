import javax.swing.SwingUtilities;

public class BankaTester {

	static int volba;

	public static void main(String[] args) {

		Moznosti obrazovka = new Moznosti();

		Runnable lambda = () -> {

			obrazovka.zobraz();

		};

		SwingUtilities.invokeLater(lambda);

	}

}
