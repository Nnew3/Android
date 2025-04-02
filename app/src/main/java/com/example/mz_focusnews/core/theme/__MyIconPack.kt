package com.example.mz_focusnews.core.theme

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mz_focusnews.core.theme.myiconpack.Bell
import com.example.mz_focusnews.core.theme.myiconpack.Category
import com.example.mz_focusnews.core.theme.myiconpack.Home
import com.example.mz_focusnews.core.theme.myiconpack.Quiz
import com.example.mz_focusnews.core.theme.myiconpack.User
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
    get() {
        if (__AllIcons != null) {
            return __AllIcons!!
        }
        __AllIcons = listOf(Bell, Category, Home, Quiz, User)
        return __AllIcons!!
    }
