package windows.param;

import util.Params;

public class ParamFromParamsPanel implements Params {
	
	private String PARAM_NAME, PARAM_LABEL, PARAM_TYPE, PARAM_CONTENTS, PARAM_ISREQUIRED;

	public ParamFromParamsPanel(String pARAM_NAME, String pARAM_LABEL,String pARAM_ISREQUIRED, String pARAM_TYPE, String pARAM_CONTENTS) {
		super();
		PARAM_NAME = pARAM_NAME;
		PARAM_LABEL = pARAM_LABEL;
		PARAM_ISREQUIRED = pARAM_ISREQUIRED;
		PARAM_TYPE = pARAM_TYPE;
		PARAM_CONTENTS = pARAM_CONTENTS;
		
	}
	@Override
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
		if(PARAM_CONTENTS == null) return null;
		return (PARAM_CONTENTS.length() != 0) ? PARAM_CONTENTS : null;
	}

	public void setPARAM_CONTENTS(String pARAM_CONTENTS) {
		PARAM_CONTENTS = pARAM_CONTENTS;
	}
	
	public void setPARAM_ISREQUIRED(String pARAM_ISREQUIRED) {
		PARAM_ISREQUIRED = pARAM_ISREQUIRED;
	}
	public String getPARAM_ISREQUIRED() {
		return (PARAM_ISREQUIRED == null) ? "1" : PARAM_ISREQUIRED; 
	}
	
	@Override
	public String toString() {
		return "PARAM_NAME = "+ getPARAM_NAME() + "\n" + 
			   "PARAM_LABEL = "+ getPARAM_LABEL() + "\n"+
			   "PARAM_ISREQUIRED = "+ getPARAM_ISREQUIRED() + "\n"+
			   "PARAM_TYPE = "+ getPARAM_TYPE()+ "\n"+
			   "PARAM_CONTENTS = "+ getPARAM_CONTENTS()+ "\n";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PARAM_CONTENTS == null) ? 0 : PARAM_CONTENTS.hashCode());
		result = prime * result + ((PARAM_LABEL == null) ? 0 : PARAM_LABEL.hashCode());
		result = prime * result + ((PARAM_ISREQUIRED == null) ? 0 : PARAM_ISREQUIRED.hashCode());
		result = prime * result + ((PARAM_NAME == null) ? 0 : PARAM_NAME.hashCode());
		result = prime * result + ((PARAM_TYPE == null) ? 0 : PARAM_TYPE.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParamFromParamsPanel other = (ParamFromParamsPanel) obj;
		if (PARAM_CONTENTS == null) {
			if (other.PARAM_CONTENTS != null)
				return false;
		} else if (!PARAM_CONTENTS.equals(other.PARAM_CONTENTS))
			return false;
		if (PARAM_LABEL == null) {
			if (other.PARAM_LABEL != null)
				return false;
		} else if (!PARAM_LABEL.equals(other.PARAM_LABEL))
			return false;
		if (PARAM_ISREQUIRED == null) {
			if (other.PARAM_ISREQUIRED != null)
				return false;
		} else if (!PARAM_ISREQUIRED.equals(other.PARAM_ISREQUIRED))
			return false;
		if (PARAM_NAME == null) {
			if (other.PARAM_NAME != null)
				return false;
		} else if (!PARAM_NAME.equals(other.PARAM_NAME))
			return false;
		if (PARAM_TYPE == null) {
			if (other.PARAM_TYPE != null)
				return false;
		} else if (!PARAM_TYPE.equals(other.PARAM_TYPE))
			return false;
		return true;
	}

}
