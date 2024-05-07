package src;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.*;

import java.util.ArrayList;
import java.util.Dictionary;

import java.io.IOException;

/**
 * RecipeParse class implements the jsoup library
 */
public class RecipeParse {
  private Dictionary<String, String[]> recipeFormatting;
  /**
   * parseRecipe takes a url and parses the data from the website to create a recipe object.
   * @param url
   * @return Recipe object
   * @throws IOException 
   */
  public static Recipe parseRecipe(String url) throws IOException {
    Recipe recipe = new Recipe();
    recipe.setURL(url);
    // downloading the target website with an HTTP GET request
    Document doc = Jsoup.connect(url).get();
    
    // WordPress Recipe Maker parsing
    // Get the recipe block
    Elements recipeBlock = doc.getElementsByClass("wprm-recipe");
    // Check if recipeBlock is empty, return an empty recipe if it is
    if (recipeBlock.toString().length() < 1) {
      return recipe;
    } else {
      // get recipeName
      Elements recipeName = doc.getElementsByClass("wprm-recipe-name");
      System.out.println(recipeName.text());
      recipe.setRecipeName(recipeName.text());
      // get recipeIngredients
      ArrayList<String> ingredientsArrayList = new ArrayList();
      Elements siteIngredientsSection = doc.getElementsByClass("wprm-recipe-ingredient-group");
      for (Element item : siteIngredientsSection) {
        Elements siteSectionName = item.getElementsByClass("wprm-recipe-group-name");
        ingredientsArrayList.add("Section: " + siteSectionName);
        Elements siteIngredientList = item.getElementsByTag("ul");
        // iterate over the ingredient list
        for (Element ingredient : siteIngredientList) {
          System.out.println(ingredient.text());
          ingredientsArrayList.add(ingredient.text());
        }
      }
      // convert the arraylist to an array
      String[] ingredientsList = new String[ingredientsArrayList.size()];
      ingredientsList = ingredientsArrayList.toArray(ingredientsList);
      // set the ingredients property of the recipe
      recipe.setIngredients(ingredientsList);
    }
    
    return recipe;
  }
}