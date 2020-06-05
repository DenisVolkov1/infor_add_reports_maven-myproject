package util.parce_rptdesign;

public class ParamFromRptDesign {
	
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
	 * example
	 *  select distinct(USERGROUP) as EMPLOYEE_GROUP from wmwhse1.TASKMANAGERUSER
	 *  select s.STORERKEY as OWNERKEY, s.COMPANY as OWNERNAME from wmwhse1.STORER s where s.TYPE = 1
	 */
	private String queryText;
	private String dataSetName;
	
	
	public ParamFromRptDesign(String name, String promptText, String dataType, String paramType, String controlType,String dataSetName) {
		super();
		this.name = name;
		this.promptText = promptText;
		this.dataType = dataType;
		this.paramType = paramType;
		this.controlType = controlType;
		this.dataSetName = dataSetName;
	}
	
	public ParamFromRptDesign(String name, String promptText, String dataType, String paramType, String controlType,String dataSetName, String queryText) {
		super();
		this.name = name;
		this.promptText = promptText;
		this.dataType = dataType;
		this.paramType = paramType;
		this.controlType = controlType;
		this.queryText = queryText;
		this.dataSetName = dataSetName;
	}

	public String getPARAM_CONTENTS() {
		return (queryText != null) ? queryText.trim() : null;
		//return (queryText != null) ? queryText.replace("<![CDATA[", "").replace("]]>", "") : null;
	}

	public String getPARAM_NAME() {
		return name;
	}

	public String getPARAM_LABEL() {
		return promptText;
	}
	/**
	 * @return  PARAM_TYPE - CONST 'Date' or 'Dropdown' or 'MultiSelect' or 'Number' or 'Text' or 'Boolean' or 'Time'
	 */
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
		return "PARAM_NAME = "+ getPARAM_NAME() + "\n" + 
			   "PARAM_LABEL = "+ getPARAM_LABEL() + "\n"+
			   "PARAM_TYPE = "+ getPARAM_TYPE()+ "\n"+
			   "PARAM_CONTENTS = "+ getPARAM_CONTENTS()+ "\n"+
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

	
	
	


	
	
	
	
	
	

}
