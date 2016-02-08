
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***********************************
 * Occures OutOfMemoryError。
 ***********************************/
class BaiBaiMan {
	public static void main(String[] args) throws IOException {
		List<Byte> bbm = new ArrayList<Byte>();
		bbm.add((byte) 1);
		System.out.println(1);
		for (int i = 2; i <= 100; i++) {
			int size = bbm.size();
			for (int j = 0; j < size; j++) {
				byte[] ps = grow(bbm.get(j));
				bbm.set(j, ps[0]);
				if (ps[1] > 0) {
					bbm.add(ps[1]);
				}
			}
			System.out.println(bbm.size());
		}
	}

	static byte[] grow(byte power) {
		byte[] ps;
		byte p = (byte) (power << 1);
		if (p >= 10) {
			ps = new byte[] {1, (byte) (p - 10)};
		} else {
			ps = new byte[] {p, 0};
		}
		return ps;
	}
}
