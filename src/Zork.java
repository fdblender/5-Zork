
import java.util.Scanner;
import java.util.Random;

public class Zork {

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

		while (goPlaces) {
			//System.out.println("room: "+room);
			switch (room) {
			case 1:
				foyer();
				response = scan.next();
				if (response.equals("Q")) {
					goPlaces = false;
				} else {
					intR = Integer.parseInt(response);
					if (intR == 1) {
						room = 2;
					} else {
						goPlaces = error();
					}
					System.out.println("room" + room);
				}
				roomCount++;
				break;
			case 2:
				frontRoom();
				response = scan.next();
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
				roomCount++;
				break;
			case 3:
				library();
				response = scan.next();
				intR = Integer.parseInt(response);
				if (intR == 1) {
					room = 2;
				} else if (intR == 2) {
					room = 5;
				} else {
					goPlaces = error();
				}
				roomCount++;
				break;
			case 4:
				kitchen();
				response = scan.next();
				intR = Integer.parseInt(response);
				if (intR == 1) {
					room = 2;
				} else if (intR == 2) {
					room = 7;
				} else {
					goPlaces = error();
				}
				roomCount++;
				break;
			case 5:
				diningRoom();
				response = scan.next();
				intR = Integer.parseInt(response);
				if (intR == 1) {
					room = 3;
				} else {
					goPlaces = error();
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
				vault(found);
				response = scan.next();
				intR = Integer.parseInt(response);
				if (intR == 1) {
					room = 7;
				} else if (intR == 2) {
					room = 8;
				} else {
					goPlaces = error();
				}
				roomCount++;
				break;
			case 7:
				parlor();
				response = scan.next();
				intR = Integer.parseInt(response);
				if (intR == 1) {
					room = 6;
				} else if (intR == 2) {
					room = 4;
				} else {
					goPlaces = error();
				}
				roomCount++;
				break;
			case 8:
				secretRoom();
				response = scan.next();
				intR = Integer.parseInt(response);
				if (intR == 1) {
					room = 6;
				} else {
					goPlaces = error();
				}
				roomCount++;
				break;
			default:
				error();
				break;
			}
		}
		randomNo = 1 + rnd.nextInt(4);
		System.out.println("No. of Rooms Visited: " + roomCount);
		System.out.println("Hope you enjoyed Zork! Exiting...");
		if (randomNo == ghost) {
			System.out.println("You were followed by a ghost!");
		}
	}

	public static void foyer() {
		System.out.println("You are standing in the foyer" + "of an old house. \n you see a dead scorpion."
				+ "\n You can (1) exit to the north or press Q to quit");
	}

	public static void frontRoom() {
		System.out.println("You are in the front room. You see a piano."
				+ "\n You can (1) exit to the north, (2) exit to the west, or (3) exit to the east");
	}

	public static void library() {
		System.out.println("You are standing in a library." + "\n You see spiders."
				+ "\n You can (1) exit to the north, (2) exit to the east");
	}

	public static void kitchen() {
		System.out.println("You are standing in a kitchen." + "\n You see bats."
				+ "\n You can (1) exit to the west, (2) exit to the north");
	}

	public static void diningRoom() {
		System.out.println(
				"You are standing in a dining room." + "\n You see dust." + "\n You can (1) exit to the south");
	}

	public static void vault(boolean found) {
		System.out.println(
				"You are standing in a vault." + "\n You see 3 walking skeletons." + "You can (1) exit to the east");
		if (found) {
			System.out.println("You can (1) go to room 7 or (2) go to room 8");
		} else {
			System.out.println("You can (1) go to room 7");
		}
	}

	public static void parlor() {
		System.out.println("You are standing in a parlor." + "\n You see treasure chest."
				+ "\n You can (1) exit to the west, (2) exit to the south");
	}

	public static void secretRoom() {
		System.out.println(
				"You are standing in a secret room." + "\n You see piles of gold." + "\n You can (1) exit to the west");
	}

	public static boolean error() {
		System.out.println("Sorry! Invalid response...");
		return false;
	}
}
