package 王逸群.hrManagerSystem.db;

import java.io.Serializable;

public class Evaluation implements Comparable<Evaluation>,Serializable{
	private int evaluationld;
	private int evaluatorId;
	private int evaluatedId;
	private double score;
	private String evaluationDate;
	public String getEvaluationDate() {
		return evaluationDate;
	}

	public void setEvaluationDate(String evaluationDate) {
		this.evaluationDate = evaluationDate;
	}

	public Evaluation(int evaluationld, int evaluatorId, int evaluatedId, double score, String evaluationDate) {
		this.evaluationld = evaluationld;
		this.evaluatorId = evaluatorId;
		this.evaluatedId = evaluatedId;
		this.score = score;
		this.evaluationDate = evaluationDate;
	}

	public Evaluation() {}
	
	public Evaluation(int evaluationld, int evaluatorId, int evaluatedId, double score) {
		this.evaluationld = evaluationld;
		this.evaluatorId = evaluatorId;
		this.evaluatedId = evaluatedId;
		this.score = score;
	}
	
	public int getEvaluationld() {
		return evaluationld;
	}

	public void setEvaluationld(int evaluationld) {
		this.evaluationld = evaluationld;
	}

	public int getEvaluatorId() {
		return evaluatorId;
	}

	public void setEvaluatorId(int evaluatorId) {
		this.evaluatorId = evaluatorId;
	}

	public int getEvaluatedId() {
		return evaluatedId;
	}

	public void setEvaluatedId(int evaluatedId) {
		this.evaluatedId = evaluatedId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public int compareTo(Evaluation o) {
		if(this.score>o.score) return -1;
		if (this.score<o.score) {
			return 1;
		}
		return 0;
	}
	
}
