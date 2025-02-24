package com.example.simplevideoplayerfortestlib.presentation

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(
    private val spanCount: Int, // Количество столбцов
    private val spacing: Int,  // Размер отступа в пикселях
    private val includeEdge: Boolean // Добавлять ли отступы по краям
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // Позиция элемента
        val column = position % spanCount // Номер столбца

        if (includeEdge) {
            // Отступы по краям
            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount

            if (position < spanCount) { // Верхний ряд
                outRect.top = spacing
            }
            outRect.bottom = spacing // Отступ снизу
        } else {
            // Отступы между элементами
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount
            if (position >= spanCount) {
                outRect.top = spacing // Отступ сверху
            }
        }
    }
}