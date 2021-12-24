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

		try {
//			Scanner scanner = new Scanner(System.in);
//			File file = new File(scanner.nextLine());

			File file = new File("parking_lot_file_inputs.txt");

			Scanner scanner = new Scanner(System.in);

			while (scanner.hasNextLine()) {
				String input = "";
				input += scanner.nextLine();

				if (input.equals("exit")) {
					System.exit(0);
					scanner.close();
					break;
				}
				
				String response = "";
				String create_parking_lot = "create_parking_lot";
		
		if (input.startsWith(create_parking_lot)) {
			String[] arrNum = input.split(" ");
			num = Integer.valueOf(arrNum[1]);
			response = "Created a parking lot with " + input + " slots";
			System.out.println(response);
			arr = new LinkedList<>();
			remArr = new ArrayList<>();
		}
		
		getParkingResponse(input, arr, num, remArr);
				
			}

			scanner.close();
		} catch (Exception e) {
			System.out.println("Error input : " + e.getMessage());
			System.exit(0);
		}
	}

	public static String getParkingResponse(String input, LinkedList<String> arr, int num, List<Integer> remArr) {

		String park = "park";
		String leave = "leave";
		String status = "status";
		String registration_numbers_for_cars_with_colour = "registration_numbers_for_cars_with_colour";
		String slot_numbers_for_cars_with_colour = "slot_numbers_for_cars_with_colour";
		String slot_number_for_registration_number = "slot_number_for_registration_number";

		String response = "Unknown Error";

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
				response = "Allocated slot number: " + x;
				System.out.println(response);
			} else {

				response = "Sorry, parking lot is full";
				System.out.println(response);
			}
		} else if (input.startsWith(leave)) {
			String[] arrNum = input.split(" ");
			response = "Slot number " + arrNum[1] + " is free";
			System.out.println(response);
			int index = Integer.valueOf(arrNum[1]) - 1;
			arr.remove(index);
			remArr.add(index);
		} else if (input.startsWith(status)) {
			response = String.format("%1s %10s %12s", "Slot no.", "Registration No", "colour") + "\n";
			System.out.println(response);
			int x = 0;
			System.out.println("sdf: "+arr.size());
			String data = "";
			for (int i = 0; i < arr.size(); i++) {
				x++;
				for (Integer in: remArr) {
					if (in == i) {
						x++;
						break;
					}
				}
				String[] arrNum = arr.get(i).split(" ");

				data += (String.format("%1s %25s %12s", x, arrNum[0], arrNum[1]) + "\n");
			}
			response = response + data;
			System.out.println(response);
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
		} else if (input.startsWith(slot_number_for_registration_number)) {
			String[] arrNum = input.split(" ");
			String text = arrNum[1];
			List<Integer> arrList = new ArrayList<Integer>();

			boolean isExist = false;
			for (String str : arr) {
				String[] find = str.split(" ");
				if (text.equals(find[0])) {
					int x = arr.indexOf(str) + 1;
					arrList.add(x);
					isExist = true;
					break;
				}
			}

			if (isExist) {
				String asString = arrList.toString();
				response = asString.substring(1, asString.length() - 1);
				System.out.println(response);
			} else {
				response = "Not found";
				System.out.println(response);
			}
		}

		return response;
	}

}
