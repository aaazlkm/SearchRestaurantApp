package com.example.presentation.shop.list.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.shop.model.SearchRange
import com.example.presentation.R
import com.example.presentation.core.string.getString

@Composable
fun SearchRangeItem(
    selected: SearchRange,
    onClickSearchRange: (SearchRange) -> Unit,
) {
    val context = LocalContext.current
    Column {
        Text(
            text = context.getString(R.string.shop_list_modal_search_range),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
            modifier = Modifier.padding(
                horizontal = bottomSheetHorizontalPadding,
            ),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(
                    horizontal = bottomSheetHorizontalPadding,
                ),

        ) {
            SearchRange.sortedByRange.forEach { searchRange ->
                SearchRangeChip(
                    searchRange = searchRange,
                    selected = selected,
                    onClick = onClickSearchRange,
                )
            }
        }
    }
}

@Composable
fun SearchRangeChip(
    searchRange: SearchRange,
    selected: SearchRange,
    onClick: (SearchRange) -> Unit,
) {
    Chip(
        name = searchRange.getString(),
        isSelected = searchRange == selected,
        onClick = { onClick(searchRange) }
    )
}

@Preview(showBackground = true)
@Composable
fun Chip(
    name: String = "Chip",
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
) {
    Surface(
        modifier = Modifier.padding(4.dp),
        elevation = 2.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier
                .clickable { onClick() }
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.body2,
                color = if (isSelected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchRangeItem() {
    SearchRangeItem(
        selected = SearchRange.L_500,
        onClickSearchRange = {}
    )
}
