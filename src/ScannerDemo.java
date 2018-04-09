import java.util.Scanner;

public class ScannerDemo {
	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		System.out.println("请输入a的值：");
		int a = sc.nextInt();
		System.out.println("请输入b的值：");
		int b = sc.nextInt();
		
		System.out.print("求和结果为：");
		System.out.println(a+b);
	}
}
