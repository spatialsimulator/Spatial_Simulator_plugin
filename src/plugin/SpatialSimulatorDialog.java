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
import org.sbml.libsbml.SpatialModelPlugin;
import org.sbml.libsbml.libsbml;


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
	
	static {
		String varname;
		String shlibname;

		if (System.getProperty("os.name").startsWith("Mac OS")) {
			varname = "DYLD_LIBRARY_PATH"; // We're on a Mac.
			shlibname = "'libsbmlj.jnilib'";
		} else {
			varname = "LD_LIBRARY_PATH"; // We're not on a Mac.
			shlibname = "'libsbmlj.so' and/or 'libsbml.so'";
		}

		try {
			System.loadLibrary("sbmlj");
			// For extra safety, check that the jar file is in the classpath.
			Class.forName("org.sbml.libsbml.libsbml");
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Error encountered while attempting to load libSBML:");
			System.err.println("Please check the value of your " + varname
					+ " environment variable and/or"
					+ " your 'java.library.path' system property"
					+ " (depending on which one you are using) to"
					+ " make sure it list the directories needed to"
					+ " find the " + shlibname + " library file and the"
					+ " libraries it depends upon (e.g., the XML parser).");
			System.exit(1);
		} catch (ClassNotFoundException e) {
			System.err.println("Error: unable to load the file 'libsbmlj.jar'."
					+ " It is likely that your -classpath command line "
					+ " setting or your CLASSPATH environment variable "
					+ " do not include the file 'libsbmlj.jar'.");
			e.printStackTrace();

			System.exit(1);
		} catch (SecurityException e) {
			System.err.println("Error encountered while attempting to load libSBML:");
			e.printStackTrace();
			System.err.println("Could not load the libSBML library files due to a"
					+ " security exception.\n");
			System.exit(1);
		}
	}
	
	/** The document. */
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
		
	/** The xoption. */
	private final String xoption = "-x";
	
	/** The yoption. */
	private final String yoption = "-y";
	
	/** The zoption. */
	private final String zoption = "-z";
	
	/** The toption. */
	private final String toption = "-t";
	
	/** The doption. */
	private final String doption = "-d";
	
	/** The ooption. */
	private final String ooption = "-o";
	
	/** The coption. */
	private final String coption = "-c";
	
	/** The Coption. */
	private final String Coption = "-C";
	
	/** The soption. */
	private final String soption = "-s";
	
	
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
		sliceCombo.setSelectedItem(sliceAxis[2]);
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
		hgroup.addGroup(layout.createParallelGroup().addComponent(panels));
		hgroup.addGroup(layout.createParallelGroup().addComponent(panelc));
		hgroup.addGroup(layout.createParallelGroup().addComponent(panelC));
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.add(clearButton);
		panel2.add(runButton);
		clearButton.addActionListener(this);
		runButton.addActionListener(this);
		getContentPane().add(panel,BorderLayout.CENTER);
		getContentPane().add(panel2,BorderLayout.SOUTH);
		
	}
	
	public SpatialSimulatorDialog(SBMLDocument document){
		this();
		this.document = document;
	}
	
	/**
	 * Gets the document.
	 *
	 * @return the document
	 */
	public SBMLDocument getDocument() {
		return document;
	}

	/**
	 * Sets the document.
	 *
	 * @param document the new document
	 */
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
	 *
	 * @throws Exception the exception
	 */
	public void validateArguments() throws Exception {
		if(xField.getText().isEmpty() || yField.getText().isEmpty() 
				|| tField.getText().isEmpty() || dtField.getText().isEmpty()
				|| outputField.getText().isEmpty() || maxColorField.getText().isEmpty()
				|| minColorField.getText().isEmpty())
			throw new NullPointerException();
		
		// check slice index
		
		if(sliceCombo.getSelectedItem().equals("x") && Integer.parseInt(sliceField.getText()) > Integer.parseInt(xField.getText()))
						throw new IllegalArgumentException();
		else if(sliceCombo.getSelectedItem().equals("y") && Integer.parseInt(sliceField.getText()) > Integer.parseInt(yField.getText()))
			throw new IllegalArgumentException();
		else if(sliceCombo.getSelectedItem().equals("z") && Integer.parseInt(sliceField.getText()) > Integer.parseInt(zField.getText()))
			throw new IllegalArgumentException();
		
	}
	
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
		if(isModel3d())
			argList.add(soption);
			argList.add((String) sliceCombo.getSelectedItem()+sliceField.getText());
		
		
		return argList.toArray(new String[argList.size()]);
	}
	
	private boolean isModel3d(){
		SpatialModelPlugin spatialplugin = (SpatialModelPlugin) document.getModel().getPlugin("spatial");
		long size = spatialplugin.getGeometry().getListOfCoordinateComponents().size();
		
		if(size == 3)
			return true;
		else
			return false;
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
					return;
				} catch (Exception e1) {
					e1.printStackTrace();
					return;
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
		SBMLDocument document = libsbml.readSBML("sample/mem_diff.xml");
		ssd.setDocument(document);
		ssd.setVisible(true);
	}



}
