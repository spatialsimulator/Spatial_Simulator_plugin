package plugin.spatialsimulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.xml.stream.XMLStreamException;

import jp.co.mki.celldesigner.simulation.util.SimulationProperties;
import jp.sbi.celldesigner.SBMLFiler;

import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLWriter;


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
	 * @param argc the argc
	 * @param argv the argv
	 */
	public void simulate(SBMLDocument document, int argc, String[] argv) {
		this.document = document;
		
		String sbml_file = SimulationProperties.simDirPath 
				+ System.getProperty("file.separator") + "input"  
				+ System.getProperty("file.separator") + "spatialsimulator_input.xml";

		//writeXML(new File(sbml_file));
		
		print();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < argv.length; i++){
			sb.append(argv[i]).append(" ");
		}
		sb.append(sbml_file);
		System.out.println(sb.toString());	
	}
	

//	/**
//	 * The Interface SpatialSimulator.
//	 */
//	public interface SpatialSimulator extends Library {
//		//SpatialSimulator INSTANCE = (SpatialSimulator) Native.loadLibrary((Platform.isWindows() ? "" : "", );
//	
//		// methods
//	}
	
	/**
	 * Write XML.
	 *
	 * @param resultFileName the result file name
	 */
	private void writeXML(File resultFileName) {
			// 日本語ファイル名対応
			if (!SBMLFiler.isAllowedFileName(resultFileName.getAbsolutePath())) {
				try {
					OutputStreamWriter out = new OutputStreamWriter(
							new FileOutputStream(resultFileName
									.getAbsolutePath()), "UTF-8");

					SBMLWriter writer = new SBMLWriter();
					out.write(writer.writeSBMLToString(document));
					out.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				SBMLWriter writer = new SBMLWriter();
				try {
					writer.writeSBMLToFile(document, resultFileName.getAbsolutePath());
				} catch (SBMLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (XMLStreamException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	

	public void print(){
		try {
			SBMLWriter.write(document, System.out, ' ', (short)2);
		} catch (SBMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
