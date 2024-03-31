package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


public class ListadoPQRSD extends javax.swing.JFrame {

    Conexion c = new Conexion();
    public DefaultTableModel datos = new DefaultTableModel();
    int IDArchivo;
    int IDPQRSD;
    private JFrame frame;
    int result;
    String IDPersona;
    
    //VARIABLES PARA EL CORREO
    private static String emailFrom = "brisasdetokioph@gmail.com";
    private static String passwordFrom = "hhvpwjhnywovoiez";
    private String emailTo;
    private String subject;
    private String content;
    
    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;
    

    public ListadoPQRSD() {
        c.establecerConexion();
        initComponents();
        mProperties = new Properties();
        TablaPQRSD.setModel(datos);

        try {
            Statement st = c.conectar.createStatement();
            String query = "EXEC sp_ListadoPQRSD";

            PreparedStatement ps = c.conectar.prepareStatement(query);
            ResultSetMetaData meta = ps.getMetaData();

            int nColumnas = meta.getColumnCount();

            Vector<String> ids = new Vector<>();

            for (int i = 1; i <= nColumnas; i++) {
                ids.add(meta.getColumnName(i));
            }
            datos.setColumnIdentifiers(ids);

            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                Vector<String> Fila = new Vector<>();
                for (int i = 1; i <= nColumnas; i++) {
                    Fila.add(res.getString(i));
                }
                datos.addRow(Fila);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    //Refrescar datos de la tabla (interfaz) cada que se realiza una accion
    public void ActualizarTabla(){
        try{
            int t = datos.getRowCount();
            int in = 0;
            while (in < t) {
                datos.removeRow(in);
                t--;
            }
            
            String refresh = "EXEC sp_ListadoPQRSD";
                    PreparedStatement ps = c.conectar.prepareStatement(refresh);
                    ResultSetMetaData meta = ps.getMetaData();

                    int nColumnas = meta.getColumnCount();

                    Vector<String> ids = new Vector<>();

                    for (int i = 1; i <= nColumnas; i++) {
                        ids.add(meta.getColumnName(i));
                    }
                    Statement st = c.conectar.createStatement();
                    datos.setColumnIdentifiers(ids);

                    
                    ResultSet res = st.executeQuery(refresh);
                    
                    
                    while (res.next()) {
                        Vector<String> Fila = new Vector<>();
                        for (int i = 1; i <= nColumnas; i++){
                            Fila.add(res.getString(i));
                        }
                        datos.addRow(Fila);
                    }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    private void createEmail(){
        //Obtener correo de la base de datos
        try{
            String query = "SELECT Correo FROM tblDatosPQRSD WHERE NumeroID = " + IDPersona;

            Statement st = c.conectar.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            if(rs.next()){
                emailTo = rs.getString("Correo");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        subject = "Sobre tu PQRSD en Brisas Tokio PH";
        content = Respuesta.getText();
        
        //Protocolo simple de envio de email
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.put("mail.smtp.starttls.enable", "true");
        mProperties.put("mail.smtp.port", "587");
        mProperties.put("mail.smtp.user", emailFrom);
        mProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.put("mail.smtp.auth", "true");
        
        //Variable de sesion
        mSession = Session.getDefaultInstance(mProperties);
        
        //Cuerpo del correo
        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setText(content, "ISO-8859-1", "html");
            
        } catch (AddressException ex) {
            Logger.getLogger(ListadoPQRSD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ListadoPQRSD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sendEmail(){
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
            
            JOptionPane.showMessageDialog(this, "Respuesta enviada al correo electrónico");
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(ListadoPQRSD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ListadoPQRSD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        VentanaComentario = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        IDResponsable = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        NombreResponsable = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ComentarioResponsable = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();
        VentanaRespuesta = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        IDResponsableR = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        NombreRespuesta = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Respuesta = new javax.swing.JTextArea();
        btnAdjuntar = new javax.swing.JButton();
        btnEnviar2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaPQRSD = new javax.swing.JTable();
        btnComentario = new javax.swing.JButton();
        DescargarArchivos = new javax.swing.JButton();
        btnResponder = new javax.swing.JButton();
        btnFormulario = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        VentanaComentario.setTitle("Agregar Comentario");
        VentanaComentario.setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        VentanaComentario.setSize(new java.awt.Dimension(450, 350));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Identificación del responsable");

        IDResponsable.setBackground(new java.awt.Color(255, 255, 255));
        IDResponsable.setForeground(new java.awt.Color(51, 51, 51));
        IDResponsable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Ingreso Comentarios");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Nombre");

        NombreResponsable.setBackground(new java.awt.Color(255, 255, 255));
        NombreResponsable.setForeground(new java.awt.Color(51, 51, 51));
        NombreResponsable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Comentario");

        ComentarioResponsable.setBackground(new java.awt.Color(255, 255, 255));
        ComentarioResponsable.setColumns(20);
        ComentarioResponsable.setForeground(new java.awt.Color(51, 51, 51));
        ComentarioResponsable.setLineWrap(true);
        ComentarioResponsable.setRows(5);
        jScrollPane2.setViewportView(ComentarioResponsable);

        btnEnviar.setBackground(new java.awt.Color(0, 204, 255));
        btnEnviar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEnviar.setForeground(new java.awt.Color(255, 255, 255));
        btnEnviar.setText("ENVIAR");
        btnEnviar.setBorder(null);
        btnEnviar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jLabel2))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel11)
                .addGap(6, 6, 6)
                .addComponent(IDResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jLabel12)
                .addGap(6, 6, 6)
                .addComponent(NombreResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel13))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel11))
                    .addComponent(IDResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel12))
                    .addComponent(NombreResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout VentanaComentarioLayout = new javax.swing.GroupLayout(VentanaComentario.getContentPane());
        VentanaComentario.getContentPane().setLayout(VentanaComentarioLayout);
        VentanaComentarioLayout.setHorizontalGroup(
            VentanaComentarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        VentanaComentarioLayout.setVerticalGroup(
            VentanaComentarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        VentanaRespuesta.setTitle("Agregar Comentario");
        VentanaRespuesta.setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        VentanaRespuesta.setSize(new java.awt.Dimension(450, 350));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Identificación del responsable");

        IDResponsableR.setBackground(new java.awt.Color(255, 255, 255));
        IDResponsableR.setForeground(new java.awt.Color(51, 51, 51));
        IDResponsableR.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Respuestas PQRSD");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Nombre");

        NombreRespuesta.setBackground(new java.awt.Color(255, 255, 255));
        NombreRespuesta.setForeground(new java.awt.Color(51, 51, 51));
        NombreRespuesta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Respuesta");

        Respuesta.setBackground(new java.awt.Color(255, 255, 255));
        Respuesta.setColumns(20);
        Respuesta.setForeground(new java.awt.Color(51, 51, 51));
        Respuesta.setLineWrap(true);
        Respuesta.setRows(5);
        jScrollPane3.setViewportView(Respuesta);

        btnAdjuntar.setBackground(new java.awt.Color(0, 204, 255));
        btnAdjuntar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdjuntar.setForeground(new java.awt.Color(255, 255, 255));
        btnAdjuntar.setText("ADJUNTAR");
        btnAdjuntar.setBorder(null);
        btnAdjuntar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdjuntar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdjuntarActionPerformed(evt);
            }
        });

        btnEnviar2.setBackground(new java.awt.Color(255, 102, 0));
        btnEnviar2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEnviar2.setForeground(new java.awt.Color(255, 255, 255));
        btnEnviar2.setText("ENVIAR");
        btnEnviar2.setBorder(null);
        btnEnviar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEnviar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jLabel3))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel14)
                .addGap(6, 6, 6)
                .addComponent(IDResponsableR, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jLabel15)
                .addGap(6, 6, 6)
                .addComponent(NombreRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel16))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAdjuntar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(171, 171, 171)
                        .addComponent(btnEnviar2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addGap(6, 6, 6)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel14))
                    .addComponent(IDResponsableR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel15))
                    .addComponent(NombreRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdjuntar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnviar2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout VentanaRespuestaLayout = new javax.swing.GroupLayout(VentanaRespuesta.getContentPane());
        VentanaRespuesta.getContentPane().setLayout(VentanaRespuestaLayout);
        VentanaRespuestaLayout.setHorizontalGroup(
            VentanaRespuestaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        VentanaRespuestaLayout.setVerticalGroup(
            VentanaRespuestaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Listado PQRSD");

        TablaPQRSD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TablaPQRSD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaPQRSDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaPQRSD);

        btnComentario.setBackground(new java.awt.Color(0, 153, 0));
        btnComentario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnComentario.setForeground(new java.awt.Color(255, 255, 255));
        btnComentario.setText("Agregar comentario");
        btnComentario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        btnComentario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnComentario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComentarioActionPerformed(evt);
            }
        });

        DescargarArchivos.setBackground(new java.awt.Color(0, 153, 0));
        DescargarArchivos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        DescargarArchivos.setForeground(new java.awt.Color(255, 255, 255));
        DescargarArchivos.setText("Descargar Adjuntos");
        DescargarArchivos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        DescargarArchivos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DescargarArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DescargarArchivosActionPerformed(evt);
            }
        });

        btnResponder.setBackground(new java.awt.Color(0, 153, 0));
        btnResponder.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnResponder.setForeground(new java.awt.Color(255, 255, 255));
        btnResponder.setText("Responder al correo");
        btnResponder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)));
        btnResponder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnResponder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResponderActionPerformed(evt);
            }
        });

        btnFormulario.setBackground(new java.awt.Color(255, 0, 0));
        btnFormulario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFormulario.setForeground(new java.awt.Color(255, 255, 255));
        btnFormulario.setText("Formulario PQRSD");
        btnFormulario.setBorder(null);
        btnFormulario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFormulario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormularioActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Acciones");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Navegación");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnComentario, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                        .addComponent(DescargarArchivos, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                        .addComponent(btnResponder, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                        .addComponent(btnFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(431, 431, 431))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DescargarArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResponder, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(4, 4, 4)
                        .addComponent(btnFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(96, Short.MAX_VALUE))
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

    private void btnComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComentarioActionPerformed
        if (IDPQRSD > 0) {
            VentanaComentario.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una PQRSD");
        }

    }//GEN-LAST:event_btnComentarioActionPerformed

    private void DescargarArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DescargarArchivosActionPerformed

        if (IDPQRSD > 0) {
            //Verificar existencia archivo 
            if (IDArchivo == 0) {
                JOptionPane.showMessageDialog(this, "No hay archivos adjuntos");
            } else {
                try {
                    String query = "SELECT Archivo FROM tblArchivosPQRSD WHERE IDArchivo = " + IDArchivo;
                    ResultSet rs = c.conectar.createStatement().executeQuery(query);

                    //Comprobar si existe el archivo
                    if (rs.next()) {
                        //Obtener archivo
                        Blob Archivo = rs.getBlob(1);

                        //Crear selector de ruta de destino
                        JFileChooser SelectorArchivo = new JFileChooser();
                        SelectorArchivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                        //Mostrar el selector de ruta de destino
                        int seleccion = SelectorArchivo.showSaveDialog(frame);

                        if (seleccion == JFileChooser.APPROVE_OPTION) {
                            //Obtener ruta de destino
                            File rutaDestino = SelectorArchivo.getSelectedFile();

                            //Descargar el archivo en la ruta de destino
                            byte[] bytesArchivo = Archivo.getBytes(1, (int) Archivo.length());
                            File ArchivoDescargado = new File(rutaDestino, "Evidencia" + ".pdf");
                            FileOutputStream fos = new FileOutputStream(ArchivoDescargado);
                            fos.write(bytesArchivo);
                            fos.close();

                            JOptionPane.showMessageDialog(rootPane, "Descargado correctamente");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "El archivo no existe");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una PQRSD");
        }


    }//GEN-LAST:event_DescargarArchivosActionPerformed

    private void btnResponderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResponderActionPerformed
        if (IDPQRSD > 0) {
            VentanaRespuesta.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una PQRSD");
        }

    }//GEN-LAST:event_btnResponderActionPerformed

    private void TablaPQRSDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaPQRSDMouseClicked
        IDPQRSD = Integer.parseInt(String.valueOf(datos.getValueAt(TablaPQRSD.getSelectedRow(), 0)));

        Object valor = TablaPQRSD.getValueAt(TablaPQRSD.getSelectedRow(), 5);

        if (valor == null) {
            IDArchivo = 0;
        } else {
            IDArchivo = Integer.parseInt(String.valueOf(datos.getValueAt(TablaPQRSD.getSelectedRow(), 5)));
        }
        
        IDPersona = String.valueOf(datos.getValueAt(TablaPQRSD.getSelectedRow(), 3));

    }//GEN-LAST:event_TablaPQRSDMouseClicked

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        try {
            String query = "INSERT INTO tblComentarios VALUES(" + IDPQRSD + ", SYSDATETIME(), " + IDResponsable.getText() + ", '" + NombreResponsable.getText() + "', '" + ComentarioResponsable.getText() + "')";
            Statement st = c.conectar.createStatement();
            st.execute(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnAdjuntarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdjuntarActionPerformed
        JFrame CuadroArchivo = new JFrame("Subir archivo");
        JFileChooser SubirArchivo = new JFileChooser();

        SubirArchivo.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));
        result = SubirArchivo.showOpenDialog(CuadroArchivo);

        try {
            if (result == JFileChooser.APPROVE_OPTION) {
                //Obtener PDF seleccionado
                File archivo = SubirArchivo.getSelectedFile();

                //Leeer contenido de PDF
                FileInputStream entrada = new FileInputStream(archivo);
                byte[] bytes = new byte[(int) archivo.length()];
                entrada.read(bytes);
                entrada.close();

                //Ingreso a la tabla ArchivosPQRSD
                try {
                    PreparedStatement pst = c.conectar.prepareStatement("INSERT INTO tblArchivosRespuesta VALUES(?)");
                    pst.setBytes(1, bytes);
                    pst.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnAdjuntarActionPerformed

    private void btnEnviar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviar2ActionPerformed
        //INGRESO DE REGISTRO A LA BASE DE DATOS
        try {
            if (result == JFileChooser.APPROVE_OPTION) {
                String query = "EXEC sp_IngresoRespuesta " + IDPQRSD + ", " + IDResponsableR.getText() + ", '" + NombreRespuesta.getText() + "', '" + Respuesta.getText() + "' ";
                Statement st = c.conectar.createStatement();
                st.execute(query);
            } else {
                String query = "INSERT INTO tblRespuestas VALUES(" + IDPQRSD + ", SYSDATETIME(), " + IDResponsableR.getText() + ", '" + NombreRespuesta.getText() + "', '" + Respuesta.getText() + "', 0";
                Statement st = c.conectar.createStatement();
                st.execute(query);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        
        //NOTIFICACION AL CORREO
        createEmail();
        sendEmail();
        
        //ACTUALIZAR TABLA (INTERFAZ)
        ActualizarTabla();
        
    }//GEN-LAST:event_btnEnviar2ActionPerformed

    private void btnFormularioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormularioActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnFormularioActionPerformed

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
            java.util.logging.Logger.getLogger(ListadoPQRSD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListadoPQRSD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListadoPQRSD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListadoPQRSD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListadoPQRSD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ComentarioResponsable;
    private javax.swing.JButton DescargarArchivos;
    private javax.swing.JTextField IDResponsable;
    private javax.swing.JTextField IDResponsableR;
    private javax.swing.JTextField NombreResponsable;
    private javax.swing.JTextField NombreRespuesta;
    private javax.swing.JTextArea Respuesta;
    private javax.swing.JTable TablaPQRSD;
    private javax.swing.JFrame VentanaComentario;
    private javax.swing.JFrame VentanaRespuesta;
    private javax.swing.JButton btnAdjuntar;
    private javax.swing.JButton btnComentario;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnEnviar2;
    private javax.swing.JButton btnFormulario;
    private javax.swing.JButton btnResponder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
