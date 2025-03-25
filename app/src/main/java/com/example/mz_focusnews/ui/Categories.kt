package com.example.mz_focusnews.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mz_focusnews.MyIconPack
import kotlin.Unit

public val MyIconPack.Category: ImageVector
    get() {
        if (_categories != null) {
            return _categories!!
        }
        _categories = Builder(name = "Categories", defaultWidth = 21.0.dp, defaultHeight = 22.0.dp,
                viewportWidth = 21.0f, viewportHeight = 22.0f).apply {
            path(fill = SolidColor(Color(0xFFD1D1D1)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(12.417f, 6.417f)
                verticalLineTo(0.422f)
                curveTo(13.304f, 0.742f, 14.109f, 1.239f, 14.779f, 1.879f)
                lineTo(18.118f, 5.075f)
                curveTo(18.788f, 5.715f, 19.308f, 6.485f, 19.643f, 7.333f)
                horizontalLineTo(13.375f)
                curveTo(13.121f, 7.333f, 12.877f, 7.237f, 12.698f, 7.065f)
                curveTo(12.518f, 6.893f, 12.417f, 6.66f, 12.417f, 6.417f)
                close()
                moveTo(20.084f, 9.611f)
                verticalLineTo(17.417f)
                curveTo(20.082f, 18.632f, 19.577f, 19.797f, 18.678f, 20.656f)
                curveTo(17.78f, 21.515f, 16.562f, 21.999f, 15.292f, 22.0f)
                horizontalLineTo(5.709f)
                curveTo(4.438f, 21.999f, 3.22f, 21.515f, 2.322f, 20.656f)
                curveTo(1.424f, 19.797f, 0.919f, 18.632f, 0.917f, 17.417f)
                verticalLineTo(4.583f)
                curveTo(0.919f, 3.368f, 1.424f, 2.203f, 2.322f, 1.344f)
                curveTo(3.22f, 0.485f, 4.438f, 0.001f, 5.709f, 0.0f)
                lineTo(10.035f, 0.0f)
                curveTo(10.192f, 0.0f, 10.346f, 0.012f, 10.5f, 0.022f)
                verticalLineTo(6.417f)
                curveTo(10.5f, 7.146f, 10.803f, 7.845f, 11.342f, 8.361f)
                curveTo(11.882f, 8.877f, 12.613f, 9.167f, 13.375f, 9.167f)
                horizontalLineTo(20.061f)
                curveTo(20.071f, 9.314f, 20.084f, 9.462f, 20.084f, 9.611f)
                close()
                moveTo(12.417f, 17.417f)
                curveTo(12.417f, 17.174f, 12.316f, 16.94f, 12.136f, 16.768f)
                curveTo(11.957f, 16.597f, 11.713f, 16.5f, 11.459f, 16.5f)
                horizontalLineTo(6.667f)
                curveTo(6.413f, 16.5f, 6.169f, 16.597f, 5.989f, 16.768f)
                curveTo(5.81f, 16.94f, 5.709f, 17.174f, 5.709f, 17.417f)
                curveTo(5.709f, 17.66f, 5.81f, 17.893f, 5.989f, 18.065f)
                curveTo(6.169f, 18.237f, 6.413f, 18.333f, 6.667f, 18.333f)
                horizontalLineTo(11.459f)
                curveTo(11.713f, 18.333f, 11.957f, 18.237f, 12.136f, 18.065f)
                curveTo(12.316f, 17.893f, 12.417f, 17.66f, 12.417f, 17.417f)
                close()
                moveTo(15.292f, 13.75f)
                curveTo(15.292f, 13.507f, 15.191f, 13.274f, 15.011f, 13.102f)
                curveTo(14.832f, 12.93f, 14.588f, 12.833f, 14.334f, 12.833f)
                horizontalLineTo(6.667f)
                curveTo(6.413f, 12.833f, 6.169f, 12.93f, 5.989f, 13.102f)
                curveTo(5.81f, 13.274f, 5.709f, 13.507f, 5.709f, 13.75f)
                curveTo(5.709f, 13.993f, 5.81f, 14.226f, 5.989f, 14.398f)
                curveTo(6.169f, 14.57f, 6.413f, 14.667f, 6.667f, 14.667f)
                horizontalLineTo(14.334f)
                curveTo(14.588f, 14.667f, 14.832f, 14.57f, 15.011f, 14.398f)
                curveTo(15.191f, 14.226f, 15.292f, 13.993f, 15.292f, 13.75f)
                close()
            }
        }
        .build()
        return _categories!!
    }

private var _categories: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Category, contentDescription = "")
    }
}
