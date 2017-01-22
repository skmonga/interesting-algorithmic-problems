package hackerrank.practice;


/**
 * Refer : http://stackoverflow.com/questions/36267354/java-string-replaceall-with-back-reference
 *
 */
public class RegexBackRefernce {

	public static void main(String[] args) {
		String str = "abcdef";
		System.out.println(str.replaceAll("(^\\*)|(\\*$)|\\*", "$1$2"));
	}
}
