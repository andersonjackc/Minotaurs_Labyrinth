package edu.ycp.cs320.Minotaurs_Labyrinth.model;

// model class for GuessingGame
// only the controller should be allowed to call the set methods
// the JSP will call the "get" and "is" methods implicitly
// when the JSP specifies game.min, that gets converted to
//    a call to model.getMin()
// when the JSP specifies if(game.done), that gets converted to
//    a call to model.isDone()
public class Numbers {
	private double first, second, third, result;
	private String errorMessage, errorfirst, errorsecond, errorthird;
	
	public Numbers() {
	}
	
	//Set and get methods for the 3 Proper Doubles
	public void setFirst(double first) {
		System.out.println("Set First = " + first);
		this.first = first;
	}
	
	public double getFirst() {
		System.out.println("GET First");
		return first;
	}
	
	public void setSecond(double second) {
		System.out.println("Set Second = " + second);
		this.second = second;
	}
	public double getSecond() {
		System.out.println("GET Second");
		return second;
	}
	
	public void setThird(double third) {
		System.out.println("Set Third = " + third);
		this.third = third;
	}
	public double getThird() {
		System.out.println("GET Third");
		return third;
	}

	//Find the result (add or mult) and stores it in a variable in the model
	public void setAddResult() {
		result = first + second + third;
	}
	public void setMultiplyresult() {
		result = first * second * third;
	}
	public double getResult() {
		System.out.println("GET add result");
		return result;
	}
	
	//set and get methods for the error
	//strings so we can send them back
	public void setErrorfirst(String errorfirst) {
		this.errorfirst=errorfirst;
	}
	public String getErrorfirst() {
		return errorfirst;
		
	}
	public void setErrorsecond(String errorsecond) {
		this.errorsecond=errorsecond;
	}
	public String getErrorsecond() {
		return errorsecond;
	}
	
	public void setErrorthird(String errorthird) {
		this.errorthird=errorthird;
	}
	public String getErrorthird() {
		return errorthird;
	}
	
	//set and get for error messages
	public void setNumbs() {
	errorMessage = "Please specify three numbers";
	}
	public void setInvalid() {
	errorMessage = "Invalid double";
	}
	public String getError() {
	return this.errorMessage;
	}
}
