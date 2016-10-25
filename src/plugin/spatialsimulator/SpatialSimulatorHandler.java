package plugin.spatialsimulator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import jp.co.mki.celldesigner.simulation.util.SimulationProperties;
import jp.sbi.celldesigner.SBMLFiler;

import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLReader;
import org.sbml.jsbml.SBMLWriter;

import plugin.spatialsimulator.SpatialSimulatorHandler.SpatialSimulator.optionList;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Structure;


// TODO: Auto-generated Javadoc
/**
 * The Class SpatialSimulator.
 *
 * @author Kaito Ii
 * 
 * Date Created: Jun 24, 2016
 */

public class SpatialSimulatorHandler {
	
	/** The spatial simulator. */
	private static SpatialSimulatorHandler spatialSimulator;
	
	/** The document. */
	private SBMLDocument document;	
	
	private SpatialSimulatorHandler(){
		System.setProperty("jna.library.path", "lib/darwin/");
	}
	
	/**
	 * Gets the single instance of SpatialSimulator.
	 *
	 * @return single instance of SpatialSimulator
	 */
	public static SpatialSimulatorHandler getInstance(){
		if(spatialSimulator == null){
			spatialSimulator = new SpatialSimulatorHandler();
		}
		
		return spatialSimulator;
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
	 * void
	 * TODO.
	 *
	 * @param document the document
	 * @param options the options
	 * @throws SBMLException the SBML exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws XMLStreamException the XML stream exception
	 */
	public void simulate(SBMLDocument document, optionList options) throws SBMLException, IOException, XMLStreamException {
		this.document = document;
		
//		String sbml_file = SimulationProperties.simDirPath 
//				+ System.getProperty("file.separator") + "input"  
//				+ System.getProperty("file.separator") + "spatialsimulator_input.xml";

		String sbml_file = System.getProperty("user.dir") + "/sample/hogehoge.xml";
	//	writeXML(new File(sbml_file));
		options.fname = sbml_file;
	
		SpatialSimulator spatialsim = SpatialSimulator.INSTANCE;
		//spatialsim.getClass();
		System.out.println(options.toString());
		spatialsim.spatialSimulator(options);
		System.out.println("hgoehoge");
	}

	/**
	 * Write XML.
	 *
	 * @param resultFileName the result file name
	 * @throws SBMLException the SBML exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws XMLStreamException the XML stream exception
	 */
	private void writeXML(File resultFileName) throws SBMLException, IOException, XMLStreamException {
			// 日本語ファイル名対応
			if (!SBMLFiler.isAllowedFileName(resultFileName.getAbsolutePath())) {
			
					OutputStreamWriter out = new OutputStreamWriter(
							new FileOutputStream(resultFileName.getAbsolutePath()), "UTF-8");

					SBMLWriter writer = new SBMLWriter();
					out.write(writer.writeSBMLToString(document));
					out.close();
			} else {
				SBMLWriter writer = new SBMLWriter();
				writer.writeSBMLToFile(document, resultFileName.getAbsolutePath());
			}
	}

	/**
	 * The Interface SpatialSimulator.
	 */
	public interface SpatialSimulator extends Library {
		
		/**
		 * The Class optionList.
		 */
		public static class optionList extends Structure implements Structure.ByValue{
			
			/** The Xdiv. */
			public int Xdiv = 100;
			
			/** The Ydiv. */
			public int Ydiv;
			
			/** The Zdiv. */
			public int Zdiv;
			
			/** The end time. */
			public double end_time;
			
			/** The dt. */
			public double dt;
			
			/** The out step. */
			public int out_step;
			
			/** The range max. */
			public double range_max;
			
			/** The range min. */
			public double range_min;
			
			/** The slice flag. */
			public boolean sliceFlag = false;
			
			/** The slice. */
			public int slice;		
			
			/** The slicedim. */
			public byte slicedim;
			
			/** The fname. */
			public String fname;
	
			/* (non-Javadoc)
			 * @see com.sun.jna.Structure#getFieldOrder()
			 */
			@Override
			protected List<?> getFieldOrder() {
				return Arrays.asList(new String[]{"Xdiv","Ydiv","Zdiv","end_time","dt","out_step","range_max","range_min","sliceFlag","slice","slicedim","fname"});
			}
			
		}
		
		/** The instance. */
		public SpatialSimulator INSTANCE = (SpatialSimulator) Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "spatialsim") , SpatialSimulator.class);
	
		/**
		 * Spatial simulator.
		 *
		 * @param options the options
		 */
		public void spatialSimulator(optionList options);
		//public void spatialSimulator( int Xdiv, int Ydiv, int Zdiv, double end_time, double dt, int out_step, double range_max, double range_min, boolean sliceFlag, int slice, char slicedim);
		
	}

	public static void main(String[] args){
		try {
			SBMLDocument doc = SBMLReader.read(new File("sample/hogehoge.xml"));

			System.setProperty("jna.library.path", "lib/darwin/");
			SpatialSimulator spatialsim = SpatialSimulator.INSTANCE;
		//	spatialsim.spatialSimulator(100, 100, 100, 100, 1, 10, 10, 0, false, 0, 'x');
		} catch (XMLStreamException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
