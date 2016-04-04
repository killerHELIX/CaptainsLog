/** Comparator implementation to compare Transmissions by number of faves.
 * @author James Murphy
 */

import java.lang.Integer;
import java.util.Comparator;

public class TCompByPopularity implements Comparator {
	public int compare(Object x, Object y) {
		Transmission a = (Transmission) x;
		Transmission b = (Transmission) y;
		return new Integer(a.getNumFavorites()).compareTo(
			new Integer(b.getNumFavorites()));
	}
}
