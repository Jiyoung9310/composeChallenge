package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddevchallenge.ui.DetailsScreen
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

class DetailActivity : AppCompatActivity()  {

    companion object{
        const val INTENT_KEY_PUPPY_ID = "puppyId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (intent.extras?.get(INTENT_KEY_PUPPY_ID) as? Int)?.let { puppyId ->
            setContent {
                MyTheme {
                    ProvideWindowInsets {
                        DetailsScreen(
                            puppyId,
                            onBackClick = { finish() }
                        )
                    }
                }
            }
        } ?: run { finish() }

    }
}