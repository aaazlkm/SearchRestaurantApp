package com.example.presentation.shop.list.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.shop.model.SearchQuery
import com.example.presentation.AppThemeWithBackground
import com.example.presentation.core.string.getString

@Composable
fun SearchBar(
    searchQuery: SearchQuery,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                clip = true
            )
            .background(color = MaterialTheme.colors.surface)
            .clickable { onClick() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    horizontal = 24.dp, vertical = 12.dp
                )
        ) {
            Icon(
                Icons.Outlined.Search,
                contentDescription = "Action Icon",
                tint = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.medium),
                modifier = Modifier
                    .padding(0.dp)
                    .size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            SearchQueryItem(
                searchQuery.searchRange.getString()
            )
        }
    }
}

@Composable
fun SearchQueryItem(
    label: String,
) {
    Surface(
        color = MaterialTheme.colors.surface,
        border = BorderStroke(
            0.5.dp,
            MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = 0.dp
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
            modifier = Modifier.padding(
                horizontal = 8.dp, vertical = 6.dp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchItem() {
    AppThemeWithBackground {
        SearchBar(
            searchQuery = SearchQuery(),
            onClick = {}
        )
    }
}
