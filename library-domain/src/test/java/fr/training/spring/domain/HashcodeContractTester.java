package fr.training.spring.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Interface used to verify the hashCode() contract.
 *
 * <ul>
 * <li>if <code>equals()</code> returns true for two objects, then hashCode()
 * method should also return the same value, i.e. if
 * <code>a.equals(b) == true</code> and <code>b.equals(a) == true</code>, then
 * <code>a.hashCode() == b.hashCode()</code>.</li>
 *
 * <li>if <code>equals()</code> returns false for two objects, then hashCode()
 * method should return different values, i.e. if
 * <code>a.equals(b) == false</code> or <code>b.equals(a) == false</code>, then
 * <code>a.hashCode() != b.hashCode()</code>.</li>
 * </ul>
 *
 * See :
 * <ul>
 * <li>Chapter 3 of Effective Java Programming Language Guide By Joshua
 * Bloch</li>
 * <li>Java Cookbook 8.2. Overriding the equals() and hashCode() Methods</li>
 * </ul>
 */
public interface HashcodeContractTester {

	/**
	 * Global hashcode tester method.
	 *
	 * Implement this in the manner of :
	 *
	 * <pre>
	 * <code>
	 * &#64;Test
	 * public final void testHashCode() {
	 *     // Instantiate here 2 same objects (obj1, obj2)
	 *     assertHashCodeConsistency(obj1);
	 *     assertHashCodeEquality(obj1, obj2)
	 * }
	 * </code>
	 * </pre>
	 */
	public void testHashCode();

	/**
	 * Whenever it is invoked on the same object more than once during an execution
	 * of a Java application, the hashCode method must consistently return the same
	 * integer
	 *
	 * @param obj
	 *            the object
	 */
	public default void assertHashCodeConsistency(final Object obj) {
		assertThat("object hashcode consistency with itself failed! Weird.", obj.hashCode(), equalTo(obj.hashCode()));
	}

	/**
	 * if o1 is equals to o2, then o1.hashcode should be equals to o2.hashcode
	 *
	 * @param obj1
	 *            the first object
	 * @param obj2
	 *            the second object
	 */
	public default void assertHashCodeEquality(final Object obj1, final Object obj2) {
		if (obj1.equals(obj2)) {
			assertThat("if o1 and o2 are equals, then they should have the same hashcode!", obj1.hashCode(),
					equalTo(obj2.hashCode()));
		}
		assertThat("Expect o1 and o2 are equals", obj1, equalTo(obj2));
	}
}