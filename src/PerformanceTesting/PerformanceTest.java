package PerformanceTesting;

import java.io.IOException;

import org.jLOAF.Agent;
import org.jLOAF.performance.PerformanceEvaluator;

import AgentModules.OAAgent;
import CaseBaseCreation.LogFile2CaseBase;

public class PerformanceTest extends PerformanceEvaluator {
	public static void main(String a[]) throws IOException{
		String [] filenames = {"Data/log_toggle1.txt","Data/log_toggle2.txt","Data/log_toggle3.txt","Data/log_toggle4.txt","Data/log_toggle5.txt","Data/log_toggle6.txt","Data/log_toggle7.txt","Data/log_toggle8.txt","Data/log_toggle9.txt", "Data/log_toggle10.txt"};
		String output_filename = "Results/ctsmodel,bayesian,none,none,none,none,.csv";
	
		//CaseBaseFilter smote = new UnderSampling(standardize);
		//CaseBaseFilter sample = new Sampling(standardize);
		PerformanceTest pt = new PerformanceTest();
		pt.PerformanceEvaluatorMethod(filenames, null, output_filename,"TB",null, null);
	}

	@Override
	public String[] createArrayOfCasebaseNames(String[] filenames) throws IOException {
		LogFile2CaseBase lg2cb = new LogFile2CaseBase();
		int count = 0;
		String [] cbnames = new String [filenames.length];
		
		for(String s: filenames){
			String str = "Data/cb"+count+".cb";
			cbnames[count]=str;
			lg2cb.logParser(s,str);
			count++;
		}
		return cbnames;
	}

	@Override
	public Agent createAgent() {
		OAAgent agent = new OAAgent();
		return agent;
	}
}
