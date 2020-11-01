## 一、JetPack组件Navigation介绍

**官方介绍：**[Navigation](https://developer.android.google.cn/topic/libraries/architecture/navigation/navigation-implementing) 是一个框架，用于在 Android 应用中的“目标”之间导航，该框架提供一致的 API，无论目标是作为 Fragment、Activity 还是其他组件实现。

## 二、JetPack组件Navigation基本使用

#### 1、导入依赖

```kotlin
dependencies {
  def nav_version = "2.3.1"

  // Java language implementation
  implementation "androidx.navigation:navigation-fragment:$nav_version"
  implementation "androidx.navigation:navigation-ui:$nav_version"

  // Kotlin
  implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
  implementation "androidx.navigation:navigation-ui-ktx:$nav_version"

  // Feature module Support
  implementation "androidx.navigation:navigation-dynamic-features-fragment:$nav_version"

  // Testing Navigation
  androidTestImplementation "androidx.navigation:navigation-testing:$nav_version"
}
```

#### 2、基本使用

做一个登录--注册--找回密码--用户协议(Activity)界面的案例

在登录界面，用户可以点击找回密码、跳转到找回密码界面；点击注册，跳转到注册界面

##### 2.1、页面创建

- BaseFragment.kt

```kotlin
/**
 * author : Iwen大大怪
 * create : 2020/11/1 22:14
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(getLayoutResId(), container, false)
        // 初始化View
        initView(rootView)

        return rootView
    }

    open fun initView(rootView: View) {

    }

    abstract fun getLayoutResId(): Int
}
```

- LoginFragment.kt

```kotlin
/**
 * 登录的Fragment
 * author : Iwen大大怪
 * create : 2020/11/1 22:04
 */
class LoginFragment: BaseFragment() {
    override fun getLayoutResId(): Int {
        return R.layout.fragment_login
    }
}
```

- ForgetFragment.kt

```kotlin
/**
 * 找回密码的Fragment
 * author : Iwen大大怪
 * create : 2020/11/1 22:04
 */
class ForgetFragment: BaseFragment() {
    override fun getLayoutResId(): Int {
        return R.layout.fragment_forget
    }
}
```

- RegisterFragment.kt

```kotlin
/**
 * 注册的Fragment
 * author : Iwen大大怪
 * create : 2020/11/1 22:04
 */
class RegisterFragment: BaseFragment() {
    override fun getLayoutResId(): Int {
        return R.layout.fragment_register
    }
}
```

##### 2.1、挖坑

- 主Activity代码

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
```

- 布局代码-----通过 XML 添加 NavHostFragment

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/fragment_container_view"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:defaultNavHost="true"
        app:navGraph="@navigation/nav_config" />

</FrameLayout>
```

```
请注意以下几点：

android:name 属性包含 NavHost 实现的类名称。
app:navGraph 属性将 NavHostFragment 与导航图相关联。导航图会在此 NavHostFragment 中指定用户可以导航到的所有目的地。
app:defaultNavHost="true" 属性确保您的 NavHostFragment 会拦截系统返回按钮。请注意，只能有一个默认 NavHost。如果同一布局（例如，双窗格布局）中有多个主机，请务必仅指定一个默认 NavHost。
```

- nav_config.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_config"
    app:startDestination="@id/login_fragment"
    tools:ignore="UnusedNavigation">

    <fragment
        android:id="@+id/login_fragment"
        android:name="com.example.iwen.jetpacknavigation.fragment.LoginFragment">

    </fragment>

    <fragment
        android:id="@+id/forget_fragment"
        android:name="com.example.iwen.jetpacknavigation.fragment.ForgetFragment">

    </fragment>

    <fragment
        android:id="@+id/register_fragment"
        android:name="com.example.iwen.jetpacknavigation.fragment.RegisterFragment">

    </fragment>

</navigation>
```

##### 2.3、实现跳转

- LoginFragment.kt

```kotlin
/**
 * 登录的Fragment
 * author : Iwen大大怪
 * create : 2020/11/1 22:04
 */
class LoginFragment: BaseFragment() {
    override fun getLayoutResId(): Int {
        return R.layout.fragment_login
    }

    override fun initView(rootView: View) {
        super.initView(rootView)
        rootView.toRegisterPage.setOnClickListener {
            // 跳转到注册界面
            findNavController().navigate(R.id.to_register_fragment)
        }

        rootView.toForgetPage.setOnClickListener {
            // 跳转到找回密码界面
            findNavController().navigate(R.id.to_forget_fragment)
        }
    }
}
```

- nav_config.xml

```xml
<fragment
        android:id="@+id/login_fragment"
        android:name="com.example.iwen.jetpacknavigation.fragment.LoginFragment">

        <action
            android:id="@+id/to_register_fragment"
            app:destination="@id/register_fragment" />

        <action
            android:id="@+id/to_forget_fragment"
            app:destination="@id/forget_fragment" />

    </fragment>
```

##### 2.4、跳转到其他Activity

- nav_config.xml

```xml
<fragment
        android:id="@+id/login_fragment"
        android:name="com.example.iwen.jetpacknavigation.fragment.LoginFragment">

        <action
            android:id="@+id/to_register_fragment"
            app:destination="@id/register_fragment" />

        <action
            android:id="@+id/to_forget_fragment"
            app:destination="@id/forget_fragment" />

        <action android:id="@+id/to_agreement_page"
            app:destination="@id/agreement_activity"/>

    </fragment>
```

- nav_config.xml

```xml
<activity
        android:id="@+id/agreement_activity"
        android:name="com.example.iwen.jetpacknavigation.AgreementActivity" />
```

- LoginFragment.kt

```kotlin
rootView.toAgreementPage.setOnClickListener {
            // 跳转到找回密码界面
            findNavController().navigate(R.id.to_agreement_page)
        }
```

#### 3、添加动画效果

##### 3.1、创建一个amin文件夹

##### 3.2、编写进入动画xml文件

- slide_from_right_to_left_in.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:duration="400"
        android:fromXDelta="100%p"
        android:toXDelta="0" />
</set>
```

- slide_from_right_to_left_out.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:duration="400"
        android:fromXDelta="0"
        android:toXDelta="-100%p" />
</set>
```

- 使用动画

```xml
<action
            android:id="@+id/to_register_fragment"
            app:destination="@id/register_fragment"
            app:enterAnim="@anim/slide_from_right_to_left_in"
            app:exitAnim="@anim/slide_from_right_to_left_out" />
```

##### 3.3、编写返回动画xml文件

- slide_from_left_to_right_in.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:duration="400"
        android:fromXDelta="-100%p"
        android:toXDelta="0" />
</set>
```

- slide_from_left_to_right_out.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:duration="400"
        android:fromXDelta="0"
        android:toXDelta="100%p" />
</set>
```

- 使用动画

```xml
<action
            android:id="@+id/to_register_fragment"
            app:destination="@id/register_fragment"
            app:popEnterAnim="@anim/slide_from_left_to_right_in"
            app:popExitAnim="@anim/slide_from_left_to_right_out"/>
```

##### 3.4、全局动画配置

- 修改主题

```xml
<resources>
    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
        <item name="android:windowAnimationStyle">@style/activityAnimation</item>
    </style>

    <style name="activityAnimation" parent="@android:style/Animation">
        <item name="android:activityOpenEnterAnimation">@anim/slide_from_right_to_left_in</item>
        <item name="android:activityOpenExitAnimation">@anim/slide_from_right_to_left_out</item>
        <item name="android:activityCloseEnterAnimation">@anim/slide_from_left_to_right_in</item>
        <item name="android:activityCloseExitAnimation">@anim/slide_from_left_to_right_out</item>
    </style>

</resources>
```

想了解更多，可以参考我的另外一篇文章

[Activity全局动画]:https://juejin.im/post/6890195769437978632/

#### 4、跳转与回退

##### 4.1、跳转

- kotlin

```kotlin
Fragment.findNavController()
View.findNavController()
Activity.findNavController(viewId: Int)
```

- java

```java
NavHostFragment.findNavController(Fragment)
Navigation.findNavController(Activity, @IdRes int viewId)
Navigation.findNavController(View)
```

##### 4.2、回退

- NavController.navigateUp()

- NavController.popBackStack()

