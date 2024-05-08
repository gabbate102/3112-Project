package src;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ShoppingList {
  private static ArrayList<Recipe> recipeList = new ArrayList<>();
  private static File shoppingListFile = null;

  /**
   * Loads the shopping list file
   */
  private static void loadFile() {
    shoppingListFile = new File("shoppingListFile");
    try {
      if (shoppingListFile.createNewFile()) {
        System.out.println("File created: " + shoppingListFile.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
  }

  /**
   * Reads the shopping list file
   */
  private static void readShoppingList() {
    try {
      FileInputStream fileIn = new FileInputStream(shoppingListFile);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      recipeList = (ArrayList<Recipe>) in.readObject();
      in.close();
      fileIn.close();
    } catch (IOException e) {
      e.printStackTrace();
      return;
    } catch (ClassNotFoundException c) {
      System.out.println("");
      c.printStackTrace();
      return;
    }
  }

  /** 
   * Writes the shopping list to a file
   */
  private static void writeList() {
    try {
      FileOutputStream fileOut = new FileOutputStream(shoppingListFile);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject( recipeList);
      out.close();
      fileOut.close();
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
  }

  /**
   * Returns ArrayList of recipes in the shoppinglist
   * @return ArrayList<Recipe>
   */
  public static ArrayList<Recipe> getRecipeList() {
    loadFile();
    readShoppingList();
    return recipeList; 
  }
  /**
   * Adds a recipe to the shopping list
   * @param recipe
   */
  public static void addRecipe(Recipe recipe) {
    recipeList.add(recipe);
    writeList();
  }
  /** 
   * removes a recipe from the shopping list
   */
  public static void removeRecipe(Recipe recipe){
    recipeList.remove(recipe);
    writeList();
  }
  /**
   * Clears the shopping list
   */
  public static void clearList(){
    recipeList.clear();
    writeList();
  }
}