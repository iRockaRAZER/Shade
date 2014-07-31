
/*
 * Copyright (C) 2014 The Android Open Source Project
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

package com.android.launcher3.compat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.os.UserManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserManagerCompatVL extends UserManagerCompatV17 {

    UserManagerCompatVL(Context context) {
        super(context);
    }

    public List<UserHandleCompat> getUserProfiles() {
        List<UserHandle> users = mUserManager.getUserProfiles();
        if (users == null) {
            return Collections.EMPTY_LIST;
        }
        ArrayList<UserHandleCompat> compatUsers = new ArrayList<UserHandleCompat>(
                users.size());
        for (UserHandle user : users) {
            compatUsers.add(UserHandleCompat.fromUser(user));
        }
        return compatUsers;
    }

    public Drawable getBadgedDrawableForUser(Drawable unbadged, UserHandleCompat user) {
        return mUserManager.getBadgedDrawableForUser(unbadged, user.getUser());
    }

    public CharSequence getBadgedLabelForUser(CharSequence label, UserHandleCompat user) {
        if (user == null) {
            return label;
        }
        // TODO Remove casts when API has made it from lmp-dev -> sdk
        return (CharSequence) mUserManager.getBadgedLabelForUser((String) label, user.getUser());
    }
}
