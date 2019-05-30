public class Kill2{
	public static void main(String[] args){
		if(args[0].length() < 2){
			
			System.out.println("Error: Please input a String with more than 1 characters.");
		}
		else{
			
			String newString = args[0];
			newString = newString.substring(0,1) + newString.substring(2, newString.length());
			System.out.println(newString);
		}
		
	}
}