package la.plume.noire1.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import la.plume.noire1.LaPlumeNoire1;

public class AndroidLauncher extends AndroidApplication {
		
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useWakelock = true;
		String packageName = getPackageName();
		initialize(new LaPlumeNoire1(packageName), config);
	}
}
