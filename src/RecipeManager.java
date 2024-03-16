package src;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class RecipeManager {
  File recipeListFile;
  /**
   * Default constructor
   */
  public RecipeManager() {
  }
  /**
   * loadFile method loads the recipeListFile and creates it if it does not exist
   */
  private void loadFile() {
    this.recipeListFile = new File("recipeListFile");
    try {
      if (this.recipeListFile.createNewFile()) {
        System.out.println("File created: " + this.recipeListFile.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }
  /**
   * readRecipes method reads the recipes from recipeListFile
   */
  private void readRecipes() {
    
  }
  /**
   * writeRecipe method writes a recipe to the recipeListFile
   */
  private void writeRecipe(Recipe recipe) {
    try {
      // work in progress.
      FileWriter writer = new FileWriter(recipeListFile);
      writer.write(recipe);
      writer.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
