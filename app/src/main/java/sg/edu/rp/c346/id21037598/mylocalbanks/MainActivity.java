package sg.edu.rp.c346.id21037598.mylocalbanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Initialize variables
    ImageButton bankBtn1, bankBtn2, bankBtn3;
    TextView bankName1, bankName2, bankName3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link variables to xml elements
        bankBtn1 = findViewById(R.id.bankBtn1);
        bankBtn2 = findViewById(R.id.bankBtn2);
        bankBtn3 = findViewById(R.id.bankBtn3);

        bankName1 = findViewById(R.id.bankName1);
        bankName2 = findViewById(R.id.bankName2);
        bankName3 = findViewById(R.id.bankName3);

        // Set text color to be modified later
        bankName1.setTextColor(Color.BLACK);
        bankName2.setTextColor(Color.BLACK);
        bankName3.setTextColor(Color.BLACK);

        // Add buttons to context menu
        registerForContextMenu(bankBtn1);
        registerForContextMenu(bankBtn2);
        registerForContextMenu(bankBtn3);
    }

    // Create context menu for buttons
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // Add string resources, so they can be translated later
        String visitWebsite = getString(R.string.website);
        String contactBank = getString(R.string.contactBank);
        String toggleFavourite = getString(R.string.toggleFavourite);

        // Change groupId depending on button pressed
        if (v.getId() == R.id.bankBtn1) {
            menu.add(0,0,0, visitWebsite);
            menu.add(0,1,1,contactBank);
            menu.add(0, 2, 2, toggleFavourite);

        } else if (v.getId() == R.id.bankBtn2) {
            menu.add(1,0,0, visitWebsite);
            menu.add(1,1,1,contactBank);
            menu.add(1, 2, 2, toggleFavourite);

        } else if (v.getId() == R.id.bankBtn3) {
            menu.add(2,0,0, visitWebsite);
            menu.add(2,1,1,contactBank);
            menu.add(2, 2, 2, toggleFavourite);

        }

    }

    // Does action depending on what option was clicked, and what button
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int groupId = item.getGroupId();
        int itemId = item.getItemId();

        // Initialize string variables to link to xml later
        String bankWebsite, bankNo;

        if (groupId == 0) {
            if (itemId == 0) {
                bankWebsite = getString(R.string.bank1Website);
                Intent intent = new Intent(Intent. ACTION_VIEW, Uri.parse(bankWebsite));
                startActivity(intent);

            } else if (itemId == 1) {
                bankNo = getString(R.string.bank1No);
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + bankNo));
                startActivity(intentCall);

            } else if (itemId == 2) {
                changeButtonTextColor(bankName1);

            }

        } else if (groupId == 1) {
            if (itemId == 0) {
                bankWebsite = getString(R.string.bank2Website);
                Intent intent = new Intent(Intent. ACTION_VIEW, Uri.parse(bankWebsite));
                startActivity(intent);

            } else if (itemId == 1) {
                bankNo = getString(R.string.bank2No);
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + bankNo));
                startActivity(intentCall);

            } else if (itemId == 2) {
                changeButtonTextColor(bankName2);
            }

        } else if (groupId == 2) {
            if (itemId == 0) {
                bankWebsite = getString(R.string.bank3Website);
                Intent intent = new Intent(Intent. ACTION_VIEW, Uri.parse(bankWebsite));
                startActivity(intent);

            } else if (itemId == 1) {
                bankNo = getString(R.string.bank3No);
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + bankNo));
                startActivity(intentCall);

            } else if (itemId == 2) {
                changeButtonTextColor(bankName3);

            }
        }

        return super.onContextItemSelected(item);
    }

    // Changes color of button text
    public void changeButtonTextColor(TextView textView) {
        if (textView.getCurrentTextColor() == Color.RED) {
            textView.setTextColor(Color.BLACK);

        } else if (textView.getCurrentTextColor() == Color.BLACK) {
            textView.setTextColor(Color.RED);
        }
    }

    // Create options menu for language selection
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Changes language according to selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.englishSelection) {
            setLanguage("en");
            return true;

        } else if (id == R.id.chineseSelection) {
            setLanguage("zh");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Changes locale to different language
    public void setLanguage(String localeCode) {
        Locale locale = new Locale(localeCode);
        Locale.setDefault(locale);

        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        recreate();
    }

}