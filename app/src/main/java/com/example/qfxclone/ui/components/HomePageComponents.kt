package com.example.qfxclone.ui.components

import MyScreen
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qfxclone.R
import com.example.qfxclone.ui.navigation.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


//      <--TopBar-->

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showSystemUi = true)
@Composable
fun TopBar(drawerState: DrawerState = DrawerState(DrawerValue.Closed)) {
    Column {
        val scope = rememberCoroutineScope()
        TopAppBar(
            title = {},
//        modifier = Modifier.height(100.dp),
            navigationIcon = {
                Row(Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp)) {
                    IconButton(onClick = {
                        scope.launch { drawerState.open() }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "",
                            modifier = Modifier
                                .size(36.dp),
                            tint = Color.White
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.nfxlogo),
                        contentDescription = "",
                        tint = Color(245, 234, 29, 255),
                        modifier = Modifier
                            .weight(0.1f)
                            .size(50.dp)
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            modifier = Modifier
                                .size(28.dp)
                                .clickable { },
                            tint = Color.White
                        )
                    }
                    MyScreen()
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(MyDefaultValues.defaultBgColor)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showSystemUi = true)
@Composable
fun PhotoSlider() {
    val photoList = listOf(
        R.drawable.devara,
        R.drawable.shaitaan,
        R.drawable.bmcm
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState(pageCount = { photoList.size }, initialPage = selectedIndex)
    val animationScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        animationScope.launch {
            while (true) {
                delay(2000)
                selectedIndex = (pagerState.currentPage + 1) % pagerState.pageCount
                pagerState.animateScrollToPage(selectedIndex)
            }
        }
    }

    Column {
        HorizontalPager(
            modifier = Modifier.weight(0.7f),
            state = pagerState
        ) { page ->
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = photoList[page]),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(270.dp)
//                        .width(340.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HeroSection() {
    PhotoSlider()
}


@Composable
fun HeadingBox(
    title: String, movies: String,
    onNavigate: () -> Unit = {}
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(MyDefaultValues.defaultBgColor)
            .clickable { onNavigate() },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title,
                Modifier
                    .padding(8.dp),
                color = MyDefaultValues.defaultButtonColor,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = movies, fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

//@Preview(showSystemUi = true)
@Composable
fun NowShowingSection(
    navigate: (String) -> Unit = {}
) {
    val nav = {
        navigate(Routes.NOW.path)
    }
    Column {
        HeadingBox(
            title = "NowShowing",
            movies = "(5 movies)",
            nav
        )
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

//@Preview(showSystemUi = true)
@Composable
fun ComingSoonSection(navigate: () -> Unit = {}) {
    Column {
        HeadingBox(
            "Coming Soon",
            "(5 movies)",
            navigate
        )
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

//@Preview(showSystemUi = true)
@Composable
fun MovieWood(modifier: Modifier = Modifier, title: String = "Title", movies: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Text(
                text = title,
                textAlign = TextAlign.Justify,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = movies,
                modifier = Modifier.width(30.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
        Row(modifier = modifier) {
            NowShowingImage(
                imgWidth = 80.dp,
                imgHeight = 130.dp,
                painterResource(id = R.drawable.devara)
            )
            NowShowingImage(
                imgWidth = 80.dp,
                imgHeight = 130.dp,
                painterResource(id = R.drawable.devara)
            )
        }
    }
}


@Composable
fun RowScope.NowShowingImage(imgWidth: Dp, imgHeight: Dp, image: Painter) {

    Column {
        Image(
            painter = image,
            contentDescription = "movie",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(2.dp)
                .requiredWidth(imgWidth)
                .requiredHeight(imgHeight)
        )
    }
}
