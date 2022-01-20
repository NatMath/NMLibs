package math;

import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * An interface representing an equivalence relation between two elements of the same type. Equivalent to <code>{@link BiPredicate<T, T>}</code> but enforces stronger semantics about the equivalence relation.
 * 
 *  The method of this functional interface is {@link #compare(T, T)}
 * 
 * @author Nathan
 *
 * @param <T>
 */
@FunctionalInterface
public interface EquivalenceRelation<T> {
	
	/**
	 * Tests the equivalence relation between <code>v1</code> and <code>v2</code>. 
	 * <p>
	 * An equivalence relation must have the following properties:
	 * <ol>
	 * 	<li> Reflexive: <code>compare(x, x)</code> is always <code>true</code> </li>
	 * 	<li> Symmetric: <code>compare(x, y) == compare(y, x) </code> </li>
	 * 	<li> Transitive: <code>compare(x, y) &amp;&amp; compare(y, z)</code>&rArr; <code>compare(x, z)</code> is <code>true</code></li>
	 * </ol>
	 * 
	 * <p>
	 * This methods accepts <code>null</code> in either parameter or both. <code>compare(null, null)</code> must always be <code>true</code> because of reflexivity. 
	 * The result of comparing a <code>null</code> to a non-<code>null</code> value is left to the implementation contract.
	 * 
	 * @param v1 The first value to compare. May be <code>null</code>
	 * @param v2 The second value to compare. May be <code>null</code>
	 * @return Whether the two parameters are equivalent
	 */
	public boolean compare(T v1, T v2);
	
	
	
	/**
	 * Creates an equivalence relation testing for equality between objects.
	 * 
	 * Two object are equivalent if and only <code>v1.equals(v2)</code> is <code>true</code>.
	 * 
	 * <p>
	 * <code>null</code> values are not equivalent to any non-<code>null</code> objects.
	 * 
	 * @return An equivalence relation comparing objects for equality
	 */
	public static EquivalenceRelation<?> equality(){
		return Objects::equals;
	}
	
	/**
	 * Creates an equivalence relation testing for identity between objects.
	 * 
	 * <p>
	 * Two objects are equal if and only if <code>v1==v2</code>. Thus, this relation tests whether the references point to the same object.
	 * <p>
	 * <code>null</code> values are not equivalent to any non-<code>null</code> objects.
	 * @return An equivalence relation comparing objects for identity
	 */
	public static EquivalenceRelation<?> identity(){
		return (v1, v2) -> v1 == v2;
	}
	
	/**
	 * Creates an equivalence relation testing for equality between hashcodes.
	 * 
	 * <p>
	 * Two objects are equal if and only if they hash to the same hashcode.
	 * <p>
	 * A non-<code>null</code> value <code>x</code> is equivalent to <code>null</code> if and only if <code>x.hashcode() == 0</code>.
	 * 
	 * @return An equivalence relation comparing objects based on their hashcodes.
	 */
	public static EquivalenceRelation<?> hashEquality(){
		return (v1, v2) -> Objects.hashCode(v2) == Objects.hashCode(v2);
	}

}
