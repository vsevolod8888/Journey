package com.sever.journey.analytics

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

object AnalyticsService {
    private lateinit var analytics: FirebaseAnalytics

    fun init(context: Context) {
        analytics = FirebaseAnalytics.getInstance(context)
    }

    fun logScreenView(screenName: String, screenClass: String? = null) {
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
            putString(FirebaseAnalytics.Param.SCREEN_CLASS, screenClass ?: screenName)
        }
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    fun logButtonClick(buttonName: String) {
        val bundle = Bundle().apply {
            putString("button_name", buttonName)
        }
        analytics.logEvent("button_clicked", bundle)
    }

    fun logRouteCreated(routeName: String, type: String) {
        val bundle = Bundle().apply {
            putString("route_name", routeName)
            putString("type", type)
        }
        analytics.logEvent("route_created", bundle)
    }

    fun logRouteOpened(routeName: String, routeId: Long, fromScreen: String) {
        val bundle = Bundle().apply {
            putString("route_name", routeName)
            putLong("route_id", routeId)
            putString("from_screen", fromScreen)
        }
        analytics.logEvent("route_opened", bundle)
    }

    fun logRouteDeleted(routeName: String?, fromScreen: String) {
        val bundle = Bundle().apply {
            putString("route_name", routeName)
            putString("from_screen", fromScreen)
        }
        analytics.logEvent("route_deleted", bundle)
    }

    fun logRouteDeletedAll() {
        analytics.logEvent("route_deleted_all", null)
    }

    fun logRouteSaved(routeName: String, type: String) {
        val bundle = Bundle().apply {
            putString("route_name", routeName)
            putString("type", type)
        }
        analytics.logEvent("route_saved", bundle)
    }

    fun logThemeToggled(isDark: Boolean) {
        val bundle = Bundle().apply {
            putString("theme", if (isDark) "dark" else "light")
        }
        analytics.logEvent("theme_toggled", bundle)
    }

    fun logFabClicked() {
        analytics.logEvent("fab_clicked", null)
    }

    fun logDaySelected(epochDays: Int) {
        val bundle = Bundle().apply {
            putInt("epoch_days", epochDays)
        }
        analytics.logEvent("day_selected", bundle)
    }

    fun logNavigation(fromScreen: String, toScreen: String) {
        val bundle = Bundle().apply {
            putString("from_screen", fromScreen)
            putString("to_screen", toScreen)
        }
        analytics.logEvent("navigation", bundle)
    }

    fun logMapContinue(type: String) {
        val bundle = Bundle().apply {
            putString("type", type)
        }
        analytics.logEvent("map_continue", bundle)
    }

    fun logNotificationSettingsOpened() {
        analytics.logEvent("notification_settings_opened", null)
    }

    fun logRouteDiscarded(routeName: String) {
        val bundle = Bundle().apply {
            putString("route_name", routeName)
        }
        analytics.logEvent("route_discarded", bundle)
    }
}
