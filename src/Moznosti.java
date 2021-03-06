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

		JLabel label1 = new JLabel("Vitajte v bankovej aplikácií");
		label1.setFont(new Font("Arial", Font.PLAIN, 30));
		label1.setForeground(Color.blue);
		label1.setBounds(500, 100, 400, 50);

		JLabel label2 = new JLabel("Tatra Banka ");
		label2.setFont(new Font("Arial", Font.PLAIN, 100));
		label2.setForeground(Color.blue);
		label2.setBounds(400, 200, 900, 100);

		JLabel label3 = new JLabel("Želáte si:");
		label3.setFont(new Font("Arial", Font.PLAIN, 20));
		label3.setBounds(630, 400, 200, 30);

		JRadioButton btnZalozit = new JRadioButton("Založiť účet");
		btnZalozit.setBounds(500, 450, 150, 50);
		JRadioButton btnPrihlasit = new JRadioButton("Prihlásiť sa");
		btnPrihlasit.setBounds(750, 450, 150, 50);

		ButtonGroup G1 = new ButtonGroup();
		G1.add(btnZalozit);
		G1.add(btnPrihlasit);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(630, 550, 80, 35);

		Image logoImg = null;
		try {
			logoImg = ImageIO.read(new File("tbanka.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		JLabel logo = new JLabel(new ImageIcon(logoImg));
		logo.setBounds(0, 0, 259, 235);

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
			logoImg = ImageIO.read(new File("tbanka.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		JLabel logo = new JLabel(new ImageIcon(logoImg));
		logo.setBounds(0, 0, 259, 235);

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

		JLabel rodneCislo = new JLabel("Rodné číslo: ");
		rodneCislo.setBounds(535, 270, 90, 20);
		add(rodneCislo);

		txarodneCislo = new JTextField(25);
		txarodneCislo.setBounds(610, 270, 150, 20);
		add(txarodneCislo);

		JLabel zaujemOUcet = new JLabel("O aký účet máte záujem?");
		zaujemOUcet.setBounds(610, 320, 200, 20);
		add(zaujemOUcet);

		JRadioButton btnBeznyUcet = new JRadioButton("Bežný účet");
		btnBeznyUcet.setBounds(570, 350, 100, 20);
		
		JRadioButton btnSporiaciUcet = new JRadioButton("Sporiaci účet");
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
		
		JButton btnNacitajZDb = new JButton("Ulož do DB");
		btnNacitajZDb.setBounds(560, 480, 120, 30);
		add(btnNacitajZDb);
		
		JButton btnUlozNaDisk = new JButton("Načítaj z DB");
		btnUlozNaDisk.setBounds(700, 480, 120, 30);
		add(btnUlozNaDisk);
		
		JButton btnSpat = new JButton("Spať");
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
			txaText.append("Vklad musí byť vyšší ako 100");
			typUctu = 1;

		};

		btnBeznyUcet.addActionListener(lambdaBeznyUcet);

		ActionListener lambdaSporiaciUcet = (udalost) -> {
			txaText.setFont(new Font("Arial", Font.PLAIN, 20));
			txaText.setText("");
			txaText.append("Vklad musí byť vyšší ako 50");
			typUctu = 2;

		};

		btnSporiaciUcet.addActionListener(lambdaSporiaciUcet);

		ActionListener lambdaVypis = (udalost) -> {
			txaText.setText("");
			oznamenie = "";
			String menoString = txaMeno.getText();
			String REGEXmeno = "^[a-z,A-Z, ,.]{1,20}$";
			if (!parser.parsujData(menoString, REGEXmeno)) {

				oznamenie = oznamenie + "Nesprávne zadané meno   ";
			}
			String priezviskoString = txaPriezvisko.getText();
			String REGEXpriezvisko = "^[a-z,A-Z, ,.]{1,20}$";
			if (!parser.parsujData(priezviskoString, REGEXpriezvisko)) {

				oznamenie = oznamenie + "Nesprávne zadané priezvisko   ";
			}
			String rodneCisloString = txarodneCislo.getText();
			String REGEXrodneCislo = "^[0-9]{10}$";
			if (!parser.parsujData(rodneCisloString, REGEXrodneCislo)) {

				oznamenie = oznamenie + "Nesprávne zadané rodné číslo  ";
			}

			String vkladString = txavklad.getText();
			String REGEXvklad = "^[0-9]{1,10}.{0,1}[0-9]{0,2}$";
			if (!parser.parsujData(vkladString, REGEXvklad))
				oznamenie = oznamenie + "Nesprávne zadaný vklad ";
			else {

				if (typUctu == 1) {
					vstupnyvklad = Double.parseDouble(vkladString);
					if (vstupnyvklad < 100)
						oznamenie = oznamenie + "Nesprávne zadaný vklad, musí byť vyšší ako 100  ";
				}

				if (typUctu == 2) {
					vstupnyvklad = Double.parseDouble(vkladString);
					if (vstupnyvklad < 50)
						oznamenie = oznamenie + "Nesprávne zadaný vklad, musí byť vyšší ako 50  ";
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
						"Vaša registrácia prebehla úspešne\nVas aktuálny zostatok je " + vstupnyvklad + "\nVaše ID je "
								+ ID + ", slúži ako prihlasovacie meno\nPre pokračovanie stlačte tlačidlo Spať");
				IDString = Integer.toString(ID);
				databaza.ZapisDoDatabazy(IDString, typUctuString, menoString, priezviskoString, rodneCisloString,
						vkladString, hesloString);
			}

		};
		
		/*
		 * ActionListener ulozDb = (udalost) -> { Moznosti moznosti = new Moznosti();
		 * String s1 = txaMeno.getText(); String s2 = txaPriezvisko.getText(); String s3
		 * = txarodneCislo.getText(); String s4 = txavklad.getText(); String s5 =
		 * txaheslo.getText(); moznosti.queryUlozDoDb(s1, s2, s3, s4, s5); };
		 */

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
			logoImg = ImageIO.read(new File("tbanka.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		JLabel logo = new JLabel(new ImageIcon(logoImg));
		logo.setBounds(0, 0, 259, 235);
		add(logo);

		JLabel prihlasovacieID = new JLabel("Vaše ID: ");
		prihlasovacieID.setBounds(530, 270, 80, 20);
		add(prihlasovacieID);

		JTextField txaprihlasovacieID = new JTextField(25);
		txaprihlasovacieID.setBounds(620, 270, 150, 20);
		add(txaprihlasovacieID);

		JLabel heslo = new JLabel("Vaše heslo: ");
		heslo.setBounds(530, 300, 80, 20);
		add(heslo);

		JTextField txaheslo = new JTextField(25);
		txaheslo.setBounds(620, 300, 150, 20);
		add(txaheslo);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(650, 350, 80, 35);
		add(btnOK);

		JButton btnSpat = new JButton("Spať");
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
				txaText.append("Nesprávne zadané heslo");
			if (vysledokPrihlasenia == 0)
				txaText.append("Nesprávne zadané ID");

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
			logoImg = ImageIO.read(new File("tbanka.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		JLabel logo = new JLabel(new ImageIcon(logoImg));
		logo.setBounds(0, 0, 259, 235);
		add(logo);

		JButton btnOdhlasenie = new JButton("Odhlásenie");
		btnOdhlasenie.setBounds(1235, 700, 120, 35);
		add(btnOdhlasenie);

		JLabel prihlasenie = new JLabel("Vaše prihlásenie prebehlo úspešne");
		prihlasenie.setFont(new Font("Arial", Font.PLAIN, 30));
		prihlasenie.setBounds(450, 150, 600, 50);
		add(prihlasenie);

		JLabel vyber = new JLabel("Vyberte jednu z nasledovných možností");
		vyber.setBounds(560, 250, 250, 20);
		add(vyber);

		JRadioButton btnVklad = new JRadioButton("Vytvoriť vklad");
		btnVklad.setBounds(630, 300, 200, 20);
		add(btnVklad);

		JRadioButton btnVyber = new JRadioButton("Vytvoriť výber");
		btnVyber.setBounds(630, 350, 200, 20);
		add(btnVyber);

		JRadioButton btnVypis = new JRadioButton("Vytvoriť výpis");
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
			logoImg = ImageIO.read(new File("tbanka.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		JLabel logo = new JLabel(new ImageIcon(logoImg));
		logo.setBounds(0, 0, 259, 235);
		add(logo);

		JLabel vklad = new JLabel("Váš vklad: ");
		vklad.setBounds(550, 270, 80, 20);
		add(vklad);

		JTextField txavklad = new JTextField(25);
		txavklad.setBounds(620, 270, 150, 20);
		add(txavklad);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(650, 350, 80, 35);
		add(btnOK);

		JButton btnSpat = new JButton("Spať");
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
				txaText.append("Nesprávne zadaný vklad");

			} else {
				DatabazaUctov databaza = new DatabazaUctov();
				double vkladDouble = Double.parseDouble(vkladString);
				String IDString = Integer.toString(prihlasovacieIDpamat);
				double zostatok = databaza.zapisVklad(IDString, vkladDouble);
				txaText.setText("Váš aktuálny zostatok je " + zostatok + "\nPre pokračovanie stlačte tlačidlo Spať");
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
			logoImg = ImageIO.read(new File("tbanka.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		JLabel logo = new JLabel(new ImageIcon(logoImg));
		logo.setBounds(0, 0, 259, 235);
		add(logo);

		JLabel vyber = new JLabel("Váš výber: ");
		vyber.setBounds(550, 270, 80, 20);
		add(vyber);

		JTextField txavyber = new JTextField(25);
		txavyber.setBounds(620, 270, 150, 20);
		add(txavyber);

		JButton btnOK = new JButton("OK");
		btnOK.setBounds(650, 350, 80, 35);
		add(btnOK);

		JButton btnSpat = new JButton("Spať");
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

				txaText.append("Nesprávne zadaný výber");

			} else {
				DatabazaUctov databaza = new DatabazaUctov();
				double vyberDouble = Double.parseDouble(vyberString);
				String IDString = Integer.toString(prihlasovacieIDpamat);
				double zostatok = databaza.zapisVyber(IDString, vyberDouble);
				if (zostatok > 0) {
					txaText.setText(
							"Váš aktuálny zostatok je " + zostatok + "\nPre pokračovanie stlačte tlačidlo Spať");
				} else
					txaText.setText("Transakciu nemožno vykonať, na účte nie je dostatok finančných prostriedkov ");
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
			logoImg = ImageIO.read(new File("tbanka.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		JLabel logo = new JLabel(new ImageIcon(logoImg));
		logo.setBounds(0, 0, 259, 235);
		add(logo);

		JLabel info = new JLabel("Aktuálne informácie o Vašom účte:");
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

		JLabel rodneCislo = new JLabel("Rodné číslo: ");
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

		JLabel typUctu = new JLabel("Typ účtu: ");
		typUctu.setBounds(560, 500, 200, 30);
		add(typUctu);

		JLabel typUctucet = new JLabel(databaza.getTypUctu(IDString));
		typUctucet.setBounds(650, 500, 200, 30);
		add(typUctucet);

		JLabel zostatok = new JLabel("Aktuálny zostatok: ");
		zostatok.setBounds(510, 550, 200, 30);
		add(zostatok);

		JLabel zostatokUcet = new JLabel(databaza.getzostatok(IDString));
		zostatokUcet.setBounds(650, 550, 200, 30);
		add(zostatokUcet);

		JButton btnSpat = new JButton("Spať");
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
		String url = "";
		String user = "";
		String password = "";
		
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
		String url = "";
		String user = "";
		String password = "";
		
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
