package com.xhateya.idn.dzikirapp.helper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xhateya.idn.dzikirapp.R
import com.xhateya.idn.dzikirapp.model.Artikel

interface OnItemClickCallback {
    fun onItemClicked(data: Artikel)
}