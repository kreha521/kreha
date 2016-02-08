


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/********************
 * A*だの、ダイクストラだの、経路探索について思い出し中。。。
 ********************/
class MonHun {
	public static void main(String[] args) throws IOException {
		int[][] fieldMap = toFieldMap(readInput(System.in));
		List<Integer[]> bossPos = findBoss(fieldMap);
		System.out.print("ちまちまテスト中（まずはボス探しから）  ");
		bossPos.forEach(pos -> { System.out.print(pos[0] + " " + pos[1] + "@"); });
	}

	private static List<Integer[]> findBoss(int[][] fieldMap) {
		int h = fieldMap.length;
		int w = fieldMap[0].length;

		Map<Integer, List<Integer[]>> bossPoses = new TreeMap<Integer, List<Integer[]>>();
		int maxPos = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				Integer pos = fieldMap[i][j];
				if (bossPoses.containsKey(pos)) {
					bossPoses.get(pos).add(new Integer[]{i, j});
				} else {
					List<Integer[]> poses = new ArrayList<Integer[]>();
					poses.add(new Integer[]{i, j});
					bossPoses.put(pos, poses);
				}
				if (pos > maxPos) {
					maxPos = pos;
				}
			}
		}

		return bossPoses.get(maxPos);
	}

	private static int[][] toFieldMap(List<String> inputs) {
		String[] hw = inputs.get(0).split(" ");
		int h = Integer.parseInt(hw[0]);
		int w = Integer.parseInt(hw[1]);

		int[][] fieldMap = new int[h][w];
		for (int i = 0; i < h; i++) {
			String line = inputs.get(i + 1);
			int len = line.length();
			for (int j = 0; j < len; j++) {
				fieldMap[i][j] = Integer.parseInt(line.substring(j, j + 1));
			}
		}

		return fieldMap;
	}

	private static List<String> readInput(InputStream is) throws IOException {
		List<String> inputs = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(is))){
			br.lines().forEach(l -> { inputs.add(l); });
		}
		return inputs;
	}

//	private static List<String> readInput(InputStream is) throws IOException {
//		List<String> inputs = new ArrayList<String>();
//
//		inputs.add("4 5");
//		inputs.add("32445");
//		inputs.add("33434");
//		inputs.add("21153");
//		inputs.add("12343");
//		
//		return inputs;
//	}
}
