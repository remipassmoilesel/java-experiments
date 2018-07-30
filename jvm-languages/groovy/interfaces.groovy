import javax.swing.*
import java.awt.*
import java.awt.event.*

button = new JButton()
label = new JLabel()

// one implementation for all methods
displayMouseLocation = { label.setText("$it.x, $it.y") }
button.addMouseListener(displayMouseLocation as MouseListener)

// multiple methods
handleFocus = [
	focusGained : { msgLabel.setText("Good to see you!") },
	focusLost : { msgLabel.setText("Come back soon!") }
]

button.addFocusListener(handleFocus as FocusListener)
