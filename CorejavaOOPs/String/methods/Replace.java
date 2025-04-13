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

		String para = "hello my name is pavan i am from latur i have completed my BE in Electronics and telecommunication and also i have completed my Diploma in computer engg";

		String newpara = para.replace('a', '-');

		System.out.println(newpara);

		String moreReplacePara = para.replace('a', '-').replace('e', '/');

		System.out.println(moreReplacePara);
	}
}
