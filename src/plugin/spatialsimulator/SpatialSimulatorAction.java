package plugin.spatialsimulator;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import jp.sbi.celldesigner.plugin.PluginAction;
import jp.sbi.celldesigner.plugin.PluginModel;

import org.sbml.jsbml.JSBML;
import org.sbml.jsbml.SBMLDocument;

/**
 * The Class SpatialSimulatorAction.
 *
 * @author Kaito Ii
 * 
 * Date Created: Jun 24, 2016
 */

public class SpatialSimulatorAction extends PluginAction {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2321778927487000209L;

	/** The plugin. */
	private SpatialSimulatorPlugin plugin;
	
	/**
	 * Instantiates a new spatial simulator action.
	 *
	 * @param plugin the plugin
	 */
	public SpatialSimulatorAction(SpatialSimulatorPlugin plugin) {
		this.plugin = plugin;
	}
	
	/* (non-Javadoc)
	 * @see jp.sbi.celldesigner.plugin.PluginActionListener#myActionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void myActionPerformed(ActionEvent arg0) {
		plugin.setStarted(true);
		PluginModel pModel = plugin.getSelectedModel();

		SBMLDocument document;
		try {
			document = JSBML.readSBMLFromString(pModel.getPureSBMLString());
			SpatialSimulatorHandler ssh = SpatialSimulatorHandler.getInstance();
			ssh.setDocument(document);
			ssh.showSimulatorDialog();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid model", "Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} 
		
	}

}
