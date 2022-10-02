package util;

public interface Params {
	/**
	 * @return String number "1", "2", "3", "4", "5"...
	 */
	public String getPARAM_SEQNO();
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
	 *  <p>'select s.STORERKEY as OWNERKEY, s.COMPANY as OWNERNAME from wmwhse1.STORER s where s.TYPE = ''1'''
	 */
	public String getPARAM_CONTENTS();
	/**@return
	 *  <p> '0'-false or '1'-true
	 */
	public String getPARAM_ISREQUIRED();
	/**
	 * @return String or 'null'
	 */
	public String getPARAM_DEFAULT();

	boolean equals(Object obj);
	
}