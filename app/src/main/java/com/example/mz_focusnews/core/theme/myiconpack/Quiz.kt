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
import kotlin.Unit

public val MyIconPack.Quiz: ImageVector
    get() {
        if (_quiz != null) {
            return _quiz!!
        }
        _quiz = Builder(name = "Quiz", defaultWidth = 23.0.dp, defaultHeight = 22.0.dp,
                viewportWidth = 23.0f, viewportHeight = 22.0f).apply {
            path(fill = SolidColor(Color(0xFFD1D1D1)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(23.0f, 7.774f)
                verticalLineTo(18.334f)
                curveTo(23.0f, 18.577f, 22.899f, 18.81f, 22.719f, 18.982f)
                curveTo(22.539f, 19.154f, 22.296f, 19.25f, 22.042f, 19.25f)
                curveTo(21.787f, 19.25f, 21.544f, 19.154f, 21.364f, 18.982f)
                curveTo(21.184f, 18.81f, 21.083f, 18.577f, 21.083f, 18.334f)
                verticalLineTo(10.773f)
                lineTo(13.992f, 14.014f)
                curveTo(13.244f, 14.436f, 12.39f, 14.656f, 11.522f, 14.651f)
                curveTo(10.611f, 14.652f, 9.717f, 14.418f, 8.934f, 13.973f)
                lineTo(1.862f, 10.753f)
                curveTo(1.306f, 10.467f, 0.84f, 10.044f, 0.513f, 9.528f)
                curveTo(0.185f, 9.011f, 0.009f, 8.421f, 0.0f, 7.817f)
                curveTo(-0.008f, 7.213f, 0.153f, 6.618f, 0.466f, 6.094f)
                curveTo(0.779f, 5.57f, 1.233f, 5.135f, 1.781f, 4.836f)
                curveTo(1.807f, 4.821f, 1.835f, 4.807f, 1.862f, 4.794f)
                lineTo(9.008f, 1.533f)
                curveTo(9.78f, 1.109f, 10.655f, 0.89f, 11.545f, 0.897f)
                curveTo(12.435f, 0.904f, 13.306f, 1.138f, 14.07f, 1.574f)
                lineTo(21.138f, 4.794f)
                curveTo(21.696f, 5.09f, 22.162f, 5.521f, 22.489f, 6.044f)
                curveTo(22.816f, 6.567f, 22.992f, 7.164f, 23.0f, 7.774f)
                verticalLineTo(7.774f)
                close()
                moveTo(11.521f, 16.482f)
                curveTo(10.295f, 16.485f, 9.09f, 16.177f, 8.028f, 15.591f)
                lineTo(3.833f, 13.676f)
                verticalLineTo(16.151f)
                curveTo(3.834f, 17.13f, 4.161f, 18.082f, 4.768f, 18.87f)
                curveTo(5.375f, 19.658f, 6.229f, 20.24f, 7.206f, 20.531f)
                curveTo(8.601f, 20.914f, 10.048f, 21.1f, 11.5f, 21.084f)
                curveTo(12.952f, 21.099f, 14.399f, 20.912f, 15.794f, 20.527f)
                curveTo(16.771f, 20.236f, 17.625f, 19.655f, 18.232f, 18.867f)
                curveTo(18.838f, 18.079f, 19.166f, 17.126f, 19.167f, 16.147f)
                verticalLineTo(13.681f)
                lineTo(14.893f, 15.633f)
                curveTo(13.868f, 16.197f, 12.704f, 16.49f, 11.521f, 16.484f)
                verticalLineTo(16.482f)
                close()
            }
        }
        .build()
        return _quiz!!
    }

private var _quiz: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = MyIconPack.Quiz, contentDescription = "")
    }
}
