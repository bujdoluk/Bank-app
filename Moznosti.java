import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Moznosti extends JFrame {

	private String oznamenie;
	private int typUctu;
	private double vstupnyvklad;

	private int volba;
	private int volbaMoznosti;
	private int prihlasovacieIDpamat;
	private JTextField txaMeno;
	private JTextField txaPriezvisko;
	private JTextField txarodneCislo;
	private JTextField txavklad;
	private JTextField txaheslo;
	static boolean dbEmpty = true;

	public void zobraz() {

		volba = 0;

		JLabel label1 = new JLabel("Vitajte v BO aplikacii");
		label1.setFont(new Font("Arial", Font.PLAIN, 30));
		label1.setForeground(Color.green);
		label1.setBounds(530, 50, 300, 50);

		JLabel label2 = new JLabel("ZUNO Bank AG ");
		label2.setFont(new Font("Arial", Font.PLAIN, 100));
		label2.setForeground(Color.green);
		label2.setBounds(300, 200, 900, 100);

		JLabel label3 = new JLabel("Zelate si:");
		label3.setFont(new Font("Arial", Font.PLAIN, 20));
		label3.setBounds(630, 400, 200, 30);

		JRadioButton btnZalozit = new JRadioButton("Zalozit ucet");
		btnZalozit.setBounds(500, 450, 150, 50);
		JRadioButton btnPrihlasit = new JRadioButton("Prihlasit sa");
		btnPrihlasit.setBounds(750, 450, 150, 50);

		ButtonGroup G1 = new ButtonGroup();
		G1.add(btnZalozit);
		G1.add(btnPrihlasit);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(630, 550, 80, 35);

		Image logoImg = null;
		try {
			logoImg = ImageIO.read(new File("logo.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		JLabel logo = new JLabel(new ImageIcon(logoImg));
		logo.setBounds(0, 0, 200, 40);

		add(label1);
		add(label2);
		add(label3);
		add(btnZalozit);
		add(btnPrihlasit);
		add(btnOK);
		add(logo);

		ActionListener lambdaZalozUcet = (udalost) -> {
			volba = 1;
		};
		btnZalozit.addActionListener(lambdaZalozUcet);

		ActionListener lambdaPrihlasit = (udalost) -> {
			volba = 2;
		};
		btnPrihlasit.addActionListener(lambdaPrihlasit);

		ActionListener lambdaOK = (udalost1) -> {

			Moznosti moznosti = new Moznosti();
			if (volba == 1) {

				moznosti.zobrazZalozitUcet();
				setVisible(false);
			}
			if (volba == 2) {
				moznosti.zobrazPrihlasenie();
				setVisible(false);
			}
			volba = 0;
		};

		btnOK.addActionListener(lambdaOK);

		setSize(1400, 800);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

	}

	public void zobrazZalozitUcet() {

		Image logoImg = null;
		try {
			logoImg = ImageIO.read(new File("logo.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		JLabel logo = new JLabel(new ImageIcon(logoImg));
		logo.setBounds(0, 0, 200, 40);

		add(logo);

		JLabel meno = new JLabel("Meno: ");
		meno.setBounds(570, 170, 40, 20);
		add(meno);

		txaMeno = new JTextField(25);
		txaMeno.setBounds(610, 170, 150, 20);
		add(txaMeno);

		JLabel priezvisko = new JLabel("Priezvisko: ");
		priezvisko.setBounds(542, 220, 90, 20);
		add(priezvisko);

		txaPriezvisko = new JTextField(25);
		txaPriezvisko.setBounds(610, 220, 150, 20);
		add(txaPriezvisko);

		JLabel rodneCislo = new JLabel("Rodne cislo: ");
		rodneCislo.setBounds(535, 270, 90, 20);
		add(rodneCislo);

		txarodneCislo = new JTextField(25);
		txarodneCislo.setBounds(610, 270, 150, 20);
		add(txarodneCislo);

		JLabel zaujemOUcet = new JLabel("O aky ucet mate zaujem?");
		zaujemOUcet.setBounds(610, 320, 200, 20);
		add(zaujemOUcet);

		JRadioButton btnBeznyUcet = new JRadioButton("Bezny ucet");
		btnBeznyUcet.setBounds(570, 350, 100, 20);
		
		JRadioButton btnSporiaciUcet = new JRadioButton("Sporiaci ucet");
		btnSporiaciUcet.setBounds(710, 350, 100, 20);
		
		ButtonGroup G1 = new ButtonGroup();
		G1.add(btnBeznyUcet);
		G1.add(btnSporiaciUcet);
		add(btnBeznyUcet);
		add(btnSporiaciUcet);
	
		JLabel vklad = new JLabel("Vklad: ");
		vklad.setBounds(570, 400, 90, 20);
		add(vklad);

		txavklad = new JTextField(25);
		txavklad.setBounds(610, 400, 150, 20);
		add(txavklad);

		JLabel heslo = new JLabel("Heslo: ");
		heslo.setBounds(570, 450, 90, 20);
		add(heslo);

		txaheslo = new JTextField(25);
		txaheslo.setBounds(610, 450, 150, 20);
		add(txaheslo);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(640, 520, 80, 35);
		add(btnOK);
		
		JButton btnUlozDoDb = new JButton("Ulož do DB");
		btnUlozDoDb.setBounds(420, 480, 120, 30);
		add(btnUlozDoDb);
		
		JButton btnNacitajZDb = new JButton("Načitaj z DB");
		btnNacitajZDb.setBounds(560, 480, 120, 30);
		add(btnNacitajZDb);
		
		JButton btnUlozNaDisk = new JButton("Ulož na disk");
		btnUlozNaDisk.setBounds(700, 480, 120, 30);
		add(btnUlozNaDisk);
		
		JButton btnNacitaj = new JButton("Načitaj z disku");
		btnNacitaj.setBounds(840, 480, 120, 30);
		add(btnNacitaj);

		JButton btnSpat = new JButton("Spat");
		btnSpat.setBounds(1235, 700, 120, 35);
		add(btnSpat);

		JPanel pnlInfo = new JPanel();
		pnlInfo.setBounds(0, 600, 1400, 200);
		pnlInfo.setBackground(Color.white);
		add(pnlInfo);

		JTextArea txaText = new JTextArea();
		pnlInfo.add(txaText);

		ParserDat parser = new ParserDat();

		ActionListener lambdaBeznyUcet = (udalost) -> {
			txaText.setFont(new Font("Arial", Font.PLAIN, 20));
			txaText.setText("");
			txaText.append("Vklad musi byt vyssi ako 100");
			typUctu = 1;

		};

		btnBeznyUcet.addActionListener(lambdaBeznyUcet);

		ActionListener lambdaSporiaciUcet = (udalost) -> {
			txaText.setFont(new Font("Arial", Font.PLAIN, 20));
			txaText.setText("");
			txaText.append("Vklad musi byt vyssi ako 50");
			typUctu = 2;

		};

		btnSporiaciUcet.addActionListener(lambdaSporiaciUcet);

		ActionListener lambdaVypis = (udalost) -> {
			txaText.setText("");
			oznamenie = "";
			String menoString = txaMeno.getText();
			String REGEXmeno = "^[a-z,A-Z, ,.]{1,20}$";
			if (!parser.parsujData(menoString, REGEXmeno)) {

				oznamenie = oznamenie + "Nespravne zadane meno   ";
			}
			String priezviskoString = txaPriezvisko.getText();
			String REGEXpriezvisko = "^[a-z,A-Z, ,.]{1,20}$";
			if (!parser.parsujData(priezviskoString, REGEXpriezvisko)) {

				oznamenie = oznamenie + "Nespravne zadane priezvisko   ";
			}
			String rodneCisloString = txarodneCislo.getText();
			String REGEXrodneCislo = "^[0-9]{10}$";
			if (!parser.parsujData(rodneCisloString, REGEXrodneCislo)) {

				oznamenie = oznamenie + "Nespravne zadane rodne cislo  ";
			}

			String vkladString = txavklad.getText();
			String REGEXvklad = "^[0-9]{1,10}.{0,1}[0-9]{0,2}$";
			if (!parser.parsujData(vkladString, REGEXvklad))
				oznamenie = oznamenie + "Nespravne zadany vklad ";
			else {

				if (typUctu == 1) {
					vstupnyvklad = Double.parseDouble(vkladString);
					if (vstupnyvklad < 100)
						oznamenie = oznamenie + "Nespravne zadany vklad, musi byt vyssi ako 100  ";
				}

				if (typUctu == 2) {
					vstupnyvklad = Double.parseDouble(vkladString);
					if (vstupnyvklad < 50)
						oznamenie = oznamenie + "Nespravne zadany vklad, musi byt vyssi ako 50  ";
				}

			}
			String hesloString = txaheslo.getText();

			txaText.setFont(new Font("Arial", Font.PLAIN, 20));
			txaText.append(oznamenie);

			if (oznamenie.equals("")) {

				String typUctuString = Integer.toString(typUctu);
				DatabazaUctov databaza = new DatabazaUctov();
				String IDString = databaza.getID();
				int ID = Integer.parseInt(IDString);
				ID++;

				txaText.append(
						"Vasa registracia prebehla uspesne\nVas aktualny zostatok je " + vstupnyvklad + "\nVase ID je "
								+ ID + ", sluzi ako prihlasovacie meno\nPre pokracovanie stlacte tlacidlo Spat");
				IDString = Integer.toString(ID);
				databaza.ZapisDoDatabazy(IDString, typUctuString, menoString, priezviskoString, rodneCisloString,
						vkladString, hesloString);
			}

		};
		
		ActionListener ulozDb = (udalost) -> {
			Moznosti moznosti = new Moznosti();
			String s1 = txaMeno.getText();
			String s2 = txaPriezvisko.getText();
			String s3 = txarodneCislo.getText();
			String s4 = txavklad.getText();
			String s5 = txaheslo.getText();
			moznosti.queryUlozDoDb(s1, s2, s3, s4, s5);
		};
		
		btnUlozDoDb.addActionListener(ulozDb);
		
		ActionListener nacitajZDb = (udalost) -> {
			Moznosti moznosti = new Moznosti();
			//txaMeno.setText("");
			//txaPriezvisko.setText("");
			//txarodneCislo.setText("");
			//txavklad.setText("");
			//txaheslo.setText("");
			moznosti.queryNacitajZDatabazy();
		};
		
		btnNacitajZDb.addActionListener(nacitajZDb);
		

		btnOK.addActionListener(lambdaVypis);

		ActionListener lambdaSpat = (udalost) -> {
			setVisible(false);
			Moznosti moznosti = new Moznosti();
			moznosti.zobraz();
		};

		btnSpat.addActionListener(lambdaSpat);

		setResizable(false);
		setSize(1400, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

	}

	public void zobrazPrihlasenie() {

		Image logoImg = null;
		try {
			logoImg = ImageIO.read(new File("logo.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		JLabel logo = new JLabel(new ImageIcon(logoImg));
		logo.setBounds(0, 0, 200, 40);
		add(logo);

		JLabel prihlasovacieID = new JLabel("Vase ID: ");
		prihlasovacieID.setBounds(530, 270, 80, 20);
		add(prihlasovacieID);

		JTextField txaprihlasovacieID = new JTextField(25);
		txaprihlasovacieID.setBounds(620, 270, 150, 20);
		add(txaprihlasovacieID);

		JLabel heslo = new JLabel("Vase heslo: ");
		heslo.setBounds(530, 300, 80, 20);
		add(heslo);

		JTextField txaheslo = new JTextField(25);
		txaheslo.setBounds(620, 300, 150, 20);
		add(txaheslo);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(650, 350, 80, 35);
		add(btnOK);

		JButton btnSpat = new JButton("Spat");
		btnSpat.setBounds(1235, 700, 120, 35);
		add(btnSpat);

		JPanel pnlInfo = new JPanel();
		pnlInfo.setBounds(0, 600, 1400, 200);
		pnlInfo.setBackground(Color.white);
		add(pnlInfo);

		JTextArea txaText = new JTextArea();
		pnlInfo.add(txaText);

		ActionListener lambdaVypis = (udalost) -> {
			txaText.setText("");
			txaText.setFont(new Font("Arial", Font.PLAIN, 20));
			String prihlasovacieIDString = txaprihlasovacieID.getText();

			String hesloString = txaheslo.getText();

			DatabazaUctov databaza = new DatabazaUctov();
			int vysledokPrihlasenia = databaza.getVysledokPrihlasenia(prihlasovacieIDString, hesloString);

			if (vysledokPrihlasenia == 1) {
				setVisible(false);
				Moznosti moznosti = new Moznosti();
				prihlasovacieIDpamat = Integer.parseInt(prihlasovacieIDString);
				moznosti.ZobrazMoznosti(prihlasovacieIDpamat);

			}
			if (vysledokPrihlasenia == 2)
				txaText.append("Nespravne zadane heslo");
			if (vysledokPrihlasenia == 0)
				txaText.append("Nespravne zadane ID");

		};

		btnOK.addActionListener(lambdaVypis);

		;

		ActionListener lambdaSpat = (udalost) -> {
			setVisible(false);
			Moznosti moznosti = new Moznosti();
			moznosti.zobraz();
		};

		btnSpat.addActionListener(lambdaSpat);

		setResizable(false);
		setSize(1400, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

	}

	public void ZobrazMoznosti(int prihlasovacieID) {

		prihlasovacieIDpamat = prihlasovacieID;

		volbaMoznosti = 0;

		Image logoImg = null;
		try {
			logoImg = ImageIO.read(new File("logo.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		JLabel logo = new JLabel(new ImageIcon(logoImg));
		logo.setBounds(0, 0, 200, 40);
		add(logo);

		JButton btnOdhlasenie = new JButton("Odhlasenie");
		btnOdhlasenie.setBounds(1235, 700, 120, 35);
		add(btnOdhlasenie);

		JLabel prihlasenie = new JLabel("Vase prihlasenie prebehlo uspesne");
		prihlasenie.setFont(new Font("Arial", Font.PLAIN, 30));
		prihlasenie.setBounds(450, 150, 600, 50);
		add(prihlasenie);

		JLabel vyber = new JLabel("Vyberte jednu z nasledovnych moznosti");
		vyber.setBounds(560, 250, 250, 20);
		add(vyber);

		JRadioButton btnVklad = new JRadioButton("Vytvorit vklad");
		btnVklad.setBounds(630, 300, 200, 20);
		add(btnVklad);

		JRadioButton btnVyber = new JRadioButton("Vytvorit vyber");
		btnVyber.setBounds(630, 350, 200, 20);
		add(btnVyber);

		JRadioButton btnVypis = new JRadioButton("Vytvorit vypis");
		btnVypis.setBounds(630, 400, 200, 20);
		add(btnVypis);

		ButtonGroup G1 = new ButtonGroup();
		G1.add(btnVklad);
		G1.add(btnVyber);
		G1.add(btnVypis);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(640, 450, 80, 35);
		add(btnOK);

		ActionListener lambdaVklad = (udalost) -> {
			volbaMoznosti = 1;
		};

		btnVklad.addActionListener(lambdaVklad);

		ActionListener lambdaVyber = (udalost) -> {
			volbaMoznosti = 2;
		};

		btnVyber.addActionListener(lambdaVyber);

		ActionListener lambdaVypis = (udalost) -> {
			volbaMoznosti = 3;
		};

		btnVypis.addActionListener(lambdaVypis);

		ActionListener lambdaOK = (udalost) -> {
			Moznosti moznosti = new Moznosti();
			setVisible(false);
			if (volbaMoznosti == 1)
				moznosti.zobrazVklad(prihlasovacieIDpamat);

			if (volbaMoznosti == 2)
				moznosti.zobrazVyber(prihlasovacieIDpamat);

			if (volbaMoznosti == 3)
				moznosti.zobrazVypis(prihlasovacieIDpamat);

		};
		btnOK.addActionListener(lambdaOK);

		ActionListener lambdaOdhlasenie = (udalost) -> {
			setVisible(false);
			Moznosti moznosti = new Moznosti();
			moznosti.zobraz();
		};

		btnOdhlasenie.addActionListener(lambdaOdhlasenie);

		setResizable(false);
		setSize(1400, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

	}

	public void zobrazVklad(int prihlasovacieID) {

		prihlasovacieIDpamat = prihlasovacieID;

		Image logoImg = null;
		try {
			logoImg = ImageIO.read(new File("logo.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		JLabel logo = new JLabel(new ImageIcon(logoImg));
		logo.setBounds(0, 0, 200, 40);
		add(logo);

		JLabel vklad = new JLabel("Vas vklad: ");
		vklad.setBounds(550, 270, 80, 20);
		add(vklad);

		JTextField txavklad = new JTextField(25);
		txavklad.setBounds(620, 270, 150, 20);
		add(txavklad);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(650, 350, 80, 35);
		add(btnOK);

		JButton btnSpat = new JButton("Spat");
		btnSpat.setBounds(1235, 700, 120, 35);
		add(btnSpat);

		JPanel pnlInfo = new JPanel();
		pnlInfo.setBounds(0, 600, 1400, 200);
		pnlInfo.setBackground(Color.white);
		add(pnlInfo);

		JTextArea txaText = new JTextArea();
		pnlInfo.add(txaText);

		ActionListener lambdaVypis = (udalost) -> {
			ParserDat parser = new ParserDat();
			txaText.setFont(new Font("Arial", Font.PLAIN, 20));
			String vkladString = txavklad.getText();
			String REGEXvklad = "^[0-9]{1,10}.{0,1}[0-9]{0,2}$";
			if (!parser.parsujData(vkladString, REGEXvklad)) {
				txaText.append("Nespravne zadany vklad");

			} else {
				DatabazaUctov databaza = new DatabazaUctov();
				double vkladDouble = Double.parseDouble(vkladString);
				String IDString = Integer.toString(prihlasovacieIDpamat);
				double zostatok = databaza.zapisVklad(IDString, vkladDouble);
				txaText.setText("Vas aktualny zostatok je " + zostatok + "\nPre pokracovanie stlacte tlacidlo Spat");
			}

		};

		btnOK.addActionListener(lambdaVypis);

		ActionListener lambdaSpat = (udalost) -> {
			Moznosti moznosti = new Moznosti();
			moznosti.ZobrazMoznosti(prihlasovacieIDpamat);
		};

		btnSpat.addActionListener(lambdaSpat);

		setResizable(false);
		setSize(1400, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

	}

	public void zobrazVyber(int prihlasovacieID) {

		prihlasovacieIDpamat = prihlasovacieID;

		Image logoImg = null;
		try {
			logoImg = ImageIO.read(new File("logo.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		JLabel logo = new JLabel(new ImageIcon(logoImg));
		logo.setBounds(0, 0, 200, 40);
		add(logo);

		JLabel vyber = new JLabel("Vas vyber: ");
		vyber.setBounds(550, 270, 80, 20);
		add(vyber);

		JTextField txavyber = new JTextField(25);
		txavyber.setBounds(620, 270, 150, 20);
		add(txavyber);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(650, 350, 80, 35);
		add(btnOK);

		JButton btnSpat = new JButton("Spat");
		btnSpat.setBounds(1235, 700, 120, 35);
		add(btnSpat);

		JPanel pnlInfo = new JPanel();
		pnlInfo.setBounds(0, 600, 1400, 200);
		pnlInfo.setBackground(Color.white);
		add(pnlInfo);

		JTextArea txaText = new JTextArea();
		pnlInfo.add(txaText);

		ActionListener lambdaVypis = (udalost) -> {
			txaText.setFont(new Font("Arial", Font.PLAIN, 20));
			ParserDat parser = new ParserDat();
			String vyberString = txavyber.getText();
			String REGEXvyber = "^[0-9]{1,10}.{0,1}[0-9]{0,2}$";
			if (!parser.parsujData(vyberString, REGEXvyber)) {

				txaText.append("Nespravne zadany vyber");

			} else {
				DatabazaUctov databaza = new DatabazaUctov();
				double vyberDouble = Double.parseDouble(vyberString);
				String IDString = Integer.toString(prihlasovacieIDpamat);
				double zostatok = databaza.zapisVyber(IDString, vyberDouble);
				if (zostatok > 0) {
					txaText.setText(
							"Vas aktualny zostatok je " + zostatok + "\nPre pokracovanie stlacte tlacidlo Spat");
				} else
					txaText.setText("Transakciu nemozno vykonat, na ucte nie je dostatok financnych prostriedkov ");
			}

		};

		btnOK.addActionListener(lambdaVypis);

		ActionListener lambdaSpat = (udalost) -> {
			setVisible(false);
			Moznosti moznosti = new Moznosti();
			moznosti.ZobrazMoznosti(prihlasovacieIDpamat);
		};

		btnSpat.addActionListener(lambdaSpat);

		setResizable(false);
		setSize(1400, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

	}

	public void zobrazVypis(int prihlasovacieID) {
		prihlasovacieIDpamat = prihlasovacieID;
		String IDString = Integer.toString(prihlasovacieIDpamat);

		Image logoImg = null;
		try {
			logoImg = ImageIO.read(new File("logo.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		JLabel logo = new JLabel(new ImageIcon(logoImg));
		logo.setBounds(0, 0, 200, 40);
		add(logo);

		JLabel info = new JLabel("Aktualne informacie o Vasom ucte:");
		info.setFont(new Font("Arial", Font.PLAIN, 20));
		info.setBounds(500, 200, 400, 30);
		add(info);

		DatabazaUctov databaza = new DatabazaUctov();

		JLabel meno = new JLabel("Meno: ");
		meno.setBounds(580, 300, 200, 30);
		add(meno);

		JLabel menoUcet = new JLabel(databaza.getMeno(IDString));
		menoUcet.setBounds(650, 300, 200, 30);
		add(menoUcet);

		JLabel priezvisko = new JLabel("Priezvisko: ");
		priezvisko.setBounds(550, 350, 200, 30);
		add(priezvisko);

		JLabel priezviskoUcet = new JLabel(databaza.getpriezvisko(IDString));
		priezviskoUcet.setBounds(650, 350, 200, 30);
		add(priezviskoUcet);

		JLabel rodneCislo = new JLabel("Rodne cislo: ");
		rodneCislo.setBounds(542, 400, 200, 30);
		add(rodneCislo);

		JLabel rodneCisloUcet = new JLabel(databaza.getrodnecislo(IDString));
		rodneCisloUcet.setBounds(650, 400, 200, 30);
		add(rodneCisloUcet);

		JLabel ID = new JLabel("ID: ");
		ID.setBounds(595, 450, 200, 30);
		add(ID);

		JLabel IDUcet = new JLabel(databaza.getID(IDString));
		IDUcet.setBounds(650, 450, 200, 30);
		add(IDUcet);

		JLabel typUctu = new JLabel("Typ uctu: ");
		typUctu.setBounds(560, 500, 200, 30);
		add(typUctu);

		JLabel typUctucet = new JLabel(databaza.getTypUctu(IDString));
		typUctucet.setBounds(650, 500, 200, 30);
		add(typUctucet);

		JLabel zostatok = new JLabel("Aktualny zostatok: ");
		zostatok.setBounds(510, 550, 200, 30);
		add(zostatok);

		JLabel zostatokUcet = new JLabel(databaza.getzostatok(IDString));
		zostatokUcet.setBounds(650, 550, 200, 30);
		add(zostatokUcet);

		JButton btnSpat = new JButton("Spat");
		btnSpat.setBounds(1235, 700, 120, 35);
		add(btnSpat);

		ActionListener lambdaSpat = (udalost) -> {
			setVisible(false);
			Moznosti moznosti = new Moznosti();
			moznosti.ZobrazMoznosti(prihlasovacieIDpamat);
		};

		btnSpat.addActionListener(lambdaSpat);

		setResizable(false);
		setSize(1400, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

	}
	
	public void queryUlozDoDb(String m, String p, String r, String v, String h) {
		String url = "jdbc:mysql://localhost:3306/projekt?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
		String user = "root";
		String password = "MARK949494tremonti";
		
		try (
				Connection spojenie = DriverManager.getConnection(url, user, password);
				Statement prikaz = spojenie.createStatement();
		){
				String sqlInsert = "INSERT INTO zakaznik (meno, priezvisko, rodneCislo, vklad, heslo) VALUES ('" + m + "','" + p + "','" + r + "','" + v + "','" + h + "')";
				System.out.println("SQL prikaz: " + sqlInsert + "\n"); // pre ucely debugovania
			
				int vysledky = prikaz.executeUpdate(sqlInsert);
				
				prikaz.close();
				spojenie.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void queryNacitajZDatabazy() {
		String url = "jdbc:mysql://localhost:3306/projekt?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
		String user = "root";
		String password = "MARK949494tremonti";
		
		try (
				Connection spojenie = DriverManager.getConnection(url, user, password);
				Statement prikaz = spojenie.createStatement();
		){
				String sqlSelect = "SELECT * FROM zakaznik";
				System.out.println("SQL prikaz: " + sqlSelect + "\n"); // pre ucely debugovania
		
				ResultSet vysledky = prikaz.executeQuery(sqlSelect);
				
				while(vysledky.next()) {
					String m = vysledky.getString("meno");
					String p = vysledky.getString("priezvisko");
					String r = vysledky.getString("rodneCislo");
					String v = vysledky.getString("vklad");
					String h = vysledky.getString("heslo");
					
					txaMeno.setText(m);
					txaPriezvisko.setText(p);
					txarodneCislo.setText(r);
					txavklad.setText(v);
					txaheslo.setText(h);
				}
			
				prikaz.close();
				spojenie.close();
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	}

}
