package com.github.mustfun.mybatis.plugin.provider;


import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import org.jetbrains.annotations.NotNull;

/**
 * 文件提供者抽象类
 *
 * @author hehaiyangwork@gmail.com
 * @date 2017/04/07
 * @update itar
 */
public abstract class AbstractFileProvider {

    protected Project project;

    protected String outputPath;

    public AbstractFileProvider(Project project, String outputPath) {
        this.project = project;
        this.outputPath = outputPath;
    }

    public abstract PsiFile create(String fullFile, String fileName);

    protected PsiFile createFile(Project project, @NotNull PsiDirectory psiDirectory, String fileName, String context,
        LanguageFileType fileType) {

        //+        final String simpleContent = XmlSorterUtil.replaceAllByRegex(content, ">" + lineSeparator + "*\\s+?<", "><");
        String replace = context.replaceAll("\r\n", "\n");
        PsiFile file = psiDirectory.findFile(fileName);
        if (file != null) {
            file.delete();
        }
        PsiFile psiFile = PsiFileFactory.getInstance(project).createFileFromText(fileName, fileType, replace);
        // reformat class
        CodeStyleManager.getInstance(project).reformat(psiFile);
        if (psiFile instanceof PsiJavaFile) {
            JavaCodeStyleManager styleManager = JavaCodeStyleManager.getInstance(project);
            styleManager.optimizeImports(psiFile);
            styleManager.shortenClassReferences(psiFile);
        }
        //加载到磁盘中
        psiDirectory.add(psiFile);
        return psiFile;
    }

}