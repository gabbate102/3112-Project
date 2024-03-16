package src;
import java.util.*;
import java.io.Serializable;

/**
 * Recipe class for storing and managing Recipe objects 
 * @author Giovanni Abbate
 */
public class Recipe implements Serializable{
  private String recipeID;
  private String author;
  private String recipeURL;
  private static ArrayList<String> ingredients = new ArrayList();
  private static ArrayList<String> procedure = new ArrayList();

  /**
   * Default constructor 
   */
  public Recipe() {
  } 
  /**
   * Parameterized Constructor 
   */
  public Recipe(String recipeID, String author, String recipeURL, ArrayList<String> ingredients, ArrayList<String> procedure) {
    this.recipeID = recipeID;
    this.author = author;
    this.recipeURL = recipeURL;
    this.ingredients = ingredients;
    this.procedure = procedure;
  }
  /**
   * getter method for author
   */
  public getAuthor() {
    return this.author;
  }
  /**
   * getter method for url 
   */
  public getRecipeURL() {
    return this.recipeURL;
  }
  /**
   * getter method for ingredients
   */
  public getIngredients() {
    return this.ingredients;
  }
  /**
   * getter method for procedure
   */
  public getProcedure() {
    return this.procedure;
  }
  /**
   * setter method for author
   */
  public setAuthor(String author) {
    this.author = author;
  }
  /**
   * setter method for procedure
   */
  public setProcedure(ArrayList<String> procedure) {
    this.procedure = procedure;
  }
  /**
   * setter method for ingredients
   */
  public setIngredients(ArrayList<String> ingredients) {
    this.ingredients = ingredients;
  }
}
