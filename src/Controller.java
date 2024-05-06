package src;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class Controller {
  public static RecipeManager recipeManager = new RecipeManager();
  private static JFrame frame = new JFrame();
  private static JPanel homePanel = new JPanel();
  
  public static void main(String[] args) {
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // 400 width and 500 height
    frame.setSize(500, 600);

    // create homePanel to display the start page of the app 
    homePanel.setLayout(new BorderLayout());
    // add toolbar
    homePanel.add(mainToolBar(), BorderLayout.NORTH);
    // add recipes list
    homePanel.add(recipesList(), BorderLayout.CENTER);

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
        Controller.changePanel(SearchView.searchView(recipeManager));
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
  public static JList<Recipe> recipesList() {
    DefaultListModel<Recipe> listModel = new DefaultListModel<>();
    ArrayList<Recipe> recipes = recipeManager.getRecipes();
    if (recipes.size() > 0) {
      for (Recipe item : recipes) {
        listModel.addElement(item);
      }
    }
    JList<Recipe> jlist = new JList<>(listModel);
    MouseListener mouseListener = new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int index = jlist.locationToIndex(e.getPoint());
          System.out.println("Double clicked on Item " + index);
          System.out.println(recipes.get(index).getRecipeName());
          // open the detailsView
          JPanel details = RecipeView.detailsView(recipes.get(index));
          changePanel(details);
        }
      }
    };
    jlist.addMouseListener(mouseListener);
    return jlist;
  }

  /**
   * Method changes current panel to panel passed as parameter
   * @param newPanel
   */
  private static void changePanel(JPanel newPanel) {
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
  }
}