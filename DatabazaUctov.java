import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DatabazaUctov {

	public static void main(String[] args) {

	}

	public void ZapisDoDatabazy(String ID1, String typUctu1, String meno1, String priezvisko1, String rodneCislo1,
			String zostatok1, String heslo1) {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document dokument = dBuilder.parse("data.xml");

			Element bankoveUcty = dokument.getDocumentElement();

			Element ucet = dokument.createElement("ucet");
			bankoveUcty.appendChild(ucet);

			Element ID = dokument.createElement("ID");
			ucet.appendChild(ID);
			ID.setTextContent(ID1);

			Element typUctu = dokument.createElement("typUctu");
			ucet.appendChild(typUctu);
			typUctu.setTextContent(typUctu1);

			Element meno = dokument.createElement("meno");
			ucet.appendChild(meno);
			meno.setTextContent(meno1);

			Element priezvisko = dokument.createElement("priezvisko");
			ucet.appendChild(priezvisko);
			priezvisko.setTextContent(priezvisko1);

			Element rodneCislo = dokument.createElement("rodneCislo");
			ucet.appendChild(rodneCislo);
			rodneCislo.setTextContent(rodneCislo1);

			Element zostatok = dokument.createElement("zostatok");
			ucet.appendChild(zostatok);
			zostatok.setTextContent(zostatok1);

			Element heslo = dokument.createElement("heslo");
			ucet.appendChild(heslo);
			heslo.setTextContent(heslo1);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			DOMSource zdroj = new DOMSource(dokument);
			StreamResult vysledok = new StreamResult("data.xml");
			transformer.transform(zdroj, vysledok);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getID() {

		String ID = "";

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document dokument = dBuilder.parse("data.xml");

			NodeList nList = dokument.getElementsByTagName("ucet");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					ID = eElement.getElementsByTagName("ID").item(0).getTextContent();

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ID;

	}

	public int getVysledokPrihlasenia(String ID, String heslo) {

		int vysledokPrihlasenia = 0;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document dokument = dBuilder.parse("data.xml");

			NodeList nList = dokument.getElementsByTagName("ucet");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if (ID.equals(eElement.getElementsByTagName("ID").item(0).getTextContent())) {
						if (heslo.equals(eElement.getElementsByTagName("heslo").item(0).getTextContent()))
							vysledokPrihlasenia = 1;
						else
							vysledokPrihlasenia = 2;
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vysledokPrihlasenia;

	}

	public double zapisVklad(String ID, double vklad) {

		double zostatok = 0;
		String zostatokString;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document dokument = dBuilder.parse("data.xml");

			NodeList nList = dokument.getElementsByTagName("ucet");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if (ID.equals(eElement.getElementsByTagName("ID").item(0).getTextContent())) {

						zostatokString = eElement.getElementsByTagName("zostatok").item(0).getTextContent();
						zostatok = Double.parseDouble(zostatokString);
						zostatok = zostatok + vklad;
						zostatokString = Double.toString(zostatok);
						eElement.getElementsByTagName("zostatok").item(0).setTextContent(zostatokString);
					}
				}
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			DOMSource zdroj = new DOMSource(dokument);
			StreamResult vysledok = new StreamResult("data.xml");
			transformer.transform(zdroj, vysledok);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return zostatok;

	}

	public double zapisVyber(String ID, double vyber) {
		double zostatok = 0;
		String zostatokString;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document dokument = dBuilder.parse("data.xml");

			NodeList nList = dokument.getElementsByTagName("ucet");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if (ID.equals(eElement.getElementsByTagName("ID").item(0).getTextContent())) {

						zostatokString = eElement.getElementsByTagName("zostatok").item(0).getTextContent();
						zostatok = Double.parseDouble(zostatokString);
						zostatok = zostatok - vyber;
						if (zostatok > 0) {
							zostatokString = Double.toString(zostatok);
							eElement.getElementsByTagName("zostatok").item(0).setTextContent(zostatokString);
						}
					}
				}
			}

			if (zostatok > 0) {

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();

				DOMSource zdroj = new DOMSource(dokument);
				StreamResult vysledok = new StreamResult("data.xml");
				transformer.transform(zdroj, vysledok);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return zostatok;
	}

	public String getMeno(String ID) {

		String menoString = null;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document dokument = dBuilder.parse("data.xml");

			NodeList nList = dokument.getElementsByTagName("ucet");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if (ID.equals(eElement.getElementsByTagName("ID").item(0).getTextContent())) {

						menoString = eElement.getElementsByTagName("meno").item(0).getTextContent();

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return menoString;
	}

	public String getpriezvisko(String ID) {
		String priezviskoString = null;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document dokument = dBuilder.parse("data.xml");

			NodeList nList = dokument.getElementsByTagName("ucet");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if (ID.equals(eElement.getElementsByTagName("ID").item(0).getTextContent())) {

						priezviskoString = eElement.getElementsByTagName("priezvisko").item(0).getTextContent();

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return priezviskoString;
	}

	public String getrodnecislo(String ID) {
		String rodneCisloString = null;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document dokument = dBuilder.parse("data.xml");

			NodeList nList = dokument.getElementsByTagName("ucet");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if (ID.equals(eElement.getElementsByTagName("ID").item(0).getTextContent())) {

						rodneCisloString = eElement.getElementsByTagName("rodneCislo").item(0).getTextContent();

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rodneCisloString;
	}

	public String getID(String ID) {
		String IDString = null;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document dokument = dBuilder.parse("data.xml");

			NodeList nList = dokument.getElementsByTagName("ucet");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if (ID.equals(eElement.getElementsByTagName("ID").item(0).getTextContent())) {

						IDString = eElement.getElementsByTagName("ID").item(0).getTextContent();

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return IDString;

	}

	public String getzostatok(String ID) {
		String zostatokString = null;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document dokument = dBuilder.parse("data.xml");

			NodeList nList = dokument.getElementsByTagName("ucet");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if (ID.equals(eElement.getElementsByTagName("ID").item(0).getTextContent())) {

						zostatokString = eElement.getElementsByTagName("zostatok").item(0).getTextContent();

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return zostatokString;

	}

	public String getTypUctu(String ID) {
		String typUctuString = null;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document dokument = dBuilder.parse("data.xml");

			NodeList nList = dokument.getElementsByTagName("ucet");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if (ID.equals(eElement.getElementsByTagName("ID").item(0).getTextContent())) {

						typUctuString = eElement.getElementsByTagName("typUctu").item(0).getTextContent();

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (typUctuString.equals("1"))
			typUctuString = "bezny";
		if (typUctuString.equals("2"))
			typUctuString = "sporiaci";
		return typUctuString;

	}

}
