package com.example.newsapp.presentation.details

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.layout.IntervalList
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.Dimens.ArticleImageHeight
import com.example.newsapp.presentation.Dimens.MediumPadding1
import com.example.newsapp.presentation.details.components.DetailsTopBar
import com.example.newsapp.presentation.nvgraph.Route
import com.example.newsapp.presentation.search.SearchEvent
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent)->Unit,
    navigationUp:()->Unit
) {
    val context= LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()) {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data= Uri.parse(article.url)
                    if(it.resolveActivity(context.packageManager)!=null){
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT,article.url)
                    it.type="text/plain"
                    if(it.resolveActivity(context.packageManager)!=null){
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = {event(DetailsEvent.UpsertDeleteArticle(article))},
            onBackClick = navigationUp
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top= MediumPadding1
            )
        ) {
            item {
                AsyncImage(model = ImageRequest.Builder(context=context).data(article.urlToImage).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(MediumPadding1))
                Text(text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(id = R.color.text_title)
                )
                Text(text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.body)
                )
            }

        }

    }
}
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailScreenPreview(){
    NewsAppTheme {

    }
}