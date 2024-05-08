package src;
import java.util.*;
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
      
      doc.insertString(doc.getLength(), "button: ", null); 
    } 
    catch (BadLocationException e) { 
      e.printStackTrace(); 
    } 

    // Set the StyledDocument for textPane
    textPane.setStyledDocument(doc); 

    panel.add(textPane, BorderLayout.CENTER);
    
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
        RecipeView.editView(recipe);
      }
    });
    deleteButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("deleteButton Pressed.");
        Controller.deleteRecipe(recipe);
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
  /**
   * editView creates the view for editing recipes
   */
  public static void editView(Recipe recipe) {
    JDialog dialog = new JDialog();
    
    // set dialog size
    dialog.setBounds(300, 90, 900, 600);

    // set dialog as not resizable 
    dialog.setResizable(false);


    // create new panel
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());    


    // Create edit panel to hold editing panes together 
    JPanel editPanel = new JPanel();
    //editPanel.setLayout(new GridLayout(5,2));

    // Create text field for recipe name
    JTextField nameTextField = new JTextField(recipe.getRecipeName());
    nameTextField.setColumns(24);
    nameTextField.setBorder(
      BorderFactory.createTitledBorder("Recipe Title"));

    // Create text field for recipe author
    JTextField authorTextField = new JTextField(recipe.getAuthor());
    authorTextField.setColumns(24);
    authorTextField.setBorder(
      BorderFactory.createTitledBorder("Author"));

    // Create text field for recipe URL
    JTextField urlTextField = new JTextField(recipe.getRecipeURL());
    urlTextField.setColumns(24);
    urlTextField.setBorder(
      BorderFactory.createTitledBorder("URL"));

    // Create text pane for recipe ingredients
    JEditorPane ingredientsTextPane = new JEditorPane();
    ingredientsTextPane.setText(recipe.getIngredientsString());
    JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsTextPane);
    ingredientsScrollPane.setBorder(
      BorderFactory.createTitledBorder("Ingredients"));

    // Create text field for recipe procedure
    JEditorPane procedureTextPane = new JEditorPane();
    procedureTextPane.setText(recipe.getProcedureString());
    JScrollPane procedureScrollPane = new JScrollPane(procedureTextPane);
    procedureScrollPane.setBorder(
      BorderFactory.createTitledBorder("Procedure"));

    // add labels to labelPanel
    editPanel.add(nameTextField);
    editPanel.add(authorTextField);
    editPanel.add(urlTextField);
    editPanel.add(ingredientsScrollPane);
    editPanel.add(procedureScrollPane);

    // add edit panel to panel
    panel.add(editPanel, BorderLayout.CENTER);

    // Create button panel to hold buttons 
    JPanel buttonPanel = new JPanel();
    // set the layout to borderlayout
    buttonPanel.setLayout(new BorderLayout());
    // create cancel button
    JButton cancelButton = new JButton("Cancel");
    // create save button
    JButton saveButton = new JButton("Save");
    // add actionlistener to cancel button
    cancelButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("cancelButton Pressed.");
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
      }
    });
    // add actionlistener to save button 
    saveButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("saveButton Pressed.");
        // get the data from each field
        recipe.setRecipeName(nameTextField.getText());
        recipe.setAuthor(authorTextField.getText());
        recipe.setURL(urlTextField.getText());
        recipe.setIngredients(ingredientsTextPane.getText().split(","));
        recipe.setProcedure(procedureTextPane.getText().split(","));
        saveRecipe(recipe);
        dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
      }
    });
    // add cancelbutton to buttonpanel
    buttonPanel.add(cancelButton, BorderLayout.WEST);
    // add savebutton to buttonpanel
    buttonPanel.add(saveButton, BorderLayout.EAST);
    // add buttonPanel to panel 
    panel.add(buttonPanel, BorderLayout.SOUTH);
    // add panel to dialog
    dialog.add(panel);
    // set dialog size 
    // dialog.setSize(400, 500);
    // set dialog to visible p
    dialog.setVisible(true);
  }
  /**
   * saveRecipe saves the changes made to the recipe in editRecipeView
   * @param Recipe
   */
  private static void saveRecipe(Recipe recipe) {
    Controller.recipeManager.addRecipe(recipe);
  }
}