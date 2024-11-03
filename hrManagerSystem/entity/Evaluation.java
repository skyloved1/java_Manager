package 王逸群.hrManagerSystem.entity;

import java.io.Serializable;

//评价类，实现比较器，用于排序
@SuppressWarnings("serial")
public class Evaluation implements Comparable<Evaluation>, Serializable {
    //评价ID
    private int evaluationID;
    //评价人ID，经理角色
    private int evaluatorID;
    //被评价人ID，员工角色
    private int evaluatedID;
    //考评成绩
    private double score;

    public Evaluation() {
        evaluationID = 0;
        evaluatedID = 0;
        evaluatorID = 0;
        score = 0;
    }

    public Evaluation(int evaluationID, int evaluatorID, int evaluatedID, double score) {
        this.evaluationID = evaluationID;
        this.evaluatorID = evaluatorID;
        this.evaluatedID = evaluatedID;
        this.score = score;
    }

    public int getEvaluationID() {
        return evaluationID;
    }

    public void setEvaluationID(int evaluationID) {
        this.evaluationID = evaluationID;
    }

    public int getEvaluatorID() {
        return evaluatorID;
    }

    public void setEvaluatorID(int evaluatorID) {
        this.evaluatorID = evaluatorID;
    }

    public int getEvaluatedID() {
        return evaluatedID;
    }

    public void setEvaluatedID(int evaluatedID) {
        this.evaluatedID = evaluatedID;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }


    @Override
    public int compareTo(Evaluation o) {
        if (score > o.score) {
            return -1;
        } else if (score < o.score) {
            return 1;
        }
        return 0;
    }
}
