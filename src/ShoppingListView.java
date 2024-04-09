package src;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ShoppingListView {
  /**
   * shoppingListView method shows the user's shopping list 
   */
  public static JPanel shoppingListView() {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    // create toolbar
    JPanel buttonPanel = new JPanel();
    JButton backButton = new JButton("Back");
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // call goToHomePanel to direct user to home
        Controller.goToHomePanel();
        System.out.println("backButton Pressed.");
      }
    });
    JButton editButton = new JButton("Edit");
    editButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        editShoppingListView();
        System.out.println("editButton Pressed.");
      }
    });
    JButton clearButton = new JButton("Clear");
    clearButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Call clearList from shopping list to clear the list
        ShoppingList.clearList();
        System.out.println("clearButton Pressed.");
      }
    });
    // Add buttons to buttonPanel
    buttonPanel.add(backButton);
    buttonPanel.add(editButton);
    buttonPanel.add(clearButton);

    // Add button panel to panel
    panel.add(buttonPanel, BorderLayout.NORTH);

    /*
     * TO DO: Add content pane to show shopping list details
     */
    // create recipeList to display contents of shopping list
    // by converting ArrayList<Recipe> shoppingList into an array
    JList recipeList = new JList(ShoppingList.getRecipeList().toArray());
    MouseListener mouseListener = new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int index = recipeList.locationToIndex(e.getPoint());
          System.out.println("Double clicked on Item " + index);
        }
      }
    };
    recipeList.addMouseListener(mouseListener);
    panel.add(recipeList, BorderLayout.CENTER);
    return panel;
  }
  /**
   * editShoppingListView method opens the view to edit the shopping list
   */
  public static void editShoppingListView() {
    JDialog dialog = new JDialog();
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    
    // Create dialogLabel and add it to the panel
    JLabel dialogLabel = new JLabel("Select a recipe to remove from the shopping list:");
    panel.add(dialogLabel, BorderLayout.NORTH);

    // create recipeList to display contents of shopping list
    // by converting ArrayList<Recipe> shoppingList into an array
    JList recipeList = new JList(ShoppingList.getRecipeList().toArray());
    MouseListener mouseListener = new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int index = recipeList.locationToIndex(e.getPoint());
          System.out.println("Double clicked on Item " + index);
        }
      }
    };
    recipeList.addMouseListener(mouseListener);
    panel.add(recipeList, BorderLayout.CENTER);

    // create panel for buttons 
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BorderLayout());
    JButton cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("cancelButton Pressed.");
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
      }
    });
    JButton deleteButton = new JButton("Delete");
    deleteButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("deleteButton Pressed.");
      }
    });
    // add the buttons to the buttonPanel
    buttonPanel.add(cancelButton, BorderLayout.WEST);
    buttonPanel.add(deleteButton, BorderLayout.EAST);
    // add button panel to panel
    panel.add(buttonPanel, BorderLayout.SOUTH);
    // add panel to dialog
    dialog.add(panel);
    // set dialog size 
    dialog.setSize(400, 500);
    // set dialog to visible 
    dialog.setVisible(true);
  }
}