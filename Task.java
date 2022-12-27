import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Task extends JPanel {

    private JTextField taskName;
    private JCheckBox taskCompleted;
    private JLabel index;

        public Task()
        {
            int baseHeight = 50;

            // task panel settings
            setBackground(new Color(206, 212, 218));
            setLayout(new BorderLayout());

            // creating the border for the task panel
            Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
            setBorder(blackLine);

            // initializing task components
            taskName = new JTextField("Type Here");
            taskCompleted = new JCheckBox();
            index = new JLabel("1.");

            taskName.setPreferredSize(new Dimension(200,baseHeight));
            taskName.setFont(new Font("Verdana", Font.BOLD,14));

            // setting preferred size and alignment of task index
            index.setPreferredSize(new Dimension(50,baseHeight));
            index.setHorizontalAlignment(JLabel.CENTER);

            // setting preferred size and alignment of checkbox
            taskCompleted.setPreferredSize(new Dimension(40, baseHeight));
            taskCompleted.setHorizontalAlignment(JCheckBox.CENTER);

            // adding index JLabel to the WEST of the panel
            add(index, BorderLayout.WEST);
            // adding taskName JTextField to the Center of the panel
            add(taskName, BorderLayout.CENTER);
            // adding taskCompleted JCheckBox to the EAST of the panel
            add(taskCompleted, BorderLayout.EAST);
        }

        // changes the number in the index of the current task
        public void changeIndex(int num)
        {
            index.setText(num + ".");

            // updates UI
            revalidate();
            repaint();
        }

        // checks the checkbox if it is completed and will clear task from the panel.
        public boolean isFinished()
        {
            return taskCompleted.isSelected();
        }

}
