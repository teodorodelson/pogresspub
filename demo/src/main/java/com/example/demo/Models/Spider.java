package com.example.demo.Models;
import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.*;



	public class Spider {

	
		@Override
		public String toString() {
			return "Spider [spiderTraineeGrades=" + spiderTraineeGrades + "]";
		}

		public GradeCategories getSpiderTraineeGrades() {
			return spiderTraineeGrades;
		}

		public void setSpiderTraineeGrades(GradeCategories spiderTraineeGrades) {
			this.spiderTraineeGrades = spiderTraineeGrades;
		}
		public class GradeCategories{
				@Override
			public String toString() {
				return "GradeCategories [traineeId=" + traineeId + ", assessmentType=" + assessmentType + ", score="
						+ score + ", week=" + week + ", weight=" + weight + "]";
			}

				public String getTraineeId() {
				return traineeId;
			}

			public void setTraineeId(String traineeId) {
				this.traineeId = traineeId;
			}

			public String getAssessmentType() {
				return assessmentType;
			}

			public void setAssessmentType(String assessmentType) {
				this.assessmentType = assessmentType;
			}

			public double getScore() {
				return score;
			}

			public void setScore(double score) {
				this.score = score;
			}

			public int getWeek() {
				return week;
			}

			public void setWeek(int week) {
				this.week = week;
			}

			public int getWeight() {
				return weight;
			}

			public void setWeight(int weight) {
				this.weight = weight;
			}

				public String traineeId;
				public String assessmentType;
			    public double score;
			    public int week;
			    public int weight;
			    
			    public GradeCategories() {}
			    
			    public GradeCategories(String traineeId, String assessmentType, double score, int week, int weight) {
			    	this.traineeId= traineeId;
			    	this.assessmentType= assessmentType;
			    	this.score= score;
			    	this.week= week;
			    	this.weight= weight;
			    }
		}
	    public Spider(){};
	    
	    public Spider(GradeCategories spiderTraineeGrades){
	    	this.spiderTraineeGrades = spiderTraineeGrades;
	      
	    };
	    public GradeCategories spiderTraineeGrades;
	    
	/*	@Override
		public String toString() {
			return "Spider [spiderTraineeGrades=" + spiderTraineeGrades + ", getClass()=" + getClass() + ", hashCode()="
					+ hashCode() + ", toString()=" + super.toString() + "]";
					*/
		}
		//-----------------------------------/
//		 public Spider(){};
//		    
//		    public Spider(ArrayList<Object> spiderTraineeGrades){
//		    	this.spiderTraineeGrades = spiderTraineeGrades;
//		      
//		    };
//		    public ArrayList<Object> spiderTraineeGrades;
		
		//--------------------------------------------/
//		 public Spider(){};
//		 public Spider(String traineeId, String assessmentType, double score, int week, int weight){
//				        this.traineeId = traineeId;
//				        this.assessmentType = assessmentType;
//				        this.score = score;
//				        this.week = week;
//				        this.weight = weight;
//				        
//		
//	 
//		 	};
//		 	 public String traineeId;
//		     public String assessmentType;
//		     public double score;
//		     public int week;
//		     public int weight;
		  
	
