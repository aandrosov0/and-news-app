package com.dvx.news.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.dvx.news.ui.themes.DVXTheme
import com.dvx.news.R

@Composable
fun VerticalPost(
    image: Painter,
    headline: String,
    subheadline: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.clickable(onClick = onClick)) {
        Image(
            painter = image,
            contentDescription = stringResource(R.string.post_image),
            modifier = Modifier.aspectRatio(1f),
            contentScale = ContentScale.Crop
        )
        Subheadline(
            text = subheadline,
            modifier = Modifier.padding(top = 3.5.dp)
        )
        Headline(
            text = headline,
            modifier = Modifier.offset(y = (-4).dp)
        )
    }
}

@Composable
fun HorizontalPost(
    image: Painter,
    headline: String,
    subheadline: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(space = 10.dp)
    ) {
        Image(
            painter = image,
            contentDescription = stringResource(R.string.post_image),
            modifier = Modifier.size(100.dp)
        )
        Column {
            Subheadline(text = subheadline)
            Headline(text = headline)
        }
    }
}

@Composable
fun VerticalPostCard(
    image: Painter,
    headline: String,
    subheadline: String,
    createdAt: String,
    category: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    thumbnail: Boolean = false
) {
    val shape = RoundedCornerShape(8.dp)
    Column(
        modifier = modifier.clickable(onClick = onClick)
            .shadow(elevation = 8.dp, shape = shape)
    ) {
        Header(
            createdAt = createdAt,
            category = category
        )
        if (!thumbnail) {
            Image(
                painter = image,
                contentDescription = stringResource(R.string.post_image),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            Footer(
                headline = headline,
                subheadline = subheadline
            )
        } else {
            Thumbnail(
                image = image,
                headline = headline,
                subheadline = subheadline,
            )
        }
    }
}

@Composable
private fun Subheadline(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 15.sp,
        overflow = TextOverflow.Ellipsis,
        lineHeight = 1.em,
        maxLines = 1,
        style = MaterialTheme.typography.titleSmall,
    )
}

@Composable
private fun Headline(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        color = LocalContentColor.current,
        lineHeight = 1.1.em,
        fontSize = 19.sp,
    )
}

@Composable
private fun Header(
    createdAt: String,
    category: String,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
    Row(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.surface,
            shape = shape
        )
            .size(width = 456.dp, height = 56.dp)
            .padding(vertical = 8.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CreatedAt(text = createdAt)
        Category(text = category)
    }
}

@Composable
private fun CreatedAt(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier.alpha(0.64f)
    )
}

@Composable
private fun Category(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier.alpha(0.64f)
    )
}

@Composable
private fun Footer(
    headline: String,
    subheadline: String,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
    Column(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.surface,
            shape = shape
        )
            .width(456.dp)
            .padding(top = 9.dp, end = 8.dp, start = 8.dp, bottom = 10.5.dp),
    ) {
        Subheadline(text = subheadline)
        Headline(text = headline)
    }
}

@Preview
@Composable
private fun VerticalPostPreview() = DVXTheme {
    VerticalPost(
        image = painterResource(R.drawable.ic_void),
        headline = "Deutsches Burger-Mekka ist...",
        subheadline = "Hamburg weit abgeschlagen",
        onClick = {}
    )
}

@Preview
@Composable
private fun HorizontalPostPreview() = DVXTheme {
    HorizontalPost(
        image = painterResource(R.drawable.ic_void),
        headline = "Deutsches Burger-Mekka ist...",
        subheadline = "Hamburg weit abgeschlagen",
        onClick = {}
    )
}

@Preview
@Composable
private fun SubheadlinePreview() = DVXTheme {
    Subheadline(text = "Hamburg weit abgeschlagen")
}

@Preview
@Composable
private fun HeadlinePreview() = DVXTheme {
    Headline(text = "Deutsches Burger-Mekka ist...")
}

@Preview
@Composable
private fun VerticalPostCardPreview() = DVXTheme {
    VerticalPostCard(
        image = painterResource(R.drawable.img_rectangle_preview),
        headline = "Deutsches Burger-Mekka ist...",
        subheadline = "Hamburg weit abgeschlagen",
        createdAt = "12:38 Uhr",
        category = "Regional",
        onClick = {}
    )
}

@Preview
@Composable
private fun HeaderPreview() = DVXTheme {
    Header(
        createdAt = "12:38 Uhr",
        category = "Regional"
    )
}

@Preview
@Composable
private fun CreatedAtPreview() = DVXTheme {
    CreatedAt(text = "12:38 Uhr")
}

@Preview
@Composable
private fun CategoryPreview() = DVXTheme {
    Category(text = "Regional")
}

@Preview
@Composable
private fun FooterPreview() = DVXTheme {
    Footer(
        headline = "Deutsches Burger-Mekka ist...",
        subheadline = "Hamburg weit abgeschlagen"
    )
}