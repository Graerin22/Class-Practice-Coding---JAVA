package gui;
import java.io.FileWriter;
import java.io.BufferedWriter;
import javax.swing.JOptionPane;

// Student-based Feedback System
public class SBFS {
	public static String studentRecords() {
		StringBuilder records = new StringBuilder("--- Student Feedback Records ---\n");
		String[] ratings = {"5 (Excellent)", "4 (Good)", "3 (Average)", "2 (Poor)", "1 (Very Poor)"};
		int[] ratingScore = new int[5], sumAndNumIdx = {0, 0};
		int yesOrNo = -1;
		
		JOptionPane.showConfirmDialog(null, "Welcome to our Student-based Feedback System!\nDo you want to proceed?", "--- WELCOME! ---", JOptionPane.YES_NO_OPTION);
		
		while (yesOrNo != 1) {
			// Append name:
			records.append(String.format("\nStudent Name: %s\n", JOptionPane.showInputDialog(null, "Enter your name: ", "--- INFORMATIONS ---", JOptionPane.QUESTION_MESSAGE)));
			// Append course:
			records.append(String.format("Course: %s\n", JOptionPane.showInputDialog(null, "Enter your course: ", "--- INFORMATIONS ---", JOptionPane.QUESTION_MESSAGE)));
			// Append feedback:
			records.append(String.format("Feedback: %s\n", JOptionPane.showInputDialog(null, "Enter your feedback message: ", "--- FEEDBACK ---", JOptionPane.QUESTION_MESSAGE)));
			
			// Sum and counter of ratings:
			String rated = JOptionPane.showInputDialog(null, "Choose you ratings: ", "--- RATINGS ---", JOptionPane.QUESTION_MESSAGE, null, ratings, ratings[0]).toString();
			if (rated.equals(ratings[0])) {
				ratingScore[0]++;
				sumAndNumIdx[0] += 5;
				sumAndNumIdx[1]++;
			} else if (rated.equals(ratings[1])) {
				ratingScore[1]++;
				sumAndNumIdx[0] += 4;
				sumAndNumIdx[1]++;
			} else if (rated.equals(ratings[2])) {
				ratingScore[2]++;
				sumAndNumIdx[0] += 3;
				sumAndNumIdx[1]++;
			} else if (rated.equals(ratings[3])) {
				ratingScore[3]++;
				sumAndNumIdx[0] += 2;
				sumAndNumIdx[1]++;
			} else if (rated.equals(ratings[4])) {
				ratingScore[4]++;
				sumAndNumIdx[0] += 1;
				sumAndNumIdx[1]++;
			}
			
			// Append rating:
			records.append(String.format("Rating: %s\n", rated));
			// Loop Stopper:
			yesOrNo = JOptionPane.showConfirmDialog(null, "Do you want to add another entry? ", "--- ADD MORE? ---", JOptionPane.YES_NO_OPTION);
		}
		
		// Average rating feedback and its average:
		float average = ((float) sumAndNumIdx[0]/sumAndNumIdx[1]);
		String averageFeedback = "";
		if (average >= 4.5) averageFeedback = "Outstanding Feedback!";
		else if (average >= 3.5) averageFeedback = "Good Feedback!";
		else if (average >= 2.5) averageFeedback = "Average Feedback";
		else averageFeedback = "Needs Improvement";
		
		// Append Summary:
		records.append("\n------------------------\n");
		records.append("Total Feedbacks: " + sumAndNumIdx[1]);
		records.append(String.format("\nAverage Rating: %.1f", average));
		records.append("\n\nRating Summary:\n");
		records.append("Excellent: " + ratingScore[0]);
		records.append("\nGood: " + ratingScore[1]);
		records.append("\nAverage: " + ratingScore[2]);
		records.append("\nPoor: " + ratingScore[3]);
		records.append("\nVery Poor: " + ratingScore[4]);
		records.append("\n\n" + averageFeedback);
		records.append("\n------------------------\n\n");
		
		// Display Results:
		JOptionPane.showMessageDialog(null, records.toString(), "--- RESULTS ---", JOptionPane.INFORMATION_MESSAGE);
		
		return records.toString();
	}
	
	public static void writeToFile(String records) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("feedback.txt", true))) {
			bw.write(records);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String records = studentRecords();
		writeToFile(records);
	}
}
