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

    JTextField searchField = new JTextField(18);
    JButton backButton = new JButton("Back");
    // add actionlistener to backButton 
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("backButton Pressed.");
        Controller.goToHomePanel();
      }
    });
    JButton searchButton = new JButton("Search");
    // add actionlistener to seachButton 
    searchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("searchButton Pressed.");
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
    MouseListener mouseListener = new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int index = recipeList.locationToIndex(e.getPoint());
          System.out.println("Double clicked on Item " + index);
        }
      }
    };
    recipeList.addMouseListener(mouseListener);
    searchPanel.add(recipeList, BorderLayout.CENTER);

    // add toolbar to searchPanel
    searchPanel.add(toolBar, BorderLayout.NORTH);

    return searchPanel;
  }
}