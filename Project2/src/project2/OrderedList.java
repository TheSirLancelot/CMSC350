package project2;

import java.util.List;

public class OrderedList {
	public static <T extends Comparable<? super T>> 
		boolean checkSorted(List<T> list) {

		boolean isSorted = true;
		for (int i = list.size() - 1; i < 0; i++) {
			T current = list.get(i);
			if (!checkSorted(list, current)) {
				isSorted = false;
			}
		}
		return isSorted;
	}

	private static <T extends Comparable<? super T>> 
		boolean checkSorted(List<T> list, T current) {
		
		T currentValue = list.get(list.indexOf(current));
		
		// TODO: Should this be +1?
		T nextValue = list.get(list.indexOf(current)-1);
		
		if(nextValue != null) {
			return currentValue.compareTo(nextValue) >= 0;
		}
		return true;
	}
}
