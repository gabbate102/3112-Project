package src;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
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
      FileOutputStream fileOut = new FileOutputStream(recipeListFile);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(recipe);
      out.close();
      fileOut.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
