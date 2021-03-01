package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.ui.theme.Dimens
import com.example.androiddevchallenge.util.InjectorUtils
import com.example.androiddevchallenge.viewmodel.PuppyDetailViewModel
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun DetailsScreen(id: Int, onBackClick: () -> Unit) {
    val detailViewModel: PuppyDetailViewModel = viewModel(
        factory = InjectorUtils.providePuppyDetailViewModelFactory(LocalContext.current, id)
    )
    val puppy = detailViewModel.puppy.observeAsState().value
    puppy?.let {
        Column {
            PuppyToolbar(puppyName = puppy.name, onBackClick = onBackClick)
            DetailContents(puppy)
        }
    }
}

@Composable
private fun DetailContents(puppy: Puppy) {
    Surface(color = MaterialTheme.colors.background) {
        Column {
            ConstraintLayout {
                val (image, info) = createRefs()
                PuppyImage(
                    imageUrl = puppy.imageUrl,
                    modifier = Modifier
                        .constrainAs(image) { top.linkTo(parent.top) }
                )
                PuppyInfo(
                    name = puppy.name,
                    sex = puppy.sex,
                    age = puppy.age,
                    color = puppy.color,
                    description = puppy.description,
                    modifier = Modifier.constrainAs(info) {
                        top.linkTo(image.bottom)
                    }
                )
            }
        }
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

@Composable
private fun PuppyInfo(
    name: String,
    sex: String,
    age: String,
    color: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(Dimens.PaddingLarge)) {
        PuppyNameAndAge(name, sex, age, color)
        Text(
            text = stringResource(id = R.string.detail_description),
            color = MaterialTheme.colors.primaryVariant,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = Dimens.PaddingSmall)
                .align(Alignment.CenterHorizontally)
        )
        Text(description)
    }
}

@Composable
private fun PuppyNameAndAge(name: String, sex: String, age: String, color:String) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = name, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(2.dp))
            Image(painter = painterResource(if(sex == "male") R.drawable.ic_male else R.drawable.ic_female),
                contentDescription = sex,
                modifier = Modifier.size(15.dp)
            )
        }
        Text(age)
        Text(color)
    }
}

@Composable
private fun PuppyToolbar(
    puppyName: String,
    onBackClick: () -> Unit
) {
    Surface(elevation = 2.dp, color = MaterialTheme.colors.surface) {
        TopAppBar(
            modifier = Modifier.height(55.dp).fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.surface
        ) {
            IconButton(onBackClick, Modifier.align(Alignment.CenterVertically)) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.action_back)
                )
            }
            Text(
                text = puppyName,
                style = MaterialTheme.typography.h6,
                // As title in TopAppBar has extra inset on the left, need to do this: b/158829169
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
            IconButton(
                {  },
                Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    Icons.Filled.Share,
                    contentDescription = stringResource(R.string.action_share)
                )
            }
        }
    }
}