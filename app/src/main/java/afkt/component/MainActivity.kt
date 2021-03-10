package afkt.component;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dev.core.lib.engine.image.GlideEngineImpl;
import dev.engine.image.DevImageEngine;

public class MainActivity
        extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView igview = new ImageView(this);
        setContentView(igview);

        DevImageEngine.setEngine(new GlideEngineImpl());

        DevImageEngine.getEngine().display(igview, "https://picsum.photos/200");
    }
}