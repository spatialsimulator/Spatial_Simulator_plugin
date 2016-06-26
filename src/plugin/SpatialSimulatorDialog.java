package plugin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.sbml.libsbml.SBMLDocument;

import plugin.newsample.NotifySelected.MessageDialog;
import jp.sbi.celldesigner.plugin.PluginModel;

// TODO: Auto-generated Javadoc
/**
 * The Class SpatialSimulatorDialog.
 *
 * @author Kaito Ii
 * 
 * Date Created: Jun 24, 2016
 */

@SuppressWarnings("serial")
public class SpatialSimulatorDialog extends JFrame implements ActionListener{

	/** The p model. */
	private PluginModel pModel; 
	
	private SBMLDocument document;
	
	/** The default text length. */
	private final int defaultTextLength = 5;
	
	/** The x label. */
	private JLabel xLabel = new JLabel("x Mesh Size");
	
	/** The x field. */
	private JTextField xField = new JTextField("100", defaultTextLength);
	
	/** The y label. */
	private JLabel yLabel = new JLabel("y Mesh Size");
	
	/** The y field. */
	private JTextField yField = new JTextField("100", defaultTextLength);
	
	/** The z label. */
	private JLabel zLabel = new JLabel("z Mesh Size");
	
	/** The z field. */
	private JTextField zField = new JTextField("100", defaultTextLength);
	
	/** The t label. */
	private JLabel tLabel = new JLabel("Time Interval");
	
	/** The t field. */
	private JTextField tField = new JTextField("1", defaultTextLength);
	
	/** The dt label. */
	private JLabel dtLabel = new JLabel("Step Size");
	
	/** The dt field. */
	private JTextField dtField = new JTextField("0.01", defaultTextLength);
	
	/** The output label. */
	private JLabel outputLabel = new JLabel("Output Step Size");
	
	/** The output field. */
	private JTextField outputField = new JTextField("10", defaultTextLength);
	
	/** The max color label. */
	private JLabel maxColorLabel = new JLabel("Color Bar Max");
	
	/** The max color field. */
	private JTextField maxColorField = new JTextField("10", defaultTextLength);
	
	/** The min color label. */
	private JLabel minColorLabel = new JLabel("Color Bar Min");
	
	/** The min color field. */
	private JTextField minColorField = new JTextField("0", defaultTextLength);
	
	/** The slice axis. */
	private final String[] sliceAxis = {"x","y","z"};
	
	/** The slice label. */
	private JLabel sliceLabel = new JLabel("Slice");
	
	/** The slice combo. */
	private JComboBox<String> sliceCombo = new JComboBox<String>(sliceAxis);
	
	/** The slice field. */
	private JTextField sliceField = new JTextField("0",defaultTextLength);
	
	/** The clear button. */
	private JButton clearButton = new JButton("Clear");
	
	/** The run button. */
	private JButton runButton = new JButton("Run");
	

