import java.util.Scanner;
public class Hotel2dRoomReservationSystem {
	public static void showRooms(int[][] rooms) {
		int row = rooms.length, col = rooms[0].length;
		System.out.println("=== HOTEL ROOMS ===");
		for (int i = 0; i < row; i++) {
			System.out.printf("Floor %d: ", row-i);
			for (int j = 0; j < col; j++) {
				System.out.printf("[%d]", rooms[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void checkIn(Scanner scan, int[][] rooms) {
		System.out.print("=== CHECK-IN ===\n"
			+ "Enter floor (1-7): ");
		int floor = rooms.length - scan.nextInt();
		System.out.print("Enter room (1-5): ");
		int room = scan.nextInt()-1;
		
		if (rooms[floor][room] == 0) {
			rooms[floor][room] = 1;
			System.out.println("Check-in successful!");
		} else {
			System.out.println("Room already occupied");
		}
	}
	
	public static void checkOut(Scanner scan, int[][] rooms) {
		System.out.print("=== CHECK-OUT ===\n"
			+ "Enter floor (1-7): ");
		int floor = rooms.length - scan.nextInt();
		System.out.print("Enter room (1-5): ");
		int room = scan.nextInt()-1;
		
		if (rooms[floor][room] == 1) {
			rooms[floor][room] = 0;
			System.out.println("Check-out successful!");
		} else {
			System.out.println("Room already empty");
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[][] rooms = new int[7][5];
		
		int choice = -1;
		while (choice != 4) {
			System.out.print("=== HOTEL RESERVATION SYSTEM ===\n"
				+ "1.) View Rooms\n"
				+ "2.) Check In\n"
				+ "3.) Check Out\n"
				+ "4.) Exit\n"
				+ "Enter choice: ");
			choice = scan.nextInt();
			System.out.println();
			
			if (choice == 1) {
				showRooms(rooms);
			} else if (choice == 2) {
				checkIn(scan, rooms);
			} else if (choice == 3) {
				checkOut(scan, rooms);
			}
			System.out.println();
		}
		
		scan.close();
	}
}
