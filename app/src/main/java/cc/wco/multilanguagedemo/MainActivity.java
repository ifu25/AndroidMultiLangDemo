package cc.wco.multilanguagedemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import java.util.Locale;

/**
 * 多语言切换演示
 * Author: ifu25
 * Date: 2018/11/11
 * http://weiku.co
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ifu25";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置应用语言，也可在Application中调用
        String userLang = getSharedPreferences("app", Context.MODE_PRIVATE).getString("lang", "zh");
        LangUtil.setLang(this, userLang);

        setContentView(R.layout.activity_main);

        //运行时动态切换语言
        findViewById(R.id.btn_lang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currLang = getResources().getConfiguration().locale.getLanguage();
                String lang = currLang.equals("zh") ? "en" : "zh";
                getSharedPreferences("app", Context.MODE_PRIVATE).edit().putString("lang", lang).apply();
                LangUtil.setLang(MainActivity.this, lang);
                refreshView(); //更改完语言后需要重新启动 Activity 才能生效
            }
        });
    }

    /**
     * 刷新/重新启动 Activity
     */
    private void refreshView() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
