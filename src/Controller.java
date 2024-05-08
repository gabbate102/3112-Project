package src;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.util.*;

public class Controller {
  public static RecipeManager recipeManager = new RecipeManager();
  private static JFrame frame = new JFrame();
  private static JPanel homePanel = new JPanel();
  private static DefaultListModel<String> listModel;
  private static ArrayList<Recipe> recipes = recipeManager.getRecipes();
  
  public static void main(String[] args) {
    // load the shoppingList file on application start
    ShoppingList.loadFile();
    
    // set the default close operation 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // set size
    frame.setBounds(300, 90, 900, 600);

    // set title 
    frame.setTitle("RecipeBook");
    
    // set a border for the panel
    homePanel.setBorder(BorderFactory.createEmptyBorder(0,30,30,30));
    // create homePanel to display the start page of the app 
    homePanel.setLayout(new BorderLayout());
    // add toolbar
    homePanel.add(mainToolBar(), BorderLayout.NORTH);
    // add recipes list
    homePanel.add(recipesList(), BorderLayout.CENTER);

    // create welcome message panel
    JPanel welcomePanel = new JPanel();
    JLabel welcomeMessage = new JLabel("Welcome to RecipeBook!\n");
    welcomePanel.add(welcomeMessage);
    welcomePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    // add welcome panel
    homePanel.add(welcomePanel, BorderLayout.WEST);

    // add homePanel to frame
    frame.getContentPane().add(homePanel);
    
    // making the frame visible
    frame.setVisible(true);
  }

  /**
   * Method creates main toolbar for home screen 
   * @return JPanel
   */
  public static JPanel mainToolBar() {
    // create toolbarpanel to hold toolbar elements
    JPanel toolbarPanel = new JPanel();

    // create newRecipeButton
    JButton newRecipeButton =  new JButton("Add Recipe");
    // create searchButton
    JButton searchButton = new JButton("Search");
    // create viewShoppingListButton
    JButton viewShoppingListButton = new JButton("Shopping List");

    // add actionlistener to newRecipeButton
    newRecipeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("newRecipeButton Pressed.");
        // open getUrlView
        AddRecipeView.getUrlView();
      }
    });

    // add action listener to searchButton
    searchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // change panel to show searchView
        Controller.changePanel(SearchView.searchView());
        System.out.println("searchButton Pressed.");
      }
    });

    // add action listener to viewShoppingListButton
    viewShoppingListButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // change view to shoppingListView
        Controller.changePanel(ShoppingListView.shoppingListView());
        System.out.println("viewShoppingListButton Pressed.");
      }
    });

    // add buttons to toolbarPanel
    toolbarPanel.add(newRecipeButton);
    toolbarPanel.add(viewShoppingListButton);
    toolbarPanel.add(searchButton);

    // return toolbarPanel
    return toolbarPanel;
  }

  /**
   * This method returns a panel with the main list of recipes.
   * @return JList
   */
  public static JScrollPane recipesList() {
    // call getListModel on render
    getListModel();

    // create JList element with model from the getListModel method
    JList<String> jlist = new JList<>(listModel);

    MouseListener mouseListener = new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int index = jlist.locationToIndex(e.getPoint());
          // open the detailsView
          JPanel details = RecipeView.detailsView(recipes.get(index));
          changePanel(details);
        }
      }
    };

    // add mouseListener to jList
    jlist.addMouseListener(mouseListener);

    // add jlist to JScrollPane
    JScrollPane scrollPane = new JScrollPane(jlist);

    // return jList
    return scrollPane;
  }

  /**
   * Gets list model to display in jlist on main page
   * @return DefaultListModel<String> 
   */
  public static void getListModel() {
    listModel = new DefaultListModel<>();

    // add the elements from the recipes list to the model
    if (recipes.size() > 0) {
      for (Recipe item : recipes) {
        listModel.addElement(item.getRecipeName());
      }
    }
  }

  /**
   * Method changes current panel to panel passed as parameter
   * @param newPanel
   */
  public static void changePanel(JPanel newPanel) {
    frame.getContentPane().removeAll();
    frame.getContentPane().add(newPanel);
    frame.getContentPane().doLayout();
    frame.update(frame.getGraphics());
    frame.revalidate();
  }

  /**
   * Static method to allow user to return to home panel
   */
  public static void goToHomePanel() {
    frame.getContentPane().removeAll();
    frame.getContentPane().add(homePanel);
    frame.getContentPane().doLayout();
    frame.update(frame.getGraphics());

    getListModel();
  }

  /**
   * Static method to allow parts of the app to delete a recipe
   */
  public static void deleteRecipe(Recipe recipe) {
    recipeManager.deleteRecipe(recipe);
  }

  public static void reload() {
    frame.revalidate();
    frame.repaint();
  }
}