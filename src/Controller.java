package src;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class Controller {
  private static RecipeManager recipeManager = new RecipeManager();
  static JFrame frame = new JFrame();
  
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
    
    // using border layout
    frame.setLayout(new BorderLayout());

    // create homePanel to display the start page of the app 
    JPanel homePanel = new JPanel();
    homePanel.setLayout(new BorderLayout());
    // add toolbar
    homePanel.add(mainToolBar(), BorderLayout.NORTH);
    // add recipes list
    homePanel.add(recipesList(), BorderLayout.CENTER);

    frame.add(homePanel, BorderLayout.CENTER);
    // making the frame visible
    frame.setVisible(true);
  }

  public static JToolBar mainToolBar() {
    JPanel toolbarPanel = new JPanel();
    JToolBar toolbar = new JToolBar();
    JButton newRecipeButton =  new JButton("Add Recipe");
    JButton searchButton = new JButton("Search");
    JButton viewShoppingListButton = new JButton("Shopping List");
    newRecipeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("newRecipeButton Pressed.");
        AddRecipeView addRecipeView = new AddRecipeView(recipeManager);
        addRecipeView.getUrlView();
      }
    });
    searchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("searchButton Pressed.");
      }
    });
    viewShoppingListButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("viewShoppingListButton Pressed.");
      }
    });
    toolbarPanel.add(newRecipeButton);
    toolbarPanel.add(viewShoppingListButton);
    toolbarPanel.add(searchButton);
    toolbar.add(toolbarPanel);
    return toolbar;
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

  private void changePanel(JPanel panel) {
    frame.getContentPane().removeAll();
    frame.getContentPane().add(panel);
    frame.getContentPane().doLayout();
    frame.update(frame.getGraphics());
  }
}