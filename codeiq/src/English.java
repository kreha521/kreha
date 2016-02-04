


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class English extends Language {
	public static void main(String[] args) throws IOException {
		List<String> lines = readInput(System.in);

		Language sentence = new English();
		lines.forEach(sentence::printSentences);
	}

	@Override
	List<String> splitSentences(String sentences) {
		List<String> eachSentences = new ArrayList<String>();

		boolean isOnlyNumSoFar = false;
		boolean wasTitle  = false;
		boolean wasExclamationMark  = false;
		boolean wasPeriod  = false;

		StringBuilder sentence = new StringBuilder();

		int len = sentences.length();
		for (int i = 0; i < len; i++) {
			char c = sentences.charAt(i);

			if (' ' == c) {
				if (wasExclamationMark | (!wasTitle && !isOnlyNumSoFar && wasPeriod)) {
					sentence.append(c);
					eachSentences.add(sentence.toString());
					sentence = new StringBuilder();
					continue;
				}
			}

			if ('0' <= c && c <= '9') {
				wasTitle = false;
				isOnlyNumSoFar = true;
				wasExclamationMark = false;
				wasPeriod = false;

				sentence.append(c);
			} else if ('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z') {
				wasTitle = false;
				wasExclamationMark = false;
				wasPeriod = false;
				isOnlyNumSoFar = false;

				sentence.append(c);
			} else if (' ' == c) {
				wasTitle = false;
				wasExclamationMark = false;
				wasPeriod = wasTitle && false;
				isOnlyNumSoFar = false;

				sentence.append(c);
			} else if (c == '!' || c == '?') {
				wasTitle = false;
				wasExclamationMark = true;
				wasPeriod = false;
				isOnlyNumSoFar = false;

				sentence.append(c);
			} else if (c == '.') {
				wasPeriod = true;
				boolean endWith = StringUtil.endWithAny(sentence.toString(), "Mr", "Ms", "Mrs", "Mt");
				endWith = (endWith && i + 1 < len && sentences.charAt(i + 1) == ' ');
				wasTitle = endWith;
				wasExclamationMark = false;

				sentence.append(c);
			} else {
				wasTitle = false;
				wasExclamationMark = false;
				wasPeriod = false;
				isOnlyNumSoFar = false;

				sentence.append(c);
			}
		}

		eachSentences.add(sentence.toString());

		return eachSentences;
	}

	private static List<String> readInput(InputStream is) throws IOException {
		List<String> inputs = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
			br.lines().forEach(l -> { inputs.add(l); });
		}
		return inputs;
	}
}

abstract class Language {
	public void printSentences(String sentences) {
		splitSentences(sentences).forEach(System.out::println);
	}

	abstract List<String> splitSentences(String sentences);
}

class StringUtil {
	public static boolean endWithAny(String target, String...keywords) {
		for (final String keyword : keywords) {
			if (target.endsWith(keyword)) {
				return true;
			}
		}
		return false;
	}
}