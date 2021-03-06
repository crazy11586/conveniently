package org.zarroboogs.maps.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import org.zarroboogs.maps.R;
import org.zarroboogs.maps.ui.fragment.AboutFragment;


public class AboutActivity extends BaseActivity{


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

		if (savedInstanceState == null){
			getFragmentManager().beginTransaction().replace(R.id.setting_fragment_frame, new AboutFragment()).commit();
		}

        ImageButton back = (ImageButton) findViewById(R.id.setting_back_image);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
	}

}
