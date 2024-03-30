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
  private Recipe recipe;
  private RecipeManager recipeManager;

  /**
   * Constructor stores Recipe and RecipeManager objects
   * @param recipe
   * @param recipeManager
   */
  public RecipeView(Recipe recipe, RecipeManager recipeManager) {
    this.recipe = recipe;
    this.recipeManager = recipeManager;
  }


  /**
   * detailsView creates the view for viewing recipes
   */
  public JPanel detailsView() {
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
  private JToolBar detailsToolBar() {
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
  public void editView(Recipe recipe) {
    JDialog dialog = new JDialog();
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    // Create edit panel to hold editing panes together 
    JPanel editPanel = new JPanel();
    // Create text field for recipe name
    JTextField nameTextField = new JTextField(24);
    // Create text field for recipe author
    JTextField authorTextField = new JTextField(24);
    // Create text field for recipe URL
    JTextField urlTextField = new JTextField(24);
    // Create text pane for recipe ingredients
    JTextPane ingredientsTextPane = new JTextPane();
    // Create text field for recipe procedure
    JTextPane procedureTextPane = new JTextPane();

    // Add text fields to edit panel 
    editPanel.add(nameTextField);
    editPanel.add(authorTextField);
    editPanel.add(authorTextField);
    editPanel.add(urlTextField);
    editPanel.add(ingredientsTextPane);
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
  }
}