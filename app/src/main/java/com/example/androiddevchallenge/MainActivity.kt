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
import androidx.cardview.widget.CardView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
    Column(
        Modifier
            .padding(Dimens.PaddingNormal)
            .fillMaxWidth()
    ) {
        PuppyNameAndAge(item)
        Spacer(modifier = Modifier.padding(Dimens.PaddingSmall))
        PuppyImage(item.imageUrl)
    }
}

@Composable
private fun PuppyNameAndAge(item: Puppy) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = item.name, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(2.dp))
            Image(painter = painterResource(if(item.sex == "male") R.drawable.ic_male else R.drawable.ic_female),
                contentDescription = item.sex,
                modifier = Modifier.size(15.dp)
            )
        }
        Text(item.age)
    }
}

@Composable
private fun PuppyImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    placeholderColor: Color = MaterialTheme.colors.onSurface.copy(0.2f)
) {
    Card {
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
