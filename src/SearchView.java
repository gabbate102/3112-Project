package src;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class SearchView {
  public static JPanel searchView(RecipeManager recipeManager) {
    JPanel searchPanel = new JPanel();
    searchPanel.setLayout(new BorderLayout());

    // create toolBar
    JPanel toolBar = new JPanel();

    JTextField searchField = new JTextField(24);
    JButton backButton = new JButton("Back");
    // add actionlistener to backButton 
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("backButton Pressed.");
      }
    });
    JButton seachButton = new JButton("Search");
    // add actionlistener to seachButton 
    seachButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("seachButton Pressed.");
        // get the text of the field
        System.out.println(searchField.getText());
 
        // set the text of field to blank
        searchField.setText(" ");
      }
    });
    JButton filterButton = new JButton("Filter");
    // add actionlistener to filterButton 
    filterButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("filterButton Pressed.");
      }
    });
    
    // add elements to toolBar
    toolBar.add(backButton);
    toolBar.add(filterButton);
    toolBar.add(searchField);
    toolBar.add(searchButton);

    // Create JList element 
    // !!!!!!! TO DO: ADD RESULTS LIST TO JLIST !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    JList recipeList = new JList();
    searchPanel.add(recipeList, BorderLayout.CENTER);

    // add toolbar to searchPanel
    searchPanel.add(toolBar, BorderLayout.NORTH);

    return searchPanel;
  }
}