# Swan Layout

[![](https://jitpack.io/v/idayrus/swan-layout.svg)](https://jitpack.io/#idayrus/swan-layout) ![GitHub closed issues](https://img.shields.io/github/issues-closed-raw/idayrus/swan-layout?label=Closed%20Issues) ![GitHub Repo stars](https://img.shields.io/github/stars/idayrus/swan-layout?style=social)

Bringing `LinearLayout` and `FrameLayout` from **Android** to **Java Swing**

---

## Install

Add to root `build.gradle`

```gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

Add to module `build.gradle`

```gradle
dependencies {
  implementation 'com.github.idayrus:swan-layout:1.00.000'
}
```

## Documentation

See full documentation at https://idayrus.github.io/swan-layout/

## Examples

#### LinearLayout

```kotlin
val panelVertical = JPanel(LinearLayout(LinearLayout.VERTICAL))
val panelHorizontal = JPanel(LinearLayout(LinearLayout.HORIZONTAL))
val lc = LinearConstraints()

lc.reset()
lc.width = 0
lc.weight = 0.5
lc.margin = 10
lc.marginEnd = 5
panelHorizontal.add(JButton("Horizontal (weight 0.5)"), lc)

lc.reset()
lc.width = 0
lc.weight = 0.5
lc.margin = 10
lc.marginStart = 5
panelHorizontal.add(JButton("Horizontal (weight 0.5)"), lc)

// Add panelHorizontal to panelVertical
lc.reset()
lc.width = LinearConstraints.MATCH_PARENT
lc.height = LinearConstraints.WRAP_CONTENT
panelVertical.add(panelHorizontal, lc)

lc.reset()
lc.margin = 10
lc.marginTop = 0
lc.width = LinearConstraints.WRAP_CONTENT
lc.height = LinearConstraints.WRAP_CONTENT
lc.gravity = LinearConstraints.CENTER
panelVertical.add(JButton("Gravity Center"), lc)

lc.reset()
lc.margin = 10
lc.marginTop = 0
lc.width = LinearConstraints.MATCH_PARENT
lc.height = LinearConstraints.MATCH_PARENT
panelVertical.add(JButton("Fill Remain"), lc)
```

#### FrameLayout

```kotlin
val panel = JPanel(FrameLayout())
val fc = FrameConstraints()

fc.margin = 10
fc.gravity = FrameConstraints.TOP_START
panel.add(JButton("TOP_START"), fc)

fc.gravity = FrameConstraints.CENTER
panel.add(JButton("CENTER_CENTER"), fc)

fc.gravity = FrameConstraints.BOTTOM_END
panel.add(JButton("BOTTOM_END"), fc)
```

## Screenshots

![LinearLayout](https://raw.githubusercontent.com/idayrus/swan-layout/master/etc/LinearLayout.png)

![FrameLayout](https://raw.githubusercontent.com/idayrus/swan-layout/master/etc/FrameLayout.png)
