
package battleskepp;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Main extends javax.swing.JFrame {

    Game game = Game.getInstance();
    
    // This sets the look and feel (from the substance library) on the GUI at runtime
    static {
       System.setProperty("swing.defaultlaf", "org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel");
    }
    
    public Main() {
        // Initiate the components
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        connectDialog = new javax.swing.JFrame();
        ipLabel = new javax.swing.JLabel();
        ipField = new javax.swing.JTextField();
        connectDialogButton = new javax.swing.JButton();
        waitingForOpponentDiag = new javax.swing.JFrame();
        waitingLbl = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        stopWaitingBtn = new javax.swing.JButton();
        menuToolbar = new javax.swing.JToolBar();
        createButton = new javax.swing.JButton();
        connectButton = new javax.swing.JButton();
        toolbarStatus = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        readyToggle = new javax.swing.JToggleButton();
        mainPanel = new javax.swing.JPanel();
        playerField = new PlayerFieldPanel();
        enemyField = new EnemyFieldPanel();

        connectDialog.setResizable(false);

        ipLabel.setText("IP:");

        ipField.setText("127.0.0.1");

        connectDialogButton.setText("Connect");
        connectDialogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectDialogButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout connectDialogLayout = new javax.swing.GroupLayout(connectDialog.getContentPane());
        connectDialog.getContentPane().setLayout(connectDialogLayout);
        connectDialogLayout.setHorizontalGroup(
            connectDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ipLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ipField)
                .addContainerGap())
            .addComponent(connectDialogButton, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
        );
        connectDialogLayout.setVerticalGroup(
            connectDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(connectDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ipLabel)
                    .addComponent(ipField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(connectDialogButton))
        );

        waitingForOpponentDiag.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        waitingForOpponentDiag.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        waitingForOpponentDiag.setResizable(false);

        waitingLbl.setText("Waitng for someone to join...");

        progressBar.setIndeterminate(true);

        stopWaitingBtn.setText("Cancel");
        stopWaitingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopWaitingBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout waitingForOpponentDiagLayout = new javax.swing.GroupLayout(waitingForOpponentDiag.getContentPane());
        waitingForOpponentDiag.getContentPane().setLayout(waitingForOpponentDiagLayout);
        waitingForOpponentDiagLayout.setHorizontalGroup(
            waitingForOpponentDiagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(waitingForOpponentDiagLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(waitingForOpponentDiagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(stopWaitingBtn)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(waitingLbl))
                .addContainerGap())
        );
        waitingForOpponentDiagLayout.setVerticalGroup(
            waitingForOpponentDiagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(waitingForOpponentDiagLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(waitingLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stopWaitingBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Battleskepp");
        setMinimumSize(new java.awt.Dimension(960, 30));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuToolbar.setFloatable(false);
        menuToolbar.setRollover(true);

        createButton.setText("Create new game");
        createButton.setFocusable(false);
        createButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        createButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });
        menuToolbar.add(createButton);

        connectButton.setText("Connect to game");
        connectButton.setFocusable(false);
        connectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        connectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });
        menuToolbar.add(connectButton);

        statusLabel.setText("Join/create a new game");

        javax.swing.GroupLayout toolbarStatusLayout = new javax.swing.GroupLayout(toolbarStatus);
        toolbarStatus.setLayout(toolbarStatusLayout);
        toolbarStatusLayout.setHorizontalGroup(
            toolbarStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, toolbarStatusLayout.createSequentialGroup()
                .addContainerGap(626, Short.MAX_VALUE)
                .addComponent(statusLabel)
                .addContainerGap())
        );
        toolbarStatusLayout.setVerticalGroup(
            toolbarStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, toolbarStatusLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(statusLabel))
        );

        menuToolbar.add(toolbarStatus);

        readyToggle.setText("Ready");
        readyToggle.setEnabled(false);
        readyToggle.setFocusable(false);
        readyToggle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        readyToggle.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        readyToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readyToggleActionPerformed(evt);
            }
        });
        menuToolbar.add(readyToggle);

        getContentPane().add(menuToolbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 30));

        mainPanel.setAlignmentX(0.0F);
        mainPanel.setAlignmentY(0.0F);
        mainPanel.setPreferredSize(new java.awt.Dimension(960, 407));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        playerField.setBackground(new java.awt.Color(255, 0, 0));
        playerField.setAlignmentX(0.0F);
        playerField.setAlignmentY(0.0F);
        playerField.setMaximumSize(new java.awt.Dimension(480, 0));
        playerField.setMinimumSize(new java.awt.Dimension(480, 0));
        playerField.setPreferredSize(new java.awt.Dimension(480, 407));

        javax.swing.GroupLayout playerFieldLayout = new javax.swing.GroupLayout(playerField);
        playerField.setLayout(playerFieldLayout);
        playerFieldLayout.setHorizontalGroup(
            playerFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        playerFieldLayout.setVerticalGroup(
            playerFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
        );

        mainPanel.add(playerField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, -1));

        enemyField.setBackground(new java.awt.Color(51, 255, 0));
        enemyField.setAlignmentX(0.0F);
        enemyField.setAlignmentY(0.0F);
        enemyField.setMaximumSize(new java.awt.Dimension(480, 0));
        enemyField.setMinimumSize(new java.awt.Dimension(480, 0));
        enemyField.setPreferredSize(new java.awt.Dimension(480, 407));

        javax.swing.GroupLayout enemyFieldLayout = new javax.swing.GroupLayout(enemyField);
        enemyField.setLayout(enemyFieldLayout);
        enemyFieldLayout.setHorizontalGroup(
            enemyFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        enemyFieldLayout.setVerticalGroup(
            enemyFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
        );

        game.createFields((PlayerFieldPanel)playerField, (EnemyFieldPanel)enemyField, this);

        mainPanel.add(enemyField, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 490, -1));

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 970, -1));

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-977)/2, (screenSize.height-470)/2, 977, 470);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * When the create button is pressed we should create a server and show the "Waiting for opponent" window
     * @param evt 
     */
    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        try {
            game.createGame();
            waitingForOpponentDiag.pack();
            
             // Get the size of the screen
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            // Determine the new location of the window
            int w = waitingForOpponentDiag.getSize().width;
            int h = waitingForOpponentDiag.getSize().height;
            int x = (dim.width-w)/2;
            int y = (dim.height-h)/2;

            // Move the window
            waitingForOpponentDiag.setLocation(x, y);
            
            waitingForOpponentDiag.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Could not create game");
        }
    }//GEN-LAST:event_createButtonActionPerformed

    /**
     * The user has entered an IP and tries to connect to it. We try this and start the game if succefully connected
     * @param evt 
     */
    private void connectDialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectDialogButtonActionPerformed
        createButton.setEnabled(false);
        connectButton.setEnabled(false);
        try {
            game.connect(ipField.getText());
            connectDialog.setVisible(false);
            readyToggle.setEnabled(true);
            setStatus("Opponent is not ready");
            game.checkShips();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Couldn't connect");
            createButton.setEnabled(true);
            connectButton.setEnabled(true);
        }
       
       
    }//GEN-LAST:event_connectDialogButtonActionPerformed

    /**
     * The user wants to connect to a game. Show the connect dialog to the user
     * @param evt 
     */
    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        
        connectDialog.pack();
        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Determine the new location of the window
        int w = connectDialog.getSize().width;
        int h = connectDialog.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;

        // Move the window
        connectDialog.setLocation(x, y);
        connectDialog.setVisible(true);
    }//GEN-LAST:event_connectButtonActionPerformed

    /**
     * Stop the server and hide the "Waiting for opponent" window if the host decides to cancel waiting.
     * @param evt 
     */
    private void stopWaitingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopWaitingBtnActionPerformed
        game.stop();
        waitingForOpponentDiag.setVisible(false);
        enableConnectCreateBtn(true);
    }//GEN-LAST:event_stopWaitingBtnActionPerformed

    /**
     * The player has tried to toggle the ready-toggle. If enough ships have been placed, notify readiness to the opponent, otherwise notify the player about missing ships.
     * @param evt 
     */
    private void readyToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readyToggleActionPerformed
        if(((PlayerFieldPanel) playerField).getSelectedCount() != 18){
            setStatus("Not enough ships selected");
            readyToggle.setSelected(false);
        } else {
           game.notifyReady();
        }
        
    }//GEN-LAST:event_readyToggleActionPerformed

    /**
     * This sets the text of the status label in the toolbar
     * @param status The status to be set
     */
    public void setStatus(String status){
        statusLabel.setText(status);
    }
    
    /**
     * Remove the "Waiting for player" dialog
     */
    public void hideWaitingForPlayer(){
        waitingForOpponentDiag.setVisible(false);
    }
    
    /**
     * Set the state of the ready-toggle. The user shouldn't be able to use it when not in game, or in shooting phase.
     * @param enable 
     */
    public void enableReadyToggle(boolean enable) {
        readyToggle.setEnabled(enable);
        readyToggle.setSelected(false);
    }
    
    /**
     * Enables/disables the connect/create buttons. The user shouldn't be able to use them while already in a game.
     * @param enable 
     */
    public void enableConnectCreateBtn(boolean enable){
        connectButton.setEnabled(enable);
        createButton.setEnabled(enable);
    }
    
    /**
     * Return if we are ready or not
     * @return if we are ready or not
     */
    public boolean weReady() {
        return readyToggle.isSelected();
    }

    public static void main(String args[]) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        // Create the EDT and set the GUI visible
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectButton;
    private javax.swing.JFrame connectDialog;
    private javax.swing.JButton connectDialogButton;
    private javax.swing.JButton createButton;
    private javax.swing.JPanel enemyField;
    private javax.swing.JTextField ipField;
    private javax.swing.JLabel ipLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JToolBar menuToolbar;
    private javax.swing.JPanel playerField;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JToggleButton readyToggle;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JButton stopWaitingBtn;
    private javax.swing.JPanel toolbarStatus;
    private javax.swing.JFrame waitingForOpponentDiag;
    private javax.swing.JLabel waitingLbl;
    // End of variables declaration//GEN-END:variables
}
