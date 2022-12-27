// Grace Foster
// TO-Do List application final semester project

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// 210 lines of code without the comments and imports from both files combined.

public class TodoListApp
{

    // creates the task panel
    private static JPanel taskPanel;

    // create the buttons for the buttons panel
    private static JButton addButton;
    private static JButton deleteButton;



    public static void main(String[] args)     {
        //creates main frame
        JFrame frame = new JFrame("To-Do List");

        // calling methods and frame info
        setupApp(frame);

        // sets the frame to become visibility
        frame.setVisible(true);
    }

    public static void setupApp(JFrame frame)    {
        //setting size for the frame
        frame.setSize(600,800);

        // setting icon img for application
        ImageIcon img = new ImageIcon("images/toDoListLogo.png");
        frame.setIconImage(img.getImage());

        // sets the frame to not be resized
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // add two panels in the main new panel to show both in the window.
        //create main panel
        JPanel mainPanel = new JPanel();

        // config title using the main panel which is passed as a parameter
        configureTitle(mainPanel);

        // config task panel using the main panel which is passed as a parameter
        configureTaskPanel(mainPanel);

        // config buttons panel using the main panel which is passed as a parameter
        configureButtonsPanel(mainPanel);

        // add main panel to the main frame
        frame.add(mainPanel);

    }

    public static void configureTitle(JPanel panel)    {
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("MY TODO LIST");

        titleLabel.setBackground(Color.LIGHT_GRAY);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD,18));

        titlePanel.add(titleLabel);
        titlePanel.setPreferredSize(new Dimension(600, 50));

        panel.add(titlePanel, BorderLayout.NORTH);
    }

    public static void configureTaskPanel(JPanel panel)    {
        // initialize the panel where the tasks are going to be added
        taskPanel = new JPanel();

        taskPanel.setLayout(new BoxLayout(taskPanel,BoxLayout.PAGE_AXIS));

        // bg color for buttons panel
        taskPanel.setBackground(new Color(125, 124, 122));

        // add scroll bar for when there is a lot of tasks
        JScrollPane scrollPane = new JScrollPane(taskPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(580,650));

        // add button panel to main frame
        panel.add(scrollPane,BorderLayout.CENTER);


    }

    public static void configureButtonsPanel(JPanel mainPanel)    {

        // create the panel where buttons are going to be added
        JPanel buttonsPanel = new JPanel();

        // set bg color for buttons panel
        buttonsPanel.setBackground(new Color(91, 91, 91));

        //sets size of the panel
        buttonsPanel.setPreferredSize(new Dimension(589,40));

        // initialize the buttons
        addButton = new JButton("ADD TASK");
        deleteButton = new JButton("CLEAR TASKS");

        // add functionality to buttons
        addButtonsListeners(mainPanel);

        // add buttons to panel
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);

        // add button panel to main frame
        mainPanel.add(buttonsPanel,BorderLayout.NORTH);

    }

    public static void addButtonsListeners(JPanel mainPanel)    {
        addTaskButtonListener(mainPanel);
        deleteButtonListener(mainPanel);
    }

    private static void addTaskButtonListener(JPanel mainPanel)     {
        addButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                // creates new Task class variable
                Task task = new Task();
                int numOfTasks = taskPanel.getComponentCount();
                task.changeIndex(numOfTasks + 1);
                task.setMaximumSize(new Dimension(500, 80));

                taskPanel.add(task);

                //update UI
                mainPanel.revalidate();
                mainPanel.repaint();

            }
        });
    }

    private static void deleteButtonListener(JPanel mainPanel)     {
        deleteButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                //remove tasks that are finished
                removeFinishedTasks();

                //re-organize indices
                organizeIndices();

                //update UI
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
    }

    private static void removeFinishedTasks()    {
        //list all tasks in tasks panel
        Component[] tasks = taskPanel.getComponents();

        for(int i = 0; i < tasks.length; i++)
        {
            if(tasks[i] instanceof Task )
            {
                if((((Task) tasks[i]).isFinished()))
                {
                    taskPanel.remove( tasks[i]);
                }
            }
        }
    }

    private static void organizeIndices()    {
        Component[] tasks = taskPanel.getComponents();

        for(int i = 0; i < tasks.length; i++)
        {
            if(tasks[i] instanceof Task )
            {
                ((Task) tasks[i]).changeIndex(i + 1);
            }
        }
    }
}

