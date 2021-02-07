/**
 * 	生成长度为size的达标数组(有无数个方法，你设计个算法，找到其中一种)
 * 	达标：对于任意的 i<k<j，满足   [i] + [j]  != [k] * 2
 *
 * 	思路：
 *  正推：如果一个长度为n的数组，前 一半为 基数 且 在其中任意满足 [i] + [j]  != [k] * 2
 *       后一半为偶数，且在其中任意满足 [i] + [j]  != [k] * 2，则这个数组满足，（一个数挑选的到前一半，一个数挑选到后一半，也满足）
 *
 *       所以可以把一个基础数组加工，基础数组前一半 的每个数i ：i*2-1 （奇数 *2 +1 也行）  后一半数组每个数 ：i *2 (偶数 *4 也行)
 *
 * 	[1]  满足 ；
 *
 *
 */
public class Code06_MakeNo {

	public static int[] makeNo(int size) {

	    if(size == 1){
	    	return new int[]{1};
		}
	    int[] ans = new int[size];
		int half = (size+1)/2;

	    int[] base = makeNo(half);

	    int index =0;

		for (; index < base.length ; index++) {
			ans[index] = 2*base[index]+1;
		}
		for ( int i =0; index < ans.length ; index++) {
			ans[index] = 2*base[index-base.length];
		}

		return ans;
	}
	

	// 检验函数
	public static boolean isValid(int[] arr) {
		int N = arr.length;
		for (int i = 0; i < N; i++) {
			for (int k = i + 1; k < N; k++) {
				for (int j = k + 1; j < N; j++) {
					
					
					if (arr[i] + arr[j] == 2 * arr[k]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println("test begin");
		for (int N = 1; N < 1000; N++) {
			int[] arr = makeNo(N);
			if (!isValid(arr)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test end");
		
		System.out.println(isValid(makeNo(1042)));
		System.out.println(isValid(makeNo(2981)));




	}

}
