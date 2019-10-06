public class Interval <T extends Comparable & Serializable> implements Serializable
{
	private T lower;
	private T upper;
	
	public Interval(T first, T second)
	{
		if (first.compareTo(second) <= 0) { lower = first; upper = second; }
		else { lower = second; upper = first; }
	}
}

// The row type Interval looks like this:

public class Interval implements Serializable
{
	private Comparable lower;
	private Comparable upper;
	
	
	public Interval(Comparable first, Comparable second) ...
}

/**
 *  NOTE: You may wonder what happens if you switch the bounds: class Interval<T
extends Serializable & Comparable>. In that case, the raw type replaces T with
Serializable, and the compiler inserts casts to Comparable when necessary. For
efficiency, you should therefore put tagging interfaces (that is, interfaces without
methods) at the end of the bounds list.
 */