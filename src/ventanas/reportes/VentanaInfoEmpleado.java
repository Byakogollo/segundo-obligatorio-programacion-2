/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ventanas.reportes;

import ventanas.personal.*;
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
public class VentanaInfoEmpleado extends javax.swing.JDialog {
    private Sistema modelo;
    
    /**
     * Creates new form VentanaModificarManager
     */
    public VentanaInfoEmpleado(java.awt.Frame parent, boolean modal, Sistema modelo, Empleado emp) {
        super(parent, modal);
        this.modelo = modelo;
        
        initComponents();
        
                
        MostrarDatos(emp);
        
        
    }

     private void MostrarDatos(Empleado empleado){
                      
      
            
        try{
            
                String cv = new ArchivoCargar().cargarCurriculum(empleado);
            
                this.txtCurriculum.setText(cv);
                this.txtCedula.setText(""+empleado.getCi());
                this.txtNombre.setText(empleado.getNombre());
                this.txtTelefono.setText(""+empleado.getCelular());
                this.txtSalario.setText(""+empleado.getSalarioMensual());
                this.txtManager.setText(empleado.getManager().getNombre());
                this.txtArea.setText(empleado.getArea().getNombre());
                
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

        lblTitulo = new javax.swing.JLabel();
        lblCurriculum = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCedula = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        txtCurriculum = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        lblSalario = new javax.swing.JLabel();
        lblManager = new javax.swing.JLabel();
        txtSalario = new javax.swing.JTextField();
        txtManager = new javax.swing.JTextField();
        lblArea = new javax.swing.JLabel();
        txtArea = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setText("Detalles del Empleado");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(40, 20, 270, 80);

        lblCurriculum.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCurriculum.setText("Curriculum");
        getContentPane().add(lblCurriculum);
        lblCurriculum.setBounds(50, 210, 67, 20);

        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombre.setText("Nombre");
        getContentPane().add(lblNombre);
        lblNombre.setBounds(50, 120, 50, 20);

        lblCedula.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCedula.setText("Cedula");
        getContentPane().add(lblCedula);
        lblCedula.setBounds(50, 150, 42, 20);

        lblTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTelefono.setText("Telefono");
        getContentPane().add(lblTelefono);
        lblTelefono.setBounds(50, 180, 52, 20);

        txtCurriculum.setEditable(false);
        txtCurriculum.setText("Seleccione un empleado");
        getContentPane().add(txtCurriculum);
        txtCurriculum.setBounds(150, 210, 150, 70);

        txtNombre.setEditable(false);
        txtNombre.setText("Seleccione un empleado");
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        getContentPane().add(txtNombre);
        txtNombre.setBounds(150, 120, 150, 22);

        txtTelefono.setEditable(false);
        txtTelefono.setText("Seleccione un empleado");
        getContentPane().add(txtTelefono);
        txtTelefono.setBounds(150, 180, 150, 22);

        lblSalario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSalario.setText("Salario");
        getContentPane().add(lblSalario);
        lblSalario.setBounds(50, 290, 50, 20);

        lblManager.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblManager.setText("Manager");
        getContentPane().add(lblManager);
        lblManager.setBounds(50, 320, 60, 20);

        txtSalario.setEditable(false);
        txtSalario.setText("Seleccione un empleado");
        txtSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtSalario);
        txtSalario.setBounds(150, 290, 150, 22);

        txtManager.setEditable(false);
        txtManager.setText("Seleccione un empleado");
        getContentPane().add(txtManager);
        txtManager.setBounds(150, 320, 150, 22);

        lblArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblArea.setText("Area");
        getContentPane().add(lblArea);
        lblArea.setBounds(50, 350, 28, 20);

        txtArea.setEditable(false);
        txtArea.setText("Seleccione un empleado");
        getContentPane().add(txtArea);
        txtArea.setBounds(150, 350, 150, 22);

        txtCedula.setEditable(false);
        txtCedula.setText("Seleccione un empleado");
        getContentPane().add(txtCedula);
        txtCedula.setBounds(150, 150, 150, 22);

        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(120, 400, 100, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalarioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                    this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblCurriculum;
    private javax.swing.JLabel lblManager;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSalario;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCurriculum;
    private javax.swing.JTextField txtManager;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSalario;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
