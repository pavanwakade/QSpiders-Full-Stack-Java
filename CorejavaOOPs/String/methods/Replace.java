/**
 * 
 */
package String.methods;

/**
 * 
 */
public class Replace {

	public static void main(String[] args) {

		String s = "Pavan Wakade";

		String s1 = s.replace('P', 'W');

		System.out.println(s1);

		String para = "hello my name is pavan Wakade";

		System.out.println("Orignal String               : " + para);

		String newpara = para.replace('a', '-');

		System.out.println("single replace String        : " + newpara);

		String moreReplacePara = para.replace('a', '-').replace('e', '/');

		System.out.println("more than one replace String : " + moreReplacePara);
	}
}
