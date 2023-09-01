package com.sevdetneng.watchify.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.sevdetneng.watchify.model.Backdrop
import com.sevdetneng.watchify.navigation.Screens
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailCarousel(backdrops: List<Backdrop>) {

    val pagerState = rememberPagerState(
        initialPage = if(backdrops.size>2) 2 else 1,
        initialPageOffsetFraction = 0f
    ) {
        backdrops.size
    }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 40.dp),
            modifier = Modifier.height(220.dp),
            verticalAlignment = Alignment.CenterVertically,
            pageSpacing = 12.dp
        ) {
            DetailBackdropCard(modifier = Modifier.graphicsLayer {
                val pageOffset = pagerState.getOffsetFractionForPage(it).absoluteValue

                lerp(
                    start = 0.9f,
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

            }, backdrop = backdrops[it])
        }
    }
}