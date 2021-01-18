package fr.training.spring.library.domain;

import static java.lang.Math.signum;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public interface ComparableTester {

	/**
	 * Global CompareTo tester method.
	 *
	 * Implement this in the manner of :
	 *
	 * <pre>
	 * <code>
	 * @Test
	 * public final void testCompareTo() {
	 *     // Instantiate here 3 same objects (obj1, obj2, obj3) and 1 different (obj4)
	 *     assertComparisonReversal(obj1, obj2);
	 *     assertNullPointerException(obj1);
	 *     assertConsistencyWithEqual(obj1, obj2);
	 *     assertTransitivity(obj1, obj2, obj3);
	 *     assertConsistency(obj1, obj2, obj3);
	 * }
	 * </code>
	 * </pre>
	 */
	public void testCompareTo();

	/**
	 * ensure sgn(x.compareTo(y)) == -sgn(y.compareTo(x)) for all x and y. (This
	 * implies that x.compareTo(y) must throw an exception iff y.compareTo(x) throws
	 * an exception.)</li>
	 *
	 * @param <T>
	 *
	 * @param obj1
	 * @param obj2
	 */
	public default <T extends Comparable<T>> void assertComparisonReversal(final T obj1, final T obj2) {
		assertTrue("Comparison reversal should apply: sgn(obj1.compareTo(obj2)) == -sgn(obj2.compareTo(obj1)). ",
				signum(obj1.compareTo(obj2)) == -signum(obj2.compareTo(obj1)));
	}

	/**
	 * comparator should be consistent with equals if and only if e1.compareTo(e2)
	 * == 0 has the same boolean value as e1.equals(e2) for every e1 and e2 of class
	 * C
	 *
	 * @param obj1
	 * @param obj2
	 */
	public default <T extends Comparable<T>> void assertConsistencyWithEqual(final T obj1, final T obj2) {
		assertEquals("obj1 and obj2 should be equal. Before testing comparison. ", obj1, obj2);
		assertTrue("since obj1 and obj2 are equals, obj1.compareTo(obj2) should return zero!",
				obj1.compareTo(obj2) == 0);
	}

	/**
	 * e.compareTo(null) should throw a NullPointerException
	 *
	 * @param obj1
	 */
	public default <T extends Comparable<T>> void assertNullPointerException(final T obj1) {
		try {
			obj1.compareTo(null);
			fail();
		} catch (final NullPointerException e) {

		}
	}

	/**
	 * (obj3.compareTo(obj2)>0 && obj2.compareTo(obj1)>0) implies
	 * obj3.compareTo(obj1)>0.
	 *
	 * @param obj1
	 * @param obj2
	 * @param obj3
	 */
	public default <T extends Comparable<T>> void assertTransitivity(final T obj1, final T obj2, final T obj3) {
		assertTrue(
				"(" + obj3 + ".compareTo(" + obj2 + ") > 0) && (" + obj2 + ".compareTo(" + obj1 + ") > 0 ) && ( " + obj3
						+ ".compareTo(" + obj1 + ") > 0 )",
				obj3.compareTo(obj2) > 0 && obj2.compareTo(obj1) > 0 && obj3.compareTo(obj1) > 0);
	}

	/**
	 * ensure that twinobj1.compareTo(twinobj2)==0 implies that
	 * sgn(twinobj1.compareTo(differentobj3)) ==
	 * sgn(twinobj2.compareTo(differentobj3)), for all z.
	 *
	 * @param twinobj1
	 * @param twinobj2
	 * @param differentobj3
	 */
	public default <T extends Comparable<T>> void assertConsistency(final T twinobj1, final T twinobj2,
			final T differentobj3) {
		assertTrue(twinobj1.compareTo(twinobj2) == 0
				&& signum(twinobj1.compareTo(differentobj3)) == signum(twinobj2.compareTo(differentobj3)));
	}
}