package plugin.spatialsimulator;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.xml.stream.XMLStreamException;

import jp.sbi.celldesigner.plugin.PluginAction;
import jp.sbi.celldesigner.plugin.PluginModel;

import org.sbml.jsbml.JSBML;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;

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
	SpatialSimulatorPlugin plugin;
	
	/** The simulator dialog. */
	SpatialSimulatorDialog simulatorDialog;
	
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
			simulatorDialog = new SpatialSimulatorDialog(document);
			simulatorDialog.pack();
			simulatorDialog.toFront();
			simulatorDialog.setAlwaysOnTop(true);
			simulatorDialog.setVisible(true);
		} catch (XMLStreamException e) {
			JOptionPane.showMessageDialog(null, "Invalid model", "Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch(SBMLException e){
			JOptionPane.showMessageDialog(null, "Invalid model", "Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}

}
