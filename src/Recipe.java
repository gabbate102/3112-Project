package src;
import java.util.*;
import java.io.Serializable;

/**
 * Recipe class for storing and managing Recipe objects 
 * @author Giovanni Abbate
 */
public class Recipe implements Serializable{
  private int recipeID;
  private String recipeName;
  private String author;
  private String recipeURL;
  private String[] ingredients;
  private String[] procedure;

  /**
   * Default constructor 
   */
  public Recipe() {
  } 
  /**
   * Parameterized Constructor 
   */
  public Recipe(int recipeID, String recipeName, String author, String recipeURL, String[] ingredients, String[] procedure) {
    // create new instance of Random to create recipeID
    Random rand = new Random();
    // use rand to create random recipeID
    this.recipeID = rand.nextInt(1000);
    this.recipeName = recipeName;
    this.author = author;
    this.recipeURL = recipeURL;
    this.ingredients = ingredients;
    this.procedure = procedure;
  }
  /**
   * getter method for recipeID
   * @return recipeID
   */
  public int getRecipeID() {
    return this.recipeID;
  }
  /**
   * getter method for recipeName
   * @return recipeName
   */
  public String getRecipeName() {
    return this.recipeName;
  }
  /**
   * getter method for author
   * @return author
   */
  public String getAuthor() {
    return this.author;
  }
  /**
   * getter method for url 
   * @return recipeURL
   */
  public String getRecipeURL() {
    return this.recipeURL;
  }
  /**
   * getter method for ingredients
   * @return ingredients
   */
  public String[]  getIngredients() {
    return this.ingredients;
  }
  /**
   * getter method for procedure
   * @return procedure
   */
  public String[]  getProcedure() {
    return this.procedure;
  }
  /**
   * setter method for recipeName
   * @return void
   */
  public void setRecipeName(String recipeName) {
    this.recipeName = recipeName;
  }
  /**
   * setter method for author
   * @return void
   */
  public void setAuthor(String author) {
    this.author = author;
  }
  /**
   * setter method for procedure
   * @return void
   */
  public void setProcedure(String[]  procedure) {
    this.procedure = procedure;
  }
  /**
   * setter method for ingredients
   * @return void
   */
  public void setIngredients(String[]  ingredients) {
    this.ingredients = ingredients;
  }
  public String getIngredientsString() {
    String ingredientsString = "";
    for (int i = 0; i < this.ingredients.length; i++) {
      ingredientsString += ingredients[i] + "\n";
    }
    return ingredientsString;
  }
  public String getProcedureString() {
    String procedureString = "";
    for (int i = 0; i < this.procedure.length; i++) {
      procedureString += procedure[i] + "\n";
    }
    return procedureString;
  }
}
