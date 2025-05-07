package MyFirstGame;

import javax.swing.*;
import java.awt.*;

public class StartMenu extends JPanel {
    private JFrame frame;

    public StartMenu(JFrame frame) {
        this.frame = frame;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("CHICKEN INVADERS!!");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.PINK);

        // טען את התמונה לרקע
        ImageIcon image = new ImageIcon(getClass().getResource("/5532919.jpg"));
        JLabel background = new JLabel(image);

// סגנון סידור אנכי
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        JButton startButton1 = new JButton("Start Game");
        JButton instructionsButton1 = new JButton("Instructions");
        JButton creditsButton1 = new JButton("Credits");

        startButton1.setFont(new Font("Arial", Font.PLAIN, 20));
        instructionsButton1.setFont(new Font("Arial", Font.PLAIN, 20));
        creditsButton1.setFont(new Font("Arial", Font.PLAIN, 20));

        startButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionsButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsButton1.setAlignmentX(Component.CENTER_ALIGNMENT);

// מאזינים ללחיצות
        startButton1.addActionListener(e -> startGame());
        instructionsButton1.addActionListener(e -> showInstructions());
        creditsButton1.addActionListener(e -> showCredits());

// מוסיפים את הרכיבים לרקע
        background.add(Box.createVerticalStrut(80));
        background.add(title);
        background.add(Box.createVerticalStrut(40));
        background.add(startButton1);
        background.add(Box.createVerticalStrut(20));
        background.add(instructionsButton1);
        background.add(Box.createVerticalStrut(20));
        background.add(creditsButton1);

// מוסיפים את הרקע לפאנל הראשי
        this.setLayout(new BorderLayout());
        this.add(background, BorderLayout.CENTER);
    }

    public void startGame() {
        frame.getContentPane().removeAll();
        MainPanel gamePanel = new MainPanel(0, 0, Main.WINDOW_WIDTH2, Main.WINDOW_HEIGHT2);
        frame.add(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        frame.revalidate();
        frame.repaint();
    }

    private void showInstructions() {
        frame.getContentPane().removeAll();

        ImageIcon image = new ImageIcon(getClass().getResource("/5532919.jpg"));
        JLabel background = new JLabel(image);
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));



        JLabel label = new JLabel("INSTRUCTIONS:");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground(Color.BLUE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] instructions = {
                "← : Move Left",
                "→ : Move Right",
                "↑ : Move Up",
                "↓ : Move Down" ,
                "SPACE : Shoot",
                "",
                " ",
                "השנה היא 3047. גלים אינסופיים של תרנגולות שעברו מוטציה בחלל פתחו בפלישה בקנה מידה עולמי לכדור הארץ.",
                "ערים שוכבות בהריסות, לוויינים הושמדו, והאנושות על סף הכחדה...",
                "אתה הטייס האחרון של טייסת ההגנה הגלקטית.",
                "המשימה שלך: להילחם בדרכך דרך שלושה אזורים קטלניים עמוק בשטח האויב",
                "לשרוד התקפות בלתי פוסקות, ולהתמודד עם המוח שמאחורי הכל",
                "הבוס האולטימטיבי שאף אחד לא ראה מעולם",

                "עתיד האנושות מונח על כתפיך.",

                "היכנס לקרב. אל תחזור בלי ניצחון."
        };

        background.add(Box.createVerticalStrut(40));
        background.add(label);
        background.add(Box.createVerticalStrut(30));

        for (int i = 0; i < instructions.length; i++) {
            JLabel instructionLabel = new JLabel(instructions[i]);
            instructionLabel.setFont(new Font("Arial", Font.PLAIN, 25));
            instructionLabel.setForeground(Color.BLUE);
            instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            background.add(instructionLabel);
        }

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 20));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> returnToMenu());

        background.add(Box.createVerticalStrut(30));
        background.add(backButton);

        frame.add(background);
        frame.revalidate();
        frame.repaint();
    }

    private void showCredits() {
        frame.getContentPane().removeAll();

        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/5532919.jpg"));
        JLabel background = new JLabel(bgIcon);
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));

        JLabel creditsLabel = new JLabel("Created by Amit Swisa and Yair Siboni :)");
        creditsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        creditsLabel.setForeground(Color.pink);
        creditsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 20));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> returnToMenu());

        background.add(Box.createVerticalStrut(100));
        background.add(creditsLabel);
        background.add(Box.createVerticalStrut(30));
        background.add(backButton);

        frame.add(background);
        frame.revalidate();
        frame.repaint();
    }

    private void returnToMenu() {
        frame.getContentPane().removeAll();
        frame.add(new StartMenu(frame));
        frame.revalidate();
        frame.repaint();
    }
}


