package src;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;

/**
 * Class RecipeManager manages recipe objects and contains methods to interact with the 
 * stored recipes.
 */
public class RecipeManager {
  private File recipeListFile = null;
  private ArrayList<Recipe> recipes = new ArrayList<>();
  
  /**
   * Default constructor
   */
  public RecipeManager() {
    this.loadFile();
    this.readRecipes();
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
      e.printStackTrace();
      return;
    }
  }

  /**
   * readRecipes method reads the recipes from recipeListFile
   */
  private void readRecipes() {
    try {
      FileInputStream fileIn = new FileInputStream(recipeListFile);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      recipes = (ArrayList<Recipe>) in.readObject();
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
   * writeRecipes method writes recipes to the recipeListFile
   */
  private void writeRecipes() {
    try {
      FileOutputStream fileOut = new FileOutputStream(recipeListFile);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(this.recipes);
      out.close();
      fileOut.close();
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
  }

  public ArrayList<Recipe> getRecipes() {
    return this.recipes;
  }

  /**
   * addRecipe method appends a new recipe to recipes arraylist
   * @param newRecipe
   */
  public void addRecipe(Recipe newRecipe) {
    // check if the recipe exists
    int matchIndex = indexSearch(newRecipe);
    if (matchIndex != -1) {
      // if the recipe exists call updateRecipe
      updateRecipe(newRecipe);
    } 
    this.recipes.add(newRecipe);

    // update recipes file
    this.writeRecipes();
  }

  /**
   * updateRecipe method updates an existing recipe in recipes arrayList
   * @param updatedRecipe
   * @return 0 if successful, -1 if not
   */
  public int updateRecipe(Recipe updatedRecipe) {
    int matchIndex = indexSearch(updatedRecipe);

    if (matchIndex != -1) {
      // put updatedRecipe at index matchIndex in recipes
      this.recipes.set(matchIndex, updatedRecipe);

      // update recipes file
      this.writeRecipes();

      return 0;
    } else {
      return -1;
    }
  }

  /**
   * deleteRecipe method deletes a given recipe
   * @param recipe
   * @return 0 if successful, -1 if not
   */
  public int deleteRecipe(Recipe recipe) {
    int matchIndex = indexSearch(recipe);

    if (matchIndex != -1) {
      this.recipes.remove(matchIndex);

      // update recipes file
      this.writeRecipes();
      return 0;
    } else {
      return -1;
    }
  }

  /**
   * indexSearch method searches recipes ArrayList for a match to queryRecipe and returns the 
   * matching index.
   * @param queryRecipe
   * @return matching index, -1 if unsuccesful
   */
  private int indexSearch(Recipe queryRecipe) {
    int matchIndex = -1;
    int searchIndex = 0;

    // while match index equals -1
    while (matchIndex == -1) {
      // if search index is out of bounds then return 
      if (searchIndex >= this.recipes.size()) {
        return -1;
      }

      // retrieve the recipe from recipes at index searchIndex
      Recipe tempRecipe = this.recipes.get(searchIndex);

      // if recipeID matches then update matchIndex to the index of the matching recipe
      if (tempRecipe.getRecipeID() == queryRecipe.getRecipeID()) {
        matchIndex = searchIndex;
      }

      searchIndex++;
    }

    return matchIndex;
  }

  /** 
   * searchByIngredient method searches all recipes for recipes matching the ingredients
   * in the ingredients list. 
   * @param ingredients
   * @return ArrayList<Recipe>
   */
/*   public ArrayList<Recipe> searchByIngredient(String[] ingredients) {
    ArrayList<Recipe> searchResults = new ArrayList();

    for (int i = 0; i < recipes.size(); i++) {
      ArrayList<String[]> tempIngredientList = recipes.get(i).getIngredients();
      // for each ingredient in ingredients list, determine if it is in tempIngredientList
      boolean containsItem = false;
      for (int j=0; j < ingredients.length; j++) {
        if (tempIngredientList.contains(ingredients[j])) {
          containsItem = true;
        } else {
          containsItem = false;
        }
      }
    }
    return searchResults;
  } */

  /**
   * searchByName method searches all recipes for a match to name
   * @param name
   * @return ArrayList<Recipe>
   */
  public ArrayList<Recipe> searchByName(String name) {
    // create arraylist for searchresults
    ArrayList<Recipe> searchResults = new ArrayList<Recipe>();

    // for each element in recipes, if the name matches name, add to searchResults
    for (int i = 0; i < recipes.size(); i++) {
      if (recipes.get(i).getRecipeName().equals(name)) {
        searchResults.add(recipes.get(i));
      }
    }

    return searchResults;
  }
}
