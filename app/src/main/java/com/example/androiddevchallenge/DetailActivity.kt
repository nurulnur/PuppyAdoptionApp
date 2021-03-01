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
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme

class DetailActivity : AppCompatActivity() {
    private var puppy: Puppy? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        puppy = intent.getParcelableExtra("puppyData")
        setContent {
            MyTheme {
                MyApp(puppy)
            }
        }
    }
}

@Composable
fun MyApp(puppy: Puppy?) {
    val context = LocalContext.current
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box {

                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    PuppyPicture(puppy?.image ?: "")
                    PuppyName(puppy?.name)
                    PuppyGender(puppy?.gender)
                    PuppyAbout(puppy?.about)
                }
                ExtendedFloatingActionButton(
                    modifier = Modifier
                        .paddingFromBaseline(bottom = 10.dp)
                        .align(Alignment.BottomEnd)
                        .padding(end = 10.dp, bottom = 10.dp),
                    text = {
                        Text(
                            text = "Adopt ${puppy?.name}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    },
                    onClick = {
                        Toast.makeText(context, "Thank You For Adopting Me :)", Toast.LENGTH_LONG)
                            .show()
                    }
                )
            }
        }
    }
}
@Composable
fun PuppyPicture(imageName: String) {
    val context = LocalContext.current
    val resId: Int = context.resources.getIdentifier(
        imageName,
        "drawable",
        context.packageName
    )
    val image: Painter = painterResource(id = resId)
    Image(
        painter = image, contentDescription = "",
        Modifier
            .height(250.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.FillBounds,
    )
}

@Composable
fun PuppyName(puppyName: String?) {
    Column {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.puppy_name),
                Modifier.padding(start = 5.dp, top = 5.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = puppyName ?: "",
                Modifier.padding(start = 5.dp, top = 5.dp),
                fontSize = 18.sp,
            )
        }
    }
}

@Composable
fun PuppyGender(puppyGender: String?) {
    Column {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.puppy_gender),
                Modifier.padding(start = 5.dp, top = 5.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = puppyGender ?: "",
                Modifier.padding(start = 5.dp, top = 5.dp),
                fontSize = 18.sp,
            )
        }
    }
}

@Composable
fun PuppyAbout(puppyAbout: String?) {
    Column {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                stringResource(R.string.puppy_about),
                Modifier.padding(start = 5.dp, top = 5.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = puppyAbout ?: "",
                Modifier.padding(start = 5.dp, top = 5.dp),
                fontSize = 18.sp,
            )
        }
    }
}
