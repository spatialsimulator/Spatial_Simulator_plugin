package plugin.spatialsimulator;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.JSBML;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.ext.spatial.SpatialConstants;
import org.sbml.jsbml.ext.spatial.SpatialModelPlugin;

import plugin.spatialsimulator.SpatialSimulatorHandler.SpatialSimulator.optionList;


// TODO: Auto-generated Javadoc
/**
 * The Class SpatialSimulatorDialog.
 *
 * @author Kaito Ii
 * 
 * Date Created: Jun 24, 2016
 */


public class SpatialSimulatorDialog extends JFrame implements ActionListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2276253283810399783L;
	
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
	private JComboBox sliceCombo = new JComboBox(sliceAxis);
	
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
		this.setSize(600, 250);
		this.setLocationByPlatform(true);
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(600,250));
		this.setMaximumSize(new Dimension(800,400));
		this.setPreferredSize(new Dimension(600,250));
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
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		panel.setLayout(layout);
		gbc.gridx = 0;
		gbc.gridy = 0;
		layout.setConstraints(panelx, gbc);
		panel.add(panelx);
		gbc.gridx = 1;
		gbc.gridy = 0;
		layout.setConstraints(panely, gbc);
		panel.add(panely);
		gbc.gridx = 2;
		gbc.gridy = 0;
		layout.setConstraints(panelz, gbc);
		panel.add(panelz);
		gbc.gridx = 0;
		gbc.gridy = 1;
		layout.setConstraints(panelt, gbc);
		panel.add(panelt);
		gbc.gridx = 1;
		gbc.gridy = 1;
		layout.setConstraints(paneldt, gbc);
		panel.add(paneldt);
		gbc.gridx = 2;
		gbc.gridy = 1;
		layout.setConstraints(panelo, gbc);
		panel.add(panelo);
		gbc.gridx = 0;
		gbc.gridy = 2;
		layout.setConstraints(panelc, gbc);
		panel.add(panelc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		layout.setConstraints(panelC, gbc);
		panel.add(panelC);
		gbc.gridx = 2;
		gbc.gridy = 2;
		layout.setConstraints(panels, gbc);
		panel.add(panels);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.add(clearButton);
		panel2.add(runButton);
		clearButton.addActionListener(this);
		runButton.addActionListener(this);
		getContentPane().add(panel,BorderLayout.CENTER);
		getContentPane().add(panel2,BorderLayout.SOUTH);
		
	}
	
	/**
	 * Instantiates a new spatial simulator dialog.
	 *
	 * @param document the document
	 * @throws SBMLException the SBML exception
	 */
	public SpatialSimulatorDialog(SBMLDocument document) throws SBMLException{
		this();
		this.document = document;
//		if(!document.getPackageRequired(SpatialConstants.namespaceURI))
//			throw new SBMLException();
		// TODO fill default mesh size with boundary size / image size
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
	 * @return true, if is valid arguments
	 */
	public boolean isValidArguments() {
		if(xField.getText().isEmpty() || yField.getText().isEmpty() 
				|| tField.getText().isEmpty() || dtField.getText().isEmpty()
				|| outputField.getText().isEmpty() || maxColorField.getText().isEmpty()
				|| minColorField.getText().isEmpty())
			return false;
			
		if(sliceCombo.getSelectedItem().equals("x") && Integer.parseInt(sliceField.getText()) > Integer.parseInt(xField.getText()))
			return false;
		else if(sliceCombo.getSelectedItem().equals("y") && Integer.parseInt(sliceField.getText()) > Integer.parseInt(yField.getText()))
			return false;
		else if(sliceCombo.getSelectedItem().equals("z") && Integer.parseInt(sliceField.getText()) > Integer.parseInt(zField.getText()))
			return false;
		
		return true;
	}
	
	/**
	 * Gets the arguments.
	 *
	 * @return the arguments
	 */
	public optionList getArguments(){
		optionList options = new optionList();
		
		options.Xdiv = Integer.parseInt(xField.getText());
		options.Ydiv = Integer.parseInt(yField.getText());
		options.Zdiv = Integer.parseInt(zField.getText());
		options.end_time = Double.parseDouble(tField.getText());
		options.dt = Double.parseDouble(dtField.getText());
		options.out_step = Integer.parseInt(outputField.getText());
		options.range_max = Double.parseDouble(maxColorField.getText());
		options.range_min = Double.parseDouble(minColorField.getText());

//		if(isModel3d()){
//			options.slicedim = ((String) sliceCombo.getSelectedItem()).getBytes()[0];
//			options.slice = Integer.parseInt(sliceField.getText());
//			options.sliceFlag = true;
//		}
			
		return options;
	}
	
	/**
	 * Checks if is model 3 d.
	 *
	 * @return true, if is model 3 d
	 */
	private boolean isModel3d(){
		SpatialModelPlugin spatialplugin = (SpatialModelPlugin) document.getModel().getPlugin(SpatialConstants.namespaceURI);
		long size = spatialplugin.getGeometry().getListOfCoordinateComponents().size();
		
		if(size == 3)
			return true;
		else
			return false;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();

		if (button.getActionCommand().equals("Clear")) {
			clearEntrys();
		} else {

			if (!isValidArguments()) {
				JOptionPane.showMessageDialog(null, "Missing argument");
				return;
			}
			
			SpatialSimulatorHandler simulator = SpatialSimulatorHandler.getInstance();
			optionList options = getArguments();

			try {
				simulator.simulate(document, options);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Unable to write SBML document");
			}
		}

	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		SpatialSimulatorDialog ssd = new SpatialSimulatorDialog();
		SBMLDocument document;
		try {
			//document = JSBML.readSBML("sample/mem_diff.xml");
			document = JSBML.readSBML("sample/hogehoge.xml");
			System.setProperty("jna.debug_load", "true");
			//System.setProperty("java.library.path", "/usr/local/lib/");
			ssd.setDocument(document);
			ssd.setVisible(true);
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
