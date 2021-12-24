/**
 * 
 */

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Fikri
 *
 */
public class Parking {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<String> arr = new LinkedList<>();
      
      int num = 0;
      
      List<Integer> remArr = new ArrayList<>();
      
      String create_parking_lot = "create_parking_lot";
      String park = "park";
      String leave = "leave";
      String status = "status";
      String registration_numbers_for_cars_with_colour = "registration_numbers_for_cars_with_colour";
      String slot_numbers_for_cars_with_colour = "slot_numbers_for_cars_with_colour";
      String slot_number_for_registration_number = "slot_number_for_registration_number";
      
      
      // random colour
    //   Random randomGenerator = new Random();
      
    //   List<String> colors = Arrays.asList("White", "Black", "Red", "Blue");
    //   int index = randomGenerator.nextInt(colors.size());
    //   String item = colors.get(index);

      try (Scanner scanner = new Scanner(System.in)) {
          while (scanner.hasNextLine()){
            String input = "";
            input+=scanner.nextLine();
      
      Map<String, Object> data =  new HashMap<>();
            
            if (input.equals("exit")) {
            System.exit(0);
                scanner.close();
                break;
            }
            
            if (input.startsWith(create_parking_lot)) {
                String[] arrNum = input.split(" ");
                num = Integer.valueOf(arrNum[1]);
                System.out.println("Created a parking lot with " +input+ " slots");
                
            }
            
            
            
            
            if (input.startsWith(park)) {
                if (arr.size() < num) {
                    String[] arrNum = input.split(" ");
                    String text = arrNum[1].concat(" " + arrNum[2]);
                    
                    if (remArr.size() > 0) {
                        arr.add(remArr.get(0), text);
                        remArr.remove(remArr.get(0));
                    } else {
                        arr.add(text);
                    }
                    
                    int x = arr.indexOf(text) + 1;
                    System.out.println("Allocated slot number: " + x);
                } else {
                    System.out.println("Sorry, parking lot is full");
                }
            } else if (input.startsWith(leave)) {
                String[] arrNum = input.split(" ");
                System.out.println("Slot number " +arrNum[1])+ " is free");
                int index = Integer.valueOf(arrNum[1]) - 1;
                arr.remove(index);
                remArr.add(index);
            } else if (input.startsWith(status)) {
                System.out.println(String.format("%1s %10s %12s", "Slot no.", "Registration No", "colour"));
                int x = 1;
                for (int i =0; i < arr.size(); i++) {
                    x++;
                    String[] arrNum = arr.get(i).split(" ");
                    
                    System.out.println(String.format("%1s %25s %12s", x, arrNum[0], arrNum[1]));
                }
            } else if (input.startsWith(registration_numbers_for_cars_with_colour)) {
				String[] arrNum = input.split(" ");
				String text = arrNum[1];
				List<String> arrList = new ArrayList<String>();
				for (String str : arr) {
					String[] find = str.split(" ");
					if (text.equals(find[1])) {
						arrList.add(find[0]);
					}
				}

				String asString = arrList.toString();
				response = asString.substring(1, asString.length() - 1);
				System.out.println(response);
			} else if (input.startsWith(slot_numbers_for_cars_with_colour)) {
				String[] arrNum = input.split(" ");
				String text = arrNum[1];
				List<Integer> arrList = new ArrayList<Integer>();
				for (String str : arr) {
					String[] find = str.split(" ");
					if (text.equals(find[1])) {
						int x = arr.indexOf(str) + 1;
						arrList.add(x);
					}
				}

				String asString = arrList.toString();
				response = asString.substring(1, asString.length() - 1);
				System.out.println(response);
			}
            
            
            System.out.println("Creat " +arr+ " slots");
          }
      } catch (Exception e) {
          System.out.println("Error input : " + e.getMessage());
          System.exit(0);
      }
	}

}
