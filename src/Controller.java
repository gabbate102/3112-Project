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
    /*  IDK IF THIS WORKS 
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
    } catch (ClassNotFoundException c) {
      System.out.println(c);
    } catch (InstantiationException i) {
      System.out.println(i);
    } catch (IllegalAccessException ill) {
      System.out.println(ill);
    } catch (UnsupportedLookAndFeelException un) {
      System.out.println(un);
    }
*/
    
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

  public static JPanel mainToolBar() {
    JPanel toolbarPanel = new JPanel();
    JButton newRecipeButton =  new JButton("Add Recipe");
    JButton searchButton = new JButton("Search");
    JButton viewShoppingListButton = new JButton("Shopping List");
    newRecipeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("newRecipeButton Pressed.");
        AddRecipeView.getUrlView();
      }
    });
    searchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("searchButton Pressed.");
      }
    });
    viewShoppingListButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Controller.changePanel(ShoppingListView.shoppingListView());
        System.out.println("viewShoppingListButton Pressed.");
      }
    });
    toolbarPanel.add(newRecipeButton);
    toolbarPanel.add(viewShoppingListButton);
    toolbarPanel.add(searchButton);
    return toolbarPanel;
  }

  public static JPanel recipesList() {
    DefaultListModel<Recipe> listModel = new DefaultListModel<>();
    ArrayList<Recipe> recipes = recipeManager.getRecipes();
    if (recipes.size() > 0) {
      for (Recipe item : recipes) {
        listModel.addElement(item);
      }
    }
    JList<Recipe> jlist = new JList<>(listModel);
    JScrollPane scrollPane = new JScrollPane(jlist);

    JPanel panel = new JPanel();
    panel.add(scrollPane);
    return panel;
  }

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