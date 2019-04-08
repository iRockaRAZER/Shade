/*
 * Copyright (C) 2018 The Android Open Source Project
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
package com.android.quickstep;

import android.content.Context;
import android.content.pm.PackageManager;

import com.android.launcher3.MainProcessInitializer;
import com.android.launcher3.Utilities;
import com.android.systemui.shared.system.ThreadedRendererCompat;

@SuppressWarnings("unused")
public class QuickstepProcessInitializer extends MainProcessInitializer {
    private static final String CONTROL_REMOTE_APP_TRANSITION_PERMISSION =
            "android.permission.CONTROL_REMOTE_APP_TRANSITION_ANIMATIONS";

    private static boolean sEnabled;

    public static boolean isEnabled() {
        return sEnabled;
    }

    public QuickstepProcessInitializer(Context context) {
        sEnabled = Utilities.ATLEAST_P
                && context.checkSelfPermission(CONTROL_REMOTE_APP_TRANSITION_PERMISSION)
                == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void init(Context context) {
        super.init(context);

        if (isEnabled()) {
            // Elevate GPU priority for Quickstep and Remote animations.
            ThreadedRendererCompat.setContextPriority(ThreadedRendererCompat.EGL_CONTEXT_PRIORITY_HIGH_IMG);
        }
    }
}
