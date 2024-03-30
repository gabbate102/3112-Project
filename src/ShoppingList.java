package src;
import java.util.ArrayList;

public class ShoppingList {
  private static ArrayList<Recipe> recipeList = new ArrayList<>();

  /**
   * Returns ArrayList of recipes in the shoppinglist
   * @return ArrayList<Recipe>
   */
  public static ArrayList<Recipe> getRecipeList() {
    return recipeList;
  }

  /**
   * Adds a recipe to the shopping list
   * @param recipe
   */
  public static void addRecipe(Recipe recipe) {
    recipeList.add(recipe);
  }
  /** 
   * removes a recipe from the shopping list
   */
  public static void removeRecipe(Recipe recipe){
    recipeList.remove(recipe);
  }
  /**
   * Clears the shopping list
   */
  public static void clearList(){
    recipeList.clear();
  }
}