package com.example.youtubebrave;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.Gravity;
import android.view.ViewGroup;
import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String YOUTUBE_URL =
            "https://www.youtube.com/";

    private static final String BRAVE_PACKAGE =
            "com.brave.browser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(48, 48, 48, 48);
        layout.setBackgroundColor(Color.BLACK);

        TextView title = new TextView(this);
        title.setText("YouTube Web");
        title.setTextColor(Color.WHITE);
        title.setTextSize(28);
        title.setGravity(Gravity.CENTER);

        TextView subtitle = new TextView(this);
        subtitle.setText("Open YouTube in Brave Browser");
        subtitle.setTextColor(Color.LTGRAY);
        subtitle.setTextSize(16);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, 20, 0, 40);

        Button button = new Button(this);
        button.setText("▶  Open YouTube in Brave");

        button.setOnClickListener(v -> openYouTube());

        layout.addView(title,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        layout.addView(subtitle,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        layout.addView(button,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        setContentView(layout);
    }

    private void openYouTube() {

        Intent braveIntent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(YOUTUBE_URL)
        );

        braveIntent.setPackage(BRAVE_PACKAGE);

        PackageManager pm = getPackageManager();

        if (braveIntent.resolveActivity(pm) != null) {
            startActivity(braveIntent);
        } else {

            Intent browserIntent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(YOUTUBE_URL)
            );

            if (browserIntent.resolveActivity(pm) != null) {
                startActivity(browserIntent);
            } else {
                Toast.makeText(
                        this,
                        "No browser found",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }
}
