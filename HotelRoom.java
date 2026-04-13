package jOption;
import javax.swing.JOptionPane;

public class HotelRoom {
	public static void displayRooms(int[][] rooms) {
		String roomsOutput = "";
		int row = rooms.length;
		for (int i = 0; i < row; i++) {
			roomsOutput += "Floor " + (row-i) + ": ";
			for (int j = 0; j < rooms[0].length; j++) {
				roomsOutput += "[" + rooms[i][j] + "] ";
			}
			roomsOutput += "\n";
		}
		JOptionPane.showMessageDialog(null, roomsOutput, "=== ROOMS ===", JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void checkIn(int[][] rooms) {
		int floor = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter floor number (1-7): ", "=== CHECKING YOU IN ===", JOptionPane.QUESTION_MESSAGE));
		int room = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter room number (1-5): ", "=== CHECKING YOU IN ===", JOptionPane.QUESTION_MESSAGE));
		int row = rooms.length - floor, col = room - 1;
		if ((floor < 1 || floor > 7) || (room < 1 || room > 5)) {
			JOptionPane.showMessageDialog(null, "Enter valid number!\nPlease try again.", "=== INPUT ERROR! ===", JOptionPane.WARNING_MESSAGE);
			checkIn(rooms);
			return;
		} else if (rooms[row][col] == 1) {
			JOptionPane.showMessageDialog(null, "Room already occpied!\n Please try again.", "=== OCCUPIED! ===", JOptionPane.WARNING_MESSAGE);
			checkIn(rooms);
			return;
		}
		rooms[row][col] = 1;
		JOptionPane.showMessageDialog(null, "CHECK-IN SUCCESSFUL!", "=== CHECKED-IN ===", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void checkOut(int[][] rooms) {
		int floor = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter floor number (1-7): ", "=== CHECKING YOU OUT ===", JOptionPane.QUESTION_MESSAGE));
		int room = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter room number (1-5): ", "=== CHECKING YOU OUT ===", JOptionPane.QUESTION_MESSAGE));
		int row = rooms.length - floor, col = room - 1;
		if ((floor < 1 && floor > 7) || (room < 1 && room > 5)) {
			JOptionPane.showMessageDialog(null, "Enter valid number!\nPlease try again.", "=== INPUT ERROR! ===", JOptionPane.WARNING_MESSAGE);
			checkIn(rooms);
			return;
		} else if (rooms[row][col] == 1) {
			JOptionPane.showMessageDialog(null, "Room already occpied!\n Please try again.", "=== OCCUPIED! ===", JOptionPane.WARNING_MESSAGE);
			checkIn(rooms);
			return;
		}
		rooms[row][col] = 0;
		JOptionPane.showMessageDialog(null, "CHECK-OUT SUCCESSFUL!", "=== CHECKED-OUT ===", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void main(String[] args) {
		int[][] hotelRooms = new int[7][5];
		
		JOptionPane.showMessageDialog(null, "WELCOME TO OUR HOTEL!", "=== HOTEL DE REY ===", JOptionPane.INFORMATION_MESSAGE);
		String[] options = {"1.) View All Rooms", "2.) Check-in", "3.) Check-out", "4.) Exit Program"};
		String selected = "";
		while (!selected.equals(options[3])) {
			selected = (String) JOptionPane.showInputDialog(null, "Please select an option: ", "System Features", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			if (selected.equals(options[0])) {
				displayRooms(hotelRooms);
			} else if (selected.equals(options[1])) {
				checkIn(hotelRooms);
			} else if (selected.equals(options[2])) {
				checkOut(hotelRooms);
			}
		}
		JOptionPane.showMessageDialog(null, "THANK YOU FOR CHOOSING US!\n PLEASE COME AGAIN!", "^ 3 ^", JOptionPane.INFORMATION_MESSAGE);
	}
}
