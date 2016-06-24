package plugin;

import jp.sbi.celldesigner.plugin.CellDesignerPlugin;
import jp.sbi.celldesigner.plugin.PluginMenu;
import jp.sbi.celldesigner.plugin.PluginMenuItem;
import jp.sbi.celldesigner.plugin.PluginSBase;

// TODO: Auto-generated Javadoc
/**
 * The Class SpatialSimulatorPlugin.
 *
 * @author Kaito Ii
 * 
 * Date Created: Jun 24, 2016
 */

public class SpatialSimulatorPlugin extends CellDesignerPlugin {
	
	/**
	 * Instantiates a new spatial simulator plugin.
	 */
	public SpatialSimulatorPlugin(){
		PluginMenu menu = new PluginMenu(SpatialSimulatorUtil.title);
		SpatialSimulatorAction action = new SpatialSimulatorAction(this);
		PluginMenuItem item = new PluginMenuItem(SpatialSimulatorUtil.title, action);
		menu.add(item);
		addCellDesignerPluginMenu(menu);
	}
	
	/* (non-Javadoc)
	 * @see jp.sbi.celldesigner.plugin.CellDesignerPlug#SBaseAdded(jp.sbi.celldesigner.plugin.PluginSBase)
	 */
	@Override
	public void SBaseAdded(PluginSBase arg0) {
		//TODO check if this needs implementation 		
	}

	/* (non-Javadoc)
	 * @see jp.sbi.celldesigner.plugin.CellDesignerPlug#SBaseChanged(jp.sbi.celldesigner.plugin.PluginSBase)
	 */
	@Override
	public void SBaseChanged(PluginSBase arg0) {
		//TODO check if this needs implementation 
	}

	/* (non-Javadoc)
	 * @see jp.sbi.celldesigner.plugin.CellDesignerPlug#SBaseDeleted(jp.sbi.celldesigner.plugin.PluginSBase)
	 */
	@Override
	public void SBaseDeleted(PluginSBase arg0) {
		//TODO check if this needs implementation 
	}

	/* (non-Javadoc)
	 * @see jp.sbi.celldesigner.plugin.CellDesignerPlug#addPluginMenu()
	 */
	@Override
	public void addPluginMenu() {
	}

	/* (non-Javadoc)
	 * @see jp.sbi.celldesigner.plugin.CellDesignerPlug#modelClosed(jp.sbi.celldesigner.plugin.PluginSBase)
	 */
	@Override
	public void modelClosed(PluginSBase arg0) {
	}

	/* (non-Javadoc)
	 * @see jp.sbi.celldesigner.plugin.CellDesignerPlug#modelOpened(jp.sbi.celldesigner.plugin.PluginSBase)
	 */
	@Override
	public void modelOpened(PluginSBase arg0) {
	}

	/* (non-Javadoc)
	 * @see jp.sbi.celldesigner.plugin.CellDesignerPlug#modelSelectChanged(jp.sbi.celldesigner.plugin.PluginSBase)
	 */
	@Override
	public void modelSelectChanged(PluginSBase sbase) {
	}

}
