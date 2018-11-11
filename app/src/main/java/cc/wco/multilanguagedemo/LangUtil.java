package cc.wco.multilanguagedemo;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LangUtil {
    /**
     * 设置应用语言
     *
     * @param lang 要设置的语言，如：zh、en，目前只支持中文和英文，其它的请自行添加
     */
    public static void setLang(Context context, String lang) {
        Resources res = context.getResources();

        //应用当前语言
        String currLang = res.getConfiguration().locale.getLanguage();

        //当前语言和用户语言不一致时更改应用的当前语言
        if (!currLang.equals(lang)) {
            //设置应用语言类型
            Configuration config = res.getConfiguration();
            DisplayMetrics dm = res.getDisplayMetrics();
            if (lang.equals("zh")) {
                config.locale = Locale.SIMPLIFIED_CHINESE;
            } else {
                config.locale = Locale.ENGLISH;
            }
            res.updateConfiguration(config, dm);
        }
    }
}
