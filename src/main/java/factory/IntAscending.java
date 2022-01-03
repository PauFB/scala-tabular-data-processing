package factory;

import java.util.Comparator;

public class IntAscending implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		return Integer.compare(Integer.parseInt((String)o1), Integer.parseInt((String)o2));
	}

}
