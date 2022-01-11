package edu.uncc.algorithm;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import edu.uncc.countrymaps.MapOfAmerica;
import edu.uncc.countrymaps.MapOfAustralia;
import edu.uncc.utility.*;


public class ExecuteConstraintSatisfactionProblem {

	private static final int TIMES_TO_EXECUTE = 4;

	public static void main(String[] args) {

		System.out.println("Executing CSP for Map of Australia");
		MapOfAustralia mapOfAustralia = new MapOfAustralia();
		executeCSPForTheMap("Map of Australia", mapOfAustralia);
		System.out.println("Finished Execution for Map of Australia");

		System.out.println("Starting Execution for Map of America");
		MapOfAmerica mapOfAmerica = new MapOfAmerica();
		executeCSPForTheMap("Map of America", mapOfAmerica);
		System.out.println("Finished Execution for Map of America");
	}

	private static void executeCSPForTheMap(final String mapCountryName,
			final CSP mapToExecuteCSP) {

		Map<String, OutputSummary> outputStatsMap = new LinkedHashMap<>();

		Backtracking cspBacktracking = new Backtracking();

		// Without using heuristics:
		System.out.println("Without Heuristic: ");
		outputStatsMap.put("Ouptput without heuristic", performCSPOperations(cspBacktracking, mapToExecuteCSP, "NONE", false, false));
		
		System.out.println("Without Heuristic and Forward Checks: ");
		outputStatsMap.put("Ouptput without heuristic", performCSPOperations(cspBacktracking, mapToExecuteCSP, "NONE", true, false));
		
		System.out.println("Without Heuristic, Forward Checks and Singleton: ");
		outputStatsMap.put("Ouptput without heuristic", performCSPOperations(cspBacktracking, mapToExecuteCSP, "NONE", true, true));


		System.out.println("With (MRV) Minimum Required Value: ");
		outputStatsMap.put("Output with MRV", performCSPOperations(cspBacktracking, mapToExecuteCSP, "MRV", false, false));

		System.out.println("With Degree Heuristic : ");
		outputStatsMap.put("Output with DEG heuristic", performCSPOperations(cspBacktracking, mapToExecuteCSP, "DEG", false, false));

		System.out.println("With (LCV) Least Constraining Value");
		outputStatsMap.put("Output with LCV heuristic", performCSPOperations(cspBacktracking, mapToExecuteCSP, "LCV", false, false));

		System.out.println("With MRV and Forward Checks");
		outputStatsMap.put("Output with MRV and Forward Checking",
				performCSPOperations(cspBacktracking, mapToExecuteCSP, "MRV", true, false));

		System.out.println("With Degree Heuristic and Forward Checks");
		outputStatsMap.put("Output with Degree Heuristic and Forward Checking",
				performCSPOperations(cspBacktracking, mapToExecuteCSP, "DEG", true, false));

		System.out.println("With Least Constraining Value and Forward Checks");
		outputStatsMap.put("Output with LCV and Forward Checking",
				performCSPOperations(cspBacktracking, mapToExecuteCSP, "LCV", true, false));

		System.out.println("With MRV and Forward Checks and Singleton");
		outputStatsMap.put("Output with MRV,Forward Checking and Singleton",
				performCSPOperations(cspBacktracking, mapToExecuteCSP, "MRV", true, true));

		System.out.println("With Degree Heuristic, Forward Checks and Singleton");
		outputStatsMap.put("Output with Degree Heuristic, Forward Checking and Singleton",
				performCSPOperations(cspBacktracking, mapToExecuteCSP, "DEG", true, true));

		System.out.println("With Least Constraining Value, Forward Checking and Singleton");
		outputStatsMap.put("Output with LCV with Forward Checking and Singleton",
				performCSPOperations(cspBacktracking, mapToExecuteCSP, "LCV", true, true));

		System.out.println(mapCountryName + ": Total number of iteration " + TIMES_TO_EXECUTE);
		
		for (String mapValue : outputStatsMap.keySet()) {
			OutputSummary outputStatistics = outputStatsMap.get(mapValue);
			
			System.out.printf("%-25s : Number of Backtracking happened is : %-3.2f, Time required (ms) is : %-6.2f\n",
					mapValue, outputStatistics.getBacktrackAverageCount(), outputStatistics.getAverageExecutionTime());
					
			
		}
		
	}

	private static OutputSummary performCSPOperations(final Backtracking cspBacktracking,
			final CSP countryMapCSP, final String strategyHeuristicChoice,
			final boolean useForwardChecks, final boolean checkForSingleton) {

		System.out.println("*********************************************************************************************************************************");

		OutputSummary outputStatistics = new OutputSummary();

		int totalNumberOfTimesBacktracked = 0;

		long totalExecutionTime = 0;

		final long executionStartTime = System.currentTimeMillis();

		for (int p = 0; p < TIMES_TO_EXECUTE; p++) {
			Calculation calculation = cspBacktracking.evaluate(countryMapCSP, strategyHeuristicChoice,
					useForwardChecks);
			totalNumberOfTimesBacktracked += calculation.returnBTCount();
			outputStatistics.insertNewCalculation(calculation);
			System.out.println("Iteration " + (p + 1) + " is finished \n");
		}

		final long executionEndTime = System.currentTimeMillis();

		totalExecutionTime = (executionEndTime - executionStartTime);

		if (checkForSingleton) {
			outputStatistics.setBacktrackAverageCount(
					(totalNumberOfTimesBacktracked / TIMES_TO_EXECUTE) * outputStatistics.getValueBacktrack());
			outputStatistics.setAverageExecutionTime(
					(totalExecutionTime / TIMES_TO_EXECUTE) * outputStatistics.getValueBacktrack());
		} else {
			outputStatistics.setBacktrackAverageCount(totalNumberOfTimesBacktracked / TIMES_TO_EXECUTE);
			outputStatistics.setAverageExecutionTime(totalExecutionTime / TIMES_TO_EXECUTE);
		}

		System.out.println("Final number of backtracking happened : " + outputStatistics.getBacktrackAverageCount());
		System.out.println();
		System.out.println("Total Execution Time (ms) : " + outputStatistics.getAverageExecutionTime());
		System.out.println();
		System.out.println("*********************************************************************************************************************************");

		return outputStatistics;
	}

}