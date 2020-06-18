package util;

public interface Params {
	/**
	 * @return String
	 */
	public String getPARAM_NAME();
	/**
	 * @return String or empty string
	 */
	public String getPARAM_LABEL();
	/**
	 * @return  PARAM_TYPE - CONST 'Date' or 'Dropdown' or 'MultiSelect' or 'Number' or 'Text' or 'Boolean' or 'Time'
	 */
	public String getPARAM_TYPE();
	/**@return String or 'null'
	 * 'example'
	 *  select distinct(USERGROUP) as EMPLOYEE_GROUP from wmwhse1.TASKMANAGERUSER
	 *  select s.STORERKEY as OWNERKEY, s.COMPANY as OWNERNAME from wmwhse1.STORER s where s.TYPE = 1
	 */
	public String getPARAM_CONTENTS();
	/*
	 * p1.getPARAM_NAME = p2.getPARAM_NAME
	 */
	boolean equals(Object obj);
	
	

}