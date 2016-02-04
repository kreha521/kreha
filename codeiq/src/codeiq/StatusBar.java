package codeiq;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class StatusBar {
	public static void main(String[] args) throws IOException {
		String[] argsIn = readInput(System.in).get(0).split(",");
		float all = Float.parseFloat(argsIn[0]);
		float current = Float.parseFloat(argsIn[1]);
		String barStr = argsIn[2];

		new StatusBar().printProgressBar(all, current, barStr);
	}

	public void printProgressBar(float all, float current, String barStr) {
		System.out.print(buildProgressBarStr(all, current, barStr));
	}

	private String buildProgressBarStr(float all, float current, String barStr) {
		if (current > all) {
			return "invalid";
		}

		int progress = (int) Math.floor((current / all) * 100);
		StringBuilder progressBar = new StringBuilder();
		for (int i = 0; i < progress; i++) {
			progressBar.append(barStr);
		}
		return progressBar.toString();
	}

	private static List<String> readInput(InputStream is) throws IOException {
		List<String> inputs = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
			br.lines().forEach(l -> { inputs.add(l); });
		}
		return inputs;
	}
}
