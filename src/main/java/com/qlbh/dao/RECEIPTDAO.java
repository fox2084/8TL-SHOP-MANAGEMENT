/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qlbh.dao;

import com.qlbh.entity.RECEIPT;
import com.qlbh.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HT
 */
public class RECEIPTDAO extends QLBHDAO<RECEIPT, String>{
    String INSERT_SQL = "INSERT INTO RECEIPT(MA_DH, MA_VanChuyen, MA_SP, SOLUONG, GHI_CHU) VALUES (?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE RECEIPT SET MA_DH=?, MA_VanChuyen=?, MA_SP=?, SOLUONG=?, GHICHU=? WHERE MA_DH=?";
    String DELETE_SQL = "DELETE FROM RECEIPT WHERE MA_DH=?";
    String SELECT_ALL_SQL = "SELECT * FROM RECEIPT";
    String SELECT_BY_ID_SQL = "SELECT * FROM RECEIPT WHERE MA_DH=?";

    @Override
    public void insert(RECEIPT entity) {
        try {
            XJdbc.update(INSERT_SQL, entity.getMADH(),
                    entity.getMAVanChuyen(),
                    entity.getMASP(),
                    entity.getSOLUONG(),
                    entity.getGHICHU());

        } catch (SQLException ex) {
            Logger.getLogger(RECEIPTDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(RECEIPT entity) {
        try {
            XJdbc.update(UPDATE_SQL, entity.getMADH(),
                    entity.getMAVanChuyen(),
                    entity.getMASP(),
                    entity.getSOLUONG(),
                    entity.getGHICHU());
        } catch (SQLException ex) {
            Logger.getLogger(RECEIPTDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        try {
            XJdbc.update(DELETE_SQL, id);
        } catch (SQLException ex) {
            Logger.getLogger(DELIVERYDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public RECEIPT selectebyID(String id) {
        List<RECEIPT> list = this.selectbySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<RECEIPT> selectAll() {
        return this.selectbySql(SELECT_ALL_SQL);
    }

    @Override
    public List<RECEIPT> selectbySql(String sql, Object... args) {
        List<RECEIPT> list = new ArrayList<RECEIPT>();
        ResultSet rs;
        try {
            rs = XJdbc.query(sql, args);

            while (rs.next()) {
                RECEIPT entity = new RECEIPT();
                entity.setMADH(rs.getString("MA_DH"));
                entity.setMAVanChuyen(rs.getString("MA_VanChuyen"));
                entity.setMASP(rs.getString("MA_SP"));
                entity.setSOLUONG(rs.getInt("SOLUONG"));
                entity.setGHICHU(rs.getString("GHI_CHU"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}