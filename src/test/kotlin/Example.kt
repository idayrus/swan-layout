import com.idayrus.layout.swan.FrameConstraints
import com.idayrus.layout.swan.FrameLayout
import com.idayrus.layout.swan.LinearConstraints
import com.idayrus.layout.swan.LinearLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.Point
import javax.swing.*

fun main() {
    SwingUtilities.invokeLater {
        // Form
        val frame = JFrame()
        frame.title = "Examples"
        frame.isVisible = true
        frame.size = Dimension(600, 650)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.location = Point(20, 20)

        /*
        * Constraints
         */
        val fc = FrameConstraints()
        val lc = LinearConstraints()


        /*
        * Linear layout #1 - Horizontal
         */
        val linearHorizontalPanel1 = JPanel(LinearLayout(LinearLayout.HORIZONTAL))
        linearHorizontalPanel1.background = Color.BLUE

        lc.reset()
        lc.width = LinearConstraints.WRAP_CONTENT
        lc.height = 50
        linearHorizontalPanel1.add(JButton("Height 50"), lc)

        lc.height = LinearConstraints.WRAP_CONTENT
        lc.gravity = LinearConstraints.BOTTOM
        linearHorizontalPanel1.add(JButton("Gravity Bottom"), lc)


        /*
        * Linear layout #2 - Horizontal
         */
        val linearHorizontalPanel2 = JPanel(LinearLayout(LinearLayout.HORIZONTAL))
        linearHorizontalPanel2.background = Color.RED

        lc.reset()
        lc.width = LinearConstraints.WRAP_CONTENT
        lc.height = LinearConstraints.MATCH_PARENT
        lc.margin = 10
        lc.marginEnd = 0
        linearHorizontalPanel2.add(JButton("Wrap Content"), lc)

        lc.marginEnd = 10
        linearHorizontalPanel2.add(JButton("Wrap Content"), lc)


        /*
        * Linear layout #3 - Horizontal
         */
        val linearHorizontalPanel3 = JPanel(LinearLayout(LinearLayout.HORIZONTAL))
        linearHorizontalPanel3.background = Color.PINK

        lc.reset()
        lc.width = 0
        lc.weight = 0.3
        lc.margin = 10
        lc.marginEnd = 0
        linearHorizontalPanel3.add(JButton("Weight 0.3"), lc)

        lc.reset()
        lc.width = 0
        lc.weight = 0.7
        lc.margin = 10
        linearHorizontalPanel3.add(JButton("Weight 0.7"), lc)


        /*
        * Linear layout #1 - Vertical
         */
        val linearVerticalPanel1 = JPanel(LinearLayout(LinearLayout.VERTICAL))
        linearVerticalPanel1.background = Color.GREEN

        lc.reset()
        lc.width = LinearConstraints.MATCH_PARENT
        lc.height = LinearConstraints.MATCH_PARENT
        lc.margin = 10
        lc.marginBottom = 0
        linearVerticalPanel1.add(linearHorizontalPanel3, lc)

        lc.reset()
        lc.margin = 10
        lc.marginBottom = 0
        linearVerticalPanel1.add(JButton("Wrap Content"), lc)

        lc.reset()
        lc.margin = 10
        lc.marginBottom = 0
        lc.gravity = LinearConstraints.CENTER
        linearVerticalPanel1.add(JButton("Center Gravity"), lc)

        lc.reset()
        lc.margin = 10
        lc.marginBottom = 0
        lc.gravity = LinearConstraints.END
        linearVerticalPanel1.add(JButton("End Gravity"), lc)

        lc.reset()
        lc.margin = 10
        lc.width = LinearConstraints.MATCH_PARENT
        linearVerticalPanel1.add(JButton("Match Parent"), lc)


        /*
        * Frame layout
         */
        val framePanel = JPanel(FrameLayout())
        framePanel.background = Color.LIGHT_GRAY

        fc.reset()
        fc.width = FrameConstraints.WRAP_CONTENT
        fc.height = FrameConstraints.WRAP_CONTENT
        fc.gravity = FrameConstraints.TOP_START
        fc.margin = 10
        framePanel.add(linearHorizontalPanel1, fc)

        fc.reset()
        fc.width = FrameConstraints.WRAP_CONTENT
        fc.height = FrameConstraints.WRAP_CONTENT
        fc.gravity = FrameConstraints.TOP_END
        fc.margin = 10
        framePanel.add(linearHorizontalPanel2, fc)

        fc.reset()
        fc.width = FrameConstraints.MATCH_PARENT
        fc.height = FrameConstraints.WRAP_CONTENT
        fc.gravity = FrameConstraints.BOTTOM_CENTER
        fc.margin = 10
        framePanel.add(linearVerticalPanel1, fc)

        fc.reset()
        fc.gravity = FrameConstraints.CENTER
        framePanel.add(JLabel("Inside Frame Layout"), fc)


        /*
        * Linear layout - Vertical
         */
        val linearVerticalPanel = JPanel(LinearLayout(LinearLayout.VERTICAL))
        linearVerticalPanel.background = Color.CYAN

        lc.reset()
        lc.height = 0
        lc.weight = 0.9
        lc.margin = 10
        lc.marginBottom = 0
        lc.width = LinearConstraints.MATCH_PARENT
        linearVerticalPanel.add(framePanel, lc)

        lc.reset()
        lc.height = 0
        lc.weight = 0.1
        lc.margin = 10
        lc.width = LinearConstraints.MATCH_PARENT
        linearVerticalPanel.add(JButton("Vertical Weight 0.1"), lc)

        // Add main frame
        frame.add(linearVerticalPanel)


        /*
        * Legend form
         */
        val legendText = """
            <html>
            <font color='white'>| </font><font color='#00ffff'>Linear Layout - Vertical - <i>linearVerticalPanel</i></font>
            <br />
            <font color='white'>|---- </font><font color='#c0c0c0'>Frame Layout - <i>framePanel</i></font>
            <br />
            <font color='white'>|----|---- </font><font color='#0000ff'>Linear Layout - Horizontal - <i>linearHorizontalPanel1</i></font>
            <br />
            <font color='white'>|----|----|---- JButton - <i>Height 50</i></font>
            <br />
            <font color='white'>|----|----|---- JButton - <i>Gravity Bottom</i></font>
            <br />
            <font color='white'>|----|---- </font><font color='#ff0000'>Linear Layout - Horizontal - <i>linearHorizontalPanel2</i></font>
            <br />
            <font color='white'>|----|----|---- JButton - <i>Wrap Content</i></font>
            <br />
            <font color='white'>|----|----|---- JButton - <i>Wrap Content</i></font>
            <br />
            <font color='white'>|----|---- JLabel - <i>Inside Frame Layout</i></font>
            <br />
            <font color='white'>|----|---- </font><font color='#00ff00'>Linear Layout - Vertical - <i>linearVerticalPanel1</i></font>
            <br />
            <font color='white'>|----|----|---- </font><font color='#ffafaf'>Linear Layout - Horizontal - <i>linearHorizontalPanel3</i></font>
            <br />
            <font color='white'>|----|----|----|---- JButton - <i>Weight 0.3</i></font>
            <br />
            <font color='white'>|----|----|----|---- JButton - <i>Weight 0.7</i></font>
            <br />
            <font color='white'>|----|----|---- JButton - <i>Wrap Content</i></font>
            <br />
            <font color='white'>|----|----|---- JButton - <i>Center Gravity</i></font>
            <br />
            <font color='white'>|----|----|---- JButton - <i>End Gravity</i></font>
            <br />
            <font color='white'>|----|----|---- JButton - <i>Match Parent</i></font>
            <br />
            <font color='white'>|----JButton - <i>Vertical Weight 0.1</i></font>
            </html>
        """.trimIndent()

        val legendFrame = JFrame()
        legendFrame.title = "Legends"
        legendFrame.isVisible = true
        legendFrame.size = Dimension(400, 400)
        legendFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        legendFrame.location = Point(frame.location.x + frame.size.width, frame.location.y)

        val legendPanel = JPanel(LinearLayout(LinearLayout.VERTICAL))
        legendPanel.background = Color.BLACK
        lc.reset()
        lc.margin = 10
        lc.width = LinearConstraints.MATCH_PARENT
        lc.height = LinearConstraints.WRAP_CONTENT
        legendPanel.add(JLabel(legendText), lc)
        legendFrame.add(legendPanel)

        // Focus on main frame
        frame.requestFocus()
    }
}