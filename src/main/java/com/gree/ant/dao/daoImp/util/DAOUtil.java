package com.gree.ant.dao.daoImp.util;

import com.gree.ant.util.HTMLUtil;
import com.gree.ant.vo.Tbuss001VO;
import com.gree.ant.vo.Tbuss009VO;
import com.gree.ant.vo.util.ResultVO;
import com.gree.ant.vo.util.TaskUtilVO;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOUtil {

    private static Logger logger = LoggerFactory.getLogger(DAOUtil.class);

    /**
     * Insert check boolean.
     *
     * @param dao  DAO
     * @param sqls Sql语句
     * @param cnd  过滤器
     * @return 有-true，无-false
     * @description 查询是否有当前信息
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:10 09:10:43.
     */
    public static  Boolean insertCheck(Dao dao, String sqls, Condition cnd) {
        Sql sql = Sqls.create(sqls);
        sql.setCondition(cnd);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                return rs.next();
            }
        });
        dao.execute(sql);
        logger.info(sql.getSourceSql());
        return sql.getBoolean();
    }

    /**
     * Get string list.
     *
     * @param sql Sql
     * @param dao Dao
     * @return Tbuss001集合
     * @description 获得Tbuss001的返回实体结果集
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:16 05:10:31.
     */
    public static List<Tbuss001VO> getT1(Sql sql, Dao dao){
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Tbuss001VO> tbuss001VOList = new ArrayList<>();
                while(rs.next()){
                    tbuss001VOList.add(new Tbuss001VO(rs.getString("ptno"),rs.getString("dsca")
                    ,rs.getString("pdat"),rs.getString("usid"),rs.getString("unam")
                    ,rs.getDate("udat"),rs.getString("grop"),rs.getString("gropnam")
                    ,rs.getInt("sta1"),rs.getInt("sta2")));
                }
                return tbuss001VOList;
            }
        });
        dao.execute(sql);
        return sql.getList(Tbuss001VO.class);
    }

    /**
     * Get t 9 list.
     *
     * @param sql the sql
     * @param dao the dao
     * @return Tbuss009集合
     * @description 获得Tbuss009的返回实体结果集
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :12:05 03:12:34.
     */
    public static List<Tbuss009VO> getT9(Sql sql, Dao dao){
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Tbuss009VO> tbuss009VOList = new ArrayList<>();
                while(rs.next()){
                    tbuss009VOList.add(new Tbuss009VO(rs.getLong("doid"),
                            rs.getDate("cdat"),rs.getString("usid"),rs.getString("unam"),rs.getString("csid"),
                            rs.getString("cnam"),rs.getString("ctyp"),rs.getString("ctypnam"),rs.getInt("stat"),
                            rs.getString("statnam"),rs.getInt("sta2"),rs.getString("sta2nam"),rs.getString("tilt")));
                }
                return tbuss009VOList;
            }
        });
        dao.execute(sql);
        return sql.getList(Tbuss009VO.class);
    }

    public static List<Tbuss009VO> getT9Search(Sql sql, Dao dao){
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<Tbuss009VO> tbuss009VOList = new ArrayList<>();
                while(rs.next()){
                    tbuss009VOList.add(new Tbuss009VO(rs.getLong("doid"),
                            rs.getDate("cdat"),rs.getString("unam"),rs.getString("tilt")));
                }
                return tbuss009VOList;
            }
        });
        dao.execute(sql);
        return sql.getList(Tbuss009VO.class);
    }

    public static List<TaskUtilVO> getTU(Sql sql, Dao dao){
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<TaskUtilVO> taskUtilVOList = new ArrayList<>();
                while(rs.next()){
                    taskUtilVOList.add(new TaskUtilVO(rs.getString("csid"),rs.getString("cnam"),rs.getString("pdat"),
                            rs.getString("titl"),rs.getString("taid"), HTMLUtil.delHTMLTag(rs.getString("note"))));
                }
                return taskUtilVOList;
            }
        });
        dao.execute(sql);
        return sql.getList(TaskUtilVO.class);
    }


    /**
     * Get ti count integer.
     *
     * @param sql the sql
     * @param dao the dao
     * @return count数量
     * @description 获得Tbuss001的返回count结果集
     * @author create by jinyuk@foxmail.com.
     * @version V1.0
     * @createTime 2017 :10:17 11:10:29.
     */
    public static Integer getTiCount(Sql sql,Dao dao){
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                int res = 0;
                if(rs.next()) {
                    res =  rs.getInt(1);
                }
                return res;
            }
        });
        dao.execute(sql);
        return sql.getInt();
    }

    public static String getTxtResult(Sql sql,Dao dao){
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                String res = "";
                if(rs.next()) {
                    res =  rs.getString(1);
                }
                return res;
            }
        });
        dao.execute(sql);
        return sql.getString();
    }

    public static List<ResultVO> getResultVO(String sqlStr,Dao dao){
        Sql sql = Sqls.create(sqlStr);
        dao.execute(setSqlCallback(sql));
        return sql.getList(ResultVO.class);
    }

    public static List<ResultVO> getResultVO(Sql sql,Dao dao){
        dao.execute(setSqlCallback(sql));
        return sql.getList(ResultVO.class);
    }

    private static Sql setSqlCallback(Sql sql){
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
                List<ResultVO> resultVOList = new ArrayList<>();
                while(rs.next()){
                    resultVOList.add(new ResultVO(rs.getString(1),rs.getString(2)));
                }
                return resultVOList;
            }
        });
        return sql;
    }


}
