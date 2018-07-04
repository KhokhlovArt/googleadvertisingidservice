# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Dom\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#-dontwarn com.av113030.**
#-dontwarn timber.**
#
#-dontwarn com.advertising_id_service.appclick.**
#-dontwarn com.squareup.okhttp.**
#-dontwarn okio.**
#-dontwarn retrofit.**
#-dontwarn rx.**
#
##-injars      in.jar
##-outjars     out.jar
##-libraryjars <java.home>/lib/rt.jar
##-printmapping out.map
#
#-keepparameternames
#-renamesourcefileattribute SourceFile
#-keepattributes Exceptions,InnerClasses,Signature,Deprecated, SourceFile,LineNumberTable,*Annotation*,EnclosingMethod
#
#-keep public class * {
#      public protected *;
#}
#
#-keepclassmembernames class * {
#    java.lang.Class class$(java.lang.String);
#    java.lang.Class class$(java.lang.String, boolean);
#}
#
#-keepclasseswithmembernames,includedescriptorclasses class * {
#    native <methods>;
#}
#
#-keepclassmembers,allowoptimization enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}
#
#-keepclassmembers class * implements java.io.Serializable {
#    static final long serialVersionUID;
#    private static final java.io.ObjectStreamField[] serialPersistentFields;
#    private void writeObject(java.io.ObjectOutputStream);
#    private void readObject(java.io.ObjectInputStream);
#    java.lang.Object writeReplace();
#    java.lang.Object readResolve();
#}
#
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#
#
#
#
#
#-keep class com.squareup.okhttp.** { *; }
#-keep class retrofit.** { *; }
#-keep interface com.squareup.okhttp.** { *; }
#
#
#-dontwarn com.av113030.**
#-dontwarn timber.**
#
#-dontwarn com.advertising_id_service.appclick.**
#-dontwarn com.squareup.okhttp.**
#-dontwarn okio.**
#-dontwarn retrofit.**
#-dontwarn rx.**
#
#-keepclasseswithmembers class * {
#    @retrofit.http.* <methods>;
#}
#
## If in your rest service interface you use methods with Callback argument.
#-keepattributes Exceptions
#
## If your rest service methods throw custom exceptions, because you've defined an ErrorHandler.
#-keepattributes Signature
#
## Also you must note that if you are using GSON for conversion from JSON to POJO representation, you must ignore those POJO classes from being obfuscated.
## Here include the POJO's that have you have created for mapping JSON response to POJO for example.
#
#### RxJava, RxAndroid (https://gist.github.com/kosiara/487868792fbd3214f9c9)
#-keep class rx.schedulers.Schedulers {
#    public static <methods>;
#}
#-keep class rx.schedulers.ImmediateScheduler {
#    public <methods>;
#}
#-keep class rx.schedulers.TestScheduler {
#    public <methods>;
#}
#-keep class rx.schedulers.Schedulers {
#    public static ** test();
#}
#-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
#    long producerIndex;
#    long consumerIndex;
#}
#-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
#    long producerNode;
#    long consumerNode;
#}
#-dontwarn sun.misc.Unsafe
#
#### LeakCanary
#-keep class org.eclipse.mat.** { *; }
#-keep class com.squareup.leakcanary.** { *; }
#
#### Stetho, Stetho Realm plugin
#-keep class com.facebook.stetho.** {
#  *;
#}
#-dontwarn com.facebook.stetho.**
#
#-keep class com.uphyca.** { *; }
#
#### Glide, Glide Okttp Module, Glide Transformations
#-keep public class * implements com.bumptech.glide.module.GlideModule
#-keep public class * extends com.bumptech.glide.module.AppGlideModule
#-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
#  **[] $VALUES;
#  public *;
#}
## -keepresourcexmlelements manifest/application/meta-data@value=GlideModule 3 For dexguard
#
#-dontwarn jp.co.cyberagent.android.gpuimage.**
#
#### Viewpager indicator
#-dontwarn com.viewpagerindicator.**
#
#### Support v7, Design
## http://stackoverflow.com/questions/29679177/cardview-shadow-not-appearing-in-lollipop-after-obfuscate-with-proguard/29698051
#-keep class android.support.v7.widget.RoundRectDrawable { *; }
#
#-keep public class android.support.v7.widget.** { *; }
#-keep public class android.support.v7.internal.widget.** { *; }
#-keep public class android.support.v7.internal.view.menu.** { *; }
#
#-keep public class * extends android.support.v4.view.ActionProvider {
#    public <init>(android.content.Context);
#}
#
#-dontwarn android.support.**
#-dontwarn android.support.design.**
#-keep class android.support.design.** { *; }
#-keep interface android.support.design.** { *; }
#-keep public class android.support.design.R$* { *; }
#
## https://github.com/Gericop/Android-Support-Preference-V7-Fix/blob/master/preference-v7/proguard-rules.pro
#-keepclassmembers class android.support.v7.preference.PreferenceGroupAdapter {
#    private ** mPreferenceLayouts;
#}
#-keepclassmembers class android.support.v7.preference.PreferenceGroupAdapter$PreferenceLayout {
#    private int resId;
#    private int widgetResId;
#}
#
## https://github.com/dandar3/android-support-animated-vector-drawable/blob/master/proguard-project.txt
##-keepclassmembers class android.support.graphics.drawable.VectorDrawableCompat$* {
##   void set*(***);
##   *** get*();
##}
#
#### Reactive Network
#-dontwarn com.github.pwittchen.reactivenetwork.library.ReactiveNetwork
#-dontwarn io.reactivex.functions.Function
#-dontwarn rx.internal.util.**
#-dontwarn sun.misc.Unsafe
#
#### Retrolambda
## as per official recommendation: https://github.com/evant/gradle-retrolambda#proguard
#-dontwarn java.lang.invoke.*
#
## Gson uses generic type information stored in a class file when working with fields. Proguard
## removes such information by default, so configure it to keep all of it.
#-keepattributes Signature
#
## For using GSON @Expose annotation
#-keepattributes *Annotation*
#
## Gson specific classes
#-keep class sun.misc.Unsafe { *; }
##-keep class com.google.gson.stream.** { *; }
#
## Application classes that will be serialized/deserialized over Gson
## -keep class com.google.gson.examples.android.model.** { *; }
#
## Prevent proguard from stripping interface information from TypeAdapterFactory,
## JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
#-keep class * implements com.google.gson.TypeAdapterFactory
#-keep class * implements com.google.gson.JsonSerializer
#-keep class * implements com.google.gson.JsonDeserializer
#
#### Retrofit 2
## Platform calls Class.forName on types which do not exist on Android to determine platform.
#-dontnote retrofit2.Platform
## Platform used when running on RoboVM on iOS. Will not be used at runtime.
#-dontnote retrofit2.Platform$IOS$MainThreadExecutor
## Platform used when running on Java 8 VMs. Will not be used at runtime.
#-dontwarn retrofit2.Platform$Java8
## Retain generic type information for use by reflection by converters and adapters.
#-keepattributes Signature
## Retain declared checked exceptions for use by a Proxy instance.
#-keepattributes Exceptions
### Retrofit 2.1.0
##-keepclasseswithmembers class * { #https://github.com/square/retrofit/issues/2129
##    @retrofit2.http.* <methods>;
##}
#-dontwarn retrofit2.adapter.rxjava.CompletableHelper$** # https://github.com/square/retrofit/issues/2034
##To use Single instead of Observable in Retrofit interface
#-keepnames class rx.Single
#
#### OkHttp3
#-dontwarn okhttp3.**
#-dontwarn okio.**
#-dontwarn javax.annotation.**
## A resource is loaded with a relative path so the package of this class must be preserved.
#-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
#
#### Picasso
#-dontwarn com.squareup.okhttp.**
#
#### SimpleXmlConverter
## Keep public classes and methods.
#-dontwarn com.bea.xml.stream.**
#-dontwarn org.simpleframework.xml.stream.**
#-keep class org.simpleframework.xml.**{ *; }
#-keepclassmembers,allowobfuscation class * {
#    @org.simpleframework.xml.* <fields>;
#    @org.simpleframework.xml.* <init>(...);
#}
#
## Findbugs Annotation
#-dontwarn edu.umd.cs.findbugs.annotations.SuppressFBWarnings
#
## Findbugs jsr305
#-dontwarn javax.annotation.**
#
#### greenDAO 3
#-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
#public static java.lang.String TABLENAME;
#}
#-keep class **$Properties
## If you do not use SQLCipher:
#-dontwarn org.greenrobot.greendao.database.**
## If you do not use RxJava:
#-dontwarn rx.**
#### greenDAO 2
#-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
#public static java.lang.String TABLENAME;
#}
#-keep class **$Properties
#
#### MoPub
#-keepclassmembers class com.mopub.** { public *; }
#-keep public class com.mopub.**
#-keep public class android.webkit.JavascriptInterface {}
#
#-keep class * extends com.mopub.mobileads.CustomEventBanner {}
#-keepclassmembers class com.mopub.mobileads.CustomEventBannerAdapter {!private !public !protected *;}
#-keep class * extends com.mopub.mobileads.CustomEventInterstitial {}
#-keep class * extends com.mopub.mobileads.CustomEventNative {}
### Android Advertiser ID
#-keep class com.google.android.gms.common.GooglePlayServicesUtil {*;}
#-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient {*;}
#-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info {*;}
#
#### Fabric
## In order to provide the most meaningful crash reports
#-keepattributes SourceFile,LineNumberTable
## If you're using custom Eception
#-keep public class * extends java.lang.Exception
#
#-keep class com.crashlytics.** { *; }
#-dontwarn com.crashlytics.**
#
#### Other
#-dontwarn com.google.errorprone.annotations.*
#
#### Exoplayer2
#-dontwarn com.google.android.exoplayer2.**
#
#### Android Architecture Components
## Ref: https://issuetracker.google.com/issues/62113696
## LifecycleObserver's empty constructor is considered to be unused by proguard
##-keepclassmembers class * implements android.arch.lifecycle.LifecycleObserver {
##    <init>(...);
##}
#-keep class * implements android.arch.lifecycle.LifecycleObserver {
#    <init>(...);
#}
#
## ViewModel's empty constructor is considered to be unused by proguard
#-keepclassmembers class * extends android.arch.lifecycle.ViewModel {
#    <init>(...);
#}
#
## keep Lifecycle State and Event enums values
#-keepclassmembers class android.arch.lifecycle.Lifecycle$State { *; }
#-keepclassmembers class android.arch.lifecycle.Lifecycle$Event { *; }
## keep methods annotated with @OnLifecycleEvent even if they seem to be unused
## (Mostly for LiveData.LifecycleBoundObserver.onStateChange(), but who knows)
#-keepclassmembers class * {
#    @android.arch.lifecycle.OnLifecycleEvent *;
#}
#
#### Carbon
#-keepclasseswithmembernames class * {
#    native <methods>;
#}
#-keep class android.support.v8.renderscript.** { *; }
#-dontwarn carbon.BR
#-dontwarn carbon.internal**
#-dontwarn carbon.databinding**
#-dontwarn java.lang.invoke**
### Databinding or library depends on databinding
#-dontwarn android.databinding.**
#-keep class android.databinding.** { *; }
#
#
#### Nothing for Butterknife 8, Realm, RxJava2, RxBinding, RxRelay, Dagger2, OneSignal, Google Play Services, Firebase, Facebook SDK, Room DB,