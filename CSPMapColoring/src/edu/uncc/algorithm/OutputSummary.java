package edu.uncc.algorithm;
import java.util.ArrayList;
import java.util.List;

import edu.uncc.utility.*;

public class OutputSummary {

	private double averageExecutionTime;
	private double backtrackAverageCount;
	private double valueBacktrack = 0.6;
	private int finalNumberOfExecutionSteps;
	private List<Calculation> listOfCalculations = new ArrayList<Calculation>();

	public OutputSummary() {

	}

	public OutputSummary(double averageExecutionTime, double backtrackAverageCount, double valueBacktrack,
			int finalNumberOfExecutionSteps, List<Calculation> listOfCalculations) {
		super();
		this.averageExecutionTime = averageExecutionTime;
		this.backtrackAverageCount = backtrackAverageCount;
		this.valueBacktrack = valueBacktrack;
		this.finalNumberOfExecutionSteps = finalNumberOfExecutionSteps;
		this.listOfCalculations = listOfCalculations;
	}

	public double getAverageExecutionTime() {
		return averageExecutionTime;
	}

	public void setAverageExecutionTime(double averageExecutionTime) {
		this.averageExecutionTime = averageExecutionTime;
	}

	public double getBacktrackAverageCount() {
		return backtrackAverageCount;
	}

	public void setBacktrackAverageCount(double backtrackAverageCount) {
		this.backtrackAverageCount = backtrackAverageCount;
	}

	public double getValueBacktrack() {
		return valueBacktrack;
	}

	public void setValueBacktrack(double valueBacktrack) {
		this.valueBacktrack = valueBacktrack;
	}

	public int getFinalNumberOfExecutionSteps() {
		return finalNumberOfExecutionSteps;
	}

	public void setFinalNumberOfExecutionSteps(int finalNumberOfExecutionSteps) {
		this.finalNumberOfExecutionSteps = finalNumberOfExecutionSteps;
	}

	public List<Calculation> getListOfCalculations() {
		return listOfCalculations;
	}

	public void insertNewCalculation(final Calculation calculation) {
		this.listOfCalculations.add(calculation);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(averageExecutionTime);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(backtrackAverageCount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + finalNumberOfExecutionSteps;
		result = prime * result + ((listOfCalculations == null) ? 0 : listOfCalculations.hashCode());
		temp = Double.doubleToLongBits(valueBacktrack);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OutputSummary other = (OutputSummary) obj;
		if (Double.doubleToLongBits(averageExecutionTime) != Double.doubleToLongBits(other.averageExecutionTime))
			return false;
		if (Double.doubleToLongBits(backtrackAverageCount) != Double.doubleToLongBits(other.backtrackAverageCount))
			return false;
		if (finalNumberOfExecutionSteps != other.finalNumberOfExecutionSteps)
			return false;
		if (listOfCalculations == null) {
			if (other.listOfCalculations != null)
				return false;
		} else if (!listOfCalculations.equals(other.listOfCalculations))
			return false;
		if (Double.doubleToLongBits(valueBacktrack) != Double.doubleToLongBits(other.valueBacktrack))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OutputStatistics [averageExecutionTime=" + averageExecutionTime + ", backtrackAverageCount="
				+ backtrackAverageCount + ", valueBacktrack=" + valueBacktrack + ", finalNumberOfExecutionSteps="
				+ finalNumberOfExecutionSteps + ", listOfCalculations=" + listOfCalculations + "]";
	}

}