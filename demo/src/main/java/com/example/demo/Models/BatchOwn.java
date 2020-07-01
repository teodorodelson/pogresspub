package com.example.demo.Models;
import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.*;

public class BatchOwn {

	public class Categories{
			public double Project;
			public  double Presentation;
		    public  double Exam;
		    public double Other;
		    public double Verbal;
		    
		    public Categories() {}
		    
		    public Categories(double Project, double Presentation, double Exam, double Other, double Verbal) {
		    	this.Project= Project;
		    	this.Presentation= Presentation;
		    	this.Exam= Exam;
		    	this.Other= Other;
		    	this.Verbal= Verbal;
		    }
	}
    public BatchOwn(){};
    public BatchOwn(Categories traineeGrades, Categories restOfBatchGrades){
    	this.traineeGrades = traineeGrades;
    	this.restOfBatchGrades = restOfBatchGrades;
      
    };
    public Categories traineeGrades;
    public Categories restOfBatchGrades;


//    @JsonAlias({"employeeAssignments"})
//    public ArrayList<Object> nothing;
//    public ArrayList<Object> associateAssignments;

    

}
