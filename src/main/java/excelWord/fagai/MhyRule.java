package excelWord.fagai;

public enum MhyRule {
	
	
	NUMBER("num"),
	STRING("str"),
	NUM_NULL("numNull"),
	STRING_NULL("strNull"),
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
	
	public static boolean contains(String v){
		for(MhyRule r : MhyRule.values()){
			if(r.toString().equals(v)){
				return true;
			}
		}
		return false;
	}
}
