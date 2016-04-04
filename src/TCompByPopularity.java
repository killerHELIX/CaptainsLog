/** Comparator implementation to compare Transmissions by number of faves.
 * @author James Murphy
 */

public class TCompByPopularity implements Comparator {
	public int compare(Object x, Object y) {
		Transmission a = (Transmission) x;
		Transmission b = (Transmission) y;
		return Integer(a.getNumFavorites()).compareTo(
			Integer(b.getNumFavorites()));
	}
}
