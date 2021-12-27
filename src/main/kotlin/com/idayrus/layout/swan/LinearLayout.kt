package com.idayrus.layout.swan

import java.awt.Component
import java.awt.Container
import java.awt.Dimension
import java.awt.LayoutManager2
import javax.swing.JPanel

class LinearLayout(private val orientation: String) : LayoutManager2 {

    private val components: MutableMap<Component, LinearConstraints> = mutableMapOf()
    private var maxWidth: Int = 0
    private var maxHeight: Int = 0
    private var wrapWidth: Int = 0
    private var wrapHeight: Int = 0
    private var remainWidth: Int = 0
    private var remainHeight: Int = 0
    private var x: Int = 0
    private var y: Int = 0

    companion object {
        const val VERTICAL = "vertical"
        const val HORIZONTAL = "horizontal"
    }

    override fun addLayoutComponent(name: String, comp: Component) {}

    override fun addLayoutComponent(comp: Component?, constraints: Any?) {
        if (comp != null) {
            if (constraints is LinearConstraints) {
                components[comp] = constraints.clone()
            } else {
                throw IllegalArgumentException("Not an instance of LinearConstraints")
            }
        }
    }

    override fun removeLayoutComponent(comp: Component) {
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
        remainWidth = maxWidth
        remainHeight = maxHeight
        x = 0
        y = insets.top

        // Do layout for LinearLayout & FrameLayout
        components.forEach { i ->
            if (i.key is JPanel) {
                val layout = (i.key as JPanel).layout
                if (layout is LinearLayout || layout is FrameLayout) {
                    i.key.doLayout()
                }
            }
        }

        // Components container
        val weightComponents: MutableMap<Component, LinearConstraints> = mutableMapOf()
        val matchComponents: MutableMap<Component, LinearConstraints> = mutableMapOf()
        val normalComponents: MutableMap<Component, LinearConstraints> = mutableMapOf()

        // Calculate component's size
        when (orientation) {
            VERTICAL -> {
                // Group components
                components.forEach { i ->
                    val c = i.value
                    when {
                        (c.weight > 0 && c.height == 0) -> weightComponents[i.key] = i.value
                        (c.height == LinearConstraints.MATCH_PARENT) -> matchComponents[i.key] = i.value
                        else -> normalComponents[i.key] = i.value
                    }
                }
                if (weightComponents.isNotEmpty()) {
                    matchComponents.forEach { i ->
                        i.value.height = LinearConstraints.WRAP_CONTENT // Override match parent if contain weight
                    }
                }

                // Calculate size
                calculateVerticalComponentSize(normalComponents)
                calculateVerticalComponentSize(matchComponents)
                calculateVerticalComponentSize(weightComponents, true)

                // Set bounds
                setVerticalComponentBounds()
            }
            HORIZONTAL -> {
                // Group components
                components.forEach { i ->
                    val c = i.value
                    when {
                        (c.weight > 0 && c.width == 0) -> weightComponents[i.key] = i.value
                        (c.width == LinearConstraints.MATCH_PARENT) -> matchComponents[i.key] = i.value
                        else -> normalComponents[i.key] = i.value
                    }
                }
                if (weightComponents.isNotEmpty()) {
                    matchComponents.forEach { i ->
                        i.value.width = LinearConstraints.WRAP_CONTENT // Override match parent if contain weight
                    }
                }

                // Calculate size
                calculateHorizontalComponentSize(normalComponents)
                calculateHorizontalComponentSize(matchComponents)
                calculateHorizontalComponentSize(weightComponents, true)

                // Set bounds
                setHorizontalComponentBounds()
            }
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

    private fun calculateVerticalComponentSize(
        comps: MutableMap<Component, LinearConstraints>,
        isWeight: Boolean = false
    ) {
        comps.forEach { i ->
            // Value
            val component = i.key
            val constraints = i.value

            // Check visibility
            if (component.isVisible) {
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
                        val h = remainHeight - (constraints.marginTop + constraints.marginBottom)
                        if (h >= component.preferredSize.height) {
                            h
                        } else {
                            component.preferredSize.height
                        }
                    }
                    constraints.height == 0 && constraints.weight > 0 -> {
                        val h =
                            (remainHeight * constraints.weight).toInt() - (constraints.marginTop + constraints.marginBottom)
                        if (h >= component.preferredSize.height) {
                            h
                        } else {
                            component.preferredSize.height
                        }
                    }
                    else -> component.preferredSize.height
                }

                // Set wrap size
                val marginWidth = (width + constraints.marginStart + constraints.marginEnd)
                val marginHeight = (height + constraints.marginTop + constraints.marginBottom)
                if (marginWidth > wrapWidth) {
                    wrapWidth = marginWidth
                }
                wrapHeight += marginHeight

                // Set Size
                constraints.calculatedWidth = width
                constraints.calculatedHeight = height
                components[component] = constraints

                // Calculate remain height
                if (!isWeight) {
                    remainHeight -= marginHeight
                }
            }
        }
    }

