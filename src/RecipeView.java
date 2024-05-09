package src;

import java.awt.event.*;
import javax.swing.text.*;
import java.awt.*;
import javax.swing.*;

/**
 * RecipeView class requires the Recipe object to be edited and a 
 * RecipeManager object to be able to manage recipes. This class creates the view
 * for editing a given recipe
 */
public class RecipeView {

  /**
   * detailsView creates the view for viewing recipes
   */
  public static JPanel detailsView(Recipe recipe) {

    // Create panel
    JPanel panel = new JPanel();
    // set layout to border
    panel.setLayout(new BorderLayout());
    // set border
    panel.setBorder(BorderFactory.createEmptyBorder(0,30,30,30));

    // create textPane to display recipe details
    JTextPane textPane = new JTextPane(); 
    // Create a custom StyledDocument to display in textPane 
    StyledDocument doc = textPane.getStyledDocument(); 
    // create styling for doc
    Style style = doc.addStyle("customStyle", null); 
    StyleConstants.setBold(style, true); 

    // insert text into document
    try {
      doc.insertString(0, recipe.getProcedureString() + "\n", style); 
      doc.insertString(0, recipe.getIngredientsString() + "\n", style); 
      doc.insertString(0, recipe.getRecipeURL() + "\n", style); 
      doc.insertString(0, recipe.getAuthor() + "\n", style); 
      doc.insertString(0, recipe.getRecipeName() + "\n", style); 
    } 
    catch (BadLocationException e) { 
      e.printStackTrace(); 
    } 

    // Set the StyledDocument for textPane
    textPane.setStyledDocument(doc); 

    // create textScrollPane to hold the recipe text
    JScrollPane textScrollPane = new JScrollPane(textPane);

    panel.add(textScrollPane, BorderLayout.CENTER); 
    
    // add toolbar to panel
    panel.add(detailsToolBar(recipe), BorderLayout.NORTH);

    return panel;
  }

  /**
   * Creates the toolbar for the details view
   * @param recipe
   * @return JPanel
   */
  private static JPanel detailsToolBar(Recipe recipe) {

    // create toolbarPanel
    JPanel toolbarPanel = new JPanel();
    
    // create buttons for the toolbar
    JButton backButton =  new JButton("Back");
    JButton addToShoppingListButton = new JButton("+ Add to Shopping List");
    JButton editButton = new JButton("Edit");
    JButton deleteButton = new JButton("Delete");

    // create event listeners for each button
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("backButton Pressed.");
        Controller.goToHomePanel();
      }
    });

    addToShoppingListButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("addToShoppingListButton Pressed.");
        ShoppingList.addRecipe(recipe);
      }
    });

    editButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("editButton Pressed.");
        AddRecipeView.editView(recipe);
      }
    });

    deleteButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("deleteButton Pressed.");
        Controller.deleteRecipe(recipe);
        Controller.reload();
        Controller.goToHomePanel();
      }
    });

    // add the buttons to the toolbar
    toolbarPanel.add(backButton);
    toolbarPanel.add(addToShoppingListButton);
    toolbarPanel.add(editButton);
    toolbarPanel.add(deleteButton);

    // return toolbarPanel
    return toolbarPanel;
  }
}