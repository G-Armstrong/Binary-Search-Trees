
public class Country implements Comparable<Country> {
	private String code;
	private String name;
	private double area;
	//Default constructor
	public Country() {
		code = "";
		name = "";
		area = 0.0;
	}
	
	//Constructor with parameters
	public Country(String c, String n,double a) {
		code = c;
		name = n;
		area = a;
	}
	/**
	 * Getter
	 * @return returns code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * Getter
	 * @return returns name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Getter
	 * @return returns area
	 */
	public double getArea() {
		return area;
	}
	/**
	 * Setter
	 * @param c to be set to code
	 */
	public void setCode(String c) {
		code = c;
	}
	/**
	 * Setter
	 * @param n to be set to name
	 */
	public void setName(String n) {
		name = n;
	}
	/**
	 * Setter
	 * @param a to be set to area
	 */
	public void setArea(double a) {
		area = a;
	}
	/**
	 * Returns formatted output
	 */
	public String toString() {
		return String.format("%-10s\t\t%-40s\t\t\t\t%-15.0f\n", getCode(),getName(),getArea());
	}
	
	/**
	 * Definition of compareTo from Comparable
	 */
	public int compareTo(Country c) {
		if(getName().equals(c.getName())) {
			return 0;
		}
		else if(getName().compareTo(c.getName()) > 0) {
			return 1;
		}
		else
			return -1;
		
	}
	

}
