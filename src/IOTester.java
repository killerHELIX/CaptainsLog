public class IOTester {
	public static void main(String[] args) {
		String[] portrait = null;
		try {
			portrait = IO.getASCIIArt("portrait.txt", true);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
		for (int i = 0; i < portrait.length; i++) {
			System.out.println(portrait[i]);
		}
	}
}
