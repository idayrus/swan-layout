
import com.idayrus.layout.swan.*
import java.awt.Color
import java.awt.Dimension
import javax.swing.*

fun main() {
    horizontalForm()
    verticalForm()
    bothForm()
}

private fun horizontalForm() {
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

private fun verticalForm() {
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

private fun bothForm() {
    SwingUtilities.invokeLater {

        val frame = JFrame()
        frame.isVisible = true
        frame.size = Dimension(400, 600)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setLocationRelativeTo(null)

        val lc = LinearConstraints()
        val panel = JPanel(LinearLayout(LinearLayout.VERTICAL))
        panel.background = Color.CYAN

        val panel2 = JPanel(LinearLayout(LinearLayout.HORIZONTAL))
        panel2.background = Color.GREEN

        lc.width = 0
        lc.weight = 0.3
        lc.margin = 5
        panel2.add(JLabel("Name"), lc)
        lc.weight = 0.7
        panel2.add(JLabel(": Hehehe"), lc)

        val panel3 = JPanel(LinearLayout(LinearLayout.HORIZONTAL))
        panel3.background = Color.RED
        lc.weight = 0.3
        panel3.add(JLabel("Gender"), lc)
        lc.weight = 0.7
        panel3.add(JLabel(": Unknown"), lc)

        lc.margin = 0
        lc.gravity = LinearConstraints.END
        lc.width = LinearConstraints.WRAP_CONTENT
        lc.weight = 0.0
        lc.height = LinearConstraints.WRAP_CONTENT
        panel.add(panel2, lc)

        lc.width = LinearConstraints.MATCH_PARENT
        lc.height = LinearConstraints.MATCH_PARENT
        lc.weight = 0.0
        panel.add(panel3, lc)

        frame.add(panel)
    }
}