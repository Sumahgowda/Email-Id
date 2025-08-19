package credentials;
import java.util.Random;
import java.util.Scanner;

public interface CredentialService {
	String generateEmail();
    String generatePassword(int length);

}
public class CredentialGenerator implements CredentialService{

		  private String firstName;
		    private String lastName;
		    private String department;
		    private String password; 

		    public CredentialGenerator(String firstName, String lastName, int deptChoice) {
		        this.setFirstName(firstName);
		        this.setLastName(lastName);
		        this.setDepartment(deptChoice);
		        this.password = generatePassword(10); // default generated password
		    }

		    // Getters and Setters
		    public String getFirstName() {
		        return firstName;
		    }

		    public void setFirstName(String firstName) {
		        this.firstName = firstName.trim();
		    }

		    public String getLastName() {
		        return lastName;
		    }

		    public void setLastName(String lastName) {
		        this.lastName = lastName.trim();
		    }

		    public String getDepartment() {
		        return department;

		    }

		    public void setDepartment(int choice) {
		        switch (choice) {
		            case 1 -> this.department = "Technical";
		            case 2 -> this.department = "Admin";
		            case 3 -> this.department = "Placement";
		            case 4 ->this.department = "CS";
		            case 5 ->this.department = "IS";
		            case 6 ->this.department = "AI";
		            case 7 ->this.department = "EC";
		            
		            default -> {
		                System.out.println("Invalid choice. Defaulting to 'General'.");
		                this.department = "General";
		            }
		        }
		    }

		    public String getPassword() {
		        return password;
		    }

		    // Strong password validation
		    private boolean isValidPassword(String newPassword) {
		        if (newPassword.length() < 8) return false;

		        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
		        String specialChars = "@#$%&*!";

		        for (char ch : newPassword.toCharArray()) {
		            if (Character.isUpperCase(ch)) hasUpper = true;
		            else if (Character.isLowerCase(ch)) hasLower = true;
		            else if (Character.isDigit(ch)) hasDigit = true;
		            else if (specialChars.indexOf(ch) >= 0) hasSpecial = true;
		        }
		        return hasUpper && hasLower && hasDigit && hasSpecial;
		    }
		    public void setPassword(String newPassword) {
		        if (!isValidPassword(newPassword)) {
		            System.out.println("❌ Weak password! Must be at least 8 chars long and include:");
		            System.out.println("   - 1 Uppercase letter");
		            System.out.println("   - 1 Lowercase letter");
		            System.out.println("   - 1 Digit");
		            System.out.println("   - 1 Special char (@#$%&*!)");
		        } else {
		            this.password = newPassword;
		            System.out.println("✅ Password updated successfully!");
		        }
		    }

		    // Generate email
		    @Override
		    public String generateEmail() {
		        return getFirstName().toLowerCase() +
		               getLastName().toLowerCase() +
		               "@" +
		               getDepartment().toLowerCase() +
		               ".skit.edu.in";
		    }

		    // Generate password
		    @Override
		    public String generatePassword(int length) {
		        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		        String smallLetters = "abcdefghijklmnopqrstuvwxyz";
		        String numbers = "0123456789";
		        String specialChars = "@#$%&*!";
		        String allChars = capitalLetters + smallLetters + numbers + specialChars;

		        Random rand = new Random();
		        StringBuilder password = new StringBuilder();

		        password.append(capitalLetters.charAt(rand.nextInt(capitalLetters.length())));
		        password.append(smallLetters.charAt(rand.nextInt(smallLetters.length())));
		        password.append(numbers.charAt(rand.nextInt(numbers.length())));
		        password.append(specialChars.charAt(rand.nextInt(specialChars.length())));

		        for (int i = 4; i < length; i++) {
		            password.append(allChars.charAt(rand.nextInt(allChars.length())));
		        }

		        return password.toString();
		    }

		    // Display credentials
		    public void displayCredentials() {
		        String email = generateEmail();
		        System.out.println("\nGenerated Credentials:");
		        System.out.println("Email    : " + email);
		        System.out.println("Password : " + getPassword());
		    }

		    
		    public static void main(String[] args) {
		        Scanner scanner = new Scanner(System.in);

		        System.out.print("Enter First Name: ");
		        String firstName = scanner.nextLine();

		        System.out.print("Enter Last Name: ");
		        String lastName = scanner.nextLine();

		        System.out.println("Choose Department:");
		        System.out.println("1. Technical\n2. Admin\n3. Placement \n4. CS \n5. IS \n6. AI \n7. EC");
		        int choice = scanner.nextInt();
		        scanner.nextLine(); // consume leftover newline

		        // Create object
		        CredentialGenerator generator = new CredentialGenerator(firstName, lastName, choice);
		        generator.displayCredentials();

		        // Ask user if they want to change password
		        System.out.print("\nDo you want to change your password? (yes/no): ");
		        String response = scanner.nextLine();

		        if (response.equalsIgnoreCase("yes")) {
		            System.out.print("Enter new password: ");
		            String newPassword = scanner.nextLine();
		            generator.setPassword(newPassword);
		            generator.displayCredentials();
		        }

	}

}

