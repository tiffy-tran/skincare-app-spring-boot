package com.java.skincare.model;

import java.util.Arrays;
import java.util.List;

//TODO is this the best way to do this? this just looks cool.
public enum ProductCategory {
    // makeup
    MAKEUP(null),

    // skincare
    SKINCARE(null),
    SUNSCREEN(SKINCARE),
    SERUM(SKINCARE),

    CLEANSER(SKINCARE),
    OIL_CLEANSER(CLEANSER),
    BALM_CLEANSER(CLEANSER),

    MOISTURIZER(SKINCARE),
    TONER(SKINCARE),
    MASK(SKINCARE),
    EXFOLIATOR(SKINCARE),
    EYE_CREAM(SKINCARE),

    LIP_CARE(SKINCARE),
    LIP_MASK(LIP_CARE),

    // haircare
    HAIRCARE(null),
    HAIR_OIL(HAIRCARE),
    SCALP_OIL(HAIRCARE),
    SHAMPOO(HAIRCARE),
    CONDITIONER(HAIRCARE);

    private final ProductCategory parent;

    ProductCategory(ProductCategory parent) {
        this.parent = parent;
    }

    public ProductCategory getParent() {
        return parent;
    }

    // Find all sub-categories of a given category
    public static List<ProductCategory> getSubCategories(ProductCategory parent) {
        return Arrays.stream(values())
                .filter(category -> category.parent == parent)
                .toList();
    }

    // Check if a category is a sub-category of another category
    public boolean isSubCategoryOf(ProductCategory category) {
        ProductCategory current = this;
        while (current != null) {
            if (current == category)
                return true;
            current = current.parent;
        }
        return false;
    }
}
