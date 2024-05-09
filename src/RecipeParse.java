package src;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.*;
import java.util.ArrayList;
import java.io.IOException;

/**
 * RecipeParse class implements the jsoup library
 */
public class RecipeParse {

  /**
   * parseRecipe takes a url and parses the data from the website to create a recipe object.
   * @param url
   * @return Recipe object
   * @throws IOException 
   */
  public static Recipe parseRecipe(String url) throws IOException {

    Recipe recipe = new Recipe();
    recipe.setURL(url);

    try {
      // downloading the target website with an HTTP GET request
      Document doc = Jsoup.connect(url).get();
      // check if the recipe is from all recipes
      if (url.contains("allrecipes")) { 
        recipe = allrecipesParse(doc, recipe);
        return recipe;
      }

      // Check for WordPress Recipe Maker parsing
      // Get the recipe block
      Elements recipeBlock = doc.getElementsByClass("wprm-recipe");
      // Check if recipeBlock is empty, return an empty recipe if it is
      if (recipeBlock.toString().length() > 1) {
        // call wprmParse
        recipe = wprmParse(doc, recipe);
      } else {
        return recipe;
      }
    } catch (java.net.MalformedURLException e) {
      System.out.println(e);
      return recipe;
    } catch (org.jsoup.HttpStatusException o) {
      System.out.println(o);
      return recipe;
    }
    return recipe;
  }

  private static Recipe allrecipesParse(Document doc, Recipe recipe) {
    // get recipe name
    Elements name = doc.getElementsByClass("article-heading");
    recipe.setRecipeName(name.text());

    // get ingredients -----------
    Elements ingredients = doc.getElementsByClass("mntl-structured-ingredients__list-item ");

    ArrayList<String> recipeIngredients = new ArrayList<String>();
    // for each section of the ingredients list
    for (Element item : ingredients) {

      // get the quantity  
      String quantity = item.select("span[data-ingredient-quantity=\"true\"]").text();
      // get the unit  
      String unit = item.select("span[data-ingredient-unit=\"true\"]").text();
      // get the name  
      String ingredientName = item.select("span[data-ingredient-name=\"true\"]").text();
      
      String newIngredient = quantity + " " + unit + " " + ingredientName;

      recipeIngredients.add(newIngredient);
    }
    // set the value of ingredients in recipe
    String[] recipeIngredientsArray = new String[recipeIngredients.size()];
    recipeIngredientsArray = recipeIngredients.toArray(recipeIngredientsArray);
    recipe.setIngredients(recipeIngredientsArray);

    // get procedure -----------
    Elements procedureSection = doc.getElementsByClass("recipe__steps-content");
    
    // create recipeProcedure arrayList to hold procedure 
    ArrayList<String> recipeProcedure = new ArrayList<String>();

    // for each element in procedure section
    for (Element element : procedureSection) {
      // get the p elements
      Elements steps = element.getElementsByTag("p");
      for (Element step : steps) { 
        recipeProcedure.add(step.text());
      }
    }
    // set procedure
    String[] recipeProcedureArray = new String[recipeProcedure.size()];
    recipeProcedureArray = recipeProcedure.toArray(recipeProcedureArray);
    recipe.setProcedure(recipeProcedureArray);

    return recipe;
  }

  /**
   * Parses websites that use Wordpress Recipe Maker
   * @param doc
   * @param recipe
   * @return Recipe
   */
  private static Recipe wprmParse(Document doc, Recipe recipe) {
    // get recipeName
    Elements recipeName = doc.getElementsByClass("wprm-recipe-name");
    System.out.println(recipeName.text());
    recipe.setRecipeName(recipeName.text());

    // Get the Ingredients  ----------
    // get the ingredients section of the site
    ArrayList<String> ingredientsArrayList = new ArrayList<String>();
    Elements siteIngredientsSection = doc.getElementsByClass("wprm-recipe-ingredient-group");

    // for each section of the ingredients list
    for (Element item : siteIngredientsSection) {

      // get the section name
      Elements siteSectionName = item.getElementsByClass("wprm-recipe-group-name");
      ingredientsArrayList.add("Section: " + siteSectionName.text());

      // get the ingredient list

      Elements siteIngredientList = item.getElementsByTag("ul");
      // iterate over the ingredient list
      for (Element ingredient : siteIngredientList) {
        String currentIngredient = ingredient.text().replace(",", " ") + ",";
        System.out.println(currentIngredient);

        // add the ingredient to ingredientsArrayList
        ingredientsArrayList.add(currentIngredient);
      }
    }

    // convert the arraylist to an array
    String[] ingredientsList = new String[ingredientsArrayList.size()];
    ingredientsList = ingredientsArrayList.toArray(ingredientsList);

    // set the ingredients property of the recipe
    recipe.setIngredients(ingredientsList);

    // Get the Procedure ----------

    ArrayList<String> procedureArrayList = new ArrayList<String>();

    // get the siteProcedureSections
    Elements siteProcedureSections = doc.getElementsByClass("wprm-recipe-instructions");

    // for each section, get the list items
    for (Element section : siteProcedureSections) {

      Elements listItems = section.getElementsByTag("li");

      // for each list item, add the text to procedureArrayList
      for (Element li : listItems) {
        procedureArrayList.add(li.text());
      }
    }

    // convert the arraylist to an array
    String[] procedureList = new String[procedureArrayList.size()];
    procedureList = procedureArrayList.toArray(procedureList);

    // set the procedure property of the recipe
    recipe.setProcedure(procedureList);

    return recipe;
  }
}