	/**
	 * Instantiates a new spatial simulator dialog.
	 */
	public SpatialSimulatorDialog(){
		super(SpatialSimulatorUtil.title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(500, 300);
		this.setLocationByPlatform(true);
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(400,300));
		this.setMaximumSize(new Dimension(800,400));
		this.addComponentListener(new ComponentAdapter() {
		      public void componentResized(ComponentEvent evt) {
		          JFrame frame = (JFrame) evt.getSource();
		    	  Dimension size = frame.getSize();
		    	  Dimension min = frame.getMinimumSize();
		    	  Dimension max = frame.getMaximumSize();
		          if (size.getWidth() < min.getWidth()) {
		            frame.setSize((int) min.getWidth(), (int) size.getHeight());
		          }
		          if (size.getWidth() > max.getWidth()) {
			            frame.setSize((int) max.getWidth(), (int) size.getHeight());
			          }
		          if (size.getHeight() < min.getHeight()) {
		            frame.setSize((int) size.getWidth(), (int) min.getHeight());
		          }
		          if (size.getHeight() > max.getHeight()) {
			            frame.setSize((int) size.getWidth(), (int) max.getHeight());
			      }
		        }
		      });
		JPanel panelx = new JPanel();
		panelx.setLayout(new FlowLayout());
		panelx.add(xLabel);
		panelx.add(xField);
		JPanel panely = new JPanel();
		panely.setLayout(new FlowLayout());
		panely.add(yLabel);
		panely.add(yField);
		JPanel panelz = new JPanel();
		panelz.setLayout(new FlowLayout());
		panelz.add(zLabel);
		panelz.add(zField);
		JPanel panelt = new JPanel();
		panelt.setLayout(new FlowLayout());
		panelt.add(tLabel);
		panelt.add(tField);
		JPanel paneldt = new JPanel();
		paneldt.setLayout(new FlowLayout());
		paneldt.add(dtLabel);
		paneldt.add(dtField);
		JPanel panelo = new JPanel();
		panelo.setLayout(new FlowLayout());
		panelo.add(outputLabel);
		panelo.add(outputField);
		JPanel panelc = new JPanel();
		panelc.setLayout(new FlowLayout());
		panelc.add(maxColorLabel);
		panelc.add(maxColorField);
		JPanel panelC = new JPanel();
		panelC.setLayout(new FlowLayout());
		panelC.add(minColorLabel);
		panelC.add(minColorField);
		JPanel panels = new JPanel();
		panels.setLayout(new FlowLayout());
		panels.add(sliceLabel);
		panels.add(sliceCombo);
		panels.add(sliceField);
		
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		  
		GroupLayout.SequentialGroup hgroup = layout.createSequentialGroup();
		hgroup.addGroup(layout.createParallelGroup().addComponent(panelx));
		hgroup.addGroup(layout.createParallelGroup().addComponent(panely));
		hgroup.addGroup(layout.createParallelGroup().addComponent(panelz));
		hgroup.addGroup(layout.createParallelGroup().addComponent(panelt));
		hgroup.addGroup(layout.createParallelGroup().addComponent(paneldt));
		hgroup.addGroup(layout.createParallelGroup().addComponent(panelo));
		hgroup.addGroup(layout.createParallelGroup().addComponent(panelc));
		hgroup.addGroup(layout.createParallelGroup().addComponent(panelC));
		hgroup.addGroup(layout.createParallelGroup().addComponent(panels));
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.add(clearButton);
		panel2.add(runButton);
		clearButton.addActionListener(this);
		runButton.addActionListener(this);
		getContentPane().add(panel,BorderLayout.CENTER);
		getContentPane().add(panel2,BorderLayout.SOUTH);
		
	}

	public SBMLDocument getDocument() {
		return document;
	}

	public void setDocument(SBMLDocument document) {
		this.document = document;
	}
	
	/**
	 * Clear entrys.
	 */
	public void clearEntrys(){
		xField.setText("");
		yField.setText("");
		zField.setText("");
		tField.setText("");
		dtField.setText("");
		outputField.setText("");
		minColorField.setText("");
		maxColorField.setText("");
		sliceField.setText("");
	}
	
	/**
	 * Validate arguments.
	 * @throws Exception 
	 */
	public void validateArguments() throws Exception {
		if(xField.getText().isEmpty() || yField.getText().isEmpty() 
				|| tField.getText().isEmpty() || dtField.getText().isEmpty()
				|| outputField.getText().isEmpty() || maxColorField.getText().isEmpty()
				||minColorField.getText().isEmpty())
			throw new NullPointerException();

	}
	
	private final String xoption = "-x";
	private final String yoption = "-y";
	private final String zoption = "-z";
	private final String toption = "-t";
	private final String doption = "-d";
	private final String ooption = "-o";
	private final String coption = "-c";
	private final String Coption = "-C";
	private final String soption = "-s";
	
	
	/**
	 * Gets the arguments.
	 *
	 * @return the arguments
	 */
	public String[] getArguments(){
		List<String> argList = new ArrayList<String>();
		
		argList.add(xoption);
		argList.add(xField.getText());
		argList.add(yoption);
		argList.add(yField.getText());
		argList.add(zoption);
		argList.add(zField.getText());
		argList.add(toption);
		argList.add(tField.getText());
		argList.add(doption);
		argList.add(dtField.getText());
		argList.add(ooption);
		argList.add(outputField.getText());
		argList.add(coption);
		argList.add(maxColorField.getText());
		argList.add(Coption);	
		argList.add(minColorField.getText());
		argList.add(soption);	
		argList.add(sliceField.getText());
		
		
		return argList.toArray(new String[argList.size()]);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		
		if(button.getActionCommand().equals("Clear")){
			clearEntrys();
		} else {
				try {
					validateArguments();
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Missing argument");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				SpatialSimulator simulator = SpatialSimulator.getInstance();
				String[] args = getArguments();
				simulator.simulate(document, args.length, args);
		}
		
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		SpatialSimulatorDialog ssd = new SpatialSimulatorDialog();
		ssd.setVisible(true);
	}



}
