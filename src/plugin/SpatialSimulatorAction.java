package plugin;

import java.awt.event.ActionEvent;

import jp.sbi.celldesigner.plugin.PluginAction;

/**
 * @author Kaito Ii
 *
 * Date Created: Jun 24, 2016
 */

@SuppressWarnings("serial")
public class SpatialSimulatorAction extends PluginAction{

	SpatialSimulatorPlugin plugin;
	
	SpatialSimulatorDialog simulatorDialog;
	
	public SpatialSimulatorAction(SpatialSimulatorPlugin plugin) {
		this.plugin = plugin;
		this.simulatorDialog = new SpatialSimulatorDialog();
	}
	
	/* (non-Javadoc)
	 * @see jp.sbi.celldesigner.plugin.PluginActionListener#myActionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void myActionPerformed(ActionEvent arg0) {
		plugin.setStarted(true);
		
	}

}
