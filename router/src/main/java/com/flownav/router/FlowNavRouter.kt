/*
 * Copyright 2019, Jeziel Lago - Alex Soares.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.flownav.router

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

abstract class FlowNavRouter {

    protected fun Context.start(
        destinationKey: String,
        flags: List<Int>? = null,
        args: IntentParams = {}
    ) {
        createIntent(destinationKey, flags, args)
            .run { startActivity(this) }
    }

    protected fun Activity.startForResult(
        destinationKey: String,
        resultCode: Int,
        flags: List<Int>? = null,
        args: IntentParams = {}
    ) {
        createIntent(destinationKey, flags, args)
            .run { startActivityForResult(intent, resultCode) }
    }

    private fun Context.createIntent(
        destinationKey: String,
        flags: List<Int>? = null,
        args: IntentParams = {}
    ) = Intent(Intent.ACTION_VIEW)
        .putExtras(Bundle().apply(args))
        .setClassName(
            packageName,
            FlowNavApp.getEntryMap()[destinationKey]?.actionName
                ?: error("$destinationKey not found.")
        ).apply { flags?.forEach { addFlags(it) } }
}

typealias IntentParams = Bundle.() -> Unit
