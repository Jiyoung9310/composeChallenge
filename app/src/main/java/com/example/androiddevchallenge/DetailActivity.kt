package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.util.InjectorUtils
import com.example.androiddevchallenge.viewmodel.PuppyDetailViewModel
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

@Composable
fun DetailsScreen(id: Int, onBackClick: () -> Unit) {
    val detailViewModel: PuppyDetailViewModel = viewModel(
        factory = InjectorUtils.providePuppyDetailViewModelFactory(LocalContext.current, id)
    )
    val puppy = detailViewModel.puppy.observeAsState().value
    Surface {
        puppy?.let {
            Text(puppy.name)
        }
    }
}