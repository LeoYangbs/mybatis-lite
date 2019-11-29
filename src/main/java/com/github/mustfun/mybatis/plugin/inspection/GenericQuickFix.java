package com.github.mustfun.mybatis.plugin.inspection;

import com.intellij.codeInspection.LocalQuickFix;
import org.jetbrains.annotations.NotNull;

/**
 * @author yanglin
 */
public abstract class GenericQuickFix implements LocalQuickFix {

    @NotNull
    @Override
    public String getFamilyName() {
        return getName();
    }

}
