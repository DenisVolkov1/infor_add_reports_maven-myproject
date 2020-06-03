package database.model;

public class ParamFromRptDesign {
	
	private String name;
	private String promptText;
	/**
	 * 'string' or 'date'
	 */
	private String dataType;
	/**
	 *  'multi-value' or 'simple'
	 */
	private String paramType;
	private String controlType;
	/**
	 * example
	 *  <![CDATA[select distinct(USERGROUP) as EMPLOYEE_GROUP from wmwhse1.TASKMANAGERUSER]]>
	 *  <![CDATA[select s.STORERKEY as OWNERKEY, s.COMPANY as OWNERNAME from wmwhse1.STORER s where s.TYPE = 1]]>
	 */
	private String queryText;
	
	public ParamFromRptDesign(String name, String promptText, String dataType, String paramType, String controlType,String dataSetName, String queryText) {
		super();
		this.name = name;
		this.promptText = promptText;
		this.dataType = dataType;
		this.paramType = paramType;
		this.controlType = controlType;
		this.queryText = queryText;
	}

	public String getPARAM_CONTENTS() {
		
		return (queryText != null) ? queryText.replace("<![CDATA[", "").replace("]]>", "") : null;
	}

	public String getPARAM_NAME() {
		return name;
	}

	public String getPARAM_LABEL() {
		return promptText;
	}
	/**
	 * @return  PARAM_TYPE - CONST 'Date' or  'Dropdown' or 'MultiSelect' or 'Number' or 'Text'
	 */
	public String getPARAM_TYPE() {
		return paramType;
	}


	
	
	
	
	
	

}
