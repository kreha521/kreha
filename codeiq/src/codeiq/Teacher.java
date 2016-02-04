package codeiq;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Teacher {
	public static void main(String[] args) throws IOException {
		List<ExamResult> results = toExamResult(readInput(System.in));

		Collections.sort(results,
			Comparator.comparingInt(ExamResult::getEnglish)
				.thenComparingInt(ExamResult::getJapanese)
				.thenComparingInt(ExamResult::getMath)
				.reversed());

		System.out.println(ExamResult.getHeader());
		results.forEach(System.out::println);
	}

	private static List<ExamResult> toExamResult(List<String> lines) throws IOException {
		List<ExamResult> results = new ArrayList<ExamResult>();

		boolean isFirstLine = true;

		for (final String line : lines) {
			ExamResult result = new ExamResult();

			if (isFirstLine) {
				ExamResult.setHeader(line);
				isFirstLine = false;
				continue;
			}

			String[] cols = line.split(",");
			int colsLen = cols.length;

			for (int i = 0; i < colsLen; i++) {
				switch (i) {
					case 0:
						result.setName(cols[i]);
						break;
					case 1:
						result.setEnglish(Integer.parseInt(cols[i]));
						break;
					case 2:
						result.setJapanese(Integer.parseInt(cols[i]));
						break;
					case 3:
						result.setMath(Integer.parseInt(cols[i]));
						break;
					default:
						break;
				}
			}

			results.add(result);
		}

		return results;
	}

	private static List<String> readInput(InputStream is) throws IOException {
		List<String> inputs = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
			br.lines().forEach(l -> { inputs.add(l); });
		}
		return inputs;
	}
}

class ExamResult {
	private static String header;
	private String name;
	private int english;
	private int japanese;
	private int math;

	public static String getHeader() {
		return header;
	}
	public static void setHeader(String header) {
		ExamResult.header = header;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getJapanese() {
		return japanese;
	}
	public void setJapanese(int japanese) {
		this.japanese = japanese;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append(name)
			.append(",").append(english)
			.append(",").append(japanese)
			.append(",").append(math)
		.toString();
	}
}