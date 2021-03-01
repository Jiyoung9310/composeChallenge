/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.ui.theme.Dimens
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.util.InjectorUtils
import com.example.androiddevchallenge.viewmodel.PuppyListViewModel
import dev.chrisbanes.accompanist.coil.CoilImage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val puppyListviewModel: PuppyListViewModel = viewModel(
        factory = InjectorUtils.providePuppyListViewModelFactory(LocalContext.current)
    )
    puppyListviewModel.getPuppies()
    val puppyList = puppyListviewModel.puppies.observeAsState().value
    Surface(color = MaterialTheme.colors.background) {
        puppyList?.let{
            PuppyList(itemList = puppyList)
        }
    }
}


@Composable
fun PuppyList(
    itemList: List<Puppy>
) {
    LazyColumn {
        items(itemList) { data ->
            PuppyListItem(item = data)
        }
    }
}
// The UI for each list item can be generated by a reusable composable
@Composable
fun PuppyListItem(item: Puppy) {
    val padding = 16.dp
    Column(
        Modifier
            .padding(padding)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(item.name)
                Text(item.age)
            }
        }
        Spacer(modifier = Modifier.padding(padding))
        PuppyImage(item.imageUrl)
    }
}

@Composable
private fun PuppyImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    placeholderColor: Color = MaterialTheme.colors.onSurface.copy(0.2f)
) {
    CoilImage(
        data = imageUrl,
        contentScale = ContentScale.Crop,
        fadeIn = true,
        contentDescription = null,
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(placeholderColor)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.cardHeight)
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
