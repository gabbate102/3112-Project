package src;
import java.util.ArrayList;

public class ShoppingList {
  private ArrayList<Recipe> recipeList;
  private ArrayList<String[]> ingredientList;

  /**
   * Default constructor
   */
  public ShoppingList() {
    this.recipeList = new ArrayList<>();
    this.ingredientList = new ArrayList<>();
  }
  public ArrayList<Recipe> getRecipeList() {
    return this.recipeList;
  }
  public ArrayList<String[]> getIngredientList() {
    return this.ingredientList;
  }
  public void addRecipe(Recipe recipe) {
    this.recipeList.add(recipe);
  }
  public void removeRecipe(Recipe recipe){
    this.recipeList.remove(recipe);
  }
}