package windows.param;

public class ParamFromParamsPanel {
	
	private String PARAM_NAME, PARAM_LABEL, PARAM_TYPE, PARAM_CONTENTS;

	public ParamFromParamsPanel(String pARAM_NAME, String pARAM_LABEL, String pARAM_TYPE, String pARAM_CONTENTS) {
		super();
		PARAM_NAME = pARAM_NAME;
		PARAM_LABEL = pARAM_LABEL;
		PARAM_TYPE = pARAM_TYPE;
		PARAM_CONTENTS = pARAM_CONTENTS;
	}

	public String getPARAM_NAME() {
		return PARAM_NAME;
	}

	public void setPARAM_NAME(String pARAM_NAME) {
		PARAM_NAME = pARAM_NAME;
	}

	public String getPARAM_LABEL() {
		return (PARAM_LABEL == null) ? "" : PARAM_LABEL;
	}

	public void setPARAM_LABEL(String pARAM_LABEL) {
		PARAM_LABEL = pARAM_LABEL;
	}

	public String getPARAM_TYPE() {
		return PARAM_TYPE;
	}

	public void setPARAM_TYPE(String pARAM_TYPE) {
		PARAM_TYPE = pARAM_TYPE;
	}

	public String getPARAM_CONTENTS() {
		
		
		return (PARAM_CONTENTS.length() != 0) ? PARAM_CONTENTS : null;
	}

	public void setPARAM_CONTENTS(String pARAM_CONTENTS) {
		PARAM_CONTENTS = pARAM_CONTENTS;
	}
	@Override
	public String toString() {
		return "PARAM_NAME = "+ getPARAM_NAME() + "\n" + 
			   "PARAM_LABEL = "+ getPARAM_LABEL() + "\n"+
			   "PARAM_TYPE = "+ getPARAM_TYPE()+ "\n"+
			   "PARAM_CONTENTS = "+ getPARAM_CONTENTS()+ "\n";
	}
	
	
	

}
