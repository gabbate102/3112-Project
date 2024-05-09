package src;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyledDocument;

public class ShoppingListView {
  /**
   * shoppingListView method shows the user's shopping list 
   */
  public static JPanel shoppingListView() {
    // create panel
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    // set a border for the panel
    panel.setBorder(BorderFactory.createEmptyBorder(0,30,30,30));

    // create toolbar
    JPanel buttonPanel = new JPanel();

    // create back button
    JButton backButton = new JButton("Back");
    backButton.setToolTipText("Go back to start page");
    // add event listener to button 
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // call goToHomePanel to direct user to home
        Controller.goToHomePanel();
        System.out.println("backButton Pressed.");
      }
    });
    
    // create edit button
    JButton editButton = new JButton("Edit");
    editButton.setToolTipText("Edit your shopping list");
    // add event listener to button
    editButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        editShoppingListView();
        System.out.println("editButton Pressed.");
      }
    });

    // create clear button
    JButton clearButton = new JButton("Clear");
    clearButton.setToolTipText("Clear your shopping list");
    // add event listener to button
    clearButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // Call clearList from shopping list to clear the list
        ShoppingList.clearList();
        System.out.println("clearButton Pressed.");
        reloadPanel();
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

    // create recipeList
    JList<Recipe> recipeList = new JList(ShoppingList.getRecipeList().toArray());
    // add mouseListener to list elements
    MouseListener mouseListener = new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int index = recipeList.locationToIndex(e.getPoint());
          System.out.println("Double clicked on Item " + index);
        }
      }
    };
    recipeList.addMouseListener(mouseListener);

    // add shoppingListDetails to panel
    panel.add(shoppingListDetails(), BorderLayout.CENTER);

    return panel;
  }

  /**
   * Creates JScrollPane containing formatted details of shopping list
   * @return
   */
  public static JScrollPane shoppingListDetails() {
    // initialize shoppingListRecipes
    Recipe[] shoppingListRecipes = new Recipe[ShoppingList.getRecipeList().size()];

    // get items on shopping list
    shoppingListRecipes = ShoppingList.getRecipeList().toArray(shoppingListRecipes);

    // create textPane to display recipe details
    JTextPane textPane = new JTextPane(); 

    // Create a custom StyledDocument to display in textPane 
    StyledDocument doc = textPane.getStyledDocument(); 

    // create styling for doc
    Style normal = doc.addStyle("customStyle", null); 

    // insert text into document
    try {
      for (Recipe recipe : shoppingListRecipes) {

        String[] ingredienstList = recipe.getIngredients();
        for (int i = 0; i < ingredienstList.length; i++) {
          doc.insertString(i, ingredienstList[i] + "\n", normal);
        }
        doc.insertString(0, "Ingredients: "+"\n", normal);
        doc.insertString(0, recipe.getRecipeName()+"\n\n", normal);
        doc.insertString(0, "\n---------------------------\n", normal);
      }
    } 
    catch (BadLocationException e) { 
      e.printStackTrace(); 
    } 

    // Set the StyledDocument for textPane
    textPane.setStyledDocument(doc); 
    // add TextPane to scrollArea
    JScrollPane scrollArea = new JScrollPane(textPane);
    
    return scrollArea;
  }

  /**
   * editShoppingListView method opens the view to edit the shopping list
   */
  public static void editShoppingListView() {
    // create dialog 
    JDialog dialog = new JDialog();

    // create panel and set layout to borderlayout
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    
    // Create dialogLabel and add it to the panel
    JLabel dialogLabel = new JLabel("Select a recipe to remove from the shopping list:");
    panel.add(dialogLabel, BorderLayout.NORTH);

    // create recipeList to display contents of shopping list
    // by converting ArrayList<Recipe> shoppingList into an array
    ArrayList<Recipe> list = ShoppingList.getRecipeList();
    String[] shoppingListArray = new String[list.size()];
    for (int i = 0; i < shoppingListArray.length; i++) {
      shoppingListArray[i] = list.get(i).getRecipeName();
    }

    JList<String> recipeList = new JList<String>(shoppingListArray);
    MouseListener mouseListener = new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int index = recipeList.locationToIndex(e.getPoint());
          System.out.println("Double clicked on Item " + index);
        }
      }
    };
    recipeList.addMouseListener(mouseListener);

    // add recipeList to panel
    panel.add(recipeList, BorderLayout.CENTER);

    // create panel for buttons 
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BorderLayout());

    // create cancelButton and add actionListener
    JButton cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("cancelButton Pressed.");
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
      }
    });

    // create delete button and add actionListener
    JButton deleteButton = new JButton("Delete");
    
    deleteButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("deleteButton Pressed.");
        int selectedIndex = recipeList.getSelectedIndex();
        ShoppingList.removeRecipe(selectedIndex);

        reloadPanel();
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

  private static void reloadPanel() {
    Controller.changePanel(shoppingListView());
  }
}