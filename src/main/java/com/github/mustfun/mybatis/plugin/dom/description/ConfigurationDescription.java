package com.github.mustfun.mybatis.plugin.dom.description;

import com.github.mustfun.mybatis.plugin.dom.model.Configuration;
import com.github.mustfun.mybatis.plugin.util.MybatisDomUtils;
import com.intellij.openapi.module.Module;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomFileDescription;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author yanglin
 * @updater itar
 * @function mybatis配置文件的描述文件
 */
public class ConfigurationDescription extends DomFileDescription<Configuration> {

    public ConfigurationDescription() {
        super(Configuration.class, "configuration");
    }

    @Override
    public boolean isMyFile(@NotNull XmlFile file, @Nullable Module module) {
        return MybatisDomUtils.isMybatisConfigurationFile(file);
    }

}
