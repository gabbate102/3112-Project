package src;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

/**
 * Class searchView is the view to the search page 
 */
public class SearchView {
  private static ArrayList<Recipe> searchResults = new ArrayList<Recipe>();
  private static DefaultListModel<String> listModel;

  /**
   * Creates search view
   * @param recipeManager
   * @return JPanel
   */
  public static JPanel searchView() {
    JPanel searchPanel = new JPanel();
    // set searchPanel layout to borderLayout
    searchPanel.setLayout(new BorderLayout());
    // set a border for the panel
    searchPanel.setBorder(BorderFactory.createEmptyBorder(0,30,30,30));

    // create toolBar
    JPanel toolBar = new JPanel();

    // create searchField
    JTextField searchField = new JTextField(18);
    searchField.setToolTipText("Search by Name or enter ingredients seperated by commas");

    // create backButton
    JButton backButton = new JButton("Back");
    backButton.setToolTipText("Go back to start page");

    // add actionlistener to backButton 
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("backButton Pressed.");
        Controller.goToHomePanel();
      }
    });

    // create searchButton
    JButton searchButton = new JButton("Search");

    // add actionlistener to seachButton 
    searchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("searchButton Pressed.");

        // get the text of the field
        System.out.println(searchField.getText());
        String query = searchField.getText();

        // set the text of field to blank
        searchField.setText(" ");

        // check if query is csv format
        if (query.contains(",")) {
          // if it is, search by ingredient 
          searchResults = Controller.recipeManager.searchByIngredient(query.split(","));
        } else {
          // call the searchByName method 
          searchResults = Controller.recipeManager.searchByName(query);
        }

        // call getListModel to refresh list
        getListModel();

        // call reloadPanel to reload panel 
        reloadPanel();
      }
    });
    
    // add elements to toolBar
    toolBar.add(backButton);
    toolBar.add(searchField);
    toolBar.add(searchButton);

    // create recipeList for listModel contents
    getListModel();
    JList<String> recipeList = new JList<String>(listModel);

    // change formatting 
    recipeList.setFont( new Font("Arial", Font.PLAIN, 16) );

    // add a mouseListener to the elements of recipeList
    MouseListener mouseListener = new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int index = recipeList.locationToIndex(e.getPoint());
          System.out.println("Double clicked on Item " + index);
          JPanel recipeDetails = RecipeView.detailsView(searchResults.get(index));
          Controller.changePanel(recipeDetails);
        }
      }
    };
    recipeList.addMouseListener(mouseListener);

    // add recipeList to a scrollPane
    JScrollPane scrollPane = new JScrollPane(recipeList);

    // add scrollPane to searchPanel
    searchPanel.add(scrollPane, BorderLayout.CENTER);

    // add toolbar to searchPanel
    searchPanel.add(toolBar, BorderLayout.NORTH);

    return searchPanel;
  }

  /**
   * Method creates the list model for jList
   */
  private static void getListModel() {
    // initialize listModel
    listModel = new DefaultListModel<>();

    // add the elements from the recipes list to the model
    if (searchResults.size() > 0) {
      for (Recipe item : searchResults) {
        listModel.addElement(item.getRecipeName());
      }
    }
  }

  /**
   * refreshes the results list by recreating the searchView
   */
  private static void reloadPanel() {
    System.out.println("reloadPanel called.");
    Controller.changePanel(searchView());
  }
}