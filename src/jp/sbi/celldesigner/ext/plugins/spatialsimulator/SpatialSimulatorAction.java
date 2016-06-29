package jp.sbi.celldesigner.ext.plugins.spatialsimulator;

import java.awt.event.ActionEvent;

import org.sbml.libsbml.SBMLDocument;
import org.sbml.libsbml.libsbml;

import jp.sbi.celldesigner.plugin.PluginAction;
import jp.sbi.celldesigner.plugin.PluginModel;

// TODO: Auto-generated Javadoc
/**
 * The Class SpatialSimulatorAction.
 *
 * @author Kaito Ii
 * 
 * Date Created: Jun 24, 2016
 */

@SuppressWarnings("serial")
public class SpatialSimulatorAction extends PluginAction{

	/** The plugin. */
	SpatialSimulatorPlugin plugin;
	
	/** The simulator dialog. */
	SpatialSimulatorDialog simulatorDialog = new SpatialSimulatorDialog();
	
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
		SBMLDocument document = libsbml.readSBMLFromString(pModel.getPureSBMLString());
		simulatorDialog.setDocument(document);
		simulatorDialog.setVisible(true);
	}

}
