LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_PACKAGE_NAME := Facelift
LOCAL_SRC_FILES := $(call all-java-files-under, app/src/main/java)
LOCAL_CHECK_SRC_DIRECTORY := app/src/main/java
LOCAL_CERTIFICATE := platform

LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/app/src/main/res

LOCAL_MANIFEST_FILE := app/src/main/AndroidManifest.xml

LOCAL_STATIC_JAVA_LIBRARIES := guava android-support-v4

include $(BUILD_PACKAGE)
include $(BUILD_MULTI_PREBUILT)
