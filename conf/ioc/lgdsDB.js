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

        /* BoneCp*/
        // dataSourceFX : {
        //     type : "com.jolbox.bonecp.BoneCPDataSource",
        //     events : {
        //
        //         depose : 'close'
        //     },
        //     fields : {
        //     	driverClass : {java :"$config.get('ant.driver')"},
        //     	jdbcUrl : {java :"$config.get('ant.url')"},
        //         username : {java :"$config.get('ant.user')"},
        //         password : {java :"$config.get('ant.password')"},
        //         idleConnectionTestPeriodInMinutes : 240,
        //         idleMaxAgeInMinutes :60 ,
        //         maxConnectionsPerPartition : 30,
        //         minConnectionsPerPartition : 10,
        //         partitionCount:3,
        //         acquireIncrement :5,
        //         statementsCacheSize : 100
        //     }
        // },

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
                maxWait : 15000, // 若不配置此项,如果数据库未启动,druid会一直等可用连接,卡住启动过程
                defaultAutoCommit :false // 提高fastInsert的性能
            }
        },
        
        
        daoFX : {
            type : "org.nutz.dao.impl.NutDao",
            args : [{refer:"dataSourceFX"}]
        }
};
