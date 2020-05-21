import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserDat {

	public static final boolean parsujData(String vstupneData, String regex) {

		if (vstupneData == null || vstupneData.isEmpty() || regex == null || regex.isEmpty()) {
			return false;
		}

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(vstupneData);

		return vstupneData.matches(regex);
	}
}
