package reservas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class ReservaZC extends javax.swing.JFrame {

    Conexion con = new Conexion();
    DefaultComboBoxModel Hora = new DefaultComboBoxModel();
    DefaultComboBoxModel Limpio = new DefaultComboBoxModel();
    String IDHorario;
    String FechaSeleccionada;

    public ReservaZC() {
        con.establecerConexion();
        Limpio.addElement(" ");
        initComponents();
        CargarHorarios();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Horas = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        NApartamento = new javax.swing.JTextField();
        Torre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        NIdentificacion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TipoIdentificacion1 = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        Fecha = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        RangosHoras = new javax.swing.JComboBox<>();
        Mensaje = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("N° Apartamento");

        NApartamento.setBackground(new java.awt.Color(255, 255, 255));
        NApartamento.setForeground(new java.awt.Color(51, 51, 51));
        NApartamento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        Torre.setBackground(new java.awt.Color(255, 255, 255));
        Torre.setForeground(new java.awt.Color(51, 51, 51));
        Torre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("N° Identificación");

        NIdentificacion.setBackground(new java.awt.Color(255, 255, 255));
        NIdentificacion.setForeground(new java.awt.Color(51, 51, 51));
        NIdentificacion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Reservas de Zonas Comunes");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Torre");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Zona a reservar");

        TipoIdentificacion1.setBackground(new java.awt.Color(255, 255, 255));
        TipoIdentificacion1.setForeground(new java.awt.Color(0, 0, 0));
        TipoIdentificacion1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Salón Social", "Zona BBQ", "Cancha Sintética", "Zona Humeda" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Seleccionar fecha");

        btnConsultar.setBackground(new java.awt.Color(0, 51, 255));
        btnConsultar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnConsultar.setForeground(new java.awt.Color(255, 255, 255));
        btnConsultar.setText("Consultar disponibilidad");
        btnConsultar.setBorder(null);
        btnConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        Fecha.setDateFormatString("yyyy-MM-dd");
        Fecha.setMinSelectableDate(new java.util.Date(-62135747924000L));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Horas disponibles");

        btnConfirmar.setBackground(new java.awt.Color(255, 153, 0));
        btnConfirmar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnConfirmar.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmar.setText("Confirmar reserva");
        btnConfirmar.setBorder(null);
        btnConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        RangosHoras.setBackground(new java.awt.Color(255, 255, 255));
        RangosHoras.setForeground(new java.awt.Color(0, 0, 0));
        RangosHoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RangosHorasActionPerformed(evt);
            }
        });

        Mensaje.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        Mensaje.setForeground(new java.awt.Color(255, 0, 0));

        btnLimpiar.setBackground(new java.awt.Color(255, 0, 0));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar campos");
        btnLimpiar.setBorder(null);
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Mensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel6))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(RangosHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel9)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(41, 41, 41)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(NApartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel3)
                                                            .addComponent(Torre, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel5)
                                                            .addComponent(TipoIdentificacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(20, 20, 20)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(NIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel7))))
                                                .addGap(8, 8, 8)))))
                                .addGap(25, 25, 25))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NApartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Torre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TipoIdentificacion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RangosHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Mensaje))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        String query = "EXEC sp_IngresoReservas " + NIdentificacion.getText() + ", " + NApartamento.getText() + ", " + Torre.getText() + ", '" + FechaSeleccionada + "', " + IDHorario;
        try {
            Statement st = con.conectar.createStatement();
            st.execute(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        LimpiarCampos();
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        //PARA AGREGAR A DB YYYY-MM-DD
        FechaSeleccionada = ((JTextField) Fecha.getDateEditor().getUiComponent()).getText();

        //PARA OBTENER DIA DE LA SEMANA
        Date fechaS = Fecha.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaS);

        int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);

        //COMPROBAR DISPONIBILIDAD HORARIA
        //Comprobar seleccion de un dia lunes
        if (diaSemana == 2) {
            Mensaje.setText("Los dias lunes no está disponible");
        } else {
            //Comrobar en base de datos
            String query = "EXEC sp_DescartarHorarios '" + FechaSeleccionada + "'";

            try {
                PreparedStatement pst = con.conectar.prepareStatement(query);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    int IDH = rs.getInt(IDHorario);
                    System.out.println(" DE"+IDH);

                    AgregarDisponibles(IDH);
                }
                RangosHoras.setModel(Hora);
            } catch (Exception e) {
                System.out.println(e);
            }
        }


    }//GEN-LAST:event_btnConsultarActionPerformed

    public void AgregarDisponibles(int IDH) {
        if (IDH == 1) {
            Hora.removeElement("9:00 AM - 10:00 AM");
        } else if (IDH == 2) {
            Hora.removeElement("10:00 AM - 11:00 AM");
        } else if (IDH == 3) {
            Hora.removeElement("11:00 AM - 12:00 AM");
        } else if (IDH == 4) {
            Hora.removeElement("12:00 PM - 1:00 PM");
        } else if (IDH == 5) {
            Hora.removeElement("1:00 PM - 2:00 PM");
        } else if (IDH == 6) {
            Hora.removeElement("2:00 PM - 3:00 PM");
        } else if (IDH == 7) {
            Hora.removeElement("3:00 PM - 4:00 PM");
        } else if (IDH == 8) {
            Hora.removeElement("4:00 AM - 5:00 PM");
        } else if (IDH == 9) {
            Hora.removeElement("9:00 AM - 11:00 AM");
        } else if (IDH == 10) {
            Hora.removeElement("11:00 AM - 1:00 PM");
        } else if (IDH == 11) {
            Hora.removeElement("1:00 PM - 3:00 PM");
        } else if (IDH == 12) {
            Hora.removeElement("3:00 PM - 5:00 PM");
        }
    }
    
    public void CargarHorarios(){
        //Definir modelo de bloque de una hora
        Hora.addElement("9:00 AM - 10:00 AM");
        Hora.addElement("10:00 AM - 11:00 AM");
        Hora.addElement("11:00 AM - 12:00 AM");
        Hora.addElement("12:00 PM - 1:00 PM");
        Hora.addElement("1:00 PM - 2:00 PM");
        Hora.addElement("2:00 PM - 3:00 PM");
        Hora.addElement("3:00 PM - 4:00 PM");
        Hora.addElement("4:00 PM - 5:00 PM");

        //Definir modelo de bloque de dos horas
        Hora.addElement("9:00 AM - 11:00 AM");
        Hora.addElement("11:00 AM - 1:00 PM");
        Hora.addElement("1:00 PM - 3:00 PM");
        Hora.addElement("3:00 PM - 5:00 PM");
    }
    
    public void LimpiarCampos(){
        NApartamento.setText("");
        Torre.setText("");
        NIdentificacion.setText("");
        RangosHoras.setModel(Limpio);
        RangosHoras.setSelectedItem(" ");
        Hora.removeAllElements();
        CargarHorarios();
    }
    
    private void RangosHorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RangosHorasActionPerformed

        if (RangosHoras.getSelectedItem().equals("9:00 AM - 10:00 AM")) {
            IDHorario = "1";
        } else if (RangosHoras.getSelectedItem().equals("10:00 AM - 11:00 AM")) {
            IDHorario = "2";
        } else if (RangosHoras.getSelectedItem().equals("11:00 AM - 12:00 AM")) {
            IDHorario = "3";
        } else if (RangosHoras.getSelectedItem().equals("12:00 PM - 1:00 PM")) {
            IDHorario = "4";
        } else if (RangosHoras.getSelectedItem().equals("1:00 PM - 2:00 PM")) {
            IDHorario = "5";
        } else if (RangosHoras.getSelectedItem().equals("2:00 PM - 3:00 PM")) {
            IDHorario = "6";
        } else if (RangosHoras.getSelectedItem().equals("3:00 PM - 4:00 PM")) {
            IDHorario = "7";
        } else if (RangosHoras.getSelectedItem().equals("4:00 PM - 5:00 PM")) {
            IDHorario = "8";
        } else if (RangosHoras.getSelectedItem().equals("9:00 AM - 11:00 AM")) {
            IDHorario = "9";
        } else if (RangosHoras.getSelectedItem().equals("11:00 AM - 1:00 PM")) {
            IDHorario = "10";
        } else if (RangosHoras.getSelectedItem().equals("1:00 PM - 3:00 PM")) {
            IDHorario = "11";
        } else if (RangosHoras.getSelectedItem().equals("3:00 PM - 5:00 PM")) {
            IDHorario = "12";
        }
    }//GEN-LAST:event_RangosHorasActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        LimpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

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
            java.util.logging.Logger.getLogger(ReservaZC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReservaZC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReservaZC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReservaZC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReservaZC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Fecha;
    private javax.swing.ButtonGroup Horas;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JTextField NApartamento;
    private javax.swing.JTextField NIdentificacion;
    private javax.swing.JComboBox<String> RangosHoras;
    private javax.swing.JComboBox<String> TipoIdentificacion1;
    private javax.swing.JTextField Torre;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
