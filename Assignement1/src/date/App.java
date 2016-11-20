package date;

public class App {

	public static void main(String[] args) {

		
//		OOSCDateTime d = new OOSCDateTime();
//		//d.setDate(2004, 3, 2);
//		d.setTime(5, 6, 7);
//		d.addHours(50);
//		System.out.print(d.getTime());
	
//		OOSCDate d = new OOSCDate();
//		d.addDays(2);
		
		try {
			OOSCDateTime d = new OOSCDateTime();
			System.out.print(d.toString()+"\t \t"+d.getTime()+"\n");
			
			d.setDate(2004, 11, 2);
			d.setTime(05, 6, 7);
			System.out.print(d.toString()+"\t \t"+d.getTime()+"\n");
		} catch (AssertionError e) {
			System.out.print(e.toString());
		    System.exit(0);
		}  

	}

}
