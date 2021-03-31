package com.sherwin.sourdough.cache.prefs

import android.content.Context
import com.sherwin.sourdough.context.BaseApplication

class Preferences(context: Context, prefName: String) {
    private val pref by lazy { context.applicationContext.getSharedPreferences(prefName, Context.MODE_PRIVATE) }
    private val editor by lazy { pref.edit() }

    constructor(prefName: String) : this(BaseApplication.instance, prefName)

    operator fun contains(key: String): Boolean {
        return pref.contains(key)
    }

    operator fun get(key: String, def: Boolean): Boolean {
        return def.asErrorResult { pref.getBoolean(key, def) }
    }

    operator fun get(key: String, def: Int): Int {
        return def.asErrorResult { pref.getInt(key, def) }
    }

    operator fun get(key: String, def: Long): Long {
        return def.asErrorResult { pref.getLong(key, def) }
    }

    operator fun get(key: String, def: Float): Float {
        return pref.getFloat(key, def)
    }

    operator fun get(key: String, def: Set<String>): Set<String> {
        return def.asErrorResult { pref.getStringSet(key, def)!! }
    }

    operator fun get(key: String, def: String = ""): String {
        return def.asErrorResult { pref.getString(key, def)!! }
    }

    operator fun <T> Preferences.get(key: String, func: (String) -> T) = func(get(key))

    operator fun set(key: String, value: Boolean): Preferences {
        editor.putBoolean(key, value)
        return this
    }

    operator fun set(key: String, value: Int): Preferences {
        editor.putInt(key, value)
        return this
    }

    operator fun set(key: String, value: Long): Preferences {
        editor.putLong(key, value)
        return this
    }

    operator fun set(key: String, value: Float): Preferences {
        editor.putFloat(key, value)
        return this
    }

    operator fun set(key: String, value: String): Preferences {
        editor.putString(key, value)
        return this
    }

    operator fun set(key: String, func: () -> String) = set(key, func())

    operator fun set(key: String, value: Set<String>): Preferences {
        editor.putStringSet(key, value)
        return this
    }

    fun remove(key: String): Preferences {
        editor.remove(key)
        return this
    }

    fun clear(): Preferences {
        editor.clear()
        return this
    }

    fun commit(): Preferences {
        editor.commit()
        return this
    }
}

