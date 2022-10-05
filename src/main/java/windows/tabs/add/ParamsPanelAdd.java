package windows.tabs.add;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;
import java.util.Vector;

import database.ParamFromDataBase;
import database.ParamsRelatedData;
import parce_rptdesign.ParamFromRptDesign;
import parce_rptdesign.ReadXML;
import windows.param.ParamFromParamsPanel;
import windows.param.ParamsPanel;
import windows.param.SettingParamsPanel;

public class ParamsPanelAdd extends ParamsPanel {
	private Vector <ParamFromParamsPanel> initParams = null;
	private SettingParamsPanel spp;
	
	public ParamsPanelAdd() {
		
		this.spp = super.settingParamsPanel;
		
		setBounds(100, 100, 932, 288);
		
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			
				int size = spp.getlistOfParamsNotCheck().size();

				if (size != 0) TabAddReport.getInstance().getNewParamButton().setStandartHover();
				else TabAddReport.getInstance().getNewParamButton().setEmptyHover();
			}
		});
		this.setVisible(true);
	}
	public ParamsPanelAdd(List<ParamFromRptDesign> paramsFromDesign) {
		
		this.spp = super.settingParamsPanel;
		
		setBounds(100, 100, 932, 288);
		// set params
		spp.addlistParams(paramsFromDesign);
		// init params
		initParams = super.settingParamsPanel.getlistOfParamsNotCheck();
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			
				int size = initParams.size();

				if (size != 0) TabAddReport.getInstance().getNewParamButton().setStandartHover();
				else TabAddReport.getInstance().getNewParamButton().setEmptyHover();
			}
		});
		this.setVisible(true);
	}
	public void setParams(List<ParamFromRptDesign> paramsFromDesign) {
		spp.addlistParams(paramsFromDesign);
	}
	public Vector<ParamFromParamsPanel> getInitParams() {
		return initParams;
	}
}
