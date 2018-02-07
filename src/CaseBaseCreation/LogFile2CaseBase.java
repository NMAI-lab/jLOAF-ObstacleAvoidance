package CaseBaseCreation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.jLOAF.casebase.CaseBase;
import org.jLOAF.inputs.AtomicInput;
import org.jLOAF.inputs.Feature;
import org.jLOAF.sim.AtomicSimilarityMetricStrategy;
import org.jLOAF.sim.ComplexSimilarityMetricStrategy;
import org.jLOAF.sim.StateBasedSimilarity;
import org.jLOAF.sim.StateBased.KOrderedSimilarity;
import org.jLOAF.sim.atomic.Equality;
import org.jLOAF.sim.atomic.EuclideanDistance;
import org.jLOAF.sim.complex.Mean;

import AgentModules.OAAction;
import AgentModules.OAInput;

public class LogFile2CaseBase {
	public void logParser(String logfile, String outfile) throws IOException {
	
		AtomicSimilarityMetricStrategy Atomic_strat1 = new EuclideanDistance();
		AtomicSimilarityMetricStrategy Atomic_strat2 = new Equality();
		ComplexSimilarityMetricStrategy complex_strat = new Mean();
		StateBasedSimilarity stateBasedSim = new KOrderedSimilarity(10);
		
		OAInput mcinput;
		OAAction action;
		
		CaseBase cb = new CaseBase();
		
		BufferedReader br = new BufferedReader(new FileReader(logfile),'r');
		String Line;
		String [] input = new String [5];
		System.out.println("Creating casebase...");
		while ((Line = br.readLine()) != null){
			mcinput = new OAInput("Observation",complex_strat);
			
			input = Line.split(",");
			
			mcinput.add(new AtomicInput("sonar",new Feature(Double.parseDouble(input[0])),Atomic_strat1));
			mcinput.add(new AtomicInput("touch",new Feature(Double.parseDouble(input[1])),Atomic_strat2));
			mcinput.add(new AtomicInput("sound",new Feature(Double.parseDouble(input[2])),Atomic_strat2));
			action = new OAAction(input[4]);
			
			cb.createThenAdd(mcinput, action, stateBasedSim);	
		}
		
		System.out.println("CaseBase created.");
		br.close();
		System.out.println("Writing to file: " + outfile);
		CaseBase.save(cb, outfile);
		System.out.println("Done!");
	}

}
