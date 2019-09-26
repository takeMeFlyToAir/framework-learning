package com.zzr.framework.builder;

import com.zzr.framework.builder.food.ChickenBurger;
import com.zzr.framework.builder.food.Coke;
import com.zzr.framework.builder.food.Pepsi;
import com.zzr.framework.builder.food.VegBurger;

/**
 * Created by zhaozhirong on 2019/6/6.
 */
public class MealBuilder {

    public Meal prepareVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNonVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }

}
