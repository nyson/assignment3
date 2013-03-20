
public class Person {
	private String name, addr, city;
	private int zip;
	
	public Person(String pName, String pAddr, int pZip, String pCity) {
		name = pName; addr = pAddr; zip = pZip; city = pCity;
	}
	
	public String toString(){
		return "Name: " + name + ", Adress: " + addr + ", " 
				+ ", PostNr: " + zip + ", City: " + city;
		
	}
}
