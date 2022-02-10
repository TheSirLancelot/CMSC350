package project2;

import java.util.List;

/**
 * Filename: OrderedList.java
 * 
 * @author William Weir Date: Jan 31, 2022 Description: class handling the weak
 *         sort check
 */

public class OrderedList {

	// first of our two overloaded methods
	public static <T extends Comparable<? super T>> 
			boolean checkSorted(List<T> list) {
		boolean isSorted = true;

		// loop through the list and call our other method
		for (int i = list.size() - 1; i > 0; i--) {
			T current = list.get(i);
			if (!checkSorted(list, current)) {
				isSorted = false;
			}
		}
		return isSorted;
	}

	// second overloaded method
	private static <T extends Comparable<? super T>> 
			boolean checkSorted(List<T> list, T current) {

		// get our working values
		T currentValue = list.get(list.indexOf(current));
		T nextValue = list.get(list.indexOf(current) - 1);

		// compare the values
		if (nextValue != null) {
			return currentValue.compareTo(nextValue) >= 0;
		}
		return true;
	}
}
