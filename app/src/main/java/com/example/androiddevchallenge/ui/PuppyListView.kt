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
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.ui.theme.Dimens
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun PuppyList(
    itemList: List<Puppy>,
    itemClick: ((Int) -> Unit)? = null
) {
    LazyColumn {
        items(itemList) { data ->
            PuppyListItem(item = data, itemClick)
        }
    }
}
// The UI for each list item can be generated by a reusable composable
@Composable
private fun PuppyListItem(item: Puppy, itemClick: ((Int) -> Unit)? = null) {
    Column(
        Modifier
            .clickable { itemClick?.invoke(item.puppyId) }
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
            Image(
                painter = painterResource(if (item.sex == "male") R.drawable.ic_male else R.drawable.ic_female),
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
