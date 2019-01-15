package com.example.bilal.opdracht6_recipe;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListRecipe {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("recipes")
    @Expose
    private java.util.List<Recipe> recipes = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public java.util.List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(java.util.List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