    private fun calculateHorizontalComponentSize(
        comps: MutableMap<Component, LinearConstraints>,
        isWeight: Boolean = false
    ) {
        comps.forEach { i ->
            // Value
            val component = i.key
            val constraints = i.value

            // Check visibility
            if (component.isVisible) {
                // Calculate width
                val width = when {
                    constraints.width > 0 -> constraints.width
                    constraints.width == LinearConstraints.MATCH_PARENT -> {
                        val h = remainWidth - (constraints.marginStart + constraints.marginEnd)
                        if (h >= component.preferredSize.width) {
                            h
                        } else {
                            component.preferredSize.width
                        }
                    }
                    constraints.width == 0 && constraints.weight > 0 -> {
                        val h =
                            (remainWidth * constraints.weight).toInt() - (constraints.marginStart + constraints.marginEnd)
                        if (h >= component.preferredSize.width) {
                            h
                        } else {
                            component.preferredSize.width
                        }
                    }
                    else -> component.preferredSize.width
                }

                // Calculate width
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

                // Set wrap size
                val marginWidth = (width + constraints.marginStart + constraints.marginEnd)
                val marginHeight = (height + constraints.marginTop + constraints.marginBottom)
                wrapWidth += marginWidth
                if (marginHeight > wrapHeight) {
                    wrapHeight = marginHeight
                }

                // Set Size
                constraints.calculatedWidth = width
                constraints.calculatedHeight = height
                components[component] = constraints

                // Calculate remain width
                if (!isWeight) {
                    remainWidth -= marginWidth
                }
            }
        }
    }

    private fun setVerticalComponentBounds() {
        components.forEach { i ->
            // Value
            val component = i.key
            val constraints = i.value

            // Check visibility
            if (component.isVisible) {
                // Size
                val width = constraints.calculatedWidth
                val height = constraints.calculatedHeight

                // Calculate position
                y += constraints.marginTop
                x = if (constraints.width != LinearConstraints.MATCH_PARENT) {
                    when (constraints.gravity) {
                        LinearConstraints.START -> constraints.marginStart
                        LinearConstraints.CENTER -> {
                            (maxWidth / 2) - (width / 2)
                        }
                        LinearConstraints.END -> {
                            maxWidth - (width + constraints.marginEnd)
                        }
                        else -> constraints.marginStart
                    }
                } else {
                    constraints.marginStart
                }

                // Set bounds
                component.setBounds(x, y, width, height)

                // Add margin bottom
                y += height + constraints.marginBottom
            }
        }
    }

    private fun setHorizontalComponentBounds() {
        components.forEach { i ->
            // Value
            val component = i.key
            val constraints = i.value

            // Check visibility
            if (component.isVisible) {
                // Size
                val width = constraints.calculatedWidth
                val height = constraints.calculatedHeight

                // Calculate position
                x += constraints.marginStart
                y = if (constraints.height != LinearConstraints.MATCH_PARENT) {
                    when (constraints.gravity) {
                        LinearConstraints.TOP -> constraints.marginTop
                        LinearConstraints.CENTER -> {
                            (maxHeight / 2) - (height / 2)
                        }
                        LinearConstraints.BOTTOM -> {
                            maxHeight - (height + constraints.marginBottom)
                        }
                        else -> constraints.marginTop
                    }
                } else {
                    constraints.marginTop
                }

                // Set bounds
                component.setBounds(x, y, width, height)

                // Add margin bottom
                x += width + constraints.marginEnd
            }
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
