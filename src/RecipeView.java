package src;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

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
   * editRecipeView creates the view for editing recipes
   */
  public void editRecipeView() {
    // Create Dialog
    JDialog dialog = new JDialog();
    // Create panel
    JPanel panel = new JPanel();
  }
}