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
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mz_focusnews.MyIconPack
import kotlin.Unit

public val MyIconPack.Home: ImageVector
    get() {
        if (_home != null) {
            return _home!!
        }
        _home = Builder(name = "Home", defaultWidth = 23.0.dp, defaultHeight = 22.0.dp,
                viewportWidth = 23.0f, viewportHeight = 22.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF3D8FEF)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(11.5f, 13.743f)
                    curveTo(9.912f, 13.743f, 8.625f, 14.974f, 8.625f, 16.493f)
                    verticalLineTo(21.993f)
                    horizontalLineTo(14.375f)
                    verticalLineTo(16.493f)
                    curveTo(14.375f, 14.974f, 13.088f, 13.743f, 11.5f, 13.743f)
                    close()
                }
                path(fill = SolidColor(Color(0xFFD1D1D1)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(16.292f, 16.493f)
                    verticalLineTo(21.993f)
                    horizontalLineTo(20.125f)
                    curveTo(21.713f, 21.993f, 23.0f, 20.762f, 23.0f, 19.243f)
                    verticalLineTo(10.882f)
                    curveTo(23.0f, 10.406f, 22.807f, 9.948f, 22.46f, 9.606f)
                    lineTo(14.317f, 1.185f)
                    curveTo(12.88f, -0.303f, 10.454f, -0.394f, 8.9f, 0.981f)
                    curveTo(8.826f, 1.046f, 8.755f, 1.114f, 8.686f, 1.185f)
                    lineTo(0.557f, 9.603f)
                    curveTo(0.2f, 9.947f, -0.0f, 10.411f, 0.0f, 10.896f)
                    verticalLineTo(19.243f)
                    curveTo(0.0f, 20.762f, 1.287f, 21.993f, 2.875f, 21.993f)
                    horizontalLineTo(6.708f)
                    verticalLineTo(16.493f)
                    curveTo(6.726f, 13.993f, 8.836f, 11.952f, 11.384f, 11.893f)
                    curveTo(14.016f, 11.833f, 16.272f, 13.908f, 16.292f, 16.493f)
                    close()
                }
                path(fill = SolidColor(Color(0xFFD1D1D1)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(11.5f, 13.743f)
                    curveTo(9.912f, 13.743f, 8.625f, 14.974f, 8.625f, 16.493f)
                    verticalLineTo(21.993f)
                    horizontalLineTo(14.375f)
                    verticalLineTo(16.493f)
                    curveTo(14.375f, 14.974f, 13.088f, 13.743f, 11.5f, 13.743f)
                    close()
                }
            }
        }
        .build()
        return _home!!
    }

private var _home: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Home, contentDescription = "")
    }
}
