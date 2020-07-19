package test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import database.ParamsRelatedData;
import exception.InfoException;
import parce_rptdesign.ParamFromRptDesign;
import parce_rptdesign.ReadXML;
import windows.param.ParamFromParamsPanel;

public class MT {

	public static void main(String[] args) throws Exception {
		
		
		String nameRptFile = "rep_2_3_Case";
			//System.out.println("qweqwe    rep_myrep10/".matches(".*\\s{4}"+nameRptFile+"/$"));
			System.out.println("Печать этикетки ящика    rep_2_3_Case/".replaceFirst("\\s{4}"+nameRptFile+"/$", ""));
		 

	}

}
