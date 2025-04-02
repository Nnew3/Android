package com.example.mz_focusnews.core.theme.myiconpack

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
import com.example.mz_focusnews.core.theme.MyIconPack

public val MyIconPack.User: ImageVector
    get() {
        if (_user != null) {
            return _user!!
        }
        _user = Builder(
            name = "User", defaultWidth = 19.0.dp, defaultHeight = 22.0.dp,
            viewportWidth = 19.0f, viewportHeight = 22.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFD1D1D1)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(9.5f, 11.0f)
                curveTo(12.676f, 11.0f, 15.25f, 8.538f, 15.25f, 5.5f)
                curveTo(15.25f, 2.462f, 12.676f, 0.0f, 9.5f, 0.0f)
                curveTo(6.325f, 0.0f, 3.75f, 2.462f, 3.75f, 5.5f)
                curveTo(3.75f, 8.538f, 6.325f, 11.0f, 9.5f, 11.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFD1D1D1)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(9.5f, 12.833f)
                curveTo(4.739f, 12.838f, 0.88f, 16.528f, 0.875f, 21.083f)
                curveTo(0.875f, 21.589f, 1.304f, 21.999f, 1.833f, 21.999f)
                horizontalLineTo(17.167f)
                curveTo(17.696f, 21.999f, 18.125f, 21.589f, 18.125f, 21.083f)
                curveTo(18.12f, 16.528f, 14.261f, 12.837f, 9.5f, 12.833f)
                close()
            }
        }
            .build()
        return _user!!
    }

private var _user: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.User, contentDescription = "")
    }
}
