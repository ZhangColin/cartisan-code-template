package com.cartisan.code.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author colin
 */
public class StringUtilsTest {
    @Test
    public void testfirstUpper() {
        assertThat(StringUtils.firstUpper("products")).isEqualTo("Products");
    }

    @Test
    public void testReplacePrefix() {
        assertThat(StringUtils.replacePrefix("goods_products")).isEqualTo("products");
        assertThat(StringUtils.replacePrefix("goods_product_details")).isEqualTo("product_details");
        assertThat(StringUtils.replacePrefix("products")).isEqualTo("products");
    }

    @Test
    public void testConvertCamel() {
        assertThat(StringUtils.convertCamel("goods_products")).isEqualTo("goodsProducts");
        assertThat(StringUtils.convertCamel("goods_product_details")).isEqualTo("goodsProductDetails");
        assertThat(StringUtils.convertCamel("products")).isEqualTo("products");
    }

    @Test
    public void testConvertPascal() {
        assertThat(StringUtils.convertPascal("goods_products")).isEqualTo("GoodsProducts");
        assertThat(StringUtils.convertPascal("goods_product_details")).isEqualTo("GoodsProductDetails");
        assertThat(StringUtils.convertPascal("products")).isEqualTo("Products");
    }
}
