package com.ifsc.contaclick;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    PackageManager packageManager;

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        packageManager = getPackageManager();
        List<ApplicationInfo> appInfo = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        Intent mainIntent = new Intent(Intent.ACTION_MAIN);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> aplicacoes = packageManager.queryIntentActivities(mainIntent, 0);

        ArrayList<Aplicativo> apps = new ArrayList<Aplicativo>();

        for (ApplicationInfo app : packageManager.getInstalledApplications(PackageManager.GET_META_DATA)) {
            if ((app.flags & android.content.pm.ApplicationInfo.FLAG_SYSTEM) == 0) {
                Aplicativo aplicativo = new Aplicativo(
                        app.loadLabel(packageManager).toString(),
                        app.packageName,
                        packageManager.resolveActivity(new Intent(Intent.ACTION_MAIN).setPackage(app.packageName), PackageManager.MATCH_DEFAULT_ONLY),
                        app.loadIcon(packageManager));
                apps.add(aplicativo);
            }
        }

        lv = findViewById(R.id.lv);

        /*ArrayAdapter<> planetaAdapter = new PlanetaAdapter(this,
                R.layout.activity_planeta_adapter,
                daoPlaneta.planetas);*/

        AppAdapter appAdapter = new AppAdapter(this, R.layout.um_item, apps);
        lv.setAdapter(appAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aplicativo aplicativo = (Aplicativo) parent.getItemAtPosition(position);
                Intent launchIntent = packageManager.getLaunchIntentForPackage(aplicativo.getPackageName());
                if (launchIntent != null) {
                    startActivity(launchIntent);
                }
            }
        });
    }
}