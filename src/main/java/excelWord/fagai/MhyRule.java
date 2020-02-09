package excelWord.fagai;

public enum MhyRule {
	
	
	NUMBER("num"),
	STRING("str"),
	EQ("eq"),
	NOTNULL("notnull");
	
	private  String eg;
	private MhyRule(String eg){
		this.eg = eg;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.eg;
	}
}
