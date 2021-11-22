package com.cartisan.code.utils;

import org.junit.jupiter.api.Test;

/**
 * @author colin
 */
public class InflectorTest {

    @Test
    public void test(){
            // TODO Auto-generated method stub
            // 单数转复数
            System.out.println(Inflector.pluralize("water"));
            System.out.println(Inflector.pluralize("box"));
            System.out.println(Inflector.pluralize("tomato"));
            // 复数转单数
            System.out.println(Inflector.singularize("apples"));
    }

    @Test
    public void underscore() {
        System.out.println(Inflector.underscore("goodsProductDetail"));
    }

    @Test
    public void pluralize() {
        System.out.println(Inflector.pluralize("water"));
        System.out.println(Inflector.pluralize("box"));
        System.out.println(Inflector.pluralize("tomato"));

        System.out.println(Inflector.pluralize("RuleAndReplacement"));
        System.out.println(Inflector.pluralize("rule_and_replacement"));
    }

    @Test
    public void singularize() {
        System.out.println(Inflector.singularize("apples"));
        System.out.println(Inflector.singularize("waters"));
        System.out.println(Inflector.singularize("boxes"));
        System.out.println(Inflector.singularize("tomatoes"));
        System.out.println(Inflector.singularize("Tomatoes"));
    }

    @Test
    public void tableize() {
        System.out.println(Inflector.tableize(Inflector.class));
        System.out.println(Inflector.tableize(RuleAndReplacement.class));

        System.out.println(Inflector.tableize("Inflector"));
        System.out.println(Inflector.tableize("RuleAndReplacement"));
    }

    @Test
    public void uncountable() {
        System.out.println(Inflector.underscore("ruleAndReplacement"));
        System.out.println(Inflector.underscore("RuleAndReplacement"));
    }
}
