package plugin;

import org.sbml.libsbml.SBMLDocument;

// TODO: Auto-generated Javadoc
/**
 * The Class SpatialSimulator.
 *
 * @author Kaito Ii
 * 
 * Date Created: Jun 24, 2016
 */

public class SpatialSimulator {
	
	/** The spatial simulator. */
	private static SpatialSimulator spatialSimulator;
	
	/** The document. */
	private SBMLDocument document;	
	
	/**
	 * Gets the single instance of SpatialSimulator.
	 *
	 * @return single instance of SpatialSimulator
	 */
	public static SpatialSimulator getInstance(){
		if(spatialSimulator == null){
			spatialSimulator = new SpatialSimulator();
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
		System.out.println(document.toSBML());
		
		for(int i = 0; i < argv.length; i++)
			System.out.println(argv[i]);
	}
	
	
}
