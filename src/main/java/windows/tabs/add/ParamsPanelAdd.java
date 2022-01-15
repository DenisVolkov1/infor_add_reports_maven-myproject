package windows.tabs.add;

import java.awt.Dialog.ModalityType;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import exception.InfoException;
import windows.MainRunWindow;
import windows.param.ParamsPanel;

public class ParamsPanelAdd extends ParamsPanel {
	
	public ParamsPanelAdd() {

		setBounds(100, 100, 736, 288);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			
				int size = settingParamsPanel.getlistOfParamsNotCheck().size();

				if (size != 0) TabAddReport.getInstance().getNewParamButton().setStandartHover();
				else TabAddReport.getInstance().getNewParamButton().setEmptyHover();
			}
		});
		this.setVisible(true);
	}
}
