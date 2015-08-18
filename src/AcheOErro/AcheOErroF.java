/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AcheOErro;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author DANIEL
 */
public class AcheOErroF extends javax.swing.JFrame {

    int s = 1, m = 0, cont = 0, aleatorio = 1;
    String z, s1, s2, p = "play";
    int numeroTmp = 0, comp = 0, points = 0;
    ActionListener action;
    Timer t;

    /**
     * Creates new form AcheOErro
     */
    public AcheOErroF() {
        initComponents();
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(40, 215, 188));
        setIconImage(new ImageIcon("Res/logoBarra.png").getImage());
        jLabel20.setIcon(new javax.swing.ImageIcon("Res/JogosExtras/AcheOErro/pause.png"));
        jPanel2.setBackground(new Color(40, 215, 188));
        Tempo();
    }

    public void Aleatorio() {
        Imagem i = new Imagem("" + aleatorio, "facil");
        if (aleatorio < 15) {
            aleatorio++;
        } else {
            aleatorio = 1;
        }
        numeroTmp = i.getNumeroTmp();
        s1 = i.getS();
        s2 = i.getS2();
        if (numeroTmp == 1) {
            jLabel1.setIcon(new javax.swing.ImageIcon(s2));
            jLabel3.setIcon(new javax.swing.ImageIcon(s1));
            jLabel4.setIcon(new javax.swing.ImageIcon(s1));
            jLabel6.setIcon(new javax.swing.ImageIcon(s1));
            jLabel5.setIcon(new javax.swing.ImageIcon(s1));
            jLabel7.setIcon(new javax.swing.ImageIcon(s1));
            jLabel10.setIcon(new javax.swing.ImageIcon(s1));
            jLabel14.setIcon(new javax.swing.ImageIcon(s1));
            jLabel13.setIcon(new javax.swing.ImageIcon(s1));
        }
        if (numeroTmp == 2) {
            jLabel3.setIcon(new javax.swing.ImageIcon(s2));
            jLabel1.setIcon(new javax.swing.ImageIcon(s1));
            jLabel4.setIcon(new javax.swing.ImageIcon(s1));
            jLabel6.setIcon(new javax.swing.ImageIcon(s1));
            jLabel5.setIcon(new javax.swing.ImageIcon(s1));
            jLabel7.setIcon(new javax.swing.ImageIcon(s1));
            jLabel10.setIcon(new javax.swing.ImageIcon(s1));
            jLabel14.setIcon(new javax.swing.ImageIcon(s1));
            jLabel13.setIcon(new javax.swing.ImageIcon(s1));
        }
        if (numeroTmp == 3) {
            jLabel4.setIcon(new javax.swing.ImageIcon(s2));
            jLabel3.setIcon(new javax.swing.ImageIcon(s1));
            jLabel1.setIcon(new javax.swing.ImageIcon(s1));
            jLabel6.setIcon(new javax.swing.ImageIcon(s1));
            jLabel5.setIcon(new javax.swing.ImageIcon(s1));
            jLabel7.setIcon(new javax.swing.ImageIcon(s1));
            jLabel10.setIcon(new javax.swing.ImageIcon(s1));
            jLabel14.setIcon(new javax.swing.ImageIcon(s1));
            jLabel13.setIcon(new javax.swing.ImageIcon(s1));
        }
        if (numeroTmp == 4) {
            jLabel5.setIcon(new javax.swing.ImageIcon(s2));
            jLabel3.setIcon(new javax.swing.ImageIcon(s1));
            jLabel4.setIcon(new javax.swing.ImageIcon(s1));
            jLabel1.setIcon(new javax.swing.ImageIcon(s1));
            jLabel6.setIcon(new javax.swing.ImageIcon(s1));
            jLabel7.setIcon(new javax.swing.ImageIcon(s1));
            jLabel10.setIcon(new javax.swing.ImageIcon(s1));
            jLabel14.setIcon(new javax.swing.ImageIcon(s1));
            jLabel13.setIcon(new javax.swing.ImageIcon(s1));
        }
        if (numeroTmp == 5) {
            jLabel6.setIcon(new javax.swing.ImageIcon(s2));
            jLabel3.setIcon(new javax.swing.ImageIcon(s1));
            jLabel4.setIcon(new javax.swing.ImageIcon(s1));
            jLabel1.setIcon(new javax.swing.ImageIcon(s1));
            jLabel5.setIcon(new javax.swing.ImageIcon(s1));
            jLabel7.setIcon(new javax.swing.ImageIcon(s1));
            jLabel10.setIcon(new javax.swing.ImageIcon(s1));
            jLabel14.setIcon(new javax.swing.ImageIcon(s1));
            jLabel13.setIcon(new javax.swing.ImageIcon(s1));
        }
        if (numeroTmp == 6) {
            jLabel7.setIcon(new javax.swing.ImageIcon(s2));
            jLabel3.setIcon(new javax.swing.ImageIcon(s1));
            jLabel4.setIcon(new javax.swing.ImageIcon(s1));
            jLabel1.setIcon(new javax.swing.ImageIcon(s1));
            jLabel5.setIcon(new javax.swing.ImageIcon(s1));
            jLabel6.setIcon(new javax.swing.ImageIcon(s1));
            jLabel10.setIcon(new javax.swing.ImageIcon(s1));
            jLabel14.setIcon(new javax.swing.ImageIcon(s1));
            jLabel13.setIcon(new javax.swing.ImageIcon(s1));
        }
        if (numeroTmp == 7) {
            jLabel10.setIcon(new javax.swing.ImageIcon(s2));
            jLabel3.setIcon(new javax.swing.ImageIcon(s1));
            jLabel4.setIcon(new javax.swing.ImageIcon(s1));
            jLabel6.setIcon(new javax.swing.ImageIcon(s1));
            jLabel5.setIcon(new javax.swing.ImageIcon(s1));
            jLabel1.setIcon(new javax.swing.ImageIcon(s1));
            jLabel7.setIcon(new javax.swing.ImageIcon(s1));
            jLabel14.setIcon(new javax.swing.ImageIcon(s1));
            jLabel13.setIcon(new javax.swing.ImageIcon(s1));
        }
        if (numeroTmp == 8) {
            jLabel14.setIcon(new javax.swing.ImageIcon(s2));
            jLabel3.setIcon(new javax.swing.ImageIcon(s1));
            jLabel4.setIcon(new javax.swing.ImageIcon(s1));
            jLabel6.setIcon(new javax.swing.ImageIcon(s1));
            jLabel5.setIcon(new javax.swing.ImageIcon(s1));
            jLabel7.setIcon(new javax.swing.ImageIcon(s1));
            jLabel1.setIcon(new javax.swing.ImageIcon(s1));
            jLabel10.setIcon(new javax.swing.ImageIcon(s1));
            jLabel13.setIcon(new javax.swing.ImageIcon(s1));
        }
        if (numeroTmp == 9) {
            jLabel13.setIcon(new javax.swing.ImageIcon(s2));
            jLabel3.setIcon(new javax.swing.ImageIcon(s1));
            jLabel4.setIcon(new javax.swing.ImageIcon(s1));
            jLabel6.setIcon(new javax.swing.ImageIcon(s1));
            jLabel5.setIcon(new javax.swing.ImageIcon(s1));
            jLabel7.setIcon(new javax.swing.ImageIcon(s1));
            jLabel1.setIcon(new javax.swing.ImageIcon(s1));
            jLabel14.setIcon(new javax.swing.ImageIcon(s1));
            jLabel10.setIcon(new javax.swing.ImageIcon(s1));
        }
    }

    public void Tempo() {
        action = new ActionListener() {
            public void actionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent e) {
                if (cont == 0) {
                    Aleatorio();
                    cont++;
                }
                if (s == 60) {
                    m++;
                    s = 0;
                }
                z = "" + s;
                if (z.length() == 1) {
                    jLabel18.setText("0" + s);
                } else {
                    jLabel18.setText("" + s);
                }
                z = "" + m;
                if (z.length() == 1) {
                    jLabel17.setText("0" + m + " :");
                } else {
                    jLabel17.setText("" + m + " :");
                }
                s++;
            }
        };
        t = new Timer(1000, action);
        t.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ache o Erro!");
        setUndecorated(true);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(55, 215, 188));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel3.setLayout(null);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Parar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(140, 20, 75, 23);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 0, 102), null, new java.awt.Color(244, 0, 114)), javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 102))));

        jLabel18.setFont(new java.awt.Font("Arial Narrow", 1, 23)); // NOI18N
        jLabel18.setText("00");

        jLabel17.setFont(new java.awt.Font("Arial Narrow", 1, 23)); // NOI18N
        jLabel17.setText("00 :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel1);
        jPanel1.setBounds(264, 12, 87, 34);

        jLabel20.setIcon(null);
        jLabel20.setToolTipText("Pausar o jogo");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel20);
        jLabel20.setBounds(217, 12, 43, 34);

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 26)); // NOI18N
        jLabel19.setText("Pontos: 0");
        jPanel3.add(jLabel19);
        jLabel19.setBounds(1, 12, 140, 33);

        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 0, 0), null, new java.awt.Color(204, 204, 204)), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(204, 204, 204), null, null)));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AcheOErro/Images/AcheoerroB.png"))); // NOI18N
        jLabel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AcheOErro/Images/AcheoerroB.png"))); // NOI18N
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AcheOErro/Images/AcheoerroB.png"))); // NOI18N
        jLabel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AcheOErro/Images/AcheoerroB.png"))); // NOI18N
        jLabel13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AcheOErro/Images/AcheoerroB.png"))); // NOI18N
        jLabel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AcheOErro/Images/AcheoerroB.png"))); // NOI18N
        jLabel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AcheOErro/Images/AcheoerroB.png"))); // NOI18N
        jLabel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AcheOErro/Images/AcheoerroB.png"))); // NOI18N
        jLabel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AcheOErro/Images/AcheoerroB.png"))); // NOI18N
        jLabel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel10))
                .addGap(108, 108, 108))
        );

        jPanel3.add(jPanel2);
        jPanel2.setBounds(11, 64, 340, 323);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        comp = 1;
        if (comp == numeroTmp) {
            JOptionPane.showMessageDialog(null, "Parabéns, você acertou!");
            points++;
            jLabel19.setText("Pontos: " + points);
            cont = 0;
        } else {
            t.stop();
            JOptionPane.showMessageDialog(null, "Você errou, você conseguiu: " + points + " pontos.\nTempo: " + jLabel17.getText() + " " + jLabel18.getText());
            PrincipalAcheOErro p = new PrincipalAcheOErro();
            p.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        comp = 2;
        if (comp == numeroTmp) {
            JOptionPane.showMessageDialog(null, "Parabéns, você acertou!");
            points++;
            jLabel19.setText("Pontos: " + points);
            cont = 0;
        } else {
            t.stop();
            JOptionPane.showMessageDialog(null, "Você errou, você conseguiu: " + points + " pontos.\nTempo: " + jLabel17.getText() + " " + jLabel18.getText());
            PrincipalAcheOErro p = new PrincipalAcheOErro();
            p.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        comp = 3;
        if (comp == numeroTmp) {
            JOptionPane.showMessageDialog(null, "Parabéns, você acertou!");
            points++;
            jLabel19.setText("Pontos: " + points);
            cont = 0;
        } else {
            t.stop();
            JOptionPane.showMessageDialog(null, "Você errou, você conseguiu: " + points + " pontos.\nTempo: " + jLabel17.getText() + " " + jLabel18.getText());
            PrincipalAcheOErro p = new PrincipalAcheOErro();
            p.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        comp = 4;
        if (comp == numeroTmp) {
            JOptionPane.showMessageDialog(null, "Parabéns, você acertou!");
            points++;
            jLabel19.setText("Pontos: " + points);
            cont = 0;
        } else {
            t.stop();
            JOptionPane.showMessageDialog(null, "Você errou, você conseguiu: " + points + " pontos.\nTempo: " + jLabel17.getText() + " " + jLabel18.getText());
            PrincipalAcheOErro p = new PrincipalAcheOErro();
            p.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        comp = 5;
        if (comp == numeroTmp) {
            JOptionPane.showMessageDialog(null, "Parabéns, você acertou!");
            points++;
            jLabel19.setText("Pontos: " + points);
            cont = 0;
        } else {
            t.stop();
            JOptionPane.showMessageDialog(null, "Você errou, você conseguiu: " + points + " pontos.\nTempo: " + jLabel17.getText() + " " + jLabel18.getText());
            PrincipalAcheOErro p = new PrincipalAcheOErro();
            p.setVisible(true);
            t.stop();
            dispose();
        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        comp = 6;
        if (comp == numeroTmp) {
            JOptionPane.showMessageDialog(null, "Parabéns, você acertou!");
            points++;
            jLabel19.setText("Pontos: " + points);
            cont = 0;
        } else {
            t.stop();
            JOptionPane.showMessageDialog(null, "Você errou, você conseguiu: " + points + " pontos.\nTempo: " + jLabel17.getText() + " " + jLabel18.getText());
            PrincipalAcheOErro p = new PrincipalAcheOErro();
            p.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        comp = 7;
        if (comp == numeroTmp) {
            JOptionPane.showMessageDialog(null, "Parabéns, você acertou!");
            points++;
            jLabel19.setText("Pontos: " + points);
            cont = 0;
        } else {
            t.stop();
            JOptionPane.showMessageDialog(null, "Você errou, você conseguiu: " + points + " pontos.\nTempo: " + jLabel17.getText() + " " + jLabel18.getText());
            PrincipalAcheOErro p = new PrincipalAcheOErro();
            p.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        comp = 8;
        if (comp == numeroTmp) {
            JOptionPane.showMessageDialog(null, "Parabéns, você acertou!");
            points++;
            jLabel19.setText("Pontos: " + points);
            cont = 0;
        } else {
            t.stop();
            JOptionPane.showMessageDialog(null, "Você errou, você conseguiu: " + points + " pontos.\nTempo: " + jLabel17.getText() + " " + jLabel18.getText());
            PrincipalAcheOErro p = new PrincipalAcheOErro();
            p.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        comp = 9;
        if (comp == numeroTmp) {
            JOptionPane.showMessageDialog(null, "Parabéns, você acertou!");
            points++;
            jLabel19.setText("Pontos: " + points);
            cont = 0;
        } else {
            t.stop();
            JOptionPane.showMessageDialog(null, "Você errou, você conseguiu: " + points + " pontos.\nTempo: " + jLabel17.getText() + " " + jLabel18.getText());
            PrincipalAcheOErro p = new PrincipalAcheOErro();
            p.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        if (p.equals("play")) {
            t.stop();
            jLabel20.setIcon(new javax.swing.ImageIcon("Res/JogosExtras/AcheOErro/play.png"));
            p = "pause";
            jLabel1.setVisible(false);
            jLabel3.setVisible(false);
            jLabel4.setVisible(false);
            jLabel5.setVisible(false);
            jLabel6.setVisible(false);
            jLabel7.setVisible(false);
            jLabel10.setVisible(false);
            jLabel13.setVisible(false);
            jLabel14.setVisible(false);
        } else {
            if (p.equals("pause")) {
                t.start();
                jLabel20.setIcon(new javax.swing.ImageIcon("Res/JogosExtras/AcheOErro/pause.png"));
                p = "play";
                jLabel1.setVisible(true);
                jLabel3.setVisible(true);
                jLabel4.setVisible(true);
                jLabel5.setVisible(true);
                jLabel6.setVisible(true);
                jLabel7.setVisible(true);
                jLabel10.setVisible(true);
                jLabel13.setVisible(true);
                jLabel14.setVisible(true);
            }
        }
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
        jButton1.setForeground(Color.red);
    }//GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
        jButton1.setForeground(Color.white);
    }//GEN-LAST:event_jButton1MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PrincipalAcheOErro p = new PrincipalAcheOErro();
        p.setVisible(true);
        t.stop();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AcheOErroF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AcheOErroF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AcheOErroF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AcheOErroF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AcheOErroF().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
