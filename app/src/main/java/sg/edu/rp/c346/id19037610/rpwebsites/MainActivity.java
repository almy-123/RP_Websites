package sg.edu.rp.c346.id19037610.rpwebsites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner cate, subCate;
    Button btnGo;
    ArrayList<String> website;
    ArrayAdapter<String> aaWebsites;

    String[][] sites = {
            {
                "https://www.rp.edu.sg",//[0][0]
                    "https://www.rp.edu.sg/student-life",//[0][1]
            },
            {
                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",//[1][0]
                    "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12",//[1][1]
            }
    };

    @Override
    protected void onPause() {
        super.onPause();

        int sCate = cate.getSelectedItemPosition();
        int sSubCate = subCate.getSelectedItemPosition();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putInt("category", sCate);
        prefEdit.putInt("subCategory", sSubCate);
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int sCate = prefs.getInt("category", 0);
        int sSubCate = prefs.getInt("subCategory", 0);

        website.clear();
        if(cate.getSelectedItemPosition()==0){
            String[] strWebsites1 = getResources().getStringArray(R.array.subCategory1);
            website.addAll(Arrays.asList(strWebsites1));

        }else{
            String[] strWebsites2 = getResources().getStringArray(R.array.subCategory2);
            website.addAll(Arrays.asList(strWebsites2));
        }
        cate.setSelection(sCate);
        subCate.setSelection(sSubCate);

        aaWebsites.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cate = findViewById(R.id.categorySpinner);
        subCate = findViewById(R.id.subSpinner);
        btnGo = findViewById(R.id.btnGo);

        website = new ArrayList<>();
        aaWebsites = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, website);
        String[] strWebsites = getResources().getStringArray(R.array.subCategory1);

        website.addAll(Arrays.asList(strWebsites));
        subCate.setAdapter(aaWebsites);


        cate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos = cate.getSelectedItemPosition();
                website.clear();
                if(pos==0){
                    String[] strWebsites1 = getResources().getStringArray(R.array.subCategory1);
                    website.addAll(Arrays.asList(strWebsites1));
                }else{
                    String[] strWebsites2 = getResources().getStringArray(R.array.subCategory2);
                    website.addAll(Arrays.asList(strWebsites2));
                }
                aaWebsites.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);

                int cat = cate.getSelectedItemPosition();
                int subCat = subCate.getSelectedItemPosition();

                //Nested loop version
//                if(cat==0 && subCat==0){
//                    intent.putExtra("choice", "https://www.rp.edu.sg/");
//                }else if(cat==0 && subCat==1){
//                    intent.putExtra("choice", "https://www.rp.edu.sg/student-life");
//                }else if(cat==1 && subCat==0){
//                    intent.putExtra("choice", "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47");
//                }else if(cat==1 && subCat==1){
//                    intent.putExtra("choice", "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12");
//                }

                String url = sites[cat][subCat];

                intent.putExtra("choice", url);

                startActivity(intent);
            }
        });
    }
}
