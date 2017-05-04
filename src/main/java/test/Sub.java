package test;

public class Sub extends Su{
public String name="1";
//public Ma ma;
public String getName(){
	//return super.name;
	this.ma.n="aa";
	return super.ma.n;
}

public static void main(String[] args) {
	Sub sub = new Sub();
	//System.out.println("1"=="1");
	System.out.println(sub.getName());
}
}
