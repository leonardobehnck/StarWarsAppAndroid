package com.app.starwarsapp.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.app.starwarsapp.data.local.CharacterContract.DATABASE_NAME
import com.app.starwarsapp.data.local.CharacterContract.DATABASE_VERSION
import com.app.starwarsapp.data.local.CharacterContract.SQL_DELETE_ENTRIES
import com.app.starwarsapp.data.local.CharacterContract.TABLE_CHARACTER

class CharacterDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
  override fun onCreate(db: SQLiteDatabase?) {
    db?.execSQL(TABLE_CHARACTER)
  }

  override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    super.onDowngrade(db, oldVersion, newVersion)
    onUpgrade(db, oldVersion, newVersion)

  }

  override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    db?.execSQL(SQL_DELETE_ENTRIES)
    onCreate(db)
  }
}