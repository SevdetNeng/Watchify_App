package com.sevdetneng.watchify.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.sevdetneng.watchify.model.ListMovie
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PopularMovieCarousel(movies: List<ListMovie>?, navController: NavController) {
    val pagerState = rememberPagerState(
        initialPage = 2,
        initialPageOffsetFraction = 0f
    ) {
        movies!!.size
    }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 40.dp),
            modifier = Modifier.height(180.dp),
            verticalAlignment = Alignment.CenterVertically,
            pageSpacing = 12.dp
        ) {
            BackdropCard(modifier = Modifier.graphicsLayer {
                val pageOffset = pagerState.getOffsetFractionForPage(it).absoluteValue

                lerp(
                    start = 0.8f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    //scaleX = scale
                    scaleY = scale
                }
                alpha = lerp(
                    0.6f,
                    1f,
                    1f - pageOffset.coerceIn(0f, 1f)
                )

            }, movie = movies!![it])


        }

    }
}