package com.github.mustfun.mybatis.plugin.locator;

import com.github.mustfun.mybatis.plugin.dom.model.Mapper;
import com.github.mustfun.mybatis.plugin.util.MapperUtils;
import com.google.common.collect.Sets;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiPackage;
import com.intellij.psi.search.GlobalSearchScope;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

/**
 * @author yanglin
 * @updater itar
 * @function 找到所有mapper文件对应的包信息
 */
public class MapperXmlPackageProvider extends PackageProvider {

    @NotNull
    @Override
    public Set<PsiPackage> getPackages(@NotNull Project project) {
        HashSet<PsiPackage> res = Sets.newHashSet();
        //找到所有的mapper文件
        Collection<Mapper> mappers = MapperUtils.findMappers(project);
        //findClass、package的一个Service
        JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(project);
        for (Mapper mapper : mappers) {
            String namespace = MapperUtils.getNamespace(mapper);
            PsiClass clazz = javaPsiFacade.findClass(namespace, GlobalSearchScope.allScope(project));
            if (null != clazz) {
                PsiFile file = clazz.getContainingFile();
                if (file instanceof PsiJavaFile) {
                    String packageName = ((PsiJavaFile) file).getPackageName();
                    PsiPackage pkg = javaPsiFacade.findPackage(packageName);
                    if (null != pkg) {
                        res.add(pkg);
                    }
                }
            }
        }
        return res;
    }

}
