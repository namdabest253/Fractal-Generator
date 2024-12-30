import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FractalGui class that creates the user interaction menu
 */
public class FractalGui extends JFrame {
    /**
     * FractalSubject object
     */
    private final FractalSubject subject;
    /**
     * Default color of the bubbles
     */
    private final Color DEFAULTCOLOR = new Color(255,255,204);

    /**
     * FractalGui Constructor
     * @param subj FractalSubject
     */
    public FractalGui(FractalSubject subj) {
        this.subject = subj;

        setSize(400,500);
        setTitle("Bubbles and Bedlam Fractal Settings");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        getContentPane().add(mainPanel);

        JLabel depthSliderLabel = new JLabel("Recursion Depth");
        depthSliderLabel.setBounds(50, 20, 150, 20);
        mainPanel.add(depthSliderLabel);

        JSlider depthSlider = new JSlider(2, 8, 3);
        depthSlider.setBounds(25, 40, 325, 40);

        depthSlider.setPaintTrack(true);
        depthSlider.setPaintTicks(true);
        depthSlider.setPaintLabels(true);

        depthSlider.setMajorTickSpacing(1);
        mainPanel.add(depthSlider);

        JLabel childToParentRatioLabel = new JLabel("Child to parent ratio");
        childToParentRatioLabel.setBounds(50, 90, 150, 20);
        mainPanel.add(childToParentRatioLabel);

        JSlider cToPSlider = new JSlider(20, 70, 60);
        cToPSlider.setBounds(25, 110, 325, 40);

        cToPSlider.setPaintTrack(true);
        cToPSlider.setPaintTicks(true);
        cToPSlider.setPaintLabels(true);

        cToPSlider.setMajorTickSpacing(10);
        cToPSlider.setMinorTickSpacing(5);

        mainPanel.add(cToPSlider);

        JLabel childCountSliderLabel = new JLabel("Child count");
        childCountSliderLabel.setBounds(50, 160, 150, 20);
        mainPanel.add(childCountSliderLabel);

        JSlider childCountSlider = new JSlider(1, 11, 6);
        childCountSlider.setBounds(25, 180, 325, 40);

        childCountSlider.setPaintTrack(true);
        childCountSlider.setPaintTicks(true);
        childCountSlider.setPaintLabels(true);

        childCountSlider.setMajorTickSpacing(2);
        childCountSlider.setMinorTickSpacing(1);

        mainPanel.add(childCountSlider);

        JLabel bedlamLevelSliderLabel = new JLabel("Bedlam level");
        bedlamLevelSliderLabel.setBounds(50, 230, 150, 20);
        mainPanel.add(bedlamLevelSliderLabel);

        JSlider bLevelSlider = new JSlider(0, 4, 0);
        bLevelSlider.setBounds(25, 250, 325, 40);

        bLevelSlider.setPaintTrack(true);
        bLevelSlider.setPaintTicks(true);
        bLevelSlider.setPaintLabels(true);

        bLevelSlider.setMajorTickSpacing(1);

        mainPanel.add(bLevelSlider);

        JButton fColorButton = new JButton("Fractal Color:");
        fColorButton.setBounds(50, 310, 120, 20);
        mainPanel.add(fColorButton);

        JLabel fColorField = new JLabel();
        fColorField.setBounds(50, 330, 50, 20);
        fColorField.setOpaque(true);
        fColorField.setBackground(DEFAULTCOLOR);
        mainPanel.add(fColorField);

        JCheckBox checkBox = new JCheckBox("Random Pastels");
        checkBox.setBounds(50, 360, 150, 20);
        mainPanel.add(checkBox);

        JButton drawButton = new JButton("DRAW THE FRACTAL!");
        drawButton.setBounds(50, 400, 165, 30);
        mainPanel.add(drawButton);

        depthSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                subject.setUserChoices(depthSlider.getValue(), cToPSlider.getValue(), childCountSlider.getValue(), bLevelSlider.getValue(), fColorField.getBackground(), checkBox.isSelected());
            }
        });

        cToPSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                subject.setUserChoices(depthSlider.getValue(), cToPSlider.getValue(), childCountSlider.getValue(), bLevelSlider.getValue(), fColorField.getBackground(), checkBox.isSelected());
            }
        });

        childCountSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                subject.setUserChoices(depthSlider.getValue(), cToPSlider.getValue(), childCountSlider.getValue(), bLevelSlider.getValue(), fColorField.getBackground(), checkBox.isSelected());
            }
        });

        bLevelSlider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (!source.getValueIsAdjusting()) {
                subject.setUserChoices(depthSlider.getValue(), cToPSlider.getValue(), childCountSlider.getValue(), bLevelSlider.getValue(), fColorField.getBackground(), checkBox.isSelected());
            }
        });

        fColorButton.addActionListener(e -> subject.setUserChoices(depthSlider.getValue(), cToPSlider.getValue(), childCountSlider.getValue(), bLevelSlider.getValue(), fColorField.getBackground(), checkBox.isSelected()));

        checkBox.addActionListener(e -> subject.setUserChoices(depthSlider.getValue(), cToPSlider.getValue(), childCountSlider.getValue(), bLevelSlider.getValue(), fColorField.getBackground(), checkBox.isSelected()));

        drawButton.addActionListener(e -> subject.setUserChoices(depthSlider.getValue(), cToPSlider.getValue(), childCountSlider.getValue(), bLevelSlider.getValue(), fColorField.getBackground(), checkBox.isSelected()));

        fColorButton.addActionListener(e -> {
            JFrame colorFrame = new JFrame("Choose Fractal Color");
            colorFrame.setSize(650, 500);
            colorFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            JColorChooser colorChooser = new JColorChooser(Color.BLACK);
            colorChooser.setBounds(10, 10, 600, 400);
            colorFrame.add(colorChooser);

            JButton okButton = new JButton("OK");
            okButton.setBounds(200, 425, 75, 30);

            JButton cancelButton = new JButton("Cancel");
            cancelButton.setBounds(285, 425, 75, 30);

            JButton resetButton = new JButton("Reset");
            resetButton.setBounds(370, 425, 75, 30);

            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fColorField.setBackground(colorChooser.getColor());
                    subject.setUserChoices(depthSlider.getValue(), cToPSlider.getValue(), childCountSlider.getValue(), bLevelSlider.getValue(), fColorField.getBackground(), checkBox.isSelected());
                    colorFrame.dispose();
                }
            });

            cancelButton.addActionListener(e1 -> colorFrame.dispose());

            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fColorField.setBackground(DEFAULTCOLOR);
                    subject.setUserChoices(depthSlider.getValue(), cToPSlider.getValue(), childCountSlider.getValue(), bLevelSlider.getValue(), fColorField.getBackground(), checkBox.isSelected());
                    colorFrame.dispose();
                }
            });

            colorFrame.add(okButton);
            colorFrame.add(cancelButton);
            colorFrame.add(resetButton);

            colorFrame.setLayout(null);
            colorFrame.setVisible(true);
        });

        setVisible(true);
        subject.setUserChoices(depthSlider.getValue(), cToPSlider.getValue(), childCountSlider.getValue(), bLevelSlider.getValue(), fColorField.getBackground(), checkBox.isSelected());
    }
}
