import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
    public static void main(String[] args) {
    	staticFileLocation("/public");
    	String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
          HashMap<String, Object> model = new HashMap<String, Object>();
          model.put("words", Word.all());
          model.put("template", "templates/index.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/dictionary/new", (request, response) -> {
          HashMap<String, Object> model  = new HashMap<String, Object>();
          model.put("template", "templates/add-word.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/dictionary", (request, response) -> {
          HashMap<String, Object> model  = new HashMap<String, Object>();
          String word = request.queryParams("word");
          Word newWord = new Word(word);
          model.put("word", word);
          model.put("template", "templates/success.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/word/:id", (request, response) -> {
          HashMap<String, Object> model  = new HashMap<String, Object>();
          Definition definition = Definition.find(Integer.parseInt(request.params(":id")));
          model.put("word", Word.find(Integer.parseInt(request.params(":id"))));
          model.put("template", "templates/definition.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/word/:id/definition/new", (request, response) -> {
          HashMap<String, Object> model = new HashMap<String, Object>();
          model.put("word", Word.find(Integer.parseInt(request.params(":id"))));
          model.put("template", "templates/add-definition.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/definitions", (request, response) -> {
          HashMap<String, Object> model = new HashMap<String, Object>();
          Word newWord = Word.find(Integer.parseInt(request.queryParams("definitionId")));
          String definition = request.queryParams("definition");
          Definition newDefinition = new Definition(definition);
          newWord.addDefinition(newDefinition);
          model.put("word", newWord);
          model.put("template", "templates/success.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
      }
}
