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

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.DetailActivity.Companion.INTENT_KEY_PUPPY_ID
import com.example.androiddevchallenge.ui.PuppyList
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.util.InjectorUtils
import com.example.androiddevchallenge.viewmodel.PuppyListViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                Column {
                    TopBar()
                    MyApp { id ->
                        startActivity(
                            Intent(this@MainActivity, DetailActivity::class.java).apply {
                                putExtra(INTENT_KEY_PUPPY_ID, id)
                            }
                        )
                    }
                }
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(goDetail: ((Int) -> Unit)? = null) {
    val puppyListViewModel: PuppyListViewModel = viewModel(
        factory = InjectorUtils.providePuppyListViewModelFactory(LocalContext.current)
    )
    val puppyList = puppyListViewModel.puppies.observeAsState().value

    Surface(color = MaterialTheme.colors.background) {
        puppyList?.let {
            PuppyList(
                itemList = puppyList,
                itemClick = goDetail
            )
        }
    }
}

@Composable
fun TopBar() {
    Surface(elevation = 2.dp, color = MaterialTheme.colors.surface) {
        Row(
            modifier = Modifier.height(55.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_dog_logo),
                contentDescription = null,
                modifier = Modifier.size(34.dp)
            )
            Text(text = "Adopt puppy", style = MaterialTheme.typography.h1, fontSize = 20.sp)
        }
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
