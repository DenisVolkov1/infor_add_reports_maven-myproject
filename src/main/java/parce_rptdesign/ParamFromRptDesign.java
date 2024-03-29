package parce_rptdesign;

import util.Params;

public class ParamFromRptDesign implements Params {
	
	private String seqno;
	private String name;
	private String promptText;
	/**
	 * RptDesignValues
	 * 'string' or 'date' or 'integer' or 'dateTime' or 'boolean' or 'decimal' or 'float' or 'time'
	 */
	private String dataType;
	/**
	 * RptDesignValues
	 *  'multi-value' or 'simple'
	 */
	private String paramType;
	/**
	 * RptDesignValues
	 *  'list-box' or 'text-box' or 'check-box' or 'radio-button'
	 */
	private String controlType;
	/**
	 * RptDesignValues
	 *  'false' or 'true' in base '0' or '1'
	 */
	private String isRequired;
	private String queryText;
	private String _default;
	private String dataSetName;
	
	
	public ParamFromRptDesign(String seqno,String name, String promptText, String dataType, String paramType, String controlType,String isRequired,String _default,String dataSetName) {
		super();
		this.seqno = seqno;
		this.name = name;
		this.promptText = promptText;
		this.dataType = dataType;
		this.paramType = paramType;
		this.controlType = controlType;
		this.isRequired = isRequired;
		this.dataSetName = dataSetName;
		this._default = _default;
	}
	
	public ParamFromRptDesign(String seqno,String name, String promptText, String dataType, String paramType, String controlType,String isRequired, String dataSetName, String queryText,String _default) {
		super();
		this.seqno = seqno;
		this.name = name;
		this.promptText = promptText;
		this.dataType = dataType;
		this.paramType = paramType;
		this.controlType = controlType;
		this.isRequired = isRequired;
		this.queryText = queryText;
		this.dataSetName = dataSetName;
		this._default = _default;
	}

	public String getPARAM_CONTENTS() {
		return (queryText != null) ? queryText.trim() : null;
	}

	public String getPARAM_NAME() {
		return name;
	}

	public String getPARAM_LABEL() {
		return (promptText == null) ? "" : promptText;
	}
	public String getPARAM_ISREQUIRED() {
		if(isRequired == null) return "1";
		return (isRequired.equals("true")) ? "1" : "0";
	}

	public String getPARAM_TYPE() {
		if (controlType == null) return null;
		if (controlType.equals("list-box")) {
			if (paramType.equals("multi-value")) return "MultiSelect";
			if (paramType.equals("simple")) return "Dropdown";
		} else {
			switch (dataType) {
				case "date": return "Date";
				case "dateTime": return "Date";
				case "time": return "Time";
				case "integer": return "Number";
				case "decimal": return "Number";
				case "float": return "Number";
				case "boolean": return "Boolean";
				case "string": return "Text";
			}
		}
		return paramType;
	}
	
	@Override
	public String toString() {
		return "PARAM_SEQNO = "+ getPARAM_SEQNO() + "\n" + 
			   "PARAM_NAME = "+ getPARAM_NAME() + "\n" + 
			   "PARAM_LABEL = "+ getPARAM_LABEL() + "\n"+
			   "PARAM_TYPE = "+ getPARAM_TYPE()+ "\n"+
			   "PARAM_ISREQUIRED = "+ getPARAM_ISREQUIRED()+"\n"+
			   "PARAM_CONTENTS = "+ getPARAM_CONTENTS()+ "\n"+
			   "PARAM_DEFAULT = "+ getPARAM_DEFAULT()+ "\n"+
			   "DataSetName = "+ getDataSetName();
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ParamFromRptDesign other = (ParamFromRptDesign) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

	public String getDataSetName() {
		return dataSetName;
	}

	@Override
	public String getPARAM_SEQNO() {
		return seqno;
	}

	@Override
	public String getPARAM_DEFAULT() {
		return _default;
	}
}
