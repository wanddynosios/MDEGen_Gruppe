package idManagement;
/**
 * Not used if database is in operation
 */
public class IDManagerTransient {
	private Integer counter;
	private static IDManagerTransient theInstance = new IDManagerTransient();
	private IDManagerTransient() {this.counter = 0;}
	public static IDManagerTransient getTheInstance() {return theInstance;}
	
	public Integer getNextId() {
		return ++this.counter;
	}
	
	public void setId(Integer counter) {
		this.counter = counter;
	}
}
