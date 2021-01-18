package fr.training.spring.library.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Interface used to verify the Equals() contract.
 *
 * <ul>
 * <li><b>Reflexivity</b> : Apple == Apple</li>
 * <li><b>Symetry</b> : if Apple == Orange then Orange == Apple</li>
 * <li><b>Transitivity</b> : if Apple == Banana and Banana == Orange then Orange
 * == Apple</li>
 * <li><b>Consistency</b> : if Apple == Orange now then Apple == Orange
 * always</li>
 * <li><b>Non Nullity</b> : Apple == null is never true</li>
 * </ul>
 *
 * See :
 * <ul>
 * <li>Chapter 3 of Effective Java Programming Language Guide By Joshua
 * Bloch</li>
 * <li>Java Cookbook 8.2. Overriding the equals() and hashCode() Methods</li>
 * </ul>
 */
public interface EqualsContractTester {

	/**
	 * Global equality tester method.
	 *
	 * Implement this in the manner of :
	 *
	 * <pre>
	 * <code>
	 * &#64;Test
	 * public final void testEqualsObject() {
	 *     // Instantiate here 3 same objects (obj1, obj2, obj3) and 1 different (obj4)
	 *     assertReflexive(obj1);
	 *     assertSymmetric(obj1, obj2);
	 *     assertTransitive(obj1, obj2, obj3);
	 *     assertNonNullity(obj1);
	 *     assertDifferent(obj1, obj4);
	 * }
	 * </code>
	 * </pre>
	 */
	public void testEqualsObject();

	/**
	 * according to Object.equals for any non-null reference value x, x.equals(x)
	 * should return true.
	 *
	 * @param obj
	 *            the object
	 */
	public default void assertReflexive(final Object obj) {
		assertThat("Object obj should be reflexibly equal to itself.", obj, equalTo(obj));
	}

	/**
	 * for any non-null reference values x and y, x.equals(y) should return true if
	 * and only if y.equals(x) returns true.
	 *
	 * @param obj1
	 *            the first object
	 * @param obj2
	 *            the second object
	 */
	public default void assertSymmetric(final Object obj1, final Object obj2) {
		assertThat("obj1 and obj2 should be symetrically equal to each other.", obj1, equalTo(obj2));
		assertThat("obj2 and obj1 should be symetrically equal to each other.", obj2, equalTo(obj1));
	}

	/**
	 * for any non-null reference values x, y, and z, if x.equals(y) returns true
	 * and y.equals(z) returns true, then x.equals(z) should return true.
	 *
	 * @param obj1
	 *            the first object
	 * @param obj2
	 *            the second object
	 * @param obj3
	 *            the third object
	 */
	public default void assertTransitive(final Object obj1, final Object obj2, final Object obj3) {
		assertThat("obj1 should transitively be equal to obj2.", obj1, equalTo(obj2));
		assertThat("obj2 should transitively be equal to obj3.", obj2, equalTo(obj3));
		assertThat("obj1 should transitively be equal to obj3.", obj1, equalTo(obj3));
	}

	/**
	 * For any non-null reference value x, x.equals(null) should return false.
	 *
	 * @param obj
	 *            the object
	 */
	public default void assertNonNullity(final Object obj) {
		assertThat("obj1 should not be equals to null!", obj.equals(null), equalTo(false));
	}

	/**
	 * test that obj1 and obj2 are different.
	 *
	 * @param obj1
	 *            the first object
	 * @param obj2
	 *            the second object
	 */
	public default void assertDifferent(final Object obj1, final Object obj2) {
		assertThat("obj1 should not be equals to obj2.", obj1.equals(obj2), equalTo(false));
	}

}