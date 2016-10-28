package plugin.spatialsimulator;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;
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
	
	/**
	 * Instantiates a new spatial simulator handler.
	 */
	private SpatialSimulatorHandler(){
		
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
		options.fname = document.getModel().getId();
		options.docFlag = true;
		options.document = new SBMLWriter().writeSBMLToString(document);
		SpatialSimulator spatialsim = SpatialSimulator.INSTANCE;
		spatialsim.spatialSimulator(options);
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
			public int Xdiv;
			
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
			public boolean sliceFlag;
			
			/** The slice. */
			public int slice;		
			
			/** The slicedim. */
			public byte slicedim;
			
			/** The fname. */
			public String fname;
	
			/** The doc flag. */
			public boolean docFlag;
			
			/** The document. */
			public String document;
			
			/* (non-Javadoc)
			 * @see com.sun.jna.Structure#getFieldOrder()
			 */
			@Override
			protected List<?> getFieldOrder() {
				return Arrays.asList(new String[]{"Xdiv","Ydiv","Zdiv","end_time","dt","out_step","range_max","range_min","sliceFlag","slice","slicedim","fname", "docFlag", "document"});
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
	}
}
