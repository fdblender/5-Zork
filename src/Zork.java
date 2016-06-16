import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Random;

public class Zork {

	private static final int FOYER = 1, FRONTROOM = 2, LIBRARY = 3, KITCHEN = 4, DININGROOM = 5, VAULT = 6, PARLOR = 7,
			SECRETROOM = 8;
	private static final String[] rooms = { "", "Foyer", "Front Room", "Library", "Kitchen", "Dining Room", "Vault",
			"Parlor", "Secret Room" };
	private static final Boolean APPEND = true;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Random rnd = new Random();
		String response;
		int intR;
		int room = 1;
		int randomNo;
		int ghost = 3;

		boolean found = false;
		int roomCount = 0;
		boolean goPlaces = true;
		int SECRET_ROOM = 1;

		int money[] = new int[9];

		while (goPlaces) {
			try {

				switch (room) {
				case 1:
					foyer(money);
					response = scan.next();
					if (response.toUpperCase().equals("Q")) {
						goPlaces = false;
					} else if (response.toUpperCase().equals("H")) {
						DisplayHistory(money);
					} else {
						intR = Integer.parseInt(response);
						if (intR == 1) {
							room = 2;
						} else {
							goPlaces = error();
						}
						// System.out.println("room" + room);
					}
					roomCount++;
					break;
				case 2:
					frontRoom(money);
					response = scan.next();
					if (response.toUpperCase().equals("H")) {
						DisplayHistory(money);
					} else {
						intR = Integer.parseInt(response);
						switch (intR) {
						case 1:
							room = 1;
							break;
						case 2:
							room = 3;
							break;
						case 3:
							room = 4;
							break;
						default:
							goPlaces = error();
							break;
						}
					}
					roomCount++;
					break;
				case 3:
					library(money);
					response = scan.next();
					if (response.toUpperCase().equals("H")) {
						DisplayHistory(money);
					} else {
						intR = Integer.parseInt(response);
						if (intR == 1) {
							room = 2;
						} else if (intR == 2) {
							room = 5;
						} else {
							goPlaces = error();
						}
					}
					roomCount++;
					break;
				case 4:
					kitchen(money);
					response = scan.next();
					if (response.toUpperCase().equals("H")) {
						DisplayHistory(money);
					} else {
						intR = Integer.parseInt(response);
						if (intR == 1) {
							room = 2;
						} else if (intR == 2) {
							room = 7;
						} else {
							goPlaces = error();
						}
					}
					roomCount++;
					break;
				case 5:
					diningRoom(money);
					response = scan.next();
					if (response.toUpperCase().equals("H")) {
						DisplayHistory(money);
					} else {
						intR = Integer.parseInt(response);
						if (intR == 1) {
							room = 3;
						} else {
							goPlaces = error();
						}
					}
					roomCount++;
					break;
				case 6:
					if (!found) {
						randomNo = 1 + rnd.nextInt(4);
						if (randomNo == SECRET_ROOM) {
							found = true;
						}
					}
					vault(found, money);
					response = scan.next();
					if (response.toUpperCase().equals("H")) {
						DisplayHistory(money);
					} else {
						intR = Integer.parseInt(response);
						if (intR == 1) {
							room = 7;
						} else if (intR == 2) {
							room = 8;
						} else {
							goPlaces = error();
						}
					}
					roomCount++;
					break;
				case 7:
					parlor(money);
					response = scan.next();
					if (response.toUpperCase().equals("H")) {
						DisplayHistory(money);
					} else {
						intR = Integer.parseInt(response);
						if (intR == 1) {
							room = 6;
						} else if (intR == 2) {
							room = 4;
						} else {
							goPlaces = error();
						}
					}
					roomCount++;
					break;
				case 8:
					secretRoom(money);
					response = scan.next();
					if (response.toUpperCase().equals("H")) {
						DisplayHistory(money);
					} else {
						intR = Integer.parseInt(response);
						if (intR == 1) {
							room = 6;
						} else {
							goPlaces = error();
						}
					}
					roomCount++;
					break;
				default:
					error();
					break;
				}
				System.out.println("----------------------------------------------------");

			} catch (NumberFormatException e) {
				System.out.println("Error: Number Format Exception");
			} catch (Exception e) {
				System.out.println("Error: General Exception");
			}
		}
		randomNo = 1 + rnd.nextInt(4);
		System.out.println("No. of Rooms Visited: " + roomCount);
		DisplayHistory(money);
		System.out.println("Hope you enjoyed Zork! Exiting...");
		if (randomNo == ghost) {
			System.out.println("You were followed by a ghost!");
		}
	}

	public static void foyer(int money[]) {
		System.out.println("You are standing in the foyer" + "of an old house. \n you see a dead scorpion."
				+ "\n You can (1) exit to the north or press Q to quit or (H) see history");
		getMoney(money, FOYER);
		writeHistory(money, FOYER);
	}

	public static void frontRoom(int money[]) {
		System.out.println("You are in the front room. You see a piano."
				+ "\n You can (1) exit to the north, (2) exit to the west,"
				+ " or (3) exit to the east or (H) see history");
		getMoney(money, FRONTROOM);
		writeHistory(money, FRONTROOM);
	}

	public static void library(int money[]) {
		System.out.println("You are standing in a library." + "\n You see spiders."
				+ "\n You can (1) exit to the north, (2) exit to the east or (H) see history");
		getMoney(money, LIBRARY);
		writeHistory(money, LIBRARY);
	}

	public static void kitchen(int money[]) {
		System.out.println("You are standing in a kitchen." + "\n You see bats."
				+ "\n You can (1) exit to the west, (2) exit to the north or (H) see history");
		getMoney(money, KITCHEN);
		writeHistory(money, KITCHEN);

	}

	public static void diningRoom(int money[]) {
		System.out.println("You are standing in a dining room." + "\n You see dust."
				+ "\n You can (1) exit to the south  or (H) see history");
		getMoney(money, DININGROOM);
		writeHistory(money, DININGROOM);
	}

	public static void vault(boolean found, int money[]) {
		System.out.println("You are standing in a vault." + "\n You see 3 walking skeletons."
				+ "You can (1) exit to the east or (H) see history");
		if (found) {
			System.out.println("You can (1) go to room 7 or (2) go to room 8  or (H) see history");
		} else {
			System.out.println("You can (1) go to room 7 or (H) see history");
		}
		getMoney(money, VAULT);
		writeHistory(money, VAULT);
	}

	public static void parlor(int money[]) {
		System.out.println("You are standing in a parlor." + "\n You see treasure chest."
				+ "\n You can (1) exit to the west, (2) exit to the south or (H) see history");
		getMoney(money, PARLOR);
		writeHistory(money, PARLOR);
	}

	public static void secretRoom(int money[]) {
		System.out.println("You are standing in a secret room." + "\n You see piles of gold."
				+ "\n You can (1) exit to the west  or (H) see history");
		getMoney(money, SECRETROOM);
		writeHistory(money, SECRETROOM);
	}

	public static boolean error() {
		System.out.println("Sorry! Invalid response...");
		return false;
	}

	public static int getMoney(int money[], int index) {
		Random rnd = new Random();
		if (money[index] == 0) {
			money[index] = 1 + rnd.nextInt(100);
			return money[index];
		} else {
			return 0;
		}

	}

	public static void writeHistory(int money[], int index) {

		// System.out.print("WriteHistory: ");
		// System.out.println(rooms[index] + " " + money[index]);

		try {
			File file = new File("mydatafile.txt");
			FileWriter fwr = new FileWriter(file, APPEND);
			fwr.write(rooms[index] + " " + money[index] + "\n");
			fwr.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void DisplayHistory(int money[]) {
		// for (int i = 1; i < money.length; i++) {
		// System.out.println(rooms[i] + " $" + money[i]);
		// }
		System.out.println("----------------------------------------------------");
		System.out.print("DisplayHistory: ");

		// Reading the file
		try {
			FileReader fr = new FileReader("mydatafile.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
			
			// practice exception handling *** REMOVE ***
			//throw new IOException();
			
		} catch (IOException e) {
			e.getMessage();
			System.out.println("Error: IOException");
		} catch (Exception e) {
			System.out.println("Error: General Exception");
		}
	}

}
