package org.example.viewer;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainViewer extends JFrame {
    private JPanel mainPanel;
    private JTextField databaseName;
    private JButton openFileButton;
    private JComboBox<String> tablesListDown;
    private JTextArea textArea;
    private JButton executeQueryButton;
    private JTable table;
    private JScrollPane scrollPane;

    public MainViewer() {
        // Setting the main app frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setName("SQLite Viewer"); // Set name or title of the frame/window
        setSize(600, 400); // Set default dimensions of the frame
        setResizable(true);
        setLocationRelativeTo(null);

//        getContentPane().setBackground(Color.LIGHT_GRAY);
//        getContentPane().setBackground(new Color(0x0d5eaf));

        // Initializing components
        initComponents();
        initListeners();

        setVisible(true);
    }


    void initComponents() {
        getContentPane().add(mainPanel);
    }

    void initListeners() {
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Open")) {
                    updateComboBox(databaseName.getText());
                }
                if (e.getSource() == openFileButton) {
                    updateComboBox(databaseName.getText());
                }
            }
        });

        executeQueryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == executeQueryButton) {
                    System.out.println(e);
                    try {
                        updateTableData(databaseName.getText());
                    } catch (SQLException ex) {
                        System.out.println("Error with the DB.");
                    }
                }
            }
        });
    }

    private void generateQuery() {
        textArea.setText("SELECT * FROM " + tablesListDown.getSelectedItem() + ";");
    }

    private void updateComboBox(String databaseName) {
        SQLiteConnection connection = SQLiteConnection.createConnection(databaseName);

        // Remove current items before updating combo box
        tablesListDown.removeAllItems();

        try {
            String[] tables = connection.getAllTables();

            for (String table : tables) {
                tablesListDown.addItem(table);
            }

            this.revalidate(); // Revalidate the container to update the layout

            if (tables.length == 0) {
                tablesListDown.setEnabled(false);
                executeQueryButton.setEnabled(false);
                textArea.setEnabled(false);
                JOptionPane.showMessageDialog(
                        this,
                        "File doesn't exist!",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                generateQuery();
                tablesListDown.setEnabled(true);
                executeQueryButton.setEnabled(true);
                textArea.setEnabled(true);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Something went wrong with the database.");
        }
    }

    private void updateTableData(String databaseName) throws SQLException {
        SQLiteConnection connection = SQLiteConnection.createConnection(databaseName);
        String query = textArea.getText();
        Object[][] newData = connection.getTable(query);
        String[] columnHeaders = connection.getTableColumnHeaders(query); // Add this line
        DataTable tableModel = (DataTable) table.getModel();
//        ((DataTable) table.getModel()).setData(newData);
        tableModel.setData(newData);
        tableModel.setColumnHeaders(columnHeaders); // Add this line
    }


    private void replaceComboBox(String databaseName) {
        SQLiteConnection connection = SQLiteConnection.createConnection(databaseName);

        // Remove current items before updating combo box
        tablesListDown.removeAllItems();

        try {
            String[] tables = connection.getAllTables();

            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

            for (String table : tables) {
                comboBoxModel.addElement(table);
            }

            if (tablesListDown != null) {
                this.remove(tablesListDown); // Remove the existing JComboBox
            }

            tablesListDown = new JComboBox<>(comboBoxModel);

            tablesListDown.setName("TablesComboBox");
            tablesListDown.setBounds(20, 60, 551, 30);
            this.add(tablesListDown);

            for (String table : tables) {
                tablesListDown.addItem(table);
            }

            this.revalidate(); // Revalidate the container to update the layout

            generateQuery();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        DataTable dataTable = new DataTable();
        dataTable.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
            }
        });
        table = new JTable(dataTable);
        table.setName("Table");
    }
}
