package plugin.spatialsimulator;

import java.awt.Dialog;
import java.awt.Dimension;
import java.io.OutputStream;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * @author Kaito Ii
 *
 * Date Created: Nov 21, 2016
 */

public class SimulationTextDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8885853467761080811L;

	private JTextArea textArea;
	
	private JScrollPane scrollPane;
	
	public SimulationTextDialog(Dialog owner){
		super(owner);
		initDefault();
	}

	private void initDefault(){
		this.setLocationByPlatform(true);
		this.setLocationRelativeTo(null);
		this.setSize(new Dimension(600,300));
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		this.setTitle("Simulation Log");
		this.setModal(true);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setAlwaysOnTop(true);
		
		textArea = new JTextArea();
		textArea.setSize(new Dimension(600,300));
		textArea.setEditable(false);
		textArea.setLineWrap(false);

		scrollPane = new JScrollPane(textArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setSize(new Dimension(600,300));
		
		getContentPane().add(scrollPane);

	}
	
	public void clearTextArea(){
		textArea.removeAll();
	}
	
	class JTextAreaStream extends OutputStream{
		private JTextArea textArea;
		
		public JTextAreaStream(JTextArea textArea) {
			super();
			this.textArea = textArea;
		}
		
		/* (non-Javadoc)
		 * @see java.io.OutputStream#write(int)
		 */
		@Override
		public void write(int b){
			char[] chars = new char[1];
			chars[0] = (char)b;
			String s = new String(chars);
			textArea.append(s);
		}
		
		public void write(char buf[], int off, int len){
			String s = new String(buf, off, len);
			textArea.append(s);
		}
	}


}
