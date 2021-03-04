
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception

-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep class android.support.v7.** {*;}
-keep class com.crashlytics.** { *; }
-keepattributes SourceFile,LineNumberTable
-dontwarn com.crashlytics.**
-dontwarn com.flurry.android.**
-dontwarn com.flurry.sdk.md
-dontwarn okio.Okio
-dontwarn okio.DeflaterSink
-dontwarn com.squareup.picasso.**
-dontwarn okhttp3.**
-dontwarn org.apache.**
-dontwarn org.threeten.**
-keepattributes Signature, InnerClasses, EnclosingMethod
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-keepclassmembers class * extends androidx.work.Worker
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn retrofit2.-KotlinExtensions
-keep class com.google.errorprone.annotations.** { *; }
-dontwarn com.google.errorprone.annotations.**
-keep class java.beans.** { *; }
-keep class sun.misc.Unsafe { *; }
-keep class org.modelmapper.**{*;}
-dontwarn java.beans.**
-dontwarn org.modelmapper.**

-dontwarn com.google.**
-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}
-keep class com.mikepenz.** { *; }

-keep class com.detsimov.leakchecker.data_local.network.dto.* {*;}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keepclassmembers enum * { *; }
-keepclassmembers class * {
    @android.webkit.JavascriptInterface public *;
}
-keep class com.google.android.gms.maps.** { *; }