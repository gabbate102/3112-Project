package src;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

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

    // Set the StyledDocument for textPane
    textPane.setStyledDocument(doc); 

    // insert text into document
    try { 
      doc.insertString(0, "This is a ", style); 
      doc.insertString(doc.getLength(), "button: ", null); 
    } 
    catch (BadLocationException e) { 
      e.printStackTrace(); 
    } 
    
    // add toolbar to panel
    panel.add(detailsToolBar(), BorderLayout.NORTH);

    return panel;
  }
  private static JToolBar detailsToolBar() {
    JPanel toolbarPanel = new JPanel();
    JToolBar toolbar = new JToolBar();
    JButton backButton =  new JButton("Back");
    JButton addToShoppingListButton = new JButton("+ Add to Shopping List");
    JButton editButton = new JButton("Edit");
    JButton deleteButton = new JButton("Delete");

    // create event listeners for each button
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("backButton Pressed.");
      }
    });
    addToShoppingListButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("addToShoppingListButton Pressed.");
      }
    });
    editButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("editButton Pressed.");
      }
    });
    deleteButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("deleteButton Pressed.");
      }
    });

    toolbarPanel.add(backButton);
    toolbarPanel.add(addToShoppingListButton);
    toolbarPanel.add(editButton);
    toolbarPanel.add(deleteButton);
    toolbar.add(toolbarPanel);

    return toolbar;
  }
  /**
   * editView creates the view for editing recipes
   */
  public static void editView(Recipe recipe) {
    JDialog dialog = new JDialog();
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    // Create edit panel to hold editing panes together 
    JPanel editPanel = new JPanel();
    // Create JLabel element for nameTextField
    JLabel nameLabel = new JLabel("Name");
    // Create text field for recipe name
    JTextField nameTextField = new JTextField(24);
    // Create JLabel element for authorTextField
    JLabel authorLabel = new JLabel("Author");
    // Create text field for recipe author
    JTextField authorTextField = new JTextField(24);
    // Create JLabel element for urlTextField
    JLabel urlLabel = new JLabel("URL");
    // Create text field for recipe URL
    JTextField urlTextField = new JTextField(24);
    // Create JLabel element for ingredientsTextPane
    JLabel ingredientsLabel = new JLabel("Ingredients");
    // Create text pane for recipe ingredients
    JTextPane ingredientsTextPane = new JTextPane();
    // Create JLabel element for procedureTextPane
    JLabel procedureLabel = new JLabel("Procedure");
    // Create text field for recipe procedure
    JTextPane procedureTextPane = new JTextPane();

    // Add text fields to edit panel 
    editPanel.add(nameLabel);
    editPanel.add(nameTextField);
    editPanel.add(authorLabel);
    editPanel.add(authorTextField);
    editPanel.add(urlLabel);
    editPanel.add(urlTextField);
    editPanel.add(ingredientsLabel);
    editPanel.add(ingredientsTextPane);
    editPanel.add(procedureLabel);
    editPanel.add(procedureTextPane);

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
      }
    });
    // add actionlistener to save button 
    saveButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("saveButton Pressed.");
        saveRecipe(recipe);
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
    dialog.setSize(400, 500);
    // set dialog to visible 
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