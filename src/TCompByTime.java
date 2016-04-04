/** Comparator implementation to compare Transmissions by time created.
 * @author James Murphy
 */

import java.time.LocalDateTime;
import java.util.Comparator;

public class TCompByTime implements Comparator {
	public int compare(Object x, Object y) {
		Transmission a = (Transmission) x;
		Transmission b = (Transmission) y;
		LocalDateTime atime = a.getTime();
		LocalDateTime btime = b.getTime();
		
		if (atime.isAfter(btime)) {
			return 1;
		} else if (atime.isBefore(btime)) {
			return -1;
		}
		return 0;
	}
}
