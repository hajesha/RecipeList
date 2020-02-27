package com.hajesha.recipelist.model;

public class RecipeObject {

    private String name;
    private String ingredients;

    public RecipeObject(String name, String ingredients){
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        ingredients = ingredients;
    }
}
