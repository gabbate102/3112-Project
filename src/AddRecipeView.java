package src;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/**
 * AddRecipeView Class creates the view for adding a new recipe using JDialog.
 * Requires RecipeManager to be passed in the constructor to allow AddRecipeView to manage recipes
 */
public class AddRecipeView {
  private RecipeManager recipemanager;

  /**
   * Constructor for AddRecipeView
   * @param recipemanager
   */
  public AddRecipeView(RecipeManager recipemanager){
    this.recipemanager = recipemanager;
  }
  /**
   * Create dialog to request a URL from the user
   */
  public void getUrlView() {
    // Create Dialog
    JDialog dialog = new JDialog();
    // Create panel
    JPanel panel = new JPanel();

    // Create JLabel element
    JLabel labelText = new JLabel("Enter the URL for a recipe");
    // Create textField to capture user input
    JTextField textField = new JTextField(24);
    // Create submit button
    JButton submitButton = new JButton("Continue");
    // Add action listener
    submitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // get the text of the field
        System.out.println(textField.getText());
 
        // set the text of field to blank
        textField.setText(" ");
      }
    });
    // Add the elements to the panel
    panel.add(labelText);
    panel.add(textField);
    panel.add(submitButton);
    // Add the panel to the dialog, set size of dialog, and set the dialog to visible
    dialog.add(panel);
    dialog.setSize(400, 200);
    dialog.setVisible(true);
  }
}