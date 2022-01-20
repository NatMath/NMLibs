package lang;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.function.Predicate;

import math.EquivalenceRelation;

/**
 * A class collecting many utility methods to work with enums
 * 
 * @author Nathan
 *
 */
public final class EnumTools {
	

	/**
	 * Searches the given <code>enum</code> for the first element which has the given name.
	 * 
	 * The search is done in the natural ordering of the <code>enum</code> type. If two elements would match the given name, the first one is returned. If no element matches the given name, an empty <code>Optional</code> is returned
	 * The search is done by a case-ignoring comparison between <code>search</code> and the declared name of the constants. If a finer control over the comparison is necessary, use {@link #searchEnum(Class, String, EquivalenceRelation)}
	 * This method is equivalent to <code>searchEnum(enumeration, search, String::equalsIgnoreCase)</code>
	 * @param <T> The type of the <code>enum</code> to search
	 * @param enumeration A <code>Class</code> object representing the <code>enum</code> type. Cannot be <code>null</code>
	 * @param search The name to search in the <code>enum</code>. Cannot be <code>null</code>
	 * @return The first element of <code>T</code> which has the given name (ignoring case), or an empty <code>Optional</code> if no constant has this name
	 */
	public static <T extends Enum<?>> Optional<T> searchEnum(Class<T> enumeration, String search) {
		requireNonNull(enumeration, "The given enumeration class cannot be null");
		requireNonNull(search, "The search string cannot be null");
	    for (T each : enumeration.getEnumConstants()) {
	        if (each.name().equalsIgnoreCase(search)) {
	            return Optional.of(each);
	        }
	    }
	    return Optional.empty();
	}
	

	/**
	 * Searches the given <code>enum</code> for the first element which has the given name, according to the given <code>Equivalence Relation</code>.
	 * 
	 * The search is done in the natural ordering of the <code>enum</code>. If two elements would match, the first one is returned. If no element matches, an empty <code>Optional</code> is returned.
	 * The search is done according to the <code>EquivalenceRelation</code> given, between the declared name of the constants and <code>search</code>.
	 * @param <T> The type of the <code>enum</code> to search
	 * @param enumeration A <code>Class</code> object representing the <code>enum</code> type. Cannot be <code>null</code>
	 * @param search The name to search for in the <code>enum</code>. Cannot be <code>null</code>
	 * @param stringEquivalence The equivalence relation with which to compare <code>search</code> with the enum constant names. Cannot be <code>null</code>
	 * @return The first element of <code>T</code> which has the given name according to <code>stringEquivalence</code>, or an empty <code>Optional</code> if no constant has this name.
	 */
	public static <T extends Enum<?>> Optional<T> searchEnum(Class<T> enumeration, String search, EquivalenceRelation<String> stringEquivalence) {
		requireNonNull(enumeration, "The given enum class cannot be null");
		requireNonNull(search, "The search string cannot be null");
	    requireNonNull(stringEquivalence, "The given string equality test cannot be null");
		for (T each : enumeration.getEnumConstants()) {
	    	if (stringEquivalence.compare(each.name(), search)) 
	            return requireNonNull(Optional.of(each));
	    }
	    return Optional.empty();
	}
	
	/**
	 * Searches the given <code>enum</code> for the first element whose declared name matches <code>condition</code>.
	 * 
	 * The search is done in the natural ordering of the <code>enum</code>. If two elements would match, the first one is returned. If no element matches, an empty <code>Optional</code> is returned.
	 * The search is done by evaluating <code>condition</code> with the declared name of the <code>enum</code> constants.
	 * @param <T> The type of the <code>enum</code> to search
	 * @param enumeration A <code>Class</code> object representing the <code>enum</code> type. Cannot be <code>null</code>.
	 * @param condition The test to perform on the constant names of <code>T</code>. Cannot be <code>null</code>
	 * @return The first element of <code>T</code> whose declare name matches <code>condition</code>, or an empty <code>Optional</code> if none do.
	 */
	public static <T extends Enum<?>> Optional<T> searchEnum(Class<T> enumeration, Predicate<T> condition) {
		requireNonNull(enumeration, "The given enum class cannot be null");
		requireNonNull(condition, "The given condition cannot be null");
	    for (T each : enumeration.getEnumConstants()) {
	        if (condition.test(each)) 
	            return Optional.of(each);
	    }
	    return Optional.empty();
	}
	

}