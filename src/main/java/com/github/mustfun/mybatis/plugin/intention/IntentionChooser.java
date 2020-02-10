package com.github.mustfun.mybatis.plugin.intention;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

/**
 * @author yanglin 最终实现操作的接口
 */
public interface IntentionChooser {

    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file);

}
