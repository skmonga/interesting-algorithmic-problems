package code.practice;

public class TestFoo {

	public static void main(String[] args) {
		IFoo i1 = new Foo1();
		IFoo i2 = new Foo2();
		i1.print();
		i2.print();
	}
}
