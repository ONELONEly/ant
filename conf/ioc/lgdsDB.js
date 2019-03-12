/**
 *
 */

var ant = {

		 config : {
		        type : "org.nutz.ioc.impl.PropertiesProxy",
		        fields : {
		            paths : ["jdbc.properties"]
		        }
		 },

        // druid连接池
        dataSourceFX : {
            type : "com.alibaba.druid.pool.DruidDataSource",
            events : {
                depose : "close"
            },
            fields : {
                url : {java : "$config.get('ant.url')"},
                username : {java : "$config.get('ant.user')"},
                password : {java : "$config.get('ant.password')"},
                maxWait : 10000, // 若不配置此项,如果数据库未启动,druid会一直等可用连接,卡住启动过程
                defaultAutoCommit :false, // 提高fastInsert的性能,
                initialSize:5,
                minIdle:5,
                maxActive:10,
                timeBetweenEvictionRunsMillis:60000,
                minEvictableIdleTimeMillis:30000,
                testOnBorrow:false,
                testOnReturn:false,
                testWhileIdle:true,
                keepAlive:true,
                removeAbandoned:true,
                removeAbandonedTimeout:80,
                logAbandoned:true,
                poolPreparedStatements:true,
                maxPoolPreparedStatementPerConnectionSize:20,
                validationQuery:'SELECT 1 FROM DUAL'

            }
        },


        daoFX : {
            type : "org.nutz.dao.impl.NutDao",
            args : [{refer:"dataSourceFX"}]
        },

        dataSource1:{
            type:"org.nutz.dao.impl.SimpleDataSource",
            fields : {
                driverClassName:"com.microsoft.sqlserver.jdbc.SQLServerDriver",
                url :"jdbc:sqlserver://10.2.4.175:1433 ;databaseName=NewDS",
                username : "ERPTEST",
                password : "ERP_TEST_2018",

            },
            events:{
                depose:"close"

            }
        },
        dao1:{
            type:"org.nutz.dao.impl.NutDao",
            args:[{refer:"dataSource1"}]

        }
};
