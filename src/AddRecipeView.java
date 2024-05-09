package src;
import java.awt.event.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * AddRecipeView Class creates the view for adding a new recipe using JDialog.
 * Requires RecipeManager to be passed in the constructor to allow AddRecipeView to manage recipes
 */
public class AddRecipeView {
  /**
   * Create dialog to request a URL from the user
   */
  public static void getUrlView() {
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
        String url = textField.getText();
        try {
          Recipe newRecipe = RecipeParse.parseRecipe(url);
          AddRecipeView.editView(newRecipe);
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        // set the text of field to blank
        textField.setText(" ");
      }
    });
    JButton createManuallyButton = new JButton("Enter Manually");
    // Add action listener
    createManuallyButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("createManuallyButton pressed");
        manualRecipeCreate();
      }
    });
    // Add the elements to the panel
    panel.add(labelText);
    panel.add(textField);
    panel.add(createManuallyButton);
    panel.add(submitButton);
    // Add the panel to the dialog, set size of dialog, and set the dialog to visible
    dialog.add(panel);
    dialog.setSize(400, 148);
    dialog.setVisible(true);
  }

  private static Recipe manualRecipeCreate() {
    Recipe recipe = new Recipe();
    AddRecipeView.editView(recipe);
    return recipe;
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
    panel.setBorder(BorderFactory.createEmptyBorder(15,15,5,15));
    panel.setLayout(new BorderLayout());    


    // Create mainEditPanel panel to hold editing panes together 
    JPanel mainEditPanel = new JPanel();
    mainEditPanel.setBorder(BorderFactory.createTitledBorder("Edit Recipe"));
    // create top edit panel to hold top elements
    JPanel topEditPanel = new JPanel();

    // create bottom edit panel to hold bottom elements
    JPanel bottomEditPanel = new JPanel();

    // set layout for panels
    mainEditPanel.setLayout(new GridLayout(2,1));
    topEditPanel.setLayout(new GridLayout(3,1));
    bottomEditPanel.setLayout(new GridLayout(2,1));

    // Create grey border to be used later on 
    Border bGreyLine = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true);

    // Create text field for recipe name
    JTextField nameTextField = new JTextField(recipe.getRecipeName());
    nameTextField.setColumns(24);
    Border bTitled5 = BorderFactory.createTitledBorder(bGreyLine, "Title", TitledBorder.LEFT, TitledBorder.TOP);
    nameTextField.setBorder(bTitled5);

    // Create text field for recipe author
    JTextField authorTextField = new JTextField(recipe.getAuthor());
    authorTextField.setColumns(24);
    Border bTitled4 = BorderFactory.createTitledBorder(bGreyLine, "Author", TitledBorder.LEFT, TitledBorder.TOP);
    authorTextField.setBorder(bTitled4);

    // Create text field for recipe URL
    JTextField urlTextField = new JTextField(recipe.getRecipeURL());
    urlTextField.setColumns(24);
    Border bTitled3 = BorderFactory.createTitledBorder(bGreyLine, "URL", TitledBorder.LEFT, TitledBorder.TOP);
    urlTextField.setBorder(bTitled3);

    // Create text pane for recipe ingredients
    JEditorPane ingredientsTextPane = new JEditorPane();
    ingredientsTextPane.setText(recipe.getIngredientsString());
    JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsTextPane);
    // add border to text pane
    Border bTitled1 = BorderFactory.createTitledBorder(bGreyLine, "Ingredients", TitledBorder.LEFT, TitledBorder.TOP);
    ingredientsScrollPane.setBorder(bTitled1);

    // Create text field for recipe procedure
    JEditorPane procedureTextPane = new JEditorPane();
    procedureTextPane.setText(recipe.getProcedureString());
    JScrollPane procedureScrollPane = new JScrollPane(procedureTextPane);
    // add border to text pane
    Border bTitled2 = BorderFactory.createTitledBorder(bGreyLine, "Procedure", TitledBorder.LEFT, TitledBorder.TOP);
    procedureScrollPane.setBorder(bTitled2);

    // add labels to labelPanel
    topEditPanel.add(nameTextField);
    topEditPanel.add(authorTextField);
    topEditPanel.add(urlTextField);
    bottomEditPanel.add(ingredientsScrollPane);
    bottomEditPanel.add(procedureScrollPane);

    mainEditPanel.add(topEditPanel);
    mainEditPanel.add(bottomEditPanel);
    // add mainEditPanel panel to panel
    panel.add(mainEditPanel, BorderLayout.CENTER);

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
        Controller.reload();
        
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