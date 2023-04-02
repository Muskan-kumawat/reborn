import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentProgressReport extends JFrame implements ActionListener {
    
    private JLabel studentNameLabel, mathGradeLabel, scienceGradeLabel, englishGradeLabel, averageGradeLabel;
    private JTextField studentNameTextField, mathGradeTextField, scienceGradeTextField, englishGradeTextField, averageGradeTextField;
    private JButton addButton, viewButton;
    private ArrayList<StudentReport> studentsList;
    private Connection connection;

    public StudentProgressReport() {
        setTitle("Student Progress Report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        studentsList = new ArrayList<StudentReport>();

        studentNameLabel = new JLabel("Student Name: ");
        mathGradeLabel = new JLabel("Math Grade: ");
        scienceGradeLabel = new JLabel("Science Grade: ");
        englishGradeLabel = new JLabel("English Grade: ");
        averageGradeLabel = new JLabel("Average Grade: ");

        studentNameTextField = new JTextField(20);
        mathGradeTextField = new JTextField(5);
        scienceGradeTextField = new JTextField(5);
        englishGradeTextField = new JTextField(5);
        averageGradeTextField = new JTextField(5);
        averageGradeTextField.setEditable(false);

        addButton = new JButton("Add Student");
        addButton.addActionListener(this);

        viewButton = new JButton("View Report");
        viewButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(studentNameLabel);
        panel.add(studentNameTextField);
        panel.add(mathGradeLabel);
        panel.add(mathGradeTextField);
        panel.add(scienceGradeLabel);
        panel.add(scienceGradeTextField);
        panel.add(englishGradeLabel);
        panel.add(englishGradeTextField);
        panel.add(averageGradeLabel);
        panel.add(averageGradeTextField);
        panel.add(addButton);
        panel.add(viewButton);

        add(panel);

        setVisible(true);

        try {
            // Connect to the database
            String url = "jdbc:mysql://localhost:3306/student_progress_report?useSSL=false";
            String user = "username";
            String password = "password";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + ex.getMessage());
        }
    
    }

    public static void main(String[] args) {
        new StudentProgressReport();
       }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String studentName = studentNameTextField.getText();
            double mathGrade = Double.parseDouble(mathGradeTextField.getText());
            double scienceGrade = Double.parseDouble(scienceGradeTextField.getText());
            double englishGrade = Double.parseDouble(englishGradeTextField.getText());

            StudentReport student = new StudentReport();
            studentsList.add(student);

            // Insert the student data into the database
            try {
                String query = "INSERT INTO students (name, math_grade, science_grade, english_grade) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, studentName);
                statement.setDouble(2, mathGrade);
                statement.setDouble(3, scienceGrade);
                statement.setDouble(4, englishGrade);
                statement.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error inserting data: " + ex.getMessage());
            }

            double averageGrade = getAverageGrade();
             averageGradeTextField.setText(Double.toString(averageGrade));
        }
        else if (e.getSource() == viewButton);}

	private double getAverageGrade() {
		// TODO Auto-generated method stub
		return 0;
	}}
            //