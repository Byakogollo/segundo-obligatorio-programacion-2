/*
TRABAJO REALIZADO POR ESTEBAN NECUSE 227582 Y MARCOS MEDINA 365070
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ventanas.personal;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Empleado;
import modelo.Manager;
import modelo.Sistema;
import persistencia.ArchivoCargar;

/**
 *
 * @author Byakogollo
 */
public class VentanaAdministrarEmpleados extends javax.swing.JDialog {
    private Sistema modelo;
    /**
     * Creates new form VentanaModificarManager
     */
    public VentanaAdministrarEmpleados(java.awt.Frame parent, boolean modal, Sistema modelo) {
        super(parent, modal);
        this.modelo = modelo;
        initComponents();
       
        lstEmpleados.setListData(modelo.getEmpleados().toArray(new Empleado[0]));
    }

     private void MostrarDatos(Empleado seleccion){
                      
      
            
        try{
            
                String cv = new ArchivoCargar().cargarCurriculum(seleccion);
            
                this.txtCurriculum.setText(cv);
                this.txtCedula.setText(""+seleccion.getCi());
                this.txtNombre.setText(seleccion.getNombre());
                this.txtTelefono.setText(""+seleccion.getCelular());
                this.txtSalario.setText(""+seleccion.getSalarioMensual());
                this.txtManager.setText(seleccion.getManager().getNombre());
                this.txtArea.setText(seleccion.getArea().getNombre());
                
        }catch(Exception e){
            
                this.txtCurriculum.setText("Seleccione un empleado");
                this.txtCedula.setText("Seleccione un empleado");
                this.txtNombre.setText("Seleccione un empleado");
                this.txtTelefono.setText("Seleccione un empleado");
                this.txtSalario.setText("Seleccione un empleado");
                this.txtManager.setText("Seleccione un empleado");
                this.txtArea.setText("Seleccione un empleado");
        }     
    }
     
   
     
 
     
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstEmpleados = new javax.swing.JList<>();
        lblTitulo = new javax.swing.JLabel();
        lblCurriculum = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCedula = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnAltaEmpleado = new javax.swing.JButton();
        txtCurriculum = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblSalario = new javax.swing.JLabel();
        lblManager = new javax.swing.JLabel();
        txtSalario = new javax.swing.JTextField();
        txtManager = new javax.swing.JTextField();
        lblArea = new javax.swing.JLabel();
        txtArea = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        lstEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstEmpleados);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 160, 180, 390);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitulo.setText("Administracion de Empleados");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(80, 20, 530, 80);

        lblCurriculum.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCurriculum.setText("Curriculum");
        getContentPane().add(lblCurriculum);
        lblCurriculum.setBounds(250, 260, 68, 20);

        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombre.setText("Nombre");
        getContentPane().add(lblNombre);
        lblNombre.setBounds(250, 170, 50, 20);

        lblCedula.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCedula.setText("Cedula");
        getContentPane().add(lblCedula);
        lblCedula.setBounds(250, 200, 43, 20);

        lblTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTelefono.setText("Telefono");
        getContentPane().add(lblTelefono);
        lblTelefono.setBounds(250, 230, 55, 20);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar);
        btnCancelar.setBounds(460, 490, 150, 60);

        btnAltaEmpleado.setText("Agregar Empleado");
        btnAltaEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltaEmpleadoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAltaEmpleado);
        btnAltaEmpleado.setBounds(240, 490, 150, 60);

        txtCurriculum.setEditable(false);
        txtCurriculum.setText("Seleccione un empleado");
        txtCurriculum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCurriculumActionPerformed(evt);
            }
        });
        getContentPane().add(txtCurriculum);
        txtCurriculum.setBounds(440, 260, 150, 70);

        txtNombre.setEditable(false);
        txtNombre.setText("Seleccione un empleado");
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        getContentPane().add(txtNombre);
        txtNombre.setBounds(440, 170, 150, 22);

        txtTelefono.setEditable(false);
        txtTelefono.setText("Seleccione un empleado");
        getContentPane().add(txtTelefono);
        txtTelefono.setBounds(440, 230, 150, 22);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Empleados");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(80, 130, 80, 30);

        lblSalario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSalario.setText("Salario");
        getContentPane().add(lblSalario);
        lblSalario.setBounds(250, 340, 50, 20);

        lblManager.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblManager.setText("Manager");
        getContentPane().add(lblManager);
        lblManager.setBounds(250, 370, 60, 20);

        txtSalario.setEditable(false);
        txtSalario.setText("Seleccione un empleado");
        txtSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtSalario);
        txtSalario.setBounds(440, 340, 150, 22);

        txtManager.setEditable(false);
        txtManager.setText("Seleccione un empleado");
        getContentPane().add(txtManager);
        txtManager.setBounds(440, 370, 150, 22);

        lblArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblArea.setText("Area");
        getContentPane().add(lblArea);
        lblArea.setBounds(250, 400, 29, 20);

        txtArea.setEditable(false);
        txtArea.setText("Seleccione un empleado");
        getContentPane().add(txtArea);
        txtArea.setBounds(440, 400, 150, 22);

        txtCedula.setEditable(false);
        txtCedula.setText("Seleccione un empleado");
        getContentPane().add(txtCedula);
        txtCedula.setBounds(440, 200, 150, 22);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
                                              
            JOptionPane alerta = new JOptionPane();
       int seleccion = alerta.showConfirmDialog(this,"Desea salir?", "Cancelar", JOptionPane.YES_NO_CANCEL_OPTION);
       
       if(seleccion == alerta.YES_OPTION){
           this.dispose();
       }
                                               

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAltaEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaEmpleadoActionPerformed
       
                   
            
        
        VentanaAltaEmpleado alta = new VentanaAltaEmpleado(this,true,this.modelo);
         alta.setBounds(0,0,700,800);
        alta.setLocationRelativeTo(this);
        alta.setVisible(true);
        
        alta.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e){
                 lstEmpleados.setListData(modelo.getEmpleados().toArray(new Empleado[0]));
            }
        });
        
        
        
    }//GEN-LAST:event_btnAltaEmpleadoActionPerformed

    private void lstEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstEmpleadosMouseClicked
       if(evt.getClickCount() >= 1){
           
        Empleado e = (Empleado) this.lstEmpleados.getSelectedValue();
        this.MostrarDatos(e);
        }
    }//GEN-LAST:event_lstEmpleadosMouseClicked

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalarioActionPerformed

    private void txtCurriculumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCurriculumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCurriculumActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAltaEmpleado;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblCurriculum;
    private javax.swing.JLabel lblManager;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSalario;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<Empleado> lstEmpleados;
    private javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCurriculum;
    private javax.swing.JTextField txtManager;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSalario;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
