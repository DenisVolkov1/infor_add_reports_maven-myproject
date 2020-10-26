package test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import database.ParamsRelatedData;
import exception.InfoException;
import files_repository.FilesRepository;
import jcifs.smb.SmbFile;
import parce_rptdesign.ParamFromRptDesign;
import parce_rptdesign.ReadXML;
import windows.param.ParamFromParamsPanel;
import static files_repository.FilesRepository.*;

public class MT {
	
	private static class Y {
		public final Long long1;
		public Y(final Long long1) {
			this.long1 = long1;
		}
	}

	public static void main(String[] args) throws Exception {
		
		Y y1 = new Y(1L);
		Y y2 = new Y(2L);
		Y y3 = new Y(3L);
		Y y4 = new Y(4L);
		Y y5 = new Y(5L);
		Y y6 = new Y(6L);
		Y y7 = new Y(7L);
		
		List<Y> list = new ArrayList<MT.Y>();
		list.add(y1);
		list.add(y2);
		list.add(y3);
		list.add(y4);
		
		for(Y y : list) {
			if(y == y2) {
				list.add(list.indexOf(y), y7);
			}
		}
		
		for(Y y : list) {
			System.out.println(y.long1);
		}
		
		
		
		
		
		
		
	
			
	}

}
