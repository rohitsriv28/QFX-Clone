package com.example.qfxclone.ui.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qfxclone.ui.components.HeadingBox
import com.example.qfxclone.ui.components.HeroSection
import com.example.qfxclone.ui.components.MovieWood
import com.example.qfxclone.ui.components.MyDefaultValues
import com.example.qfxclone.ui.components.TopBar
import com.example.qfxclone.ui.navigation.Routes

@Preview(showSystemUi = true)
@Composable
fun DashboardScreen(
    navigate: (String) -> Unit = {}
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    //            <--Drawer-->
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        scrimColor = Color(247, 226, 226, 105),
        drawerContent = {
            Column(Modifier.fillMaxHeight()) {

                //                    <--User Info-->

                Box {
                    Row(
                        Modifier
                            .width(240.dp)
                            .background(Color.LightGray)
                            .background(Color.LightGray)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "",
                            modifier = Modifier
                                .size(50.dp, 50.dp)
                                .padding(8.dp, 0.dp, 0.dp, 0.dp)
                        )
                        Column(Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)) {
                            Text(text = "UserName", color = Color.Black, fontSize = 24.sp)
                            Row {
                                Text(
                                    text = "online", color = Color.Black,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(68.dp, 0.dp, 0.dp, 0.dp)
                                )
                            }
                        }
                    }
                }

                //                    <--Drawer Content-->

                Column(
                    Modifier
                        .width(240.dp)
                        .fillMaxHeight()
                        .background(Color.Gray)
                ) {
                    Text(text = ".", modifier = Modifier
                        .padding(16.dp, 0.dp, 8.dp, 0.dp)
                        .clickable { })
                    Text(text = ".", modifier = Modifier
                        .padding(16.dp, 0.dp, 8.dp, 0.dp)
                        .clickable { })
                    Text(text = ".", modifier = Modifier
                        .padding(16.dp, 0.dp, 8.dp, 0.dp)
                        .clickable { })
                    Text(text = ".", modifier = Modifier
                        .padding(16.dp, 0.dp, 8.dp, 0.dp)
                        .clickable { })
                }
            }
        }) {
        Scaffold(
            topBar = { TopBar(drawerState = drawerState) },
            bottomBar = {

                BottomAppBar(
                    modifier = Modifier.height(50.dp),
                    containerColor = MyDefaultValues.defaultBgColor
                ) {

                }
            }) { innerPadding ->

            Column(
                Modifier
                    .verticalScroll(ScrollState(0))
                    .padding(innerPadding)
            ) {
                HeroSection()

                // now showing section

                Column {
                    HeadingBox(
                        title = "NowShowing",
                        movies = "(5 movies)"
                    ) { navigate(Routes.NOW.path) }
                    LazyRow(Modifier.fillMaxWidth()) {
                        items(1) {
                            MovieWood(
                                modifier = Modifier
                                    .requiredSize(180.dp)
                                    .padding(8.dp),
                                "BollyWood",
                                "(2)"
                            )
                            MovieWood(
                                modifier = Modifier
                                    .requiredSize(180.dp)
                                    .padding(8.dp),
                                "HollyWood",
                                "(2)"
                            )
                            MovieWood(
                                modifier = Modifier
                                    .requiredSize(180.dp)
                                    .padding(8.dp),
                                "KollyWood",
                                "(2)"
                            )
                        }
                    }
                }

                // coming soon section

                Column {
                    HeadingBox(
                        "Coming Soon",
                        "(5 movies)",

                        ) { navigate(Routes.COMING_SOON.path) }
                    LazyRow(Modifier.fillMaxWidth()) {
                        items(1) {
                            MovieWood(
                                modifier = Modifier
                                    .requiredSize(180.dp)
                                    .padding(8.dp),
                                "BollyWood",
                                "(2)"
                            )
                            MovieWood(
                                modifier = Modifier
                                    .requiredSize(180.dp)
                                    .padding(8.dp),
                                "HollyWood",
                                "(2)"
                            )
                            MovieWood(
                                modifier = Modifier
                                    .requiredSize(180.dp)
                                    .padding(8.dp),
                                "KollyWood",
                                "(2)"
                            )
                        }
                    }
                }
            }
        }
    }
}
