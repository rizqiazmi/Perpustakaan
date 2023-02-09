/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mac
 */
public class FDataAnggotaStaff extends javax.swing.JFrame {

    FLogin login = new FLogin();
    String status = login.login;
    /**
     * Creates new form FDataAnggota
     */
    public FDataAnggotaStaff() 
    {
        initComponents();
        load_data(); //Memanggil menampilkan Data
        IDOtotmatis(); // menampilkan id otomatis
        LoadTingkat(); //load combo tingkat
        LoadJurusan(); //load combo jurusan
        LoadKelas();
        /*if("kepala".equals(status)){
            TabStaff.setEnabled(true);
        }else{
            TabStaff.setEnabled(false);
        }*/
    }
    
    // load Data Dari Database tbl_anggota
    private void load_data()
    {
        Connection kon=koneksi.koneksiDb();
        Object header[]={"ID ANGGOTA","NIM","NAMA ANGGOTA","JK","TINGKAT","JURUSAN","KELAS","NO HP","STATUS"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        TabelAnggota.setModel(data);
        String sql_data="SELECT * FROM tbl_anggota";
        try
        {
            Statement st=kon.createStatement();
            ResultSet rs=st.executeQuery(sql_data);
            while(rs.next())
            {
                String d1=rs.getString(1);
                String d2=rs.getString(2);
                String d3=rs.getString(3);
                String d4=rs.getString(4);
                String d5=rs.getString(5);
                String d6=rs.getString(6);
                String d7=rs.getString(7);
                String d8=rs.getString(8);
                String d9=rs.getString(9);
                
                String d[]={d1,d2,d3,d4,d5,d6,d7,d8,d9};
                data.addRow(d);
            }
        }
        catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, e);
                }
    }

    //ID Anggota Otomatis
    private void IDOtotmatis()
    {
        try
        {
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_id="SELECT * FROM tbl_anggota order by id_anggota desc";
            ResultSet rs=st.executeQuery(sql_id);
            if(rs.next())
            {
                String id_anggota=rs.getString("id_anggota").substring(1);
                String AN=""+(Integer.parseInt(id_anggota) + 1);
                String No1="";
                if(AN.length()==1) // jika id_anggota A00001
                {
                    No1="0000";
                }
                else if(AN.length()==2) // jika id Anggota A00010
                {
                    No1="000";
                }
                else if(AN.length()==3) // jika id_anggota A00100
                {
                    No1="00";
                }
                ID.setText("A"+No1+AN);
            }
            else
            {
                ID.setText("A00001");
            }
        }
        catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, e);
                }
    }
    
    // Load Combo Tingkat -------------------------------------------------------------------------
    public void LoadTingkat()
    {
        try
        {
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_tingkat="SELECT * FROM tbl_tingkat";
            ResultSet rs=st.executeQuery(sql_tingkat);
            while(rs.next())
            {
                KTINGKAT.addItem(rs.getString("id_tingkat"));
            }
            rs.close();
        }
        catch(Exception e)
        {
            
        }
    }
    
    //Load Nama Tingkat-------------------------------------------------------------------------
    public void NamaTingkat()
    {
        try
        {
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_tingkat="SELECT tingkat FROM tbl_tingkat WHERE id_tingkat='"+KTINGKAT.getSelectedItem()+"'";
            ResultSet rs=st.executeQuery(sql_tingkat);
            while(rs.next())
            {
                NTINGKAT.setText(rs.getString("tingkat"));
            }
        }
        catch(Exception e)
        {
            
        }
    }
    
    // Load Combo Jurusan -------------------------------------------------------------------------
    public void LoadJurusan()
    {
        try
        {
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_tingkat="SELECT kd_jurusan FROM tbl_jurusan";
            ResultSet rs=st.executeQuery(sql_tingkat);
            while(rs.next())
            {
                KJURUSAN.addItem(rs.getString("kd_jurusan"));
            }
            rs.close();
        }
        catch(Exception e)
        {
            
        }
    }
    
    //Load Nama Tingkat-------------------------------------------------------------------------
    public void NamaJurusan()
    {
        try
        {
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_tingkat="SELECT jurusan FROM tbl_jurusan WHERE kd_jurusan='"+KJURUSAN.getSelectedItem()+"'";
            ResultSet rs=st.executeQuery(sql_tingkat);
            while(rs.next())
            {
                NJURUSAN.setText(rs.getString("jurusan"));
            }
        }
        catch(Exception e)
        {
            
        }
    }
    
    //Load Nama Kelas-------------------------------------------------------------------------
    public void LoadKelas()
    {
        try
        {
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_tingkat="SELECT id_kelas FROM tbl_kelas";
            ResultSet rs=st.executeQuery(sql_tingkat);
            while(rs.next())
            {
                KKELAS.addItem(rs.getString("id_kelas"));
            }
            rs.close();
        }
        catch(Exception e)
        {
            
        }
    }

    
    // Input Data -------------------------------------------------------------------------
    private void input_data()
    {
        if(NAMA.getText().equals("")&&NOPE.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Salah satu atau lebih data masih kosong, mohon cek kembail");
        }else{
        try
        {
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            //untuk jenis kelamin
            String jk="";
            if(JKP.isSelected())
            {
                jk=JKP.getText();
            } 
            else
            {
                jk=JKW.getText();
            }
            
            String sql="INSERT INTO tbl_anggota values ('"+ID.getText()
                    +"','"+NIM.getText()
                    +"','"+NAMA.getText()
                    +"','"+jk
                    +"','"+KTINGKAT.getSelectedItem()
                    +"','"+KJURUSAN.getSelectedItem()
                    +"','"+KKELAS.getSelectedItem()
                    +"','"+NOPE.getText()
                    +"','"+STATUS.getSelectedItem()
                    +"')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Anggota Berhasil Dimasukan");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        }
    }
    
    private void Update(){
        if(NAMA.getText().equals("")&&NOPE.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Salah satu atau lebih data masih kosong, mohon cek kembail");
        }
        try
        {
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            //untuk jenis kelamin
            String jk="";
            if(JKP.isSelected())
            {
                jk=JKP.getText();
            } 
            else
            {
                jk=JKW.getText();
            }
            
            String sql="UPDATE tbl_anggota SET nim='"+NIM.getText()
                    +"',nama='"+NAMA.getText()
                    +"',jk='"+jk
                    +"',id_tingkat='"+KTINGKAT.getSelectedItem()
                    +"',kd_jurusan='"+KJURUSAN.getSelectedItem()
                    +"',id_kelas='"+KKELAS.getSelectedItem()
                    +"',nope='"+NOPE.getText()
                    +"',status='"+STATUS.getSelectedItem()
                    +"'WHERE id_anggota='"+ID.getText()+"'";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Anggota Berhasil Diubah");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void Clear(){
        NIM.setText("");
        NAMA.setText("");
        JKP.setSelected(rootPaneCheckingEnabled);
        KTINGKAT.setSelectedItem(1);
        KJURUSAN.setSelectedItem("FAR");
        KKELAS.setSelectedItem(1);
        STATUS.setSelectedItem("AKTIF");
        NOPE.setText("");

    }
    private void Delete_Data(){
            try
        {
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            //untuk jenis kelamin
            String jk="";
            
            String sql="DELETE FROM `tbl_anggota` WHERE id_anggota='"+ID.getText()+"'";
            st.execute(sql);
            JOptionPane.showMessageDialog(null, "Data Anggota Berhasil Dihapus");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JK = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        NIM = new javax.swing.JTextField();
        NAMA = new javax.swing.JTextField();
        JKP = new javax.swing.JRadioButton();
        JKW = new javax.swing.JRadioButton();
        KTINGKAT = new javax.swing.JComboBox<>();
        KJURUSAN = new javax.swing.JComboBox<>();
        KKELAS = new javax.swing.JComboBox<>();
        STATUS = new javax.swing.JComboBox<>();
        NOPE = new javax.swing.JTextField();
        NTINGKAT = new javax.swing.JTextField();
        NJURUSAN = new javax.swing.JTextField();
        TambahBTN = new javax.swing.JButton();
        UbahBTN = new javax.swing.JButton();
        DeleteBTN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelAnggota = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        TabBuku = new javax.swing.JMenu();
        TabAnggota = new javax.swing.JMenu();
        TabTransaksi = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 20)); // NOI18N
        jLabel1.setText("KELOLA DATA ANGGOTA PERPUSTAKAAN");

        jButton1.setText("Keluar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("ID ANGGOTA");

        jLabel3.setText("NIM");

        jLabel4.setText("NAMA ANGGOTA");

        jLabel5.setText("JENIS KELAMIN");

        jLabel6.setText("TINGKAT");

        jLabel7.setText("JURUSAN");

        jLabel8.setText("KELAS");

        jLabel9.setText("NO HP");

        jLabel10.setText("STATUS");

        ID.setEnabled(false);

        NAMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NAMAActionPerformed(evt);
            }
        });

        JK.add(JKP);
        JKP.setSelected(true);
        JKP.setText("PRIA");
        JKP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JKPActionPerformed(evt);
            }
        });

        JK.add(JKW);
        JKW.setText("WANITA");

        KTINGKAT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KTINGKATMouseClicked(evt);
            }
        });
        KTINGKAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KTINGKATActionPerformed(evt);
            }
        });

        KJURUSAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KJURUSANActionPerformed(evt);
            }
        });

        KKELAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KKELASActionPerformed(evt);
            }
        });

        STATUS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AKTIF", "TIDAK AKTIF" }));

        NOPE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NOPEActionPerformed(evt);
            }
        });

        NTINGKAT.setEditable(false);
        NTINGKAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NTINGKATActionPerformed(evt);
            }
        });

        NJURUSAN.setEditable(false);
        NJURUSAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NJURUSANActionPerformed(evt);
            }
        });

        TambahBTN.setText("INPUT");
        TambahBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TambahBTNActionPerformed(evt);
            }
        });

        UbahBTN.setText("EDIT");
        UbahBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UbahBTNActionPerformed(evt);
            }
        });

        DeleteBTN.setText("DELETE");
        DeleteBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBTNActionPerformed(evt);
            }
        });

        TabelAnggota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabelAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelAnggotaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelAnggota);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jButton1))
                                .addGap(71, 71, 71)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(TambahBTN)
                                        .addGap(18, 18, 18)
                                        .addComponent(UbahBTN)
                                        .addGap(20, 20, 20)
                                        .addComponent(DeleteBTN))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NOPE, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(NIM, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(STATUS, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(KKELAS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(KJURUSAN, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(JKP, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(KTINGKAT, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(13, 13, 13)
                                                .addComponent(JKW))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(NTINGKAT, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                                    .addComponent(NJURUSAN)))))
                                    .addComponent(NAMA, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(453, 453, 453)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(NIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(NAMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(JKP)
                                .addComponent(JKW)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(KTINGKAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(KJURUSAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(NTINGKAT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(NJURUSAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(KKELAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NOPE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(STATUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(TambahBTN)
                            .addComponent(UbahBTN)
                            .addComponent(DeleteBTN)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );

        jMenu1.setText("Logout");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        TabBuku.setText("Buku");
        TabBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabBukuMouseClicked(evt);
            }
        });
        jMenuBar1.add(TabBuku);

        TabAnggota.setText("Anggota");
        TabAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabAnggotaMouseClicked(evt);
            }
        });
        jMenuBar1.add(TabAnggota);

        TabTransaksi.setText("Transaksi");
        TabTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabTransaksiMouseClicked(evt);
            }
        });
        jMenuBar1.add(TabTransaksi);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int keluar;
        keluar = JOptionPane.showOptionDialog(this,
                "Keluar dari Kelola Data Anggota?",
                "Exit",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,null,null);
        if(keluar==JOptionPane.YES_NO_OPTION)
        {
            new FUtamaKepala().show();
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void JKPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JKPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JKPActionPerformed

    private void DeleteBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBTNActionPerformed
        // TODO add your handling code here:
        Delete_Data();
        load_data();
        IDOtotmatis();
        Clear();
    }//GEN-LAST:event_DeleteBTNActionPerformed

    private void TambahBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TambahBTNActionPerformed
        // TODO add your handling code here:
        input_data();
        load_data();
        IDOtotmatis();
        Clear();
    }//GEN-LAST:event_TambahBTNActionPerformed

    private void NAMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NAMAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NAMAActionPerformed

    private void KTINGKATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KTINGKATActionPerformed
        // TODO add your handling code here:
        NamaTingkat();
    }//GEN-LAST:event_KTINGKATActionPerformed

    private void NJURUSANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NJURUSANActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NJURUSANActionPerformed

    private void NOPEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NOPEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NOPEActionPerformed

    private void KTINGKATMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KTINGKATMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_KTINGKATMouseClicked

    private void KJURUSANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KJURUSANActionPerformed
        // TODO add your handling code here:
        NamaJurusan();
    }//GEN-LAST:event_KJURUSANActionPerformed

    private void KKELASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KKELASActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_KKELASActionPerformed

    private void NTINGKATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NTINGKATActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NTINGKATActionPerformed

    private void TabelAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelAnggotaMouseClicked
        // TODO add your handling code here:
        int bar=TabelAnggota.getSelectedRow();
        String a=TabelAnggota.getValueAt(bar, 0).toString();
        String b=TabelAnggota.getValueAt(bar, 1).toString();
        String c=TabelAnggota.getValueAt(bar, 2).toString();
        String d=TabelAnggota.getValueAt(bar, 3).toString();
        String e=TabelAnggota.getValueAt(bar, 4).toString();
        String f=TabelAnggota.getValueAt(bar, 5).toString();
        String g=TabelAnggota.getValueAt(bar, 6).toString();
        String h=TabelAnggota.getValueAt(bar, 7).toString();
        String i=TabelAnggota.getValueAt(bar, 8).toString();
        
        ID.setText(a);
        NIM.setText(b);
        NAMA.setText(c);
        //Jensi Kelamin
        if("PRIA".equals(d)){
            JKP.setSelected(true);
        }else{
            JKW.setSelected(true);
        }
        KTINGKAT.setSelectedItem(e);
        KJURUSAN.setSelectedItem(f);
        KKELAS.setSelectedItem(g);
        NOPE.setText(h);
        //Keaktifan
        if("AKTIF".equals(i)){
            STATUS.setSelectedItem(i);
        }else{
            STATUS.setSelectedItem(i);
        }
        
        //Disable Tombol Tambah
        TambahBTN.setEnabled(false);
        UbahBTN.setEnabled(true);
        DeleteBTN.setEnabled(true);
    }//GEN-LAST:event_TabelAnggotaMouseClicked

    private void UbahBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UbahBTNActionPerformed
        // TODO add your handling code here:
        Update();
        load_data();
        Clear();
        TambahBTN.setEnabled(true);
        UbahBTN.setEnabled(false);
        DeleteBTN.setEnabled(false);
    }//GEN-LAST:event_UbahBTNActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
                int keluar;
        keluar = JOptionPane.showOptionDialog(this,
                "Keluar dari Kelola Data Anggota?",
                "Exit",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,null,null);
        if(keluar==JOptionPane.YES_NO_OPTION)
        {
            new FLogin().show();
            this.dispose();
        }
    }//GEN-LAST:event_jMenu1MouseClicked

    private void TabBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabBukuMouseClicked
        // TODO add your handling code here:
         new FBukuStaff().show();
         this.dispose();
    }//GEN-LAST:event_TabBukuMouseClicked

    private void TabTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabTransaksiMouseClicked
        // TODO add your handling code here:
         new FTransaksiStaff().show();
         this.dispose();
    }//GEN-LAST:event_TabTransaksiMouseClicked

    private void TabAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabAnggotaMouseClicked
        // TODO add your handling code here:
        new FDataAnggotaStaff().show();
        this.dispose();
    }//GEN-LAST:event_TabAnggotaMouseClicked

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
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FDataAnggota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteBTN;
    private javax.swing.JTextField ID;
    private javax.swing.ButtonGroup JK;
    private javax.swing.JRadioButton JKP;
    private javax.swing.JRadioButton JKW;
    private javax.swing.JComboBox<String> KJURUSAN;
    private javax.swing.JComboBox<String> KKELAS;
    private javax.swing.JComboBox<String> KTINGKAT;
    private javax.swing.JTextField NAMA;
    private javax.swing.JTextField NIM;
    private javax.swing.JTextField NJURUSAN;
    private javax.swing.JTextField NOPE;
    private javax.swing.JTextField NTINGKAT;
    private javax.swing.JComboBox<String> STATUS;
    private javax.swing.JMenu TabAnggota;
    private javax.swing.JMenu TabBuku;
    private javax.swing.JMenu TabTransaksi;
    private javax.swing.JTable TabelAnggota;
    private javax.swing.JButton TambahBTN;
    private javax.swing.JButton UbahBTN;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
