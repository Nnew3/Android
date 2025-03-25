package com.example.mz_focusnews

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mz_focusnews.ui.Category
import com.example.mz_focusnews.ui.Home
import com.example.mz_focusnews.ui.Quiz
import com.example.mz_focusnews.ui.User
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Category, Home, Quiz, User)
    return __AllIcons!!
  }
