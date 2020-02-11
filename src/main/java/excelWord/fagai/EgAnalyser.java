package excelWord.fagai;

public class EgAnalyser {
	public static final String FLAG = "$#";
	private String rule;
	private String target;
	boolean noRule;
	
	public boolean isNoRule() {
		return noRule;
	}
	public void setNoRule(boolean noRule) {
		this.noRule = noRule;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	/**
	 * 解析单元格表达式
	 * @param str
	 * @return
	 */
	public static EgAnalyser egAnalyse(String str){
		
		EgAnalyser egResult = new EgAnalyser();
		if(str == null){
			egResult.setNoRule(true);
			return egResult;
		}
		int flagIdx = str.lastIndexOf(FLAG);
		String eg = str.substring(flagIdx+FLAG.length(), str.length());
		String target = str.substring(0,flagIdx);
		egResult.setRule(eg);
		egResult.setTarget(target);
		if(MhyRule.contains(eg)){//如果不是程序预定义的表达式则不进行验证
			egResult.setNoRule(false);
		}else{
			egResult.setNoRule(true);
		}
		return egResult;
	}
}
