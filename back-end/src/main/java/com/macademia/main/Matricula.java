package com.macademia.main;

import java.util.ArrayList;
import java.util.List;

public class Matricula {

	// -[Fields]----------------------------------------------
	private List<Section> sections;
	private List<Course> courses;
	private int totalCredits;
	private MatriculaPeriod period;
	private int ID = -1;

	// -[Constructor]-----------------------------------------
	public Matricula(MatriculaPeriod period) {
		this.sections = new ArrayList<Section>();
		this.courses = new ArrayList<Course>();
		this.totalCredits = 0;
		this.period = period;
	}

	public Matricula(List<Section> sections, MatriculaPeriod period) {
		this.sections = sections;
		this.totalCredits = 0;
		this.period = period;
	}

	// -[Add-Remove]]---------------------------------------------

	// had to include both section and course whenever adding a new section into a matricula 
	// in order to keep track of sections and courses taken
	public void addSection(Section e, Course f) {
		if(!f.getCourseCode().contentEquals(e.getCourseCode())) {throw new IllegalArgumentException("Course doesn't match with section.");}
		this.totalCredits += e.getCredits();
		this.sections.add(e);		
		this.courses.add(f);
	}

	public void removeSection(Section e, Course f) {
		if(f.getDept()+f.getCode()!=e.getCourseCode()) {throw new IllegalArgumentException("Course doesn't match with section.");}
		if(!sections.contains(e)) {return;} //make sure we have it before decreasing todo.
		this.totalCredits -= e.getCredits();
		this.sections.remove(f);
		this.courses.remove(f);
	}
	
	public void removeSection(Section e) {
		if(!sections.contains(e)) {return;}
		
		//Find the course to remove
		Course f=null;
		for (Course course : courses) {
			if(course.getCourseCode()==e.getCourseCode()) {f=course; break;} //found it
		}
		
		if(f==null) {
			//remove it anyways
			this.sections.remove(e);
			return;
		}
		
		//Do the propper removal procedure
		removeSection(e, f);
		
	}
	
	public void removeCourse(Course f) {
		if(!courses.contains(f)) {return;}
		//find the section that we have that matches the course code
		Section e=null;
		for (Section section : sections) {
			if(section.getCourseCode()==f.getCourseCode()) {e=section; break;} //found it
		}
		
		if(e==null) {
			//We didn't find it, but remove the course anyway. It should be removed in that case.
			courses.remove(f);
			return;
		}
		
		//Remove the section and course together.
		removeSection(e, f);
		
	}

	// -[Getters]---------------------------------------------
	public List<Section> getSections() {return sections;}
	public List<Course> getCourses() {return courses;}
	public int getTotalCredits() {return totalCredits;}
	public MatriculaPeriod getPeriod() {return period;}
	public int getID() {return ID;}

	public void setSections(List<Section> sections) {this.sections = sections;}
	public void setCourse(List<Course> courses) {this.courses=courses;}
	public void setTotalCredits(int totalCredits) {this.totalCredits = totalCredits;}
	public void setPeriod(MatriculaPeriod period) {this.period = period;}
	public void setID(int ID) {this.ID=ID;}

	/**
	 * Returns a list of 6 lists of sections. Each list corresponds to a specified day, and contains sections that occur on those days.
	 * @return
	 */
	public List<List<Section>> getSectionsByDay(){
		List<List<Section>> Week = new ArrayList<List<Section>>();
		for (int Day = 0; Day < 7; Day++) {Week.add(new ArrayList<Section>());} //Make sure each de-esta cosa has an arraylist.
		
		//now go through each section.
		for (Section section : sections) {
			for (char DayLetter : section.getDay().toCharArray()) { //Now go through each letter in their days
				Week.get(LetterToDay(DayLetter)).add(section); //And add them to one of the 6 de-estas cosas using letter to day.
			}
		}
		
		return Week;
	}
	
	/**
	 * Turns a letter (LMWJVSD) to a day number (0-7)
	 * @param Letter
	 * @return "LMWJVSD".indexOf(Letter)
	 */
	private static int LetterToDay(char Letter) {return "LMWJVSD".indexOf(Letter);}
	
	/**
	 * Returns a displayable string for this Matricula
	 * 
	 * @return CourseNumber Course(s) (Credits Credit(s)) during Period (IE "2
	 *         Course(s) totaling 6 Credit(s) during FALL")
	 */
	public String toString() {return sections.size() + " Course(s) totaling " + getTotalCredits() + " Credit(s) during " + getPeriod();}

}
