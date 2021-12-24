
import com.idayrus.layout.swan.*
import java.awt.Color
import java.awt.Dimension
import javax.swing.*

fun main() {
    //linearHorizontalForm()
    //linearVerticalForm()
    linearBothForm()
    frameForm()
}

private fun linearHorizontalForm() {
    SwingUtilities.invokeLater {

        val frame = JFrame()
        frame.isVisible = true
        frame.size = Dimension(700, 200)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setLocationRelativeTo(null)

        val panel = JPanel(LinearLayout(LinearLayout.HORIZONTAL))
        panel.background = Color.CYAN

        val lc = LinearConstraints()

        lc.margin = 10
        lc.marginEnd = 5
        lc.height = LinearConstraints.WRAP_CONTENT
        lc.gravity = LinearConstraints.BOTTOM
        panel.add(JButton("Bottom"), lc)

        lc.margin = 10
        lc.marginStart = 5
        lc.marginEnd = 5
        lc.height = LinearConstraints.WRAP_CONTENT
        lc.gravity = LinearConstraints.CENTER
        panel.add(JButton("Center"), lc)

        lc.margin = 10
        lc.marginStart = 5
        lc.marginEnd = 5
        lc.height = LinearConstraints.WRAP_CONTENT
        lc.gravity = LinearConstraints.TOP
        panel.add(JButton("Top"), lc)

        lc.margin = 10
        lc.marginStart = 5
        lc.marginEnd = 5
        lc.width = 0
        lc.weight = 1.0
        lc.height = LinearConstraints.MATCH_PARENT
        panel.add(JButton("A"), lc)

        lc.margin = 10
        lc.marginStart = 5
        lc.width = LinearConstraints.WRAP_CONTENT
        lc.height = LinearConstraints.MATCH_PARENT
        val x = JButton("B")
        x.isVisible = true
        panel.add(x, lc)

        frame.add(panel)
    }
}

private fun linearVerticalForm() {
    SwingUtilities.invokeLater {

        val frame = JFrame()
        frame.isVisible = true
        frame.size = Dimension(300, 400)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setLocationRelativeTo(null)

        val lc = LinearConstraints()
        val panel = JPanel(LinearLayout(LinearLayout.VERTICAL))
        panel.background = Color.CYAN

        lc.margin = 10
        lc.marginBottom = 5
        lc.width = 150
        lc.gravity = LinearConstraints.END
        lc.weight = 0.5
        lc.height = 0
        panel.add(JButton("A"), lc)

        lc.margin = 10
        lc.marginTop = 5
        lc.marginBottom = 5
        lc.width = LinearConstraints.MATCH_PARENT
        lc.weight = 0.5
        lc.height = 0
        panel.add(JButton("B"), lc)

        lc.margin = 10
        lc.marginTop = 5
        lc.marginBottom = 5
        lc.width = 80
        lc.height = LinearConstraints.WRAP_CONTENT
        lc.gravity = LinearConstraints.START
        panel.add(JButton("Start"), lc)

        lc.margin = 10
        lc.marginTop = 5
        lc.marginBottom = 5
        lc.width = 80
        lc.gravity = LinearConstraints.CENTER
        panel.add(JButton("Center"), lc)

        lc.margin = 10
        lc.marginTop = 5
        lc.marginBottom = 5
        lc.width = 80
        lc.gravity = LinearConstraints.END
        panel.add(JButton("End"), lc)

        lc.margin = 10
        lc.marginTop = 5
        lc.marginBottom = 5
        lc.width = 80
        lc.gravity = LinearConstraints.CENTER
        panel.add(JButton("Center"), lc)

        lc.margin = 10
        lc.marginTop = 5
        lc.marginBottom = 5
        lc.width = 80
        lc.gravity = LinearConstraints.START
        panel.add(JButton("Start"), lc)

        lc.margin = 10
        lc.marginTop = 5
        lc.width = LinearConstraints.MATCH_PARENT
        lc.height = LinearConstraints.MATCH_PARENT
        panel.add(JButton("#3"), lc)

        frame.add(panel)
    }
}

