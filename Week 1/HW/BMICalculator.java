import java.util.Scanner;

public class BMICalculator {

    public static void main(String[] args) {
        final int TEAM_SIZE = 10;
        Scanner scanner = new Scanner(System.in);
        double[][] healthData = new double[TEAM_SIZE][2];

        System.out.println("Enter the Weight (kg) and Height (cm) for each of the " + TEAM_SIZE + " team members.");
        System.out.println("==============================================================");

        for (int i = 0; i < TEAM_SIZE; i++) {
            System.out.println("\nEnter details for Person " + (i + 1) + ":");
            System.out.print(" -> Weight (kg): ");
            healthData[i][0] = scanner.nextDouble();
            System.out.print(" -> Height (cm): ");
            healthData[i][1] = scanner.nextDouble();
        }
        scanner.close();

        String[][] finalReport = processTeamData(healthData);
        displayResults(finalReport);
    }

    public static String[] calculateBMIAndStatus(double weightKg, double heightCm) {
        double heightM = heightCm / 100.0;
        double bmi = weightKg / (heightM * heightM);
        String status;

        if (bmi < 18.5) {
            status = "Underweight";
        } else if (bmi < 25) {
            status = "Normal weight";
        } else if (bmi < 30) {
            status = "Overweight";
        } else {
            status = "Obese";
        }

        String[] result = new String[2];
        result[0] = String.format("%.2f", bmi);
        result[1] = status;
        return result;
    }

    public static String[][] processTeamData(double[][] healthData) {
        int teamSize = healthData.length;
        String[][] resultsTable = new String[teamSize][4];

        for (int i = 0; i < teamSize; i++) {
            double weight = healthData[i][0];
            double height = healthData[i][1];

            String[] bmiInfo = calculateBMIAndStatus(weight, height);

            resultsTable[i][0] = String.format("%.1f", weight); // Weight first
            resultsTable[i][1] = String.format("%.1f", height); // Height second
            resultsTable[i][2] = bmiInfo[0];
            resultsTable[i][3] = bmiInfo[1];
        }
        return resultsTable;
    }

    public static void displayResults(String[][] resultsTable) {
        System.out.println("\n\n=============== BMI REPORT ================");
        System.out.printf("%-8s| %-12s| %-12s| %-10s| %-15s%n", "Person", "Weight (kg)", "Height (cm)", "BMI", "Status");
        System.out.println("-------------------------------------------------------------------");

        for (int i = 0; i < resultsTable.length; i++) {
            System.out.printf("%-8d| %-12s| %-12s| %-10s| %-15s%n",
                    (i + 1),
                    resultsTable[i][0],
                    resultsTable[i][1],
                    resultsTable[i][2],
                    resultsTable[i][3]
            );
        }
        System.out.println("===========================================");
    }
}
