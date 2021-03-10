package afkt.component

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import dev.engine.image.DevImageEngine

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val igview = ImageView(this)
        setContentView(igview)
        DevImageEngine.getEngine().display(igview, "https://picsum.photos/201")
    }
}