private fun linearBothForm() {
    SwingUtilities.invokeLater {

        val frame = JFrame()
        frame.title = "Linear Layout"
        frame.isVisible = true
        frame.size = Dimension(400, 350)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setLocationRelativeTo(null)

        val lc = LinearConstraints()
        val panel = JPanel(LinearLayout(LinearLayout.VERTICAL))

        val panel1 = JPanel(LinearLayout(LinearLayout.HORIZONTAL))

        lc.width = 0
        lc.weight = 0.5
        lc.margin = 10
        lc.marginEnd = 5
        panel1.add(JButton("Horizontal (weight 0.5)"), lc)

        lc.marginStart = 5
        lc.marginEnd = 10
        panel1.add(JButton("Horizontal (weight 0.5)"), lc)

        lc.reset()
        lc.width = LinearConstraints.MATCH_PARENT
        lc.height = LinearConstraints.WRAP_CONTENT
        panel.add(panel1, lc)

        lc.reset()
        lc.margin = 10
        lc.marginTop = 0
        lc.width = LinearConstraints.MATCH_PARENT
        lc.height = LinearConstraints.WRAP_CONTENT
        panel.add(JButton("Width Match Parent"), lc)

        lc.reset()
        lc.margin = 10
        lc.marginTop = 0
        lc.width = LinearConstraints.WRAP_CONTENT
        lc.height = LinearConstraints.WRAP_CONTENT
        lc.gravity = LinearConstraints.START
        panel.add(JButton("Gravity Start"), lc)

        lc.reset()
        lc.margin = 10
        lc.marginTop = 0
        lc.width = LinearConstraints.WRAP_CONTENT
        lc.height = LinearConstraints.WRAP_CONTENT
        lc.gravity = LinearConstraints.CENTER
        panel.add(JButton("Gravity Center"), lc)

        lc.reset()
        lc.margin = 10
        lc.marginTop = 0
        lc.width = LinearConstraints.WRAP_CONTENT
        lc.height = LinearConstraints.WRAP_CONTENT
        lc.gravity = LinearConstraints.END
        panel.add(JButton("Gravity End"), lc)

        lc.reset()
        lc.margin = 10
        lc.marginTop = 0
        lc.width = LinearConstraints.MATCH_PARENT
        lc.height = LinearConstraints.MATCH_PARENT
        panel.add(JButton("Width & Height Fill Remain"), lc)

        frame.add(panel)
    }
}

private fun frameForm() {
    SwingUtilities.invokeLater {

        val frame = JFrame()
        frame.title = "Frame Layout"
        frame.isVisible = true
        frame.size = Dimension(500, 250)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setLocationRelativeTo(null)

        val panel = JPanel(FrameLayout())
        val fc = FrameConstraints()

        fc.margin = 10
        fc.gravity = FrameConstraints.TOP_START
        panel.add(JButton("TOP_START"), fc)

        fc.gravity = FrameConstraints.TOP_CENTER
        panel.add(JButton("TOP_CENTER"), fc)

        fc.gravity = FrameConstraints.TOP_END
        panel.add(JButton("TOP_END"), fc)

        fc.gravity = FrameConstraints.CENTER_START
        panel.add(JButton("CENTER_START"), fc)

        fc.gravity = FrameConstraints.CENTER
        panel.add(JButton("CENTER_CENTER"), fc)

        fc.gravity = FrameConstraints.CENTER_END
        panel.add(JButton("CENTER_END"), fc)

        fc.gravity = FrameConstraints.BOTTOM_START
        panel.add(JButton("BOTTOM_START"), fc)

        fc.gravity = FrameConstraints.BOTTOM_CENTER
        panel.add(JButton("BOTTOM_CENTER"), fc)

        fc.gravity = FrameConstraints.BOTTOM_END
        panel.add(JButton("BOTTOM_END"), fc)

        frame.add(panel)
    }
}