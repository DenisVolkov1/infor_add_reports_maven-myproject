package windows.tabs.update;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import java.util.Vector;

import database.ParamFromDataBase;
import database.ParamsRelatedData;
import exception.InfoException;
import windows.param.ParamFromParamsPanel;
import windows.param.ParamsPanel;

public class ParamsPanelUpdate extends ParamsPanel {
	
	private Vector <ParamFromParamsPanel> initParams = null;
	private int startHeight = 88;
	private String RPT_IDInput;
	
	public ParamsPanelUpdate(String RPT_ID) throws Exception {
	
		this.RPT_IDInput = RPT_ID; 
		setBounds(100, 100, 652, 88);
		// set params
			Vector<ParamFromDataBase> params = ParamsRelatedData.getListOfParam(RPT_ID);
			settingParamsPanel.addlistParams(params);
		// init params
			initParams = settingParamsPanel.getlistOfParamsNotCheck();
			
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					if (isChangeParams()) TabUpdateReport.getInstance().getParamsButton().setStandartHover();
					else TabUpdateReport.getInstance().getParamsButton().setEmptyHover();
				}
			});
			
			int plusHeight = params.size() * 34;
			setBounds(100, 100, 757, startHeight + 50 + plusHeight);
	setVisible(true);		
	}

	public Vector<ParamFromParamsPanel> getInitParams() {
		return initParams;
	}
	
	public boolean isChangeParams() {
		return !initParams.equals(settingParamsPanel.getlistOfParamsNotCheck());
	}

	public String getRPT_IDInput() {
		return RPT_IDInput;
	}

}
