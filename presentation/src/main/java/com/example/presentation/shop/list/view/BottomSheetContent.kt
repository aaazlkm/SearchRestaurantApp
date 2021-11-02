package com.example.presentation.shop.list.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.shop.model.SearchQuery
import com.example.domain.shop.model.SearchRange
import com.example.presentation.R
import com.example.presentation.core.theme.AppThemeWithBackground

val bottomSheetHorizontalPadding = 24.dp

@Composable
fun BottomSheetContent(
    searchQueryBuilder: SearchQuery.Builder,
    onClickSearchRange: (SearchRange) -> Unit,
    onClickSearchButton: () -> Unit,
) {
    val context = LocalContext.current
    Column {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = context.getString(R.string.shop_list_modal_title),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                horizontal = bottomSheetHorizontalPadding,
            ),
        )
        Spacer(modifier = Modifier.height(24.dp))
        SearchRangeItem(
            selected = searchQueryBuilder.searchRange,
            onClickSearchRange = onClickSearchRange,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onClickSearchButton,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = bottomSheetHorizontalPadding,
                ),
        ) {
            Text(
                text = context.getString(R.string.shop_list_modal_search_button),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    vertical = 4.dp
                ),
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomSheetContent() {
    AppThemeWithBackground {
        BottomSheetContent(
            searchQueryBuilder = SearchQuery.Builder(),
            onClickSearchRange = {},
            onClickSearchButton = {},
        )
    }
}
