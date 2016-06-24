package plugin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import jp.sbi.celldesigner.plugin.PluginModel;

/**
 * @author Kaito Ii
 *
 * Date Created: Jun 24, 2016
 */

@SuppressWarnings("serial")
public class SpatialSimulatorDialog extends JFrame implements ActionListener{

	PluginModel pModel; 
	
	public SpatialSimulatorDialog(){
		super();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
