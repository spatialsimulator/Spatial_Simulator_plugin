package jp.sbi.celldesigner.ext.plugins.spatialsimulator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import jp.co.mki.celldesigner.simulation.util.SimulationProperties;
import jp.sbi.celldesigner.SBMLFiler;

import org.sbml.libsbml.SBMLDocument;
import org.sbml.libsbml.SBMLWriter;

import com.sun.jna.Library;

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

		writeXML(new File(sbml_file));
		
		System.out.println(document.toSBML());
		
		for(int i = 0; i < argv.length; i++)
			System.out.println(argv[i]);
	}
	
	
	/**
	 * The Interface SpatialSimulator.
	 */
	public interface SpatialSimulator extends Library {
		//SpatialSimulator INSTANCE = (SpatialSimulator) Native.loadLibrary((Platform.isWindows() ? "" : "", );
	
		// methods
	}
	
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
				}
			} else {
				SBMLWriter writer = new SBMLWriter();
				writer.writeSBMLToFile(document, resultFileName.getAbsolutePath());
			}
	}
	
}
