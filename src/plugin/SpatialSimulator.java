package plugin;

import org.sbml.libsbml.Model;
import org.sbml.libsbml.SBMLDocument;

/**
 * @author Kaito Ii
 *
 * Date Created: Jun 24, 2016
 */

public class SpatialSimulator {
	
	private static SpatialSimulator spatialSimulator = new SpatialSimulator();
	
	private SBMLDocument document;
	
	private Model model;
	
	private SpatialSimulator(){
		
	}
	
	public static SpatialSimulator getInstance(){
		return spatialSimulator;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public SBMLDocument getDocument() {
		return document;
	}

	public void setDocument(SBMLDocument document) {
		this.document = document;
	}
	
	
}
