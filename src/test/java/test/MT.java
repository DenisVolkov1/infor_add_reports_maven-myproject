package test;


public class MT {
	


	public static void main(String[] args) {
	
		String cString = "C:\\infor\\sce\\jboss-as-7.2.0.Final\\scprd-reports1\\deployments\\scprd_scereports$$$$_temp.war (Не удается найти указанный файл)";
		cString = cString.replaceAll("[a-zA-Z]\\.[a-zA-Z&&[^wrz]]", "."+"\r\n");
		
		System.out.println(cString);
		
	}


}
