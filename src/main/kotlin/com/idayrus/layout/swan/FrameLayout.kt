package com.idayrus.layout.swan

import java.awt.Component
import java.awt.Container
import java.awt.Dimension
import java.awt.LayoutManager2
import javax.swing.JPanel

class FrameLayout: LayoutManager2 {

    private val components: MutableMap<Component, FrameConstraints> = mutableMapOf()
    private var maxWidth: Int = 0
    private var maxHeight: Int = 0
    private var wrapWidth: Int = 0
    private var wrapHeight: Int = 0
    private var x: Int = 0
    private var y: Int = 0

    override fun addLayoutComponent(name: String?, comp: Component?) { }

    override fun addLayoutComponent(comp: Component?, constraints: Any?) {
        if (comp != null) {
            if (constraints is FrameConstraints) {
                components[comp] = constraints.copy()
            } else {
                throw IllegalArgumentException("Not an instance of LinearConstraints")
            }
        }
    }

    override fun removeLayoutComponent(comp: Component?) {
        components.remove(comp)
    }

    override fun preferredLayoutSize(parent: Container): Dimension {
        return Dimension(parent.width, parent.height)
    }

    override fun minimumLayoutSize(parent: Container): Dimension {
        return Dimension(0, 0)
    }

    override fun maximumLayoutSize(parent: Container): Dimension {
        return Dimension(parent.width, parent.height)
    }

    override fun layoutContainer(parent: Container) {
        // Values
        val insets = parent.insets
        maxWidth = parent.width - (insets.left + insets.right)
        maxHeight = parent.height - (insets.top + insets.bottom)

        // Set bounds
        components.forEach { i ->
            // Value
            val component = i.key
            val constraints = i.value
            x = 0
            y = 0

            // Do layout for LinearLayout & FrameLayout
            if (component is JPanel) {
                if (component.layout is LinearLayout || component.layout is FrameLayout) {
                    component.doLayout()
                }
            }

            // Calculate width
            val width = when {
                constraints.width > 0 -> constraints.width
                constraints.width == LinearConstraints.MATCH_PARENT -> {
                    val w = maxWidth - (constraints.marginStart + constraints.marginEnd)
                    if (w >= component.preferredSize.width) {
                        w
                    } else {
                        component.preferredSize.width
                    }
                }
                else -> component.preferredSize.width
            }

            // Calculate height
            val height = when {
                constraints.height > 0 -> constraints.height
                constraints.height == LinearConstraints.MATCH_PARENT -> {
                    val w = maxHeight - (constraints.marginTop + constraints.marginBottom)
                    if (w >= component.preferredSize.height) {
                        w
                    } else {
                        component.preferredSize.height
                    }
                }
                else -> component.preferredSize.height
            }

            // Calculate position X (left, center, right)
            when(constraints.gravity) {
                FrameConstraints.TOP_START, FrameConstraints.CENTER_START, FrameConstraints.BOTTOM_START -> {
                    x += insets.left + constraints.marginStart
                }
                FrameConstraints.TOP_CENTER, FrameConstraints.CENTER_CENTER, FrameConstraints.BOTTOM_CENTER -> {
                    x += (maxWidth / 2) - (width / 2)
                }
                FrameConstraints.TOP_END, FrameConstraints.CENTER_END, FrameConstraints.BOTTOM_END -> {
                    x += maxWidth - (width + constraints.marginEnd)
                }
            }

            // Calculate position Y (top, center, bottom)
            when(constraints.gravity) {
                FrameConstraints.TOP_START, FrameConstraints.TOP_CENTER, FrameConstraints.TOP_END -> {
                    y += insets.top + constraints.marginTop
                }
                FrameConstraints.CENTER_START, FrameConstraints.CENTER_CENTER, FrameConstraints.CENTER_END -> {
                    y += (maxHeight / 2) - (height / 2)
                }
                FrameConstraints.BOTTOM_START, FrameConstraints.BOTTOM_CENTER, FrameConstraints.BOTTOM_END -> {
                    y += maxHeight - (height + constraints.marginBottom)
                }
            }

            // Set bounds
            component.setBounds(x, y, width, height)

            // Set wrap size
            val marginWidth = (width + constraints.marginStart + constraints.marginEnd)
            val marginHeight = (height + constraints.marginTop + constraints.marginBottom)
            if (width > wrapWidth) wrapWidth = marginWidth
            if (height > wrapHeight) wrapHeight += marginHeight
        }

        // Set wrap size
        if (maxWidth <= 0) {
            parent.size = Dimension(wrapWidth, parent.height)
            parent.preferredSize = parent.size
        }
        if (maxHeight <= 0) {
            parent.size = Dimension(parent.width, wrapHeight)
            parent.preferredSize = parent.size
        }
    }

    override fun getLayoutAlignmentX(target: Container?): Float {
        return Component.CENTER_ALIGNMENT
    }

    override fun getLayoutAlignmentY(target: Container?): Float {
        return Component.CENTER_ALIGNMENT
    }

    override fun invalidateLayout(target: Container?) {}
}