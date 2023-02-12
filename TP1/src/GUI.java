import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GUI {
    JFrame frame;
    JTable table;

    GUI() {
        frame = new JFrame();

        frame.setTitle("Trabalho Prático - AEDsIII");

        String[][] data = {
            {"John Doe", "1234", "Test 1"},
            {"Mary Doe", "4321", "Test 2"}
        };

        String[] columns = {"Name", "Roll Number", "Department"};

        table = new JTable(data, columns);
        table.setBounds(30, 40, 200, 300);

        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane);

        frame.setSize(500, 200);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